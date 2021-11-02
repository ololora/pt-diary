package co.scrobbler.ptdiary.business.client;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.scrobbler.ptdiary.business.SharedViewModel;
import co.scrobbler.ptdiary.db.AppDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ClientEditViewModel extends ViewModel {
    private String nameEdit = "";
    private String notesEdit = "";

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final AppDatabase db;
    private final SharedViewModel sharedViewModel;

    @Inject
    public ClientEditViewModel(AppDatabase db, SharedViewModel sharedViewModel) {
        this.db = db;
        this.sharedViewModel = sharedViewModel;
    }

    public void setNameEdit(String nameEdit) {
        this.nameEdit = nameEdit;
    }

    public void setNotesEdit(String notesEdit) {
        this.notesEdit = notesEdit;
    }

    @NonNull
    public Observable<Client> getSelectedClient() {
        return sharedViewModel.selectedClientId
                .filter(id -> id > 0)
                .flatMapMaybe(id -> db.clientDao().getById(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void createOrUpdateClient() {
        compositeDisposable.add(
                sharedViewModel.selectedClientId
                        .take(1)
                        .flatMapSingle(id -> db.clientDao()
                                .getById(id)
                                .defaultIfEmpty(new Client())
                                .map(this::updateClientFields)
                                .flatMap(client -> db.clientDao().insert(client)))
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    private Client updateClientFields(Client client) {
        client.name = nameEdit;
        client.notes = notesEdit;
        return client;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
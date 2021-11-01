package co.scrobbler.ptdiary.business.client;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.scrobbler.ptdiary.db.AppDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ClientEditViewModel extends ViewModel {
    private String nameEdit = "";
    private String notesEdit = "";

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final AppDatabase db;

    @Inject
    public ClientEditViewModel(AppDatabase db) {
        this.db = db;
    }

    public void setNameEdit(String nameEdit) {
        this.nameEdit = nameEdit;
    }

    public void setNotesEdit(String notesEdit) {
        this.notesEdit = notesEdit;
    }

    public void createOrUpdateClient(long selectedClientId) {
        Client client = new Client();
        client.name = nameEdit;
        client.notes = notesEdit;
        Disposable disposable;
        if (selectedClientId == 0) {
            disposable = db.clientDao()
                    .insert(client)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(id -> client.id = id);
        } else {
            client.id = selectedClientId;
            disposable = db.clientDao()
                    .update(client)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }
        disposables.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
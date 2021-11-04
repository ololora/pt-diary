package co.scrobbler.ptdiary.logic.client;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.scrobbler.ptdiary.db.AppDatabase;
import co.scrobbler.ptdiary.db.entity.Client;
import co.scrobbler.ptdiary.logic.SharedViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ClientProfileInfoViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final AppDatabase db;
    private final SharedViewModel sharedViewModel;

    @Inject
    public ClientProfileInfoViewModel(AppDatabase db, SharedViewModel sharedViewModel) {
        this.db = db;
        this.sharedViewModel = sharedViewModel;
    }

    @NonNull
    public Observable<Client> getSelectedClient() {
        return sharedViewModel.selectedClientId
                .filter(id -> id > 0)
                .flatMapMaybe(id -> db.clientDao().getById(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
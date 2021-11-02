package co.scrobbler.ptdiary.business.client;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.scrobbler.ptdiary.db.AppDatabase;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Singleton
public class ClientViewModel extends ViewModel {
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final AppDatabase db;

    @Inject
    public ClientViewModel(AppDatabase db) {
        this.db = db;
    }

    public Flowable<List<Client>> getAllClients() {
        return db.clientDao().getAll();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
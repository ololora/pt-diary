package co.scrobbler.ptdiary.business.client;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.scrobbler.ptdiary.db.AppDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class ClientViewModel extends ViewModel {
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final AppDatabase db;

    @Inject
    public ClientViewModel(AppDatabase db) {
        this.db = db;
    }

    public Flowable<List<Client>> getAllClients() {
        return db.clientDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
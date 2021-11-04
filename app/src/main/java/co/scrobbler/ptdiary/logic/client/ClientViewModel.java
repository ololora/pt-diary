package co.scrobbler.ptdiary.logic.client;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.scrobbler.ptdiary.db.AppDatabase;
import co.scrobbler.ptdiary.db.entity.Client;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class ClientViewModel extends ViewModel {
    public final PublishProcessor<String> query = PublishProcessor.create();

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final AppDatabase db;

    @Inject
    public ClientViewModel(AppDatabase db) {
        this.db = db;
    }

    public Flowable<List<Client>> getClientList() {
        return query.defaultIfEmpty("")
                .flatMap(query -> query.isEmpty() ?
                    db.clientDao().getAll() :
                    db.clientDao().getFiltered(query))
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
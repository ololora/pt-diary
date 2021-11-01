package co.scrobbler.ptdiary.business;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.scrobbler.ptdiary.business.client.Client;
import co.scrobbler.ptdiary.db.AppDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class SharedViewModel extends ViewModel {
    private long selectedClientId;

    private final AppDatabase db;

    @Inject
    public SharedViewModel(AppDatabase db) {
        this.db = db;
    }

    public long getSelectedClientId() {
        return selectedClientId;
    }

    public void setSelectedClientId(long selectedClientId) {
        this.selectedClientId = selectedClientId;
    }

    public Maybe<Client> getSelectedClient(long id) {
        return db.clientDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

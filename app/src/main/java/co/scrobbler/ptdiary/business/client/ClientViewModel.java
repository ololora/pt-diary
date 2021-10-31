package co.scrobbler.ptdiary.business.client;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.scrobbler.ptdiary.db.AppDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ClientViewModel extends ViewModel {
    /*private PublishSubject<Client> selectedClient = PublishSubject.create();
    private PublishSubject<String> nameEdit = PublishSubject.create();
    private PublishSubject<String> notesEdit = PublishSubject.create();*/
    private Client selectedClient = new Client();
    private String nameEdit = "";
    private String notesEdit = "";

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final AppDatabase db;

    @Inject
    public ClientViewModel(AppDatabase db) {
        this.db = db;
    }

    /*public PublishSubject<Client> getSelectedClient() {
        return selectedClient;
    }

    public PublishSubject<String> getNameEdit() {
        return nameEdit;
    }

    public PublishSubject<String> getNotesEdit() {
        return notesEdit;
    }*/

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public void setNameEdit(String nameEdit) {
        this.nameEdit = nameEdit;
    }

    public void setNotesEdit(String notesEdit) {
        this.notesEdit = notesEdit;
    }

    public void createOrUpdateClient() {
        selectedClient.name = nameEdit;
        selectedClient.notes = notesEdit;
        Disposable disposable;
        if (selectedClient.id == 0) {
            disposable = db.clientDao()
                    .insert(selectedClient)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(id -> selectedClient.id = id);
        } else {
            disposable = db.clientDao()
                    .update(selectedClient)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }
        disposables.add(disposable);
        /*selectedClient.lastElement().subscribe(client -> {
            client.name = nameEdit.blockingLast();
            client.notes = notesEdit.blockingLast();
            if (client.id == 0) {
                Disposable disposable = db.clientDao()
                        .insert(client)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(id -> client.id = id);
                disposables.add(disposable);
            } else {
                Disposable disposable = db.clientDao()
                        .update(client)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
                disposables.add(disposable);
            }
            selectedClient.onNext(client);
        });*/
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
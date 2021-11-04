package co.scrobbler.ptdiary.logic.schedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.scrobbler.ptdiary.db.AppDatabase;
import co.scrobbler.ptdiary.db.entity.Client;

public class ScheduleViewModel extends ViewModel {
    private final AppDatabase db;

    private final MutableLiveData<Client> selectedClient = new MutableLiveData<>();

    @Inject
    public ScheduleViewModel(AppDatabase db) {
        this.db = db;
    }

    public void selectClient(Client client) {
        selectedClient.setValue(client);
    }

    public LiveData<Client> getSelectedClient() {
        return selectedClient;
    }
}
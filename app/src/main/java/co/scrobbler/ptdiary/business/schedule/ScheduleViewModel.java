package co.scrobbler.ptdiary.business.schedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.scrobbler.ptdiary.business.client.Client;
import co.scrobbler.ptdiary.db.AppDatabase;

public class ScheduleViewModel extends ViewModel {
    private final AppDatabase db;

    private final MutableLiveData<Client> selectedClient = new MutableLiveData<Client>();

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
package co.scrobbler.ptdiary.business.client;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.scrobbler.ptdiary.db.AppDatabase;

public class ClientViewModel extends ViewModel {
    private final AppDatabase db;

    @Inject
    public ClientViewModel(AppDatabase db) {
        this.db = db;
    }
}
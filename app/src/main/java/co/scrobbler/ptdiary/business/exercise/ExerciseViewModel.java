package co.scrobbler.ptdiary.business.exercise;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.scrobbler.ptdiary.db.AppDatabase;

public class ExerciseViewModel extends ViewModel {
    private final AppDatabase db;

    @Inject
    public ExerciseViewModel(AppDatabase db) {
        this.db = db;
    }
}
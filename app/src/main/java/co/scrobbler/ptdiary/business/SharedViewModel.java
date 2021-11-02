package co.scrobbler.ptdiary.business;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

@Singleton
public class SharedViewModel extends ViewModel {
    public final BehaviorSubject<Long> selectedClientId = BehaviorSubject.createDefault(0L);

    @Inject
    public SharedViewModel() {
    }
}

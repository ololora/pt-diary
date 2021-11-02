package co.scrobbler.ptdiary;

import javax.inject.Singleton;

import co.scrobbler.ptdiary.business.client.ClientEditFragment;
import co.scrobbler.ptdiary.business.client.ClientListFragment;
import co.scrobbler.ptdiary.business.exercise.ExerciseFragment;
import co.scrobbler.ptdiary.business.schedule.ScheduleFragment;
import co.scrobbler.ptdiary.business.client.ClientProfileFragment;
import co.scrobbler.ptdiary.business.client.ClientProfileInfoFragment;
import co.scrobbler.ptdiary.db.DbModule;
import co.scrobbler.ptdiary.ui.MainActivity;
import dagger.Component;

@Singleton
@Component(modules={AppModule.class, DbModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(ScheduleFragment activity);
    void inject(ClientListFragment activity);
    void inject(ClientEditFragment activity);
    void inject(ClientProfileFragment activity);
    void inject(ClientProfileInfoFragment activity);
    void inject(ExerciseFragment activity);
}

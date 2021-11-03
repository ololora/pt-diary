package co.scrobbler.ptdiary;

import javax.inject.Singleton;

import co.scrobbler.ptdiary.business.client.ClientEditFragment;
import co.scrobbler.ptdiary.business.client.ClientListFragment;
import co.scrobbler.ptdiary.business.client.ClientProfileFragment;
import co.scrobbler.ptdiary.business.client.ClientProfileInfoFragment;
import co.scrobbler.ptdiary.business.exercise.ExerciseEditFragment;
import co.scrobbler.ptdiary.business.exercise.ExerciseListFragment;
import co.scrobbler.ptdiary.business.schedule.ScheduleFragment;
import co.scrobbler.ptdiary.db.DbModule;
import co.scrobbler.ptdiary.ui.MainActivity;
import dagger.Component;

@Singleton
@Component(modules={AppModule.class, DbModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(ScheduleFragment fragment);

    void inject(ClientListFragment fragment);
    void inject(ClientEditFragment fragment);
    void inject(ClientProfileFragment fragment);
    void inject(ClientProfileInfoFragment fragment);

    void inject(ExerciseListFragment fragment);
    void inject(ExerciseEditFragment fragment);
}

package co.scrobbler.ptdiary;

import javax.inject.Singleton;

import co.scrobbler.ptdiary.db.DbModule;
import co.scrobbler.ptdiary.ui.MainActivity;
import co.scrobbler.ptdiary.business.client.ClientFragment;
import co.scrobbler.ptdiary.business.exercise.ExerciseFragment;
import co.scrobbler.ptdiary.business.schedule.ScheduleFragment;
import dagger.Component;

@Singleton
@Component(modules={AppModule.class, DbModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(ScheduleFragment activity);
    void inject(ClientFragment activity);
    void inject(ExerciseFragment activity);
}

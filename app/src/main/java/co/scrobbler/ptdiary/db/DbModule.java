package co.scrobbler.ptdiary.db;

import androidx.room.Room;

import javax.inject.Singleton;

import co.scrobbler.ptdiary.MyApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    private static final String DB_NAME = "database";

    @Provides
    @Singleton
    public AppDatabase providesDb(MyApplication application) {
        return Room
                .databaseBuilder(application.getApplicationContext(), AppDatabase.class, DB_NAME)
                .build();
    }
}

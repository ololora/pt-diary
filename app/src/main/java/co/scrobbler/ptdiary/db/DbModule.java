package co.scrobbler.ptdiary.db;

import android.content.res.Configuration;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import co.scrobbler.ptdiary.PtDiaryApp;
import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    private static final String DB_NAME = "database";

    @Provides
    @Singleton
    public AppDatabase providesDb(PtDiaryApp application) {
        return Room
                .databaseBuilder(application.getApplicationContext(), AppDatabase.class, DB_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        db.execSQL("INSERT INTO applocale (name) " +
                                   "VALUES('user'), ('en'), ('ru')");
                    }
                })
                .build();
    }

    @Provides
    @Named("LocaleTag")
    public Locale providesLocaleTag(PtDiaryApp application) {
        Configuration conf = application.getApplicationContext().getResources().getConfiguration();
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ?
                conf.getLocales().get(0) :
                conf.locale;
    }
}

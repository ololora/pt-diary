package co.scrobbler.ptdiary.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import co.scrobbler.ptdiary.db.dao.AppLocaleDao;
import co.scrobbler.ptdiary.db.dao.ClientDao;
import co.scrobbler.ptdiary.db.dao.ExerciseDao;
import co.scrobbler.ptdiary.db.entity.AppLocale;
import co.scrobbler.ptdiary.db.entity.Client;
import co.scrobbler.ptdiary.db.entity.Exercise;

@Database(
        version = 1,
        entities = {
                AppLocale.class,
                Client.class,
                Exercise.class
        }
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppLocaleDao appLocaleDao();
    public abstract ClientDao clientDao();
    public abstract ExerciseDao exerciseDao();
}

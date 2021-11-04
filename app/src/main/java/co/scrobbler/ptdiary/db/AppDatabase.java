package co.scrobbler.ptdiary.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import co.scrobbler.ptdiary.db.dao.ClientDao;
import co.scrobbler.ptdiary.db.entity.Client;

@Database(entities = {Client.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClientDao clientDao();
}

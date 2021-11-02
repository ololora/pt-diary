package co.scrobbler.ptdiary.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import co.scrobbler.ptdiary.business.client.Client;
import co.scrobbler.ptdiary.business.client.ClientDao;

@Database(entities = {Client.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClientDao clientDao();
}

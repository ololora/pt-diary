package co.scrobbler.ptdiary.business.client;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ClientDao {
    @Query("SELECT * FROM client")
    LiveData<Client> getAll();

    @Query("SELECT * FROM client WHERE id = :id")
    Client getById(long id);

    @Insert
    void insert(Client client);

    @Update
    void update(Client client);

    @Delete
    void delete(Client client);
}

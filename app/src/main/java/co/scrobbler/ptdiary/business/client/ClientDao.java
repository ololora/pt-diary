package co.scrobbler.ptdiary.business.client;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ClientDao {
    @Query("SELECT * FROM client")
    Flowable<List<Client>> getAll();

    @Query("SELECT * FROM client WHERE id = :id")
    Maybe<Client> getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insert(Client client);

    @Update
    Completable update(Client client);

    @Delete
    Completable delete(Client client);
}

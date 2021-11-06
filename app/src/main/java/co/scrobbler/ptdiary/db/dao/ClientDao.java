package co.scrobbler.ptdiary.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import co.scrobbler.ptdiary.db.entity.Client;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

@Dao
public abstract class ClientDao extends AbstractBaseEntityDao<Client>{
    @Query("SELECT * FROM client")
    public abstract Flowable<List<Client>> getAll();

    @Query("SELECT * FROM client WHERE lower(name) LIKE lower('%' || :query || '%')")
    public abstract Flowable<List<Client>> getFiltered(String query);

    @Query("SELECT * FROM client WHERE id = :id")
    public abstract Maybe<Client> getById(long id);
}

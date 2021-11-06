package co.scrobbler.ptdiary.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import co.scrobbler.ptdiary.db.entity.BaseEntity;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public abstract class AbstractBaseEntityDao<T extends BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<Long> actualInsert(T t);

    public Single<Long> insert(T t) {
        setCreatedAndUpdated(t);
        return actualInsert(t);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> actualInsertAll(List<T> ts);

    public List<Long> insertAll(List<T> ts) {
        if (ts != null) {
            for (T t : ts) {
                setCreatedAndUpdated(t);
            }
        }
        return actualInsertAll(ts);
    }

    private void setCreatedAndUpdated(T t) {
        t.createdAt = t.createdAt != null ? t.createdAt : new Date();
        t.updatedAt = new Date();
    }

    @Update
    public abstract Completable actualUpdate(T t);

    public Completable update(T t) {
        t.updatedAt = new Date();
        return actualUpdate(t);
    }

    @Update
    public abstract Completable actualUpdateAll(List<T> ts);

    public Completable updateAll(List<T> ts) {
        if (ts != null) {
            for (T t : ts) {
                t.updatedAt = new Date();
            }
        }
        return actualUpdateAll(ts);
    }

    @Delete
    public abstract Completable delete(T t);

    @Delete
    public abstract Completable deleteAll(List<T> ts);
}

package co.scrobbler.ptdiary.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import co.scrobbler.ptdiary.db.entity.AppLocale;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public abstract class AppLocaleDao extends AbstractBaseEntityDao<AppLocale>{
    @Query("SELECT * FROM applocale")
    public abstract Flowable<List<AppLocale>> getAll();
}

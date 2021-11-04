package co.scrobbler.ptdiary.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    @Override
    public String toString() {
        return name;
    }
}

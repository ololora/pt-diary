package co.scrobbler.ptdiary.business.exercise;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
}

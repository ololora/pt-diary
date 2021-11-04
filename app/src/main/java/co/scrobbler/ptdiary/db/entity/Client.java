package co.scrobbler.ptdiary.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Client {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public String notes;
}

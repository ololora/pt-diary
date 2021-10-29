package co.scrobbler.ptdiary.business.client;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Client {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public String comment;
}

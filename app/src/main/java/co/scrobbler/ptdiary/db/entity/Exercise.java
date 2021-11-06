package co.scrobbler.ptdiary.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class Exercise extends BaseEntity {
    @Ignore
    public String name;

    @Ignore
    public String description;

    public Exercise() {
    }

    public Exercise(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

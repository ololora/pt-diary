package co.scrobbler.ptdiary.db.entity;

import androidx.room.Entity;

@Entity
public class Client extends BaseEntity {
    public String name;

    public String notes;
}

package co.scrobbler.ptdiary.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.sql.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Client.class, parentColumns = "id", childColumns = "client_id", onDelete = CASCADE)
})
public class Workout extends BaseEntity {
    public Date date;

    @ColumnInfo(name = "client_id")
    public long clientId;

    public String comment;
}

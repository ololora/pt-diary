package co.scrobbler.ptdiary.business.workout;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

import co.scrobbler.ptdiary.business.client.Client;

@Entity(foreignKeys = {
        @ForeignKey(entity = Client.class, parentColumns = "id", childColumns = "client_id", onDelete = CASCADE)
})
public class Workout {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public Date date;

    @ColumnInfo(name = "client_id")
    public long clientId;

    public String comment;
}

package co.scrobbler.ptdiary.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Workout.class, parentColumns = "id", childColumns = "workout_id", onDelete = CASCADE)
})
public class WorkoutRow {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "workout_id")
    public long workoutId;
}

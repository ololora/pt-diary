package co.scrobbler.ptdiary.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = WorkoutRow.class, parentColumns = "id", childColumns = "workout_row_id", onDelete = CASCADE),
        @ForeignKey(entity = Exercise.class, parentColumns = "id", childColumns = "exercise_id", onDelete = CASCADE)
})
public class WorkoutExercise extends BaseEntity {
    @ColumnInfo(name = "workout_row_id")
    public long workoutRowId;

    @ColumnInfo(name = "exercise_id")
    public long exerciseId;

    public int sets;

    public String comment;
}

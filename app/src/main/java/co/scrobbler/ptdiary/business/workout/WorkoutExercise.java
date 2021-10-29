package co.scrobbler.ptdiary.business.workout;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import co.scrobbler.ptdiary.business.exercise.Exercise;

@Entity(foreignKeys = {
        @ForeignKey(entity = WorkoutRow.class, parentColumns = "id", childColumns = "workout_row_id", onDelete = CASCADE),
        @ForeignKey(entity = Exercise.class, parentColumns = "id", childColumns = "exercise_id", onDelete = CASCADE)
})
public class WorkoutExercise {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "workout_row_id")
    public long workoutRowId;

    @ColumnInfo(name = "exercise_id")
    public long exerciseId;

    public int sets;

    public String comment;
}

package co.scrobbler.ptdiary.business.workout;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = WorkoutExercise.class, parentColumns = "id", childColumns = "workout_exercise_id", onDelete = CASCADE)
})
public class WorkoutSet {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "workout_exercise_id")
    public long workoutExerciseId;

    public long reps;

    public WorkoutSetUnits units;

    public double weight;
}

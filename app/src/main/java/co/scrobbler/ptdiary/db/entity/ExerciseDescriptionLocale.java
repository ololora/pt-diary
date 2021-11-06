package co.scrobbler.ptdiary.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = AppLocale.class, parentColumns = "id", childColumns = "locale_id", onDelete = CASCADE),
        @ForeignKey(entity = Exercise.class, parentColumns = "id", childColumns = "exercise_id", onDelete = CASCADE)
})
public class ExerciseDescriptionLocale extends BaseEntity {
    @ColumnInfo(name = "locale_id")
    public long localeId;

    @ColumnInfo(name = "exercise_id")
    public long exerciseId;

    public String description;
}

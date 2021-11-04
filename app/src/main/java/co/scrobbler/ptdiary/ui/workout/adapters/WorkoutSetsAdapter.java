package co.scrobbler.ptdiary.ui.workout.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter;

import java.util.ArrayList;
import java.util.List;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.db.entity.WorkoutSet;
import io.reactivex.rxjava3.subjects.PublishSubject;
import mobi.upod.timedurationpicker.TimeDurationPickerDialog;
import mobi.upod.timedurationpicker.TimeDurationUtil;
import rx.subscriptions.CompositeSubscription;

public class WorkoutSetsAdapter extends DragDropSwipeAdapter<WorkoutSet, WorkoutSetsAdapter.ViewHolder> {
    private List<WorkoutSet> workoutSets = new ArrayList<>();
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private final PublishSubject<Long> selectedItemId = PublishSubject.create();

    @SuppressLint("NotifyDataSetChanged")
    public void setWorkoutSets(List<WorkoutSet> workoutSets) {
        this.setDataSet(workoutSets);
        notifyDataSetChanged();
    }

    public PublishSubject<Long> getSelectedItemId() {
        return selectedItemId;
    }

    @NonNull
    @Override
    protected ViewHolder getViewHolder(@NonNull View view) {
        return new WorkoutSetsAdapter.ViewHolder(view);
    }

    @Nullable
    @Override
    protected View getViewToTouchToStartDraggingItem(WorkoutSet workoutSet,
                                                     @NonNull ViewHolder viewHolder, int i) {
        return null;
    }

    @Override
    protected void onBindViewHolder(WorkoutSet workoutSet, @NonNull ViewHolder holder, int position) {
        holder.getRepsEdit().setText(String.valueOf(workoutSet.reps));
        holder.getWeightEdit().setText(String.valueOf(workoutSet.weight));
        holder.setRepDuration(workoutSet.repDuration);
        holder.setRestDuration(workoutSet.restDuration);

        holder.getRepDurationEdit().setOnClickListener(v -> {
            TimeDurationPickerDialog dialog = new TimeDurationPickerDialog(v.getContext(),
                    (view, duration) -> {
                        holder.setRepDuration(duration);
                        holder.getRepDurationEdit().setText(WorkoutSetsAdapter.ViewHolder.getDurationString(duration));
                    }, holder.getRepDuration());
            dialog.show();
        });

        holder.getRestDurationEdit().setOnClickListener(v -> {
            TimeDurationPickerDialog dialog = new TimeDurationPickerDialog(v.getContext(),
                    (view, duration) -> {
                        holder.setRestDuration(duration);
                        holder.getRestDurationEdit().setText(WorkoutSetsAdapter.ViewHolder.getDurationString(duration));
                    }, holder.getRestDuration());
            dialog.show();
        });
    }

    public static class ViewHolder extends DragDropSwipeAdapter.ViewHolder {
        private final EditText repsEdit;
        private final EditText weightEdit;
        private final EditText restDurationEdit;
        private final EditText repDurationEdit;

        public long getRepDuration() {
            return repDuration;
        }

        public void setRepDuration(long repDuration) {
            this.repDuration = repDuration;
            this.repDurationEdit.setText(getDurationString(repDuration));
        }

        public long getRestDuration() {
            return restDuration;
        }

        public void setRestDuration(long restDuration) {
            this.restDuration = restDuration;
            this.restDurationEdit.setText(getDurationString(restDuration));
        }

        private long repDuration;
        private long restDuration;

        public ViewHolder(View view) {
            super(view);
            repsEdit = view.findViewById(R.id.repsEditView);
            weightEdit = view.findViewById(R.id.weightEditView);
            repDurationEdit = view.findViewById(R.id.durationEditView);
            restDurationEdit = view.findViewById(R.id.restTimeEditView);
        }

        public EditText getRepsEdit() {
            return repsEdit;
        }

        public EditText getWeightEdit() {
            return weightEdit;
        }

        public EditText getRestDurationEdit() {
            return restDurationEdit;
        }

        public EditText getRepDurationEdit() {
            return repDurationEdit;
        }

        public static String getDurationString(long duration) {
            if (duration == 0) {
                return "--:--";
            }

            return TimeDurationUtil.formatMinutesSeconds(duration);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        compositeSubscription.unsubscribe();
        super.onDetachedFromRecyclerView(recyclerView);
    }
}

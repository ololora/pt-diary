package co.scrobbler.ptdiary.ui.exercise.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.db.entity.Exercise;
import io.reactivex.rxjava3.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    private List<Exercise> exercises = new ArrayList<>();
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private final PublishSubject<Long> selectedItemId = PublishSubject.create();

    @SuppressLint("NotifyDataSetChanged")
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public PublishSubject<Long> getSelectedItemId() {
        return selectedItemId;
    }

    @NonNull
    @Override
    public ExercisesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_list_item, parent, false);

        return new ExercisesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesAdapter.ViewHolder holder, int position) {
        holder.getClientNameTextView().setText(exercises.get(position).name);

        compositeSubscription.add(
                RxView.clicks(holder.itemView)
                        .subscribe(view -> selectedItemId.onNext(exercises.get(position).id))
        );
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView exerciseNameTextView;

        public ViewHolder(View view) {
            super(view);
            exerciseNameTextView = view.findViewById(R.id.exercise_name);
        }

        public TextView getClientNameTextView() {
            return exerciseNameTextView;
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        compositeSubscription.unsubscribe();
        super.onDetachedFromRecyclerView(recyclerView);
    }
}

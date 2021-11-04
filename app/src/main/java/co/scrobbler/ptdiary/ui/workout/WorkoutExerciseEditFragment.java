package co.scrobbler.ptdiary.ui.workout;

import static android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
import static android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView;
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener;

import java.util.ArrayList;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.databinding.WorkoutExerciseEditFragmentBinding;
import co.scrobbler.ptdiary.db.entity.Exercise;
import co.scrobbler.ptdiary.db.entity.WorkoutSet;
import co.scrobbler.ptdiary.ui.BaseFragment;
import co.scrobbler.ptdiary.ui.workout.adapters.WorkoutSetsAdapter;

public class WorkoutExerciseEditFragment extends BaseFragment {
    private WorkoutExerciseEditFragmentBinding binding;


    public static WorkoutExerciseEditFragment newInstance() {
        return new WorkoutExerciseEditFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        appComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = WorkoutExerciseEditFragmentBinding.inflate(getLayoutInflater());
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(createExercise("Pull up"));
        exercises.add(createExercise("Push up"));
        ArrayAdapter<Exercise> exerciseAdapter = new ArrayAdapter<>
                (getContext(), android.R.layout.select_dialog_item, exercises);


        binding.exerciseAutocompleteTextView.setAdapter(exerciseAdapter);
        binding.exerciseAutocompleteTextView.setThreshold(1);
        binding.exerciseAutocompleteTextView.setOnItemRemoveListener(item -> {
            // exercise cleared
        });

        binding.exerciseAutocompleteTextView.setOnItemClickListener((parent, view,
                                                                             position, id) -> {
            // exercise selected
        });

        binding.supersetExerciseAutocompleteTextView.setAdapter(exerciseAdapter);
        binding.supersetExerciseAutocompleteTextView.setThreshold(1);
        binding.supersetExerciseAutocompleteTextView.setOnItemRemoveListener(item -> {
            // superset cleared
        });

        binding.supersetExerciseAutocompleteTextView.setOnItemClickListener((parent, view,
                                                                             position, id) -> {
            // superset selected
        });

        WorkoutSetsAdapter workoutSetsAdapter = new WorkoutSetsAdapter();
        ArrayList<WorkoutSet> workoutSets = new ArrayList<>();
        workoutSets.add(createWorkoutSet(16, 8));
        workoutSetsAdapter.setWorkoutSets(workoutSets);

        binding.workoutExerciseSetsListView.setAdapter(workoutSetsAdapter);
        binding.workoutExerciseSetsListView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.workoutExerciseSetsListView.setNestedScrollingEnabled(true);
        binding.workoutExerciseSetsListView.setOrientation(DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING);
        binding.workoutExerciseSetsListView.disableDragDirection(DragDropSwipeRecyclerView.ListOrientation.DirectionFlag.UP);
        binding.workoutExerciseSetsListView.disableDragDirection(DragDropSwipeRecyclerView.ListOrientation.DirectionFlag.DOWN);
        binding.workoutExerciseSetsListView.disableSwipeDirection(DragDropSwipeRecyclerView.ListOrientation.DirectionFlag.RIGHT);
        binding.workoutExerciseSetsListView.setSwipeListener((OnItemSwipeListener<WorkoutSet>) (i
                , swipeDirection, workoutSet) -> {
            workoutSets.remove(workoutSet);
            return false;
        });

        binding.addSetView.setOnClickListener(v -> {
            workoutSets.add(new WorkoutSet());
            workoutSetsAdapter.setWorkoutSets(workoutSets);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.exercise_edit_toolbar_menu, menu);

        menu.findItem(R.id.action_submit)
                .setShowAsAction(SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        navigateBack();
        return true;
    }

    private WorkoutSet createWorkoutSet(long reps, long weight) {
        WorkoutSet set = new WorkoutSet();
        set.reps = reps;
        set.weight = weight;
        return set;
    }

    private Exercise createExercise(String name) {
        Exercise exercise = new Exercise();
        exercise.name = name;
        return exercise;
    }
}

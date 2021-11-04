package co.scrobbler.ptdiary.ui.exercise;

import static android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
import static android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import co.scrobbler.ptdiary.PtDiaryApp;
import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.databinding.ExerciseEditFragmentBinding;

public class ExerciseEditFragment extends Fragment {
    private ExerciseEditFragmentBinding binding;


    public static ExerciseEditFragment newInstance() {
        return new ExerciseEditFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((PtDiaryApp) requireActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ExerciseEditFragmentBinding.inflate(getLayoutInflater());
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

    private void navigateBack() {
        Navigation
                .findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
                .popBackStack();
    }
}

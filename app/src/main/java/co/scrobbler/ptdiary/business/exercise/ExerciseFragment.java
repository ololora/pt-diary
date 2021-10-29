package co.scrobbler.ptdiary.business.exercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import co.scrobbler.ptdiary.MyApplication;
import co.scrobbler.ptdiary.databinding.ExerciseFragmentBinding;
import co.scrobbler.ptdiary.business.schedule.ScheduleFragment;

public class ExerciseFragment extends Fragment {
    private ExerciseFragmentBinding binding;

    @Inject
    ExerciseViewModel viewModel;

    public static ExerciseFragment newInstance() {
        return new ExerciseFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ExerciseFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}
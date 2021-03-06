package co.scrobbler.ptdiary.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import co.scrobbler.ptdiary.PtDiaryApp;
import co.scrobbler.ptdiary.databinding.ScheduleFragmentBinding;
import co.scrobbler.ptdiary.logic.schedule.ScheduleViewModel;

public class ScheduleFragment extends Fragment {
    private ScheduleFragmentBinding binding;

    @Inject
    ScheduleViewModel viewModel;

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((PtDiaryApp) requireActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ScheduleFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
         * on client selected:
         * viewModel.selectClient(client);
         *
         * than open client schedule fragment and subscribe:
         * viewModel.getSelectedClient().observe(getViewLifecycleOwner(), selectedClient -> {
         *      do some stuff, load workouts etc.
         * });
         */
    }
}
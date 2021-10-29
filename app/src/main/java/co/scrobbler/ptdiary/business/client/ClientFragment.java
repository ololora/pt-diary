package co.scrobbler.ptdiary.business.client;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import co.scrobbler.ptdiary.MyApplication;
import co.scrobbler.ptdiary.business.schedule.ScheduleFragment;
import co.scrobbler.ptdiary.databinding.ClientFragmentBinding;

public class ClientFragment extends Fragment {
    private ClientFragmentBinding binding;

    @Inject
    ClientViewModel viewModel;

    public static ClientFragment newInstance() {
        return new ClientFragment();
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
        binding = ClientFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}
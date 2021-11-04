package co.scrobbler.ptdiary.ui.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import co.scrobbler.ptdiary.databinding.ClientProfileInfoFragmentBinding;
import co.scrobbler.ptdiary.logic.client.ClientViewModel;
import co.scrobbler.ptdiary.ui.BaseFragment;

public class ClientProfileInfoFragment extends BaseFragment {

    private ClientProfileInfoFragmentBinding binding;

    @Inject
    ClientViewModel viewModel;

    public static ClientProfileInfoFragment newInstance() {
        return new ClientProfileInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        appComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ClientProfileInfoFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}

package co.scrobbler.ptdiary.business.client;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import co.scrobbler.ptdiary.MyApplication;
import co.scrobbler.ptdiary.databinding.ClientFragmentBinding;

public class ClientCreateFragment extends Fragment {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ClientFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}
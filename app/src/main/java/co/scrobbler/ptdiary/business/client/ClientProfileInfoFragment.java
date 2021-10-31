package co.scrobbler.ptdiary.business.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import co.scrobbler.ptdiary.MyApplication;
import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.databinding.ClientProfileInfoFragmentBinding;

public class ClientProfileInfoFragment extends Fragment {

    private ClientProfileInfoFragmentBinding binding;

    @Inject
    ClientViewModel viewModel;

    public static ClientProfileInfoFragment newInstance() {
        return new ClientProfileInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ClientProfileInfoFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}

package co.scrobbler.ptdiary.ui.client;

import static android.widget.TextView.BufferType.EDITABLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import co.scrobbler.ptdiary.databinding.ClientProfileInfoFragmentBinding;
import co.scrobbler.ptdiary.logic.client.ClientProfileInfoViewModel;
import co.scrobbler.ptdiary.ui.BaseFragment;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ClientProfileInfoFragment extends BaseFragment {
    private ClientProfileInfoFragmentBinding binding;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    ClientProfileInfoViewModel clientProfileInfoViewModel;

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
        compositeDisposable.add(setupClientInfo());
        return binding.getRoot();
    }

    private Disposable setupClientInfo() {
        return clientProfileInfoViewModel.getSelectedClient().subscribe(client -> {
            binding.clientNameView.setText(client.name, EDITABLE);
            binding.clientNotesText.setText(client.notes, EDITABLE);
        });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}

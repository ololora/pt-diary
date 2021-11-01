package co.scrobbler.ptdiary.business.client;

import static android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
import static android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM;
import static android.widget.TextView.BufferType.EDITABLE;

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

import com.jakewharton.rxbinding.widget.RxTextView;

import javax.inject.Inject;

import co.scrobbler.ptdiary.MyApplication;
import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.business.SharedViewModel;
import co.scrobbler.ptdiary.databinding.ClientEditFragmentBinding;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class ClientEditFragment extends Fragment {
    private ClientEditFragmentBinding binding;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Inject
    SharedViewModel sharedViewModel;
    @Inject
    ClientEditViewModel clientEditViewModel;

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ClientEditFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Subscription nameSubscription = RxTextView.textChanges(binding.clientNameEditView)
                .subscribe(value -> clientEditViewModel.setNameEdit(value.toString()));
        compositeSubscription.add(nameSubscription);

        Subscription notesSubscription = RxTextView.textChanges(binding.clientNotesEditText)
                .subscribe(value -> clientEditViewModel.setNotesEdit(value.toString()));
        compositeSubscription.add(notesSubscription);

        compositeDisposable.add(
                sharedViewModel
                        .getSelectedClient(sharedViewModel.getSelectedClientId())
                        .subscribe(client -> {
                            binding.clientNameEditView.setText(client.name, EDITABLE);
                            binding.clientNotesEditText.setText(client.notes, EDITABLE);
                        })
        );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.client_create_toolbar_menu, menu);

        menu.findItem(R.id.action_submit)
                .setShowAsAction(SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_submit) {
            clientEditViewModel.createOrUpdateClient(sharedViewModel.getSelectedClientId());
            navigateBack();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            navigateBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateBack() {
        Navigation
                .findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
                .popBackStack();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        compositeSubscription.unsubscribe();
        super.onDestroy();
    }
}

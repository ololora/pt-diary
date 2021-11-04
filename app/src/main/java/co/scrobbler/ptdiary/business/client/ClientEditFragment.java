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
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Objects;

import javax.inject.Inject;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.business.SharedViewModel;
import co.scrobbler.ptdiary.databinding.ClientEditFragmentBinding;
import co.scrobbler.ptdiary.ui.BaseFragment;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class ClientEditFragment extends BaseFragment {
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
        appComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        compositeDisposable.add(sharedViewModel.selectedClientId.take(1).subscribe(this::setTitle));
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

        compositeSubscription.add(subscribeNameEdit());
        compositeSubscription.add(subscribeNotesEdit());

        compositeDisposable.add(setupClientInfo());
    }

    private Subscription subscribeNameEdit() {
        return RxTextView.textChanges(binding.clientNameEditView)
                .subscribe(value -> clientEditViewModel.setNameEdit(value.toString()));
    }

    private Subscription subscribeNotesEdit() {
        return RxTextView.textChanges(binding.clientNotesEditText)
                .subscribe(value -> clientEditViewModel.setNotesEdit(value.toString()));
    }

    private Disposable setupClientInfo() {
        return clientEditViewModel.getSelectedClient().subscribe(client -> {
            binding.clientNameEditView.setText(client.name, EDITABLE);
            binding.clientNotesEditText.setText(client.notes, EDITABLE);
        });
    }

    private void setTitle(Long clientId) {
        int titleResId = clientId > 0 ? R.string.title_client_edit : R.string.title_client_create;
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar())
                .setTitle(getString(titleResId));
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
            clientEditViewModel.createOrUpdateClient();
            navigateBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        compositeSubscription.unsubscribe();
        super.onDestroy();
    }
}

package co.scrobbler.ptdiary.ui.client;

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
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import javax.inject.Inject;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.databinding.ClientListFragmentBinding;
import co.scrobbler.ptdiary.logic.SharedViewModel;
import co.scrobbler.ptdiary.logic.client.ClientViewModel;
import co.scrobbler.ptdiary.ui.BaseFragment;
import co.scrobbler.ptdiary.ui.client.adapters.ClientsAdapter;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class ClientListFragment extends BaseFragment {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Inject
    SharedViewModel sharedViewModel;
    @Inject
    ClientViewModel clientViewModel;

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        appComponent().inject(this);
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ClientListFragmentBinding binding = ClientListFragmentBinding.inflate(getLayoutInflater());

        sharedViewModel.selectedClientId.onNext(0L);

        binding.fab.setOnClickListener(v -> navigateToClientEdit());

        ClientsAdapter clientsAdapter = new ClientsAdapter();
        compositeDisposable.add(
                clientViewModel.getClientList().subscribe(clientsAdapter::setClients));
        binding.clientList.setAdapter(clientsAdapter);
        binding.clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        compositeDisposable.add(
                clientsAdapter.getSelectedItemId().subscribe(id -> {
                            sharedViewModel.selectedClientId.onNext(id);
                            navigateToClientEdit();
                        }
                ));

        return binding.getRoot();
    }

    private void navigateToClientEdit() {
        navController().navigate(R.id.navigation_client_edit);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.client_toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setShowAsAction(SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | SHOW_AS_ACTION_IF_ROOM);
        compositeSubscription.add(
                subscribeSearchQueryChanges((SearchView) searchItem.getActionView()));
    }

    private Subscription subscribeSearchQueryChanges(SearchView searchView) {
        return RxSearchView.queryTextChanges(searchView)
                .subscribe(query -> clientViewModel.query.onNext(query.toString()));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        compositeSubscription.unsubscribe();
        super.onDestroy();
    }
}

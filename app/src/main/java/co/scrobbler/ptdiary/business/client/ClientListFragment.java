package co.scrobbler.ptdiary.business.client;

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
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import co.scrobbler.ptdiary.MyApplication;
import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.business.SharedViewModel;
import co.scrobbler.ptdiary.business.client.adapters.ClientsAdapter;
import co.scrobbler.ptdiary.databinding.ClientListFragmentBinding;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ClientListFragment extends Fragment {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SharedViewModel sharedViewModel;
    @Inject
    ClientViewModel clientViewModel;

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
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
                clientViewModel.getAllClients().subscribe(clientsAdapter::setClients));
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
        Navigation
                .findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
                .navigate(R.id.navigation_client_edit);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.client_toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setShowAsAction(SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}

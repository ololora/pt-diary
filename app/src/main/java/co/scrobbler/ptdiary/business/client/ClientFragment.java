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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import co.scrobbler.ptdiary.MyApplication;
import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.business.client.adapters.ClientsAdapter;
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
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ClientFragmentBinding.inflate(getLayoutInflater());

        binding.fab.setOnClickListener(v -> Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_client_create));

        binding.clientsList.setAdapter(new ClientsAdapter(new Client[]{
                CreateTestClient("William H. Perez"),
                CreateTestClient("Lucy C. Edwards"),
                CreateTestClient("Donald S. Coleman"),
                CreateTestClient("Robert K. Dangelo"),
                CreateTestClient("Debra C. Macdonald"),
                CreateTestClient("Robin S. Strange"),
                CreateTestClient("Debbie J. Walker"),
                CreateTestClient("Roberta L. Jordan"),
                CreateTestClient("Carl D. Andrews"),
                CreateTestClient("Harold L. Elliott"),
                CreateTestClient("Danny B. Brouwer"),
                CreateTestClient("Alex C. Birchfield"),
                CreateTestClient("Frieda P. Hoover"),
                CreateTestClient("Phil D. Weller"),
                CreateTestClient("Lynette J. Barbour"),
                CreateTestClient("Alex S. Robinson"),
                CreateTestClient("Irene B. Nash"),
        }));
        binding.clientsList.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.client_toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
                | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) item.getActionView();
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

    private static Client CreateTestClient(String name) {
        Client client = new Client();
        client.name = name;
        return client;
    }
}

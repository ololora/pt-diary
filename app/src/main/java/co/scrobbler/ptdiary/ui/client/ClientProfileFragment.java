package co.scrobbler.ptdiary.ui.client;

import static android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
import static android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import co.scrobbler.ptdiary.R;
import co.scrobbler.ptdiary.databinding.ClientProfileFragmentBinding;
import co.scrobbler.ptdiary.logic.client.ClientViewModel;
import co.scrobbler.ptdiary.ui.BaseFragment;
import co.scrobbler.ptdiary.ui.client.adapters.ProfileClientTabFragmentAdapter;

public class ClientProfileFragment extends BaseFragment {

    private ClientProfileFragmentBinding binding;

    @Inject
    ClientViewModel viewModel;

    public static ClientProfileFragment newInstance() {
        return new ClientProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        appComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ClientProfileFragmentBinding.inflate(getLayoutInflater());
        binding.clientProfileViewPager.setAdapter(new ProfileClientTabFragmentAdapter(this));
        binding.clientProfileTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.clientProfileViewPager.setCurrentItem(tab.getPosition());
                binding.clientProfileTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.clientProfileViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.clientProfileViewPager.setCurrentItem(position);
                binding.clientProfileTabLayout.getTabAt(position).select();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.client_profile_toolbar_menu, menu);

        menu.findItem(R.id.action_edit)
                .setShowAsAction(SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | SHOW_AS_ACTION_IF_ROOM);
    }
}

package co.scrobbler.ptdiary.ui.client.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import co.scrobbler.ptdiary.ui.client.ClientProfileInfoFragment;

public class ProfileClientTabFragmentAdapter extends FragmentStateAdapter {

    public ProfileClientTabFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return ClientProfileInfoFragment.newInstance();
            case 1:
                return ClientProfileInfoFragment.newInstance();
            case 2:
                return ClientProfileInfoFragment.newInstance();
            default:
                throw new IllegalArgumentException("Unexpected tab position for ClientProfileFragment");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

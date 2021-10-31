package co.scrobbler.ptdiary.business.client.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import co.scrobbler.ptdiary.business.client.ClientProfileInfoFragment;
import co.scrobbler.ptdiary.business.schedule.ScheduleFragment;

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

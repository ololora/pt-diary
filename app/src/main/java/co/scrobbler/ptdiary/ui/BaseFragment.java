package co.scrobbler.ptdiary.ui;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import co.scrobbler.ptdiary.AppComponent;
import co.scrobbler.ptdiary.PtDiaryApp;
import co.scrobbler.ptdiary.R;

public abstract class BaseFragment extends Fragment {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigateBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected AppComponent appComponent() {
        return ((PtDiaryApp) requireActivity().getApplicationContext()).getAppComponent();
    }

    protected void navigateBack() {
        navController().popBackStack();
    }

    protected NavController navController() {
        return Navigation
                .findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
    }
}

package net.mpentek.sportify.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;
import net.mpentek.sportify.viewmodel.MainViewModel;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    View mainactivity;
    BottomAppBar bar;
    FloatingActionButton btn;
    Button signOutBnt;
    MainViewModel viewModel;

    TextView email;
    TextView NumOfWorkouts;
    TextView Rang;
    TextView Profile;


    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                navController.navigateUp();
                if (navController.getCurrentBackStackEntry().getDestination().getId() == R.id.mainFragment) {
                    NavBarBackToMain();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainactivity = getActivity().findViewById(R.id.Main_activity);
        bar = mainactivity.findViewById(R.id.bottomAppBar);
        btn = mainactivity.findViewById(R.id.floating_button);
        signOutBnt = mainactivity.findViewById(R.id.buttonSingOut);
        email = mainactivity.findViewById(R.id.emailTV);
        NumOfWorkouts = mainactivity.findViewById(R.id.workoutTV);
        Rang = mainactivity.findViewById(R.id.rangTV);
        Profile = mainactivity.findViewById(R.id.Profile_name);

        signOutBnt.setOnClickListener(this);
        btn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        checkIfSignedIn();

        navController = Navigation.findNavController(view);
        bar.getMenu().setGroupVisible(R.id.group, false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
                getActivity().onBackPressed();
                break;
            case R.id.buttonSingOut:
                signOut(v);
                break;
        }
    }

    private void NavBarBackToMain() {
        bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        btn.setImageResource(R.drawable.ic_round_add_24);
        bar.getMenu().setGroupVisible(R.id.group, true);
    }

    public void signOut(View v) {
        viewModel.signOut();
    }

    private void startLoginActivity() {
        navController.navigate(R.id.action_global_to_sing_in);
        getActivity().finish();
    }

    private void checkIfSignedIn() {

        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                email.setText(user.getEmail());
                Profile.setText(user.getDisplayName());
            } else
                startLoginActivity();
        });
    }

}
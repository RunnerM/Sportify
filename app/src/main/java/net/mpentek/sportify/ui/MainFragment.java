package net.mpentek.sportify.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutAdapter;
import net.mpentek.sportify.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    RecyclerView Rv;
    WorkoutAdapter adapter;
    View mainactivity;
    BottomAppBar bar;
    FloatingActionButton btn;
    MainViewModel viewModel;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);


    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        mainactivity= getActivity().findViewById(R.id.Main_activity);
        bar= mainactivity.findViewById(R.id.bottomAppBar);
        btn= mainactivity.findViewById(R.id.floating_button);
        btn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        checkIfSignedIn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.floating_button:
                navController.navigate(R.id.action_mainFragment_to_add_fragment);
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);

        }
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {

            } else
                startLoginActivity();
        });
    }
    private void startLoginActivity() {
        navController.navigate(R.id.action_global_to_sing_in);
        getActivity().finish();
    }
}
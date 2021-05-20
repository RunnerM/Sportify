package net.mpentek.sportify.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;
import net.mpentek.sportify.data.Room.WorkoutWithSteps;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutAdapter;
import net.mpentek.sportify.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment implements View.OnClickListener, WorkoutAdapter.OnListItemClickListener{
    NavController navController;
    RecyclerView workoutRecycleView;
    WorkoutAdapter adapter;
    View mainactivity;
    BottomAppBar bar;
    FloatingActionButton btn;
    MainViewModel viewModel;
    Button button3;

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
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //do nothing so user cant escape;
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

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
        mainactivity = getActivity().findViewById(R.id.Main_activity);
        bar = mainactivity.findViewById(R.id.bottomAppBar);
        btn = mainactivity.findViewById(R.id.floating_button);
        btn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        checkIfSignedIn();

        //recycle view

        workoutRecycleView = mainactivity.findViewById(R.id.workoutRV);
        workoutRecycleView.hasFixedSize();
        workoutRecycleView.setLayoutManager(new LinearLayoutManager(mainactivity.getContext()));

        adapter = new WorkoutAdapter(this,new ArrayList<>());
        workoutRecycleView.setAdapter(adapter);

        viewModel.getWorkouts().observe(getViewLifecycleOwner(), workouts->{
            adapter.UpdateSource(workouts);
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.floating_button:
                navController.navigate(R.id.action_mainFragment_to_add_fragment);
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                break;
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

    @Override
    public void onListItemClick(int clickedItemIndex) {
        viewModel.setSelectedWorkout(clickedItemIndex);

        Bundle bundle = new Bundle();
        bundle.putString("Selected",Integer.valueOf(clickedItemIndex).toString());
        navController.navigate(R.id.action_mainFragment_to_workout_view_fragment,bundle);
        bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);

    }
}
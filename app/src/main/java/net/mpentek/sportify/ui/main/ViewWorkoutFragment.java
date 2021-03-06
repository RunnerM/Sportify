package net.mpentek.sportify.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;
import net.mpentek.sportify.data.Room.WorkoutWithSteps;
import net.mpentek.sportify.model.WorkoutElement;
import net.mpentek.sportify.viewmodel.MainViewModel;
import org.jetbrains.annotations.NotNull;

public class ViewWorkoutFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    MainViewModel viewModel;
    TextView name;
    TextView type;
    TextView steps;
    View mainactivity;
    BottomAppBar bar;
    FloatingActionButton btn;
    WorkoutWithSteps workout;
    int index;
    Button btnDoWorkout;

    public ViewWorkoutFragment() {
        // Required empty public constructor
    }

    public static ViewWorkoutFragment newInstance() {
        ViewWorkoutFragment fragment = new ViewWorkoutFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainactivity = getActivity().findViewById(R.id.Main_activity);
        String indexAsString= this.getArguments().getString("Selected");
         index= Integer.parseInt(indexAsString);




        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                navController.navigateUp();
                NavBarBackToMain();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_workout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = getActivity().findViewById(R.id.workout_name);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //name.setText(viewModel.getWorkouts().get(viewModel.getSelectedWorkout()).getName());

        mainactivity= getActivity().findViewById(R.id.Main_activity);
        bar= mainactivity.findViewById(R.id.bottomAppBar);
        btn= mainactivity.findViewById(R.id.floating_button);
        btn.setOnClickListener(this);
        bar.getNavigationIcon().setVisible(false, true);
        navController = Navigation.findNavController(view);
        type= getActivity().findViewById(R.id.type_text);
        steps= getActivity().findViewById(R.id.steps_number);
        btnDoWorkout= getActivity().findViewById(R.id.button_do_workout);
        btnDoWorkout.setOnClickListener(this);

        workout = new WorkoutWithSteps();

        viewModel.getWorkouts().observe(getViewLifecycleOwner(), workouts->{
            workout=workouts.get(index);
            name.setText(workout.workout.getName());
            type.setText(workout.workout.getType());
            int i = 0;
            for (WorkoutElement e:workout.step) {
                i++;
            }
            steps.setText(Integer.valueOf(i).toString());


        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
                getActivity().onBackPressed();
                break;
            case R.id.button_do_workout:

                navController.navigate(R.id.action_workout_view_fragment_to_do_workout,this.getArguments());
                break;
        }
    }
    private void NavBarBackToMain(){
        bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        btn.setImageResource(R.drawable.ic_round_add_24);
        bar.getMenu().setGroupVisible(R.id.group,true);
    }
}
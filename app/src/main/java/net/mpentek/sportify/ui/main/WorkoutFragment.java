package net.mpentek.sportify.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import net.mpentek.sportify.model.ACTIVITY_TYPE;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;
import net.mpentek.sportify.viewmodel.AddViewModel;
import net.mpentek.sportify.viewmodel.MainViewModel;
import net.mpentek.sportify.viewmodel.SettingsViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class WorkoutFragment extends Fragment implements View.OnClickListener {

    private int index;
    private MainViewModel viewModel;
    private SettingsViewModel settingsViewModel;
    private AddViewModel addViewModel;
    private NavController navController;
    private WorkoutWithSteps workout;
    private TextView minute;
    private TextView second;
    private TextView distance;
    private TextView reps;
    private TextView weight;
    private TextView description;
    private TextView step;
    private TextView distance_unit;
    private TextView weight_unit;
    private Button nextStep;
    BottomAppBar appbar;
    FloatingActionButton fab;
    Integer stepCounter;
    ConstraintLayout moving;
    ConstraintLayout strength;
    int NoOfSteps;


    public WorkoutFragment() {
        // Required empty public constructor
    }

    public static WorkoutFragment newInstance() {
        WorkoutFragment fragment = new WorkoutFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String indexAsString = this.getArguments().getString("Selected");
        index = Integer.parseInt(indexAsString);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //trapping user here as well.
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        minute = getActivity().findViewById(R.id.Minute_counter);
        second = getActivity().findViewById(R.id.Second_counter);
        distance = getActivity().findViewById(R.id.distance_counter);
        reps = getActivity().findViewById(R.id.Rep_textview_workout);
        weight = getActivity().findViewById(R.id.weight_textview_do_workout);
        description = getActivity().findViewById(R.id.description_textView);
        step = getActivity().findViewById(R.id.Step_Counter_workout);
        distance_unit = getActivity().findViewById(R.id.distance_unit_textView);
        weight_unit = getActivity().findViewById(R.id.weight_unit_textview);
        nextStep = getActivity().findViewById(R.id.next_step);
        fab = getActivity().findViewById(R.id.floating_button);
        appbar = getActivity().findViewById(R.id.bottomAppBar);
        moving = getActivity().findViewById(R.id.Moving_layout_do);
        strength = getActivity().findViewById(R.id.Strenght_layout_do);
        nextStep.setOnClickListener(this);
        appbar.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);
        stepCounter = 0;


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        addViewModel = new ViewModelProvider(this).get(AddViewModel.class);
        navController = Navigation.findNavController(view);
        viewModel.getWorkouts().observe(getViewLifecycleOwner(), workouts -> {
            workout = workouts.get(index);
            if (workout.workout.getType().equals("MOVING")) {
                strength.setVisibility(View.INVISIBLE);
            } else {
                moving.setVisibility(View.INVISIBLE);
            }
            WorkoutElement element = workout.step.get(stepCounter);
            drawUI(element, workout.workout.getType());
            stepCounter++;
            NoOfSteps = workout.step.size();
        });

        settingsViewModel.getData().observe(getViewLifecycleOwner(), list -> {
            distance_unit.setText(list.get(0));
            weight_unit.setText(list.get(1));
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step:
                RefreshUI();
                break;
        }
    }

    private void RefreshUI() {
        if (stepCounter == NoOfSteps) {
            Toast.makeText(getActivity(), "Congratulation your workout is done!", Toast.LENGTH_SHORT).show();
            if (workout.workout.isPlan()) {
                workout.workout.setPlan(false);
                addViewModel.editWorkout(workout);
            } else {
                saveWorkout(workout);
            }
            navController.navigate(R.id.action_do_workout_to_mainFragment);
            appbar.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            appbar.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            appbar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            fab.setImageResource(R.drawable.ic_round_add_24);
            appbar.getMenu().setGroupVisible(R.id.group, true);
        } else {
            WorkoutElement element = workout.step.get(stepCounter);
            drawUI(element, workout.workout.getType());
            stepCounter++;
        }
    }

    private void saveWorkout(WorkoutWithSteps workout) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        WorkoutWithSteps w = new WorkoutWithSteps();
        w.workout = new Workout();
        w.workout.setDay(mDay);
        w.workout.setMonth(mMonth+1);
        w.workout.setYear(mYear);
        w.workout.setType(workout.workout.getType());
        w.workout.setPlan(workout.workout.isPlan());
        w.workout.setName(workout.workout.getName());
        w.step = new ArrayList<>();

        for (WorkoutElement e : workout.step) {
            WorkoutElement i = new WorkoutElement();
            i.setDescription(e.getDescription());
            i.setWeight(e.getWeight());
            i.setRep(e.getRep());
            i.setSec(e.getSec());
            i.setMin(e.getMin());
            i.setDistance(e.getDistance());
            w.step.add(i);
        }
        addViewModel.finalizeWorkout(w);
    }

    private void drawUI(WorkoutElement element, String type) {
        step.setText(getString(R.string.Step_counter_workout)+ " "+ Integer.valueOf(stepCounter + 1).toString());
        description.setText(element.getDescription());
        if (type.equals(ACTIVITY_TYPE.MOVING.toString())) {
            distance.setText(Integer.toString(element.getDistance()));
            minute.setText(Integer.toString(element.getMin()));
            second.setText(Integer.toString(element.getSec()));
        } else {
            weight.setText(Integer.toString(element.getWeight()));
            reps.setText(Integer.toString(element.getRep()));
        }
    }
}
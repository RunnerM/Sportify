package net.mpentek.sportify.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.gson.Gson;
import net.mpentek.sportify.R;
import net.mpentek.sportify.data.Room.WorkoutWithSteps;
import net.mpentek.sportify.model.ACTIVITY_TYPE;
import net.mpentek.sportify.model.WorkoutElement;
import net.mpentek.sportify.viewmodel.AddViewModel;

public class SpecifyStepsFragment extends Fragment implements View.OnClickListener {
    FloatingActionButton fab;
    AddViewModel model;
    ConstraintLayout movingLayout;
    ConstraintLayout strengthLayout;
    WorkoutWithSteps workout;
    Integer stepCounter;
    Integer NumOfSteps;
    TextView stepCounterTextView;
    EditText description;
    EditText minutesEdit;
    EditText secondsEdit;
    EditText RepsEdit;
    EditText weightEdit;
    Button addBtn;
    EditText distance;
    NavController navController;
    BottomAppBar appbar;
    AddViewModel viewModel;


    public SpecifyStepsFragment() {
        // Required empty public constructor
    }

    public static SpecifyStepsFragment newInstance() {
        SpecifyStepsFragment fragment = new SpecifyStepsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String workoutAsString = this.getArguments().getString("workout");
        String counter = this.getArguments().getString("counter");

        Gson gson = new Gson();
        workout = gson.fromJson(workoutAsString, WorkoutWithSteps.class);
        NumOfSteps = gson.fromJson(counter, Integer.class);
        stepCounter = 0;

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
        return inflater.inflate(R.layout.fragment_specify_steps, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        fab = getActivity().findViewById(R.id.floating_button);
        //fab.setOnClickListener(this);
        model = new ViewModelProvider(this).get(AddViewModel.class);
        //workout = model.getWorkout();
        navController = Navigation.findNavController(view);

        stepCounterTextView = getActivity().findViewById(R.id.Step_Counter_Text_View);
        description = getActivity().findViewById(R.id.edit_description);
        minutesEdit = getActivity().findViewById(R.id.edit_minutes);
        secondsEdit = getActivity().findViewById(R.id.edit_seconds);
        RepsEdit = getActivity().findViewById(R.id.edit_reps);
        weightEdit = getActivity().findViewById(R.id.edit_weight);
        addBtn = getActivity().findViewById(R.id.add_step_btn);
        distance = getActivity().findViewById(R.id.editTextDistance);
        appbar = getActivity().findViewById(R.id.bottomAppBar);
        appbar.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);

        addBtn.setOnClickListener(this);

        stepCounterTextView.setText(R.string.Step_1);
        movingLayout = getActivity().findViewById(R.id.Moving_layout_do);
        strengthLayout = getActivity().findViewById(R.id.Strenght_layout_do);

        if (workout.workout.getType().equals("MOVING")) {
            strengthLayout.setVisibility(View.INVISIBLE);
        } else if (workout.workout.getType().equals("STRENGTH")) {
            movingLayout.setVisibility(View.INVISIBLE);
        }
        viewModel= new ViewModelProvider(this).get(AddViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (workout.workout.getType().equals("MOVING")) {
            strengthLayout.setVisibility(View.INVISIBLE);
        } else if (workout.workout.getType().equals("STRENGTH")) {
            movingLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_step_btn:
                AddStep();
                ClearUI();
                Toast.makeText(getActivity(), "Step added", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void ClearUI() {
        description.setText("");
        minutesEdit.setText("");
        secondsEdit.setText("");
        RepsEdit.setText("");
        weightEdit.setText("");
        distance.setText("");
        stepCounterTextView.setText("Step " + new Integer(stepCounter+1).toString());
    }

    public void AddStep() {
        WorkoutElement element = new WorkoutElement();
        element.setDescription(description.getText().toString());
        if (workout.workout.getType().equals(ACTIVITY_TYPE.MOVING.toString())) {
            element.setMin(Integer.parseInt(minutesEdit.getText().toString()));
            element.setSec(Integer.parseInt(secondsEdit.getText().toString()));
            element.setDistance(Integer.parseInt(distance.getText().toString()));
        } else if (workout.workout.getType().equals(ACTIVITY_TYPE.STRENGTH.toString())) {
            element.setRep(Integer.parseInt(RepsEdit.getText().toString()));
            element.setWeight(Integer.parseInt(weightEdit.getText().toString()));
        }
        workout.step.add(element);
        stepCounter++;
        if(stepCounter == NumOfSteps){
            workout.workout.setPlan(true);
            viewModel.finalizeWorkout(workout);
            navController.navigate(R.id.action_specify_step_fragment_to_mainFragment);
            appbar.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            appbar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            fab.setImageResource(R.drawable.ic_round_add_24);
        }
    }
}
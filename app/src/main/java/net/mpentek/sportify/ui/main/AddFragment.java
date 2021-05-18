package net.mpentek.sportify.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.viewmodel.AddViewModel;
import net.mpentek.sportify.viewmodel.MainViewModel;

public class AddFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    NavController navController;
    View mainactivity;
    BottomAppBar bar;
    FloatingActionButton btn;
    EditText name;
    AddViewModel viewModel;
    int NumOfSteps;
    String WorkoutType;
    Spinner spinnerNumOf;
    Spinner spinnerType;

    public AddFragment() {
        // Required empty public constructor
    }


    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainactivity = getActivity().findViewById(R.id.Main_activity);
        bar = mainactivity.findViewById(R.id.bottomAppBar);
        btn = mainactivity.findViewById(R.id.floating_button);
        btn.setOnClickListener(this);
        navController = Navigation.findNavController(view);
        bar.getMenu().setGroupVisible(R.id.group, false);


        name = mainactivity.findViewById(R.id.editTextName);
        Button btn = mainactivity.findViewById(R.id.Button_specify_steps);
        btn.setOnClickListener(this);


        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
        setUpSpinners();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
                getActivity().onBackPressed();
                break;
            case R.id.Button_specify_steps:
                Workout w = new Workout();
                w.setName(name.getText().toString());
                w.setType(WorkoutType);
                viewModel.setSteps(NumOfSteps);
                navController.navigate(R.id.action_add_fragment_to_specify_step_fragment);

        }
    }

    private void NavBarBackToMain() {
        bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        btn.setImageResource(R.drawable.ic_round_add_24);
        bar.getMenu().setGroupVisible(R.id.group, true);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        if (parent.getId() == R.id.spinner_type) {
            WorkoutType = parent.getItemAtPosition(pos).toString();
        }
        if (parent.getId() == R.id.spinner_num_of) {
            NumOfSteps = Integer.parseInt(parent.getItemAtPosition(pos).toString());
        }
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void setUpSpinners(){

        spinnerNumOf = mainactivity.findViewById(R.id.spinner_num_of);
        spinnerType = mainactivity.findViewById(R.id.spinner_type);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(mainactivity.getContext(),
                R.array.type_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterNumOf = ArrayAdapter.createFromResource(mainactivity.getContext(),
                R.array.num_of_steps_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterNumOf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerType.setAdapter(adapterType);
        spinnerNumOf.setAdapter(adapterNumOf);

        spinnerType.setOnItemSelectedListener(this);
        spinnerNumOf.setOnItemSelectedListener(this);
    }

}
package net.mpentek.sportify.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
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
import androidx.preference.PreferenceFragmentCompat;
import net.mpentek.sportify.viewmodel.SettingsViewModel;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    View mainactivity;
    BottomAppBar bar;
    FloatingActionButton btn;
    Switch distanceSwitch;
    Switch weightSwitch;

    private SettingsViewModel viewModel;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainactivity = getActivity().findViewById(R.id.Main_activity);
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
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        setSwitchText(distanceSwitch, "km");
        setSwitchText(weightSwitch, "kg");
    }

    public void setSwitchText(Switch _switch, String True) {
        if (_switch.getText().equals(True)) {
            _switch.setChecked(true);
        } else {
            _switch.setChecked(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainactivity = getActivity().findViewById(R.id.Main_activity);
        bar = mainactivity.findViewById(R.id.bottomAppBar);
        bar.getMenu().setGroupVisible(R.id.group, false);
        btn = mainactivity.findViewById(R.id.floating_button);
        btn.setOnClickListener(this);
        bar.getNavigationIcon().setVisible(false, true);

        navController = Navigation.findNavController(view);


        distanceSwitch = getActivity().findViewById(R.id.switch_distance);
        weightSwitch = getActivity().findViewById(R.id.switch_weight);
        SettingsViewModel model = new ViewModelProvider(this).get(SettingsViewModel.class);
        model.getData().observe(this, prefs -> {
            if (distanceSwitch != null && weightSwitch != null) {
                distanceSwitch.setText(prefs.get(0));
                weightSwitch.setText(prefs.get(1));
            }
        });
        distanceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    distanceSwitch.setText(R.string.Kilometer);
                } else {
                    distanceSwitch.setText(R.string.Mile);
                }
                model.savePref("distance", distanceSwitch.getText().toString());
            }
        });
        weightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weightSwitch.setText(R.string.Kilogramm);
                } else {
                    weightSwitch.setText(R.string.Pound);
                }
                model.savePref("weight", weightSwitch.getText().toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
                getActivity().onBackPressed();
        }
    }
    private void NavBarBackToMain(){
        bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        btn.setImageResource(R.drawable.ic_round_add_24);
        bar.getMenu().setGroupVisible(R.id.group,true);
    }
}
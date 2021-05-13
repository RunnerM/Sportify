package net.mpentek.sportify.ui;

import android.content.SharedPreferences;
import android.view.*;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutAdapter;
import net.mpentek.sportify.viewmodel.SettingsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floating_btn;
    BottomAppBar appBar;
    NavController navController;
    //Main
    RecyclerView Rv;
    WorkoutAdapter adapter;
    //Settings
    Switch distanceSwitch;
    Switch weightSwitch;
    SettingsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floating_btn = findViewById(R.id.floating_button);
        floating_btn.setOnClickListener(this);
        appBar = findViewById(R.id.bottomAppBar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//Nav
        appBar.setNavigationOnClickListener((navigation) -> {
            switch(navController.getCurrentDestination().getId()){
                case R.id.mainFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_mainFragment_to_profile_fragment);
                    break;
                case R.id.about_fragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_about_fragment_to_profile_fragment);
                    break;
                case R.id.settings_fragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_settings_fragment_to_profile_fragment);
                    break;
                case R.id.add_fragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_add_fragment_to_profile_fragment);
                    break;
            }
        });
//Menu
        appBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.settings_menu_item:
                    if (navController.getCurrentDestination().getId() == R.id.settings_fragment) {
                        return false;
                    } else {
                        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                        navController.navigate(R.id.action_mainFragment_to_settings_fragment);
                    }
                    return true;
                case R.id.about_menu_item:
                    if (navController.getCurrentDestination().getId() == R.id.about_fragment) {
                        return false;
                    } else {
                        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                        navController.navigate(R.id.action_mainFragment_to_about_fragment);

                    }

                    return true;
                default:
                    return false;
            }
        });

        //Settings
        distanceSwitch= findViewById(R.id.switch_distance);
        weightSwitch = findViewById(R.id.switch_weight);

        SettingsViewModel model = new ViewModelProvider(this).get(SettingsViewModel.class);
        model.getData().observe(this, prefs -> {
            // itt a hiba bitches
            distanceSwitch.setText(prefs.get(0));
            weightSwitch.setText(prefs.get(1));
        });

//        distanceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    distanceSwitch.setText("km");
//                }else {
//                    distanceSwitch.setText("mile");
//                }
//                storePrefs();
//            }
//        });
//        weightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    weightSwitch.setText("kg");
//                }else {
//                    weightSwitch.setText("pound");
//                }
//                storePrefs();
//            }
//        });
    }

    public void storePrefs(){
        String distance= distanceSwitch.getText().toString();
        String weight = weightSwitch.getText().toString();
        model.savePref("distance",distance);
        model.savePref("weight",weight);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
                appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                navController.navigate(R.id.action_mainFragment_to_add_fragment);
                break;

        }

    }



    private void pushNAvInEnd(){
        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);

    }

}
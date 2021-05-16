package net.mpentek.sportify.ui;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;
import net.mpentek.sportify.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floating_btn;
    BottomAppBar appBar;
    NavController navController;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floating_btn = findViewById(R.id.floating_button);

        appBar = findViewById(R.id.bottomAppBar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//Nav
        appBar.setNavigationOnClickListener((navigation) -> {
            switch(navController.getCurrentDestination().getId()){
                case R.id.mainFragment:
                    pushNavToEnd();
                    navController.navigate(R.id.action_mainFragment_to_profile_fragment);
                    break;
                case R.id.about_fragment:
                    pushNavToEnd();
                    navController.navigate(R.id.action_about_fragment_to_profile_fragment);
                    break;
                case R.id.settings_fragment:
                    pushNavToEnd();
                    navController.navigate(R.id.action_settings_fragment_to_profile_fragment);
                    break;
                case R.id.add_fragment:
                    pushNavToEnd();
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
                        pushNavToEnd();
                        navController.navigate(R.id.action_mainFragment_to_settings_fragment);
                    }
                    return true;
                case R.id.about_menu_item:
                    if (navController.getCurrentDestination().getId() == R.id.about_fragment) {
                        return false;
                    } else {
                        pushNavToEnd();
                        navController.navigate(R.id.action_mainFragment_to_about_fragment);
                    }
                    return true;
                default:
                    return false;
            }
        });
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                Toast.makeText(this,"Welcome back "+user.getDisplayName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void pushNavToEnd(){
        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
    }

}
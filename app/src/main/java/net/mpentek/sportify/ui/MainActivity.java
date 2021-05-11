package net.mpentek.sportify.ui;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floating_btn;
    BottomAppBar appBar;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floating_btn = findViewById(R.id.floating_button);
        floating_btn.setOnClickListener(this);
        appBar = findViewById(R.id.bottomAppBar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        this.getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
               // navController.navigate(R.id.action_mainFragment_to_settings_fragment);

        }

    }

    public void onSettingsBtn(MenuItem item) {
        //Todo: nav to settings
    }
}
package net.mpentek.sportify.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
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
    Switch distance_texView;
    Switch weight_textView;

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
        mainactivity= getActivity().findViewById(R.id.Main_activity);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
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
        mainactivity= getActivity().findViewById(R.id.Main_activity);
        bar= mainactivity.findViewById(R.id.bottomAppBar);
        btn= mainactivity.findViewById(R.id.floating_button);
        btn.setOnClickListener(this);
        bar.getNavigationIcon().setVisible(false,true);

        navController = Navigation.findNavController(view);
        bar.getMenu().setGroupVisible(R.id.group,false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_button:
                getActivity().onBackPressed();
                    bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    btn.setImageResource(R.drawable.ic_round_add_24);
                    bar.getMenu().setGroupVisible(R.id.group,true);
        }
    }
}
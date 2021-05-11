package net.mpentek.sportify.ui;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;

public class MainFragment extends Fragment implements View.OnClickListener {
    NavController navController;


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
                View mainactivity= getActivity().findViewById(R.id.Main_activity);
                BottomAppBar bar= mainactivity.findViewById(R.id.bottomAppBar);
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
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
        view.findViewById(R.id.btn).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        View mainactivity= getActivity().findViewById(R.id.Main_activity);
        BottomAppBar bar= mainactivity.findViewById(R.id.bottomAppBar);
        FloatingActionButton btn= mainactivity.findViewById(R.id.floating_button);
        switch (v.getId()){
            case R.id.btn:
                navController.navigate(R.id.action_mainFragment_to_add_fragment);

                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                btn.setOnClickListener(this);
                btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                break;

            case R.id.floating_button:
                getActivity().onBackPressed();
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                btn.setImageResource(R.drawable.ic_round_add_24);

        }
    }
}
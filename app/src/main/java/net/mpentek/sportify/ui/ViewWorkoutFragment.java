package net.mpentek.sportify.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.mpentek.sportify.R;

public class ViewWorkoutFragment extends Fragment {


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_workout, container, false);
    }
}
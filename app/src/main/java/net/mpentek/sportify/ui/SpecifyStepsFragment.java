package net.mpentek.sportify.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.mpentek.sportify.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpecifyStepsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpecifyStepsFragment extends Fragment {



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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_steps, container, false);
    }
}
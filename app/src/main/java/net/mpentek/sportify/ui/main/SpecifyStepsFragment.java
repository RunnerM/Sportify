package net.mpentek.sportify.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import net.mpentek.sportify.R;

public class SpecifyStepsFragment extends Fragment implements View.OnClickListener {
    FloatingActionButton fab;

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
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        fab= getActivity().findViewById(R.id.floating_button);
        fab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
package net.mpentek.sportify.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import net.mpentek.sportify.R;

public class SpecifyStepsFragment extends Fragment {
    RecyclerView recyclerView;



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
//        recyclerView = getActivity().findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(new RandomNumListAdapter(1234));
    }
}
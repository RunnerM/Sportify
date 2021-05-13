package net.mpentek.sportify.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import net.mpentek.sportify.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workoutitem, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.name.setText(workouts.get(position).getName());
    }

    private ArrayList<Workout> workouts;

    public WorkoutAdapter(ArrayList<Workout> pokemons){
        workouts = pokemons;

    }
    public int getItemCount() {
        return workouts.size();
    }





    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.WoFrTx);

        }
    }

}

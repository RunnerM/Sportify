package net.mpentek.sportify.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import net.mpentek.sportify.R;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    final private OnListItemClickListener mOnListItemClickListener;

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workout_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.name.setText(workouts.get(position).getName());
    }

    private ArrayList<Workout> workouts;

    public WorkoutAdapter(OnListItemClickListener OnListItemClickListener, ArrayList<Workout> workouts){
        this.mOnListItemClickListener = OnListItemClickListener;
        this.workouts = workouts;
    }
    public int getItemCount() {
        return workouts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


}

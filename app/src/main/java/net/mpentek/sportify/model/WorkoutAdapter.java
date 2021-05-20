package net.mpentek.sportify.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import net.mpentek.sportify.R;
import net.mpentek.sportify.data.Room.WorkoutWithSteps;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    final private OnListItemClickListener mOnListItemClickListener;

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workout_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.name.setText(workouts.get(position).workout.getName());
        viewHolder.step.setText(Integer.valueOf(workouts.get(position).step.size()).toString()+" Steps");
        viewHolder.date.setText(Integer.valueOf(workouts.get(position).workout.getYear()).toString()+
                "/"+Integer.valueOf(workouts.get(position).workout.getMonth()).toString()+"/"
                +Integer.valueOf(workouts.get(position).workout.getDay()).toString());
    }

    private ArrayList<WorkoutWithSteps> workouts;

    public WorkoutAdapter(OnListItemClickListener OnListItemClickListener, ArrayList<WorkoutWithSteps> workouts){
        this.mOnListItemClickListener = OnListItemClickListener;
        this.workouts = workouts;
    }
    public int getItemCount() {
        return workouts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView step;
        TextView date;

        ViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tv_name);
            step = itemView.findViewById(R.id.step_no_text_item);
            date = itemView.findViewById(R.id.text_date_item);
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

    public void UpdateSource(List<WorkoutWithSteps> workouts){
        this.workouts= (ArrayList<WorkoutWithSteps>) workouts;
        notifyDataSetChanged();
    }


}

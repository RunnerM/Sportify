package net.mpentek.sportify.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import net.mpentek.sportify.data.Room.WorkoutDatabaseRepository;
import net.mpentek.sportify.data.Room.WorkoutWithSteps;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

public class AddViewModel extends AndroidViewModel {
    WorkoutWithSteps workout;
    WorkoutDatabaseRepository repository;
    private int steps;

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public AddViewModel(Application app) {
        super(app);
        this.workout = new WorkoutWithSteps();
        repository = WorkoutDatabaseRepository.getInstance(app);
    }

    public void addStep(WorkoutElement step){
        workout.step.add(step);
    }
    public void SaveWorkout(Workout workout){
        this.workout.workout= workout;
    }
    public void finalizeWorkout(){
        repository.addWorkout(workout);
    }
}

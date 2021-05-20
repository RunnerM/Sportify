package net.mpentek.sportify.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import net.mpentek.sportify.data.Room.WorkoutDatabaseRepository;
import net.mpentek.sportify.data.Room.WorkoutWithSteps;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

import java.sql.Date;

public class AddViewModel extends AndroidViewModel {
    WorkoutWithSteps workout;
    WorkoutDatabaseRepository repository;
    private int steps;
    private Date date;

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

    public void finalizeWorkout(WorkoutWithSteps workout){
        repository.addWorkout(workout);
    }
    public void editWorkout(WorkoutWithSteps workout){
        repository.updateWorkout(workout);
    }

    public void saveDate(int year, int month, int day) {
        date= new Date(year, month, day);
    }

    public Date getDate() {
        return date;
    }

    public WorkoutWithSteps getWorkout() {
        return workout;
    }
}

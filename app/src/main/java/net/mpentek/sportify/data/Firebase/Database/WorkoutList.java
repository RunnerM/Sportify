package net.mpentek.sportify.data.Firebase.Database;

import net.mpentek.sportify.model.Workout;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutList {
private ArrayList<Workout> workouts;

    public WorkoutList(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    public WorkoutList() {
        this.workouts= new ArrayList<>(1);
    }

    public ArrayList<Workout> getWorkouts() {
        return new ArrayList<Workout>(workouts);
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }
}

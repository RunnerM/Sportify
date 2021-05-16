package net.mpentek.sportify.model;

import java.util.ArrayList;

public class Workout {
    private String name;
    private ACTIVITY_TYPE type;
    private ArrayList<WorkoutElement> steps;

    public Workout(String name, ACTIVITY_TYPE type) {
        this.name = name;
        this.type = type;
        steps = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workout() {
        steps = new ArrayList<>();
    }
    public Workout(Workout workout){
        this.name = workout.getName();
        this.type = workout.getType();
        this.steps = workout.getSteps();
    }

    public ArrayList<WorkoutElement> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<WorkoutElement> steps) {
        this.steps = steps;
    }

    public ACTIVITY_TYPE getType() {
        return type;
    }

    public void setType(ACTIVITY_TYPE type) {
        this.type = type;
    }
}

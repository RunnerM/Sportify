package net.mpentek.sportify.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;


public class Workout {
    private String name;
    private ACTIVITY_TYPE type;
    private Time time;
    private Date date;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

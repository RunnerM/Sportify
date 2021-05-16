package net.mpentek.sportify.model;

public class MovingWorkoutElement extends WorkoutElement{
    private double distance;
    public MovingWorkoutElement(String description) {
        super(description);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public MovingWorkoutElement() {
        super();
    }
}

package net.mpentek.sportify.model;

public class LiftingWorkoutElement extends WorkoutElement{
    private int rep;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public LiftingWorkoutElement(String description) {
        super(description);
    }

    public LiftingWorkoutElement() {
        super();
    }
}

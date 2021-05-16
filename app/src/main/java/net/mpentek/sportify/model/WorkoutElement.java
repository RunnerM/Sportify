package net.mpentek.sportify.model;

public abstract class WorkoutElement {
    private String description;

    public WorkoutElement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WorkoutElement() {
    }
}

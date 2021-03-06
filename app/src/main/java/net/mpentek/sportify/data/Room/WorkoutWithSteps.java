package net.mpentek.sportify.data.Room;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

import java.io.Serializable;
import java.util.List;

public class WorkoutWithSteps implements Serializable {
    @Embedded
    public Workout workout;
    @Relation(
            parentColumn = "id",
            entityColumn = "workoutId"
    )
    public List<WorkoutElement> step;

}

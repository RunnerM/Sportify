package net.mpentek.sportify.data.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

import java.util.List;

@Dao
public interface WorkoutDAO {
    @Insert
    void addWorkout(Workout workoutE);

    @Query("DELETE FROM workout WHERE id=:id")
    void removeWorkout(int id);

    @Query("SELECT * FROM workout")
    List<Workout> getAllWorkouts();
}
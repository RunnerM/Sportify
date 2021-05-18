package net.mpentek.sportify.data.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

@Dao
public interface WorkoutElementDAO {
    @Insert
    void addWorkoutElement(WorkoutElement workoutElement);

    @Query("DELETE FROM WorkoutElement WHERE id=:id")
    void removeWorkoutElement(int id);


}

package net.mpentek.sportify.data.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import net.mpentek.sportify.model.Workout;

import java.util.List;

@Dao
public interface WorkoutWithStepsDAO {
    @Transaction
    @Query("SELECT * FROM workout")
    LiveData<List<WorkoutWithSteps>> getAllWorkoutsWithSteps();


}

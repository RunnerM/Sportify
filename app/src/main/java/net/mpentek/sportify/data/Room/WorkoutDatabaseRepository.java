package net.mpentek.sportify.data.Room;

import android.app.Application;
import androidx.lifecycle.LiveData;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutDatabaseRepository {
    private static WorkoutDatabaseRepository instance;

    private WorkoutDAO workoutDAO;
    private WorkoutElementDAO workoutElementDAO;
    private WorkoutWithStepsDAO workoutWithStepsDAO;
    private LiveData<List<Workout>> allWorkouts;
    private ExecutorService executorService;

    private WorkoutDatabaseRepository(Application application) {
        WorkoutDatabase database = WorkoutDatabase.getInstance(application);
        workoutDAO = database.workoutDAO();
        workoutElementDAO = database.WorkoutElementDAO();
        workoutWithStepsDAO = database.workoutWithStepsDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized WorkoutDatabaseRepository getInstance(Application application) {
        if (instance == null) {
            instance = new WorkoutDatabaseRepository(application);
        }
        return instance;
    }
    public LiveData<List<WorkoutWithSteps>> getAllWorkoutsWithSteps(){
        return workoutWithStepsDAO.getAllWorkoutsWithSteps();
    }
    public void addWorkout(WorkoutWithSteps workout){
        executorService.execute(()-> {
            int id= workoutDAO.getAllWorkouts().size();
            workout.workout.setId(id);
            workoutDAO.addWorkout(workout.workout);
            for(WorkoutElement e:workout.step){
                e.setWorkoutId(id);
                workoutElementDAO.addWorkoutElement(e);
            }
        });
    }
    public void updateWorkout(WorkoutWithSteps workout){
        executorService.execute(()-> {
            workoutDAO.updateWorkout(workout.workout);
        });

    }


}

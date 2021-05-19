package net.mpentek.sportify.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import net.mpentek.sportify.data.Firebase.Auth.FireUserRepository;
import net.mpentek.sportify.data.Room.WorkoutDatabaseRepository;
import net.mpentek.sportify.data.Room.WorkoutWithSteps;
import net.mpentek.sportify.model.ACTIVITY_TYPE;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final FireUserRepository fireUserRepository;
    private final WorkoutDatabaseRepository workoutRepository;
    private int workoutSelected;

    public MainViewModel(Application app) {
        super(app);
        fireUserRepository = FireUserRepository.getInstance(app);
        workoutRepository= WorkoutDatabaseRepository.getInstance(app);
    }


    public LiveData<FirebaseUser> getCurrentUser() {
        return fireUserRepository.getCurrentUser();
    }

    public void signOut() {
        fireUserRepository.signOut();
    }

    public LiveData<List<WorkoutWithSteps>> getWorkouts() {
        return workoutRepository.getAllWorkoutsWithSteps();
    }

    public void setSelectedWorkout(int selectedWorkout) {
        workoutSelected= selectedWorkout;
    }

    public int getSelectedWorkout() {
        return workoutSelected;
    }
}

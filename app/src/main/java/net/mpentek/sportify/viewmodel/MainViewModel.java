package net.mpentek.sportify.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import net.mpentek.sportify.data.FireUserRepository;
import net.mpentek.sportify.data.WorkoutList;
import net.mpentek.sportify.data.WorkoutRepository;
import net.mpentek.sportify.model.ACTIVITY_TYPE;
import net.mpentek.sportify.model.MovingWorkoutElement;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class MainViewModel extends AndroidViewModel {
    private final FireUserRepository fireUserRepository;
    private final WorkoutRepository workoutRepository;
    private int workoutSelected;

    public MainViewModel(Application app) {
        super(app);
        fireUserRepository = FireUserRepository.getInstance(app);
        workoutRepository= WorkoutRepository.getInstance();
    }

    public void init(FirebaseUser user) {
        workoutRepository.init(user.getUid());
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return fireUserRepository.getCurrentUser();
    }

    public void signOut() {
        fireUserRepository.signOut();
    }

    public ArrayList<Workout> getWorkouts() {
        Workout w = new Workout();
        w.setName("Run");
        ArrayList<Workout> l = new ArrayList<>();
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        l.add(w);
        return l;
    }

    public void setSelectedWorkout(int selectedWorkout) {
        workoutSelected= selectedWorkout;
    }

    public int getSelectedWorkout() {
        return workoutSelected;
    }
    public void saveWorkout(Workout workout){
        Workout dummi= new Workout();
        dummi.setName("name");
        dummi.setType(ACTIVITY_TYPE.MOVING);
        dummi.setTime(new Time(12,30,00));
        dummi.setDate(new Date(2021,5,18));
        ArrayList<WorkoutElement> steps= new ArrayList<>();
        steps.add(new MovingWorkoutElement("description"));
        dummi.setSteps(steps);
        WorkoutList list= new WorkoutList();
        list.getWorkouts().add(dummi);

        workoutRepository.saveWorkout(list);
    }
}

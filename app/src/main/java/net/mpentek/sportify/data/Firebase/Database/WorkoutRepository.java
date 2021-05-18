package net.mpentek.sportify.data.Firebase.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import net.mpentek.sportify.model.Workout;

import java.util.ArrayList;

public class WorkoutRepository {
    private static WorkoutRepository instance;
    private DatabaseReference myRef;
    private WorkoutLiveData workout;

    private WorkoutRepository(){}

    public static synchronized WorkoutRepository getInstance() {
        if(instance == null)
            instance = new WorkoutRepository();
        return instance;
    }

    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance("https://sportify-fb8b2-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("users").child(userId);
        workout = new WorkoutLiveData(myRef);
    }

    public void saveWorkout(WorkoutList workoutList) {
        ArrayList<Workout> cache = workoutList.getWorkouts();
        myRef.setValue(workoutList);
    }

    public WorkoutLiveData getWorkout() {
        return workout;
    }

}

package net.mpentek.sportify.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import net.mpentek.sportify.model.Workout;

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
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        workout = new WorkoutLiveData(myRef);
    }

    public void saveMessage(Workout workout) {
        myRef.setValue(new Workout(workout));
    }

    public WorkoutLiveData getWorkout() {
        return workout;
    }
}

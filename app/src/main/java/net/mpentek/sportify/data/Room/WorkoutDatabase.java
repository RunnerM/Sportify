package net.mpentek.sportify.data.Room;

import android.content.Context;
import androidx.room.*;
import net.mpentek.sportify.model.Workout;
import net.mpentek.sportify.model.WorkoutElement;

import java.sql.Date;

@Database(entities = {Workout.class,WorkoutElement.class}, version = 7)
@TypeConverters({WorkoutDatabase.Converters.class})
public abstract class WorkoutDatabase extends RoomDatabase {

    private static WorkoutDatabase instance;
    public abstract WorkoutWithStepsDAO workoutWithStepsDAO();
    public abstract WorkoutDAO workoutDAO();
    public abstract WorkoutElementDAO WorkoutElementDAO();

    public static synchronized WorkoutDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WorkoutDatabase.class, "workout_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

     static class Converters {
        @TypeConverter
        public static Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public static Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }
    }
}

package net.mpentek.sportify.data.Firebase.Auth;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

public class FireUserRepository {
    private final FireUserLiveData currentUser;
    private final Application app;
    private static FireUserRepository instance;

    private FireUserRepository(Application app) {
        this.app = app;
        currentUser = new FireUserLiveData();
    }

    public static synchronized FireUserRepository getInstance(Application app) {
        if(instance == null)
            instance = new FireUserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
            AuthUI.getInstance().signOut(app);

    }
}
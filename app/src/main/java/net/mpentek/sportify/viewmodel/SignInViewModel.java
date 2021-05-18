package net.mpentek.sportify.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import net.mpentek.sportify.data.Firebase.Auth.FireUserRepository;

public class SignInViewModel extends AndroidViewModel {
    private final FireUserRepository fireUserRepository;

    public SignInViewModel(Application app){
        super(app);
        fireUserRepository = FireUserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return fireUserRepository.getCurrentUser();
    }
}


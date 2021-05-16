package net.mpentek.sportify.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import net.mpentek.sportify.data.FireUserRepository;

public class MainViewModel extends AndroidViewModel {
    private final FireUserRepository fireUserRepository;

    public MainViewModel(Application app){
        super(app);
        fireUserRepository = FireUserRepository.getInstance(app);
    }

    public void init() {
       // String userId = fireUserRepository.getCurrentUser().getValue().getUid();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return fireUserRepository.getCurrentUser();
    }

    public void signOut() {
        fireUserRepository.signOut();
    }

}

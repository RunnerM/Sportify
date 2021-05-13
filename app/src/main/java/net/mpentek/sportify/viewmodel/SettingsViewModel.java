package net.mpentek.sportify.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import net.mpentek.sportify.data.SharedPrefsRepository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewModel extends AndroidViewModel {
    private MutableLiveData<List<String>> data;
    private SharedPrefsRepository repository;
    private Context context = getApplication().getBaseContext();

    public SettingsViewModel(@NonNull @NotNull Application application) {
        super(application);
        repository = SharedPrefsRepository.getInstance(context);
    }

    public LiveData<List<String>> getData() {
        if (data == null) {
            data = new MutableLiveData<List<String>>();
            loadPrefs();
        }
        return data;
    }
    private void loadPrefs() {
        List<String> cache = new ArrayList<>();
        cache.add(repository.getPreference("distance","km"));
        cache.add(repository.getPreference("weight","kg"));
        data.setValue(cache);
    }

    public void savePref(String key, String value){
        repository.savePreferences(key,value);
        loadPrefs();
    }



}

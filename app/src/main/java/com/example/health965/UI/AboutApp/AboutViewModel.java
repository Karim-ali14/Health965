package com.example.health965.UI.AboutApp;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.Setting.Setting;

public class AboutViewModel extends ViewModel {
    AboutRepository aRepository;
    public AboutViewModel() {
        this.aRepository = AboutRepository.getInstance();
    }

    public LiveData<Setting> getSettings(Context context){
        return aRepository.getDataOfSettings(context);
    }
}

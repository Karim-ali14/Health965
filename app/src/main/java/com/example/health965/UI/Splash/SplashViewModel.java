package com.example.health965.UI.Splash;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.LoginClient.LoginClient;
import com.example.health965.Models.LoginClinc.LoginClinc;

public class SplashViewModel extends ViewModel {
    SplashRepository sRepository;

    public SplashViewModel() {
        this.sRepository = SplashRepository.getInstance();
    }

    public LiveData<LoginClient> getDataOfClient(String Token, String ID, final Context context){
        return sRepository.getDataOfClient(Token, ID, context);
    }

    public LiveData<LoginClinc> getDataOfClinic(String Token, String ID,
                                                final Context context){
        return sRepository.getDataOfClinic(Token, ID, context);
    }
}

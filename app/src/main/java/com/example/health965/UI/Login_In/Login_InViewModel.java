package com.example.health965.UI.Login_In;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.FireBaseToken.FireBaseToken;
import com.example.health965.Models.FireBaseToken.FireBaseTokenRespons;
import com.example.health965.Models.LoginClient.LoginClient;
import com.example.health965.Models.LoginClinc.LoginClinc;

public class Login_InViewModel extends ViewModel {
    Login_InRepository lIRepository;
    public Login_InViewModel() {
        this.lIRepository = Login_InRepository.getInstance();
    }

    //TODO Login As Client
    public LiveData<LoginClient> onLoginAsClient(String Phone, String Password,
                                                 final ProgressDialog dialog,
                                                 final Context context){
        return lIRepository.loginAsClient(Phone,Password,dialog,context);
    }

    //TODO Update FireBase Token For Client
    public LiveData<FireBaseTokenRespons> onUpDateFireBaseTokenClient(String accessToken,
                                                                      String client_id,
                                                                      FireBaseToken fireBaseToken,
                                                                      final Context context){
        return lIRepository.onUpDateFireBaseTokenClient(accessToken,client_id,fireBaseToken,context);
    }

    //TODO Login As Clinic
    public LiveData<LoginClinc> onLoginAsClinic(String Phone, String Password,
                                                final ProgressDialog dialog,
                                                final Context context){
        return lIRepository.loginAsClinic(Phone,Password,dialog,context);
    }

    //TODO Update FireBase Token For Client
    public LiveData<FireBaseTokenRespons> onUpDateFireBaseTokenClinic(String accessToken,
                                                                      String clinic_id,
                                                                      FireBaseToken fireBaseToken,
                                                                      final Context context){
        return lIRepository.onUpDateFireBaseTokenClinic(accessToken,clinic_id,fireBaseToken,context);
    }
}

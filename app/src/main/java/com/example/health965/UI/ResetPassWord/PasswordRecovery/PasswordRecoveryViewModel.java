package com.example.health965.UI.ResetPassWord.PasswordRecovery;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.ReSetPassword;

public class PasswordRecoveryViewModel extends ViewModel {
    PasswordRecoveryRepository pRRepository;

    public PasswordRecoveryViewModel() {
        this.pRRepository = PasswordRecoveryRepository.getInstance();
    }

    public LiveData<ReSetPassword> onVerficationClient(String Email, String Code,
                                                       String NewPassword ,
                                                       final ProgressDialog dialog,
                                                       final Context context){
        return pRRepository.onVerficationClient(Email,Code,NewPassword,dialog,context);
    }

    public LiveData<ReSetPassword> onVerficationClinic(String Email, String Code,
                                                       String NewPassword ,
                                                       final ProgressDialog dialog,
                                                       final Context context){
        return pRRepository.onVerficationClinic(Email,Code,NewPassword,dialog,context);
    }
}

package com.example.health965.UI.ResetPassWord.ForgotPassword;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.ReSetPassword;

public class ForgotPasswordViewModel extends ViewModel {
    ForgotPasswordRepository fPRepository;

    public ForgotPasswordViewModel() {
        this.fPRepository = ForgotPasswordRepository.getInstance();
    }

    public LiveData<ReSetPassword> onSendCodeVerficationClient(String Email,
                                                               final ProgressDialog dialog,
                                                               final Context context){
        return fPRepository.onSendCodeVerficationClient(Email,dialog,context);
    }

    public LiveData<ReSetPassword> onSendCodeVerficationClinic(String Email,
                                                                      final ProgressDialog dialog,
                                                                      final Context context){
        return fPRepository.onSendCodeVerficationClinic(Email,dialog,context);
    }
}

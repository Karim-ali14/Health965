package com.example.health965.UI.Registration.ActivateYourAccount;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class ActivateYourAccountViewModel extends ViewModel {
    ActivateYourAccountRepository aYARepository;

    public ActivateYourAccountViewModel() {
        this.aYARepository = new ActivateYourAccountRepository();
    }

    public void onSendVerificationMessage(String Phone, Context context, ProgressDialog dialog){
        aYARepository.sendVerificationMessage(Phone,context,dialog);
    }

    public void onActive(String code, FirebaseAuth mAuth, final Context context,String Phone){
        aYARepository.active(code,mAuth,context,Phone);
    }
}

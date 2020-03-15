package com.example.health965.UI.Registration.Registration;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.Regestration.DataUserRegistration;
import com.example.health965.Models.Regestration.Registration;

public class RegistrationViewModel extends ViewModel {
    RegistrationRepository nARepository;

    public RegistrationViewModel() {
        this.nARepository = RegistrationRepository.getInstance();
    }

    public LiveData<Registration> onRegistration(DataUserRegistration dataUser, ProgressDialog dialog, Context context){
        return nARepository.onRegistration(dataUser,dialog,context);
    }
}

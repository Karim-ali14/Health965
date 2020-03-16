package com.example.health965.UI.PasswordModificationActivity;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.ChangePassword.ChangePassword;
import com.example.health965.Models.ChangePassword.ResponseChangePassword.ResponseChangePassword;

public class PasswordModificationViewModel extends ViewModel {
    PasswordModificationRepository pMRepository;

    public PasswordModificationViewModel() {
        this.pMRepository = PasswordModificationRepository.getInstance();
    }

    public LiveData<ResponseChangePassword> onChangeClinicPass(
            String accessToken, String clinicID, ChangePassword changePassword,
            final Context context, final ProgressDialog dialog){
        return pMRepository.onChangeClinicPass(accessToken,clinicID,changePassword,context,dialog);
    }

    public LiveData<ResponseChangePassword> onChangeClientPass(
            String accessToken, String clientID, ChangePassword changePassword,
            final Context context, final ProgressDialog dialog){
        return pMRepository.onChangeClientPass(accessToken,clientID,changePassword,context,dialog);
    }
}

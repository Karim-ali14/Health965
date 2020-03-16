package com.example.health965.UI.ModifyPersonalInformation;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.UpdateClientInfo.RequestUpdateClientInfo;
import com.example.health965.Models.UpdateClientInfo.ResponseUpdateClientInfo.ResponseUpdateClientInfo;

public class ModifyPersonalInformationViewModel extends ViewModel {
    ModifyPersonalInformationRepository mPIRepository;

    public ModifyPersonalInformationViewModel() {
        this.mPIRepository = ModifyPersonalInformationRepository.getInstance();
    }

    public LiveData<ResponseUpdateClientInfo> onUpdateClientInfo(
            RequestUpdateClientInfo model, final Context context){
        return mPIRepository.onUpdateClientInfo(model,context);
    }
}

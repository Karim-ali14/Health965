package com.example.health965.UI.AreaDetails;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;

public class AreaDetailsViewModel extends ViewModel {
    AreaDetailsRepository aDRepository;

    public AreaDetailsViewModel() {
        this.aDRepository = AreaDetailsRepository.getInstance();
    }

    public LiveData<Clinics> getClinicsByGovernorate(final Context context, final ProgressDialog dialog,String C_ID,String G_ID){
        return aDRepository.getDataClinicsByGovernorate(context,dialog,C_ID,G_ID);
    }

    public LiveData<BannerForCategory> getBannerForGovernorate(Context context,String G_ID,ProgressDialog dialog){
        return aDRepository.getDataBannerForGovernorate(context,G_ID,dialog);
    }
}

package com.example.health965.UI.Clinics;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Options.Option;

public class Clinics_ViewModel extends ViewModel {
    Clinics_Repository cRepository;

    public Clinics_ViewModel() {
        this.cRepository = Clinics_Repository.getInstance();
    }

    public LiveData<Clinics> getAllClinics(String id, final Context context){
        return cRepository.getAllDataClinics(id,context);
    }
    public LiveData<BannerForCategory> getBannerForCategory(String id, final Context context){
        return cRepository.getDataBannerForCategory(id,context);
    }

    public LiveData<Option> getDataOfOption(final Context context){
        return cRepository.getDataOfOption(context);
    }
}

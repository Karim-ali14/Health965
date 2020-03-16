package com.example.health965.UI.Clinics_Details;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.OfferForClinic.OfferForClinic;

public class Clinics_DetailsViewModel extends ViewModel {
    Clinics_DetailsRepository cDRepository;

    public Clinics_DetailsViewModel() {
        this.cDRepository = Clinics_DetailsRepository.getInstance();
    }

    public LiveData<BannerForCategory> getBannerForClinic(String ClinicId){
        return cDRepository.getDataBannerForClinic(ClinicId);
    }

    public LiveData<OfferForClinic> getOffersForClinic(String ClinicId ,
                                                       Context context){
        return cDRepository.getDataOffersForClinic(ClinicId,context);
    }
}

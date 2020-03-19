package com.example.health965.UI.Clinics_Details;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Clinics.Row;
import com.example.health965.Models.Doctors.Doctors;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.Models.Options.Option;
import com.example.health965.Models.Reservation.Reservation;

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

    // TODO All of Details Fragment

        public LiveData<Doctors> getListDoctor(final Context context,
                                                      Row clinic, final ProgressDialog dialog){
            return cDRepository.getListDoctor(context,clinic,dialog);
        }

        public LiveData<Option> getDataOptions(final Context context){
            return cDRepository.getDataOptions(context);
        }

        public LiveData<Reservation> makeReservation (Row clinic, int Doctor_Id,
                                                                      final ProgressDialog dialog,
                                                                      final Context context){
            return cDRepository.makeReservation(clinic,Doctor_Id,dialog,context);
        }

        public LiveData<Clinics> getDataOfClinic(String Clinic_ID){
            return cDRepository.getDataOfClinic(Clinic_ID);
        }
}

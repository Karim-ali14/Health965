package com.example.health965.UI.Doctor_Page;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.DoctorsWithClinics.DoctorsWithClinics;

public class Doctor_Page_ViewModel extends ViewModel {
    Doctor_Page_Repository dPRepository;

    public Doctor_Page_ViewModel() {
        this.dPRepository = Doctor_Page_Repository.getInstance();
    }

    public LiveData<DoctorsWithClinics> getDoctorsWithClinics(Context context,
                                                              String categoryId, ProgressDialog dialog){
        return dPRepository.getDataDoctorsWithClinics(context,categoryId,dialog);
    }

}

package com.example.health965.UI.Governorate;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.Governorate.Governorate;

public class GovernorateViewModel extends ViewModel {
    GovernorateRepository gRepository;

    public GovernorateViewModel() {
        this.gRepository = new GovernorateRepository();
    }

    public LiveData<Governorate> getGovernorate(Context c){
        return gRepository.getDataOfGovernorate(c);
    }
}

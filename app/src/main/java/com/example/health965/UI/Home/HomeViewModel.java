package com.example.health965.UI.Home;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.Offers.Offers;

public class HomeViewModel extends ViewModel {
    HomeRepository hRepository;

    public HomeViewModel() {
        this.hRepository = HomeRepository.getInstance();
    }


    public LiveData<Offers> getOffers(ProgressDialog dialog, Context context){
        return hRepository.getDataOfOffers(dialog,context);
    }
}

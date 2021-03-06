package com.example.health965.UI.Main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Category.Category;
import com.example.health965.Models.ClientReservation.ClientReservation;
import com.example.health965.Models.Notification.Notifications;
import com.example.health965.Models.OfferForClinic.OfferForClinic;

public class MainViewModel extends ViewModel {
    MainRepository mRepository;

    public MainViewModel() {
        this.mRepository = MainRepository.getInstance();
    }

    public LiveData<Category> getCategories(Context context,ProgressDialog dialog){
        return mRepository.getDataOfCategories(context,dialog);
    }

    public LiveData<BannerForCategory> getBanners(Context context, ProgressDialog dialog){
        return mRepository.getDataOfBanners(context,dialog);
    }

    public LiveData<Notifications> getNotification(SharedPreferences preferences){
        return mRepository.getDataOfNotification(preferences);
    }

    public LiveData<OfferForClinic> getOffer(ProgressDialog dialog){
        return mRepository.getDataOffer(dialog);
    }
    public LiveData<OfferForClinic> getOfferByCategory(ProgressDialog dialog,String category_id){
        return mRepository.getDataOfferByCategory(dialog,category_id);
    }
    public LiveData<ClientReservation> getAllClientReservation(final Context context){
        return mRepository.getAllClientReservation(context);
    }
}

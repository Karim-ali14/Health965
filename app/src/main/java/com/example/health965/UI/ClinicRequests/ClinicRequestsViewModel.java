package com.example.health965.UI.ClinicRequests;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.Notification.Notifications;
import com.example.health965.Models.Reservation.Reservation;

public class ClinicRequestsViewModel extends ViewModel {
    ClinicRequestsRepository cRRepository;

    public ClinicRequestsViewModel() {
        this.cRRepository = ClinicRequestsRepository.getInstance();
    }

    public LiveData<Reservation> getDataReservation(final Context context, String accessToken,
                                                    String clinicID){
        return cRRepository.getDataReservation(context,accessToken,clinicID);
    }

    public LiveData<Notifications> getNotification(String accessToken){
        return cRRepository.getDataOfNotification(accessToken);
    }
}

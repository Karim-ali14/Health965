package com.example.health965.UI.Client_reservation;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.health965.Models.ClientReservation.ClientReservation;

public class Client_ReservationViewModels extends ViewModel {
    Client_ReservationRepository cRRepository;

    public Client_ReservationViewModels() {
        this.cRRepository = Client_ReservationRepository.getInstance();
    }

    public LiveData<ClientReservation> getAllClientReservation(final Context context,
                                                               ProgressDialog dialog){
        return cRRepository.getAllClientReservation(context,dialog);
    }
}

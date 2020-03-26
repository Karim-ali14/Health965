package com.example.health965.UI.Client_reservation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.ClientReservation.ClientReservation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Client_ReservationRepository {
    public static Client_ReservationRepository cRRepository;
    public static Client_ReservationRepository getInstance(){
        if (cRRepository == null)
            cRRepository = new Client_ReservationRepository();
        return  cRRepository;
    }

    //TODO get Data Of Reservation for Reservation Fragment
    public MutableLiveData<ClientReservation> getAllClientReservation(final Context context,
                                                                      final ProgressDialog dialog,
                                                                      SharedPreferences preferences){
        final MutableLiveData<ClientReservation> mutableLiveData = new MutableLiveData<>();
        if (Common.CurrentUser != null) {
            Common.getAPIRequest().getAllClientReservation(
                    preferences.getString(Common.Token, ""),
                    preferences.getString(Common.ID, "")).enqueue(new Callback<ClientReservation>() {
                @Override
                public void onResponse(Call<ClientReservation> call, Response<ClientReservation> response) {
                    dialog.dismiss();
                    if (response.code() == 200)
                        mutableLiveData.setValue(response.body());
                    else {
                        try {
                            Toast.makeText(context, new JSONObject(
                                            response.errorBody().string()).getString("message"),
                                    Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ClientReservation> call, Throwable t) {

                }
            });
        }else {
            dialog.dismiss();
        }
        return mutableLiveData;
    }
}

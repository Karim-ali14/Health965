package com.example.health965.UI.ClinicRequests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Adapters.AdapterForClinicRequests;
import com.example.health965.Common.Common;
import com.example.health965.Models.Notification.Notifications;
import com.example.health965.Models.Reservation.Reservation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicRequestsRepository {
    public static ClinicRequestsRepository cRRepository;
    public static ClinicRequestsRepository getInstance(){
        if (cRRepository == null)
            cRRepository = new ClinicRequestsRepository();
        return cRRepository;
    }

    public MutableLiveData<Reservation> getDataReservation(final Context context,String accessToken,
                                                           String clinicID){
        final MutableLiveData<Reservation> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getAllClinicReservation(accessToken,clinicID).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response.code() == 200)
                    mutableLiveData.setValue(response.body());
                else {
                    try {
                        Toast.makeText(context,
                                new JSONObject(
                                        response.errorBody().string()
                                ).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    //TODO get Data Of Notifications for Notify Fragment
    public MutableLiveData<Notifications> getDataOfNotification(String accessToken){
        final MutableLiveData<Notifications> notificationData = new MutableLiveData<>();
        Common.getAPIRequest().getNotification(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Notifications>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Notifications notification) {
                        notificationData.setValue(notification);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return notificationData;
    }
}

package com.example.health965.UI.Doctor_Page;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Adapters.AdapterForDoctorCard;
import com.example.health965.Common.Common;
import com.example.health965.Models.DoctorsWithClinics.DoctorsWithClinics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_Page_Repository {
    public static Doctor_Page_Repository dPRepository;
    public static Doctor_Page_Repository getInstance(){
        if (dPRepository == null)
            dPRepository = new Doctor_Page_Repository();
        return dPRepository;
    }

    public MutableLiveData<DoctorsWithClinics> getDataDoctorsWithClinics(final Context context
            , String categoryId, final ProgressDialog dialog){
        final MutableLiveData<DoctorsWithClinics> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getDoctorsWithClinics("image",categoryId)
                .enqueue(new Callback<DoctorsWithClinics>() {
            @Override
            public void onResponse(Call<DoctorsWithClinics> call, Response<DoctorsWithClinics> response) {
                if (response.code() == 200)
                    mutableLiveData.setValue(response.body());
                else {
                    dialog.dismiss();
                    try {
                        Toast.makeText(context,new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorsWithClinics> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}

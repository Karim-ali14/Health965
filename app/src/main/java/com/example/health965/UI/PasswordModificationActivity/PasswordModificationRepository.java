package com.example.health965.UI.PasswordModificationActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.ChangePassword.ChangePassword;
import com.example.health965.Models.ChangePassword.ResponseChangePassword.ResponseChangePassword;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordModificationRepository {
    public static PasswordModificationRepository pMRepository;
    public static PasswordModificationRepository getInstance(){
        if (pMRepository == null)
            pMRepository = new PasswordModificationRepository();
        return pMRepository;
    }

    public MutableLiveData<ResponseChangePassword> onChangeClinicPass(
            String accessToken, String clinicID, ChangePassword changePassword,
            final Context context, final ProgressDialog dialog){
        final MutableLiveData<ResponseChangePassword> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onChangeClinicPass(accessToken,"application/json",clinicID,
                changePassword).enqueue(new Callback<ResponseChangePassword>() {
            @Override
            public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                dialog.dismiss();
                if (response.code() == 200)
                    mutableLiveData.setValue(response.body());
                else {
                    try {
                        Toast.makeText(context,new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseChangePassword> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<ResponseChangePassword> onChangeClientPass(
            String accessToken, String clientID, ChangePassword changePassword,
            final Context context, final ProgressDialog dialog){
        final MutableLiveData<ResponseChangePassword> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onChangeClientPass(accessToken,"application/json",clientID,
                changePassword).enqueue(new Callback<ResponseChangePassword>() {
            @Override
            public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                dialog.dismiss();
                if (response.code() == 200)
                    mutableLiveData.setValue(response.body());
                else {
                    try {
                        Toast.makeText(context,new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseChangePassword> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}

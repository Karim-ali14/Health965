package com.example.health965.UI.ResetPassWord.PasswordRecovery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.ReSetPassword;
import com.example.health965.UI.Login_In.Login_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordRecoveryRepository {
    public static PasswordRecoveryRepository pRRepository;
    public static PasswordRecoveryRepository getInstance(){
        if (pRRepository == null)
            pRRepository = new PasswordRecoveryRepository();
        return pRRepository;
    }

    public MutableLiveData<ReSetPassword> onVerficationClient(String Email, String Code,
                                                              String NewPassword ,
                                                              final ProgressDialog dialog,
                                                              final Context context){
        final MutableLiveData<ReSetPassword> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onVerficationClient(Email,Code,NewPassword)
                .enqueue(new Callback<ReSetPassword>() {
            @Override
            public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                dialog.dismiss();
                if (response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                } else {
                    try {
                        Toast.makeText(context
                                , new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReSetPassword> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<ReSetPassword> onVerficationClinic(String Email, String Code,
                                                              String NewPassword ,
                                                              final ProgressDialog dialog,
                                                              final Context context){
        final MutableLiveData<ReSetPassword> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onVerficationClinic(Email,Code,NewPassword)
                .enqueue(new Callback<ReSetPassword>() {
            @Override
            public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                dialog.dismiss();
                if (response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                } else {
                    try {
                        Toast.makeText(context
                                , new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReSetPassword> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return mutableLiveData;
    }
}

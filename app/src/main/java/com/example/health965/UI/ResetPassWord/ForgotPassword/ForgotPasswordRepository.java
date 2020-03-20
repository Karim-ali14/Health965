package com.example.health965.UI.ResetPassWord.ForgotPassword;

import android.app.ProgressDialog;
import android.content.Context;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.ReSetPassword;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordRepository {
    public static ForgotPasswordRepository fPRepository;
    public static ForgotPasswordRepository getInstance(){
        if (fPRepository == null)
            fPRepository = new ForgotPasswordRepository();
        return fPRepository;
    }

    public MutableLiveData<ReSetPassword> onSendCodeVerficationClient(String Email,
                                                                      final ProgressDialog dialog,
                                                                      final Context context){
        final MutableLiveData<ReSetPassword> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onSendCodeVerficationClient(Email)
                .enqueue(new Callback<ReSetPassword>() {
                    @Override
                    public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                        dialog.dismiss();
                        if (response.code() == 200) {
                            mutableLiveData.setValue(response.body());
                        } else {
                            try {
                                Toast.makeText(context,
                                        new JSONObject(
                                                response.errorBody().string())
                                                .getString("message"),
                                        Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
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

    public MutableLiveData<ReSetPassword> onSendCodeVerficationClinic(String Email,
                                                                      final ProgressDialog dialog,
                                                                      final Context context){
        final MutableLiveData<ReSetPassword> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onSendCodeVerficationClinic(Email)
                .enqueue(new Callback<ReSetPassword>() {
            @Override
            public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                dialog.dismiss();
                if (response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                } else {
                    try {
                        Toast.makeText(context, new JSONObject(
                                response.errorBody().string()).getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReSetPassword> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return mutableLiveData;
    }
}

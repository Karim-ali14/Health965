package com.example.health965.UI.Login_In;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.FireBaseToken.FireBaseToken;
import com.example.health965.Models.FireBaseToken.FireBaseTokenRespons;
import com.example.health965.Models.LoginClient.LoginClient;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.UI.ClinicRequests.ClinicRequestsActivity;
import com.example.health965.UI.Main.MainActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_InRepository {
    public static Login_InRepository lIRepository;
    public static Login_InRepository getInstance(){
        if (lIRepository == null)
            lIRepository = new Login_InRepository();
        return lIRepository;
    }

    //TODO Login As Client
    public MutableLiveData<LoginClient> loginAsClient(String Phone, String Password,
                                                      final ProgressDialog dialog,
                                                      final Context context){
        final MutableLiveData<LoginClient> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onLoginAsClient(Phone,
                Password).enqueue(new Callback<LoginClient>() {
            @Override
            public void onResponse(Call<LoginClient> call, Response<LoginClient> response) {
                if (response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
                else{
                    dialog.dismiss();
                    try {
                        Toast.makeText(context,new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginClient> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    //TODO Update FireBase Token For Client
    public MutableLiveData<FireBaseTokenRespons> onUpDateFireBaseTokenClient(String accessToken,
                                                                              String client_id,
                                                                              FireBaseToken fireBaseToken,
                                                                              final Context context){
        final MutableLiveData<FireBaseTokenRespons> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onUpDateFireBaseTokenClient(accessToken,"application/json",
                client_id,fireBaseToken)
                .enqueue(new Callback<FireBaseTokenRespons>() {
                    @Override
                    public void onResponse(Call<FireBaseTokenRespons> call, Response<FireBaseTokenRespons> response) {
                        if (response.code() == 200){
                            mutableLiveData.setValue(response.body());
                        }else {
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
                    public void onFailure(Call<FireBaseTokenRespons> call, Throwable t) {

                    }
                });
        return mutableLiveData;
    }

    //TODO Login As Clinic
    public MutableLiveData<LoginClinc> loginAsClinic(String Phone, String Password,
                                                     final ProgressDialog dialog,
                                                     final Context context){
        final MutableLiveData<LoginClinc> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onLoginAsPartner(Phone,
                Password).enqueue(new Callback<LoginClinc>() {
            @Override
            public void onResponse(Call<LoginClinc> call, Response<LoginClinc> response) {
                if (response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
                else {
                    try {
                        Toast.makeText(context,new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginClinc> call, Throwable t) {

            }
        });
       return mutableLiveData;
    }
    //TODO Update FireBase Token For Client
    public MutableLiveData<FireBaseTokenRespons> onUpDateFireBaseTokenClinic(String accessToken,
                                                                              String clinic_id,
                                                                              FireBaseToken fireBaseToken,
                                                                              final Context context){
        final MutableLiveData<FireBaseTokenRespons> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().onUpDateFireBaseTokenClinic(accessToken,
                clinic_id,fireBaseToken)
                .enqueue(new Callback<FireBaseTokenRespons>() {
                    @Override
                    public void onResponse(Call<FireBaseTokenRespons> call, Response<FireBaseTokenRespons> response) {
                        if (response.code() == 200){
                            mutableLiveData.setValue(response.body());
                        }else {
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
                    public void onFailure(Call<FireBaseTokenRespons> call, Throwable t) {

                    }
                });
        return mutableLiveData;
    }
}

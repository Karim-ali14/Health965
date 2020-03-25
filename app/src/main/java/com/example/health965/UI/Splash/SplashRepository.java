package com.example.health965.UI.Splash;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.health965.Common.Common;
import com.example.health965.Models.LoginClient.LoginClient;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.UI.Home.Home_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashRepository {
    public static SplashRepository sRepository;
    public static SplashRepository getInstance(){
        if (sRepository == null)
            sRepository = new SplashRepository();
        return sRepository;
    }

    public MutableLiveData<LoginClient> getDataOfClient(String Token, String ID,
                                                        final Context context)
    {
        final MutableLiveData<LoginClient> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getDataOfClient(
                Token,ID).enqueue(new Callback<LoginClient>() {
            @Override
            public void onResponse(Call<LoginClient> call, Response<LoginClient> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }else {
                    try {
                        Log.i("HHHHHHH",response.code()+
                                new JSONObject(response.errorBody()
                                        .string()).getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                context.startActivity(new Intent(context, Home_Activity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            }catch (Exception e){
                                Log.d("TTTTTTT",e.getMessage());
                            }
                        }
                    }.start();
                }
            }

            @Override
            public void onFailure(Call<LoginClient> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<LoginClinc> getDataOfClinic(String Token, String ID,
                                                       final Context context){
        final MutableLiveData<LoginClinc> mutableLiveData = new MutableLiveData<>();
        Common.getAPIRequest().getDataOfClinic(Token,ID).enqueue(new Callback<LoginClinc>() {
            @Override
            public void onResponse(Call<LoginClinc> call, Response<LoginClinc> response) {
                if (response.code() == 200)
                    mutableLiveData.setValue(response.body());
                else {
                    try {
                        Log.i("HHHHHHH",response.code()+
                                new JSONObject(response.errorBody()
                                        .string()).getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                context.startActivity(new Intent(context, Home_Activity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            }catch (Exception e){
                                Log.d("TTTTTTT",e.getMessage());
                            }
                        }
                    }.start();
                }
            }

            @Override
            public void onFailure(Call<LoginClinc> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}

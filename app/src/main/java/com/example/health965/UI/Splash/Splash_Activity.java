package com.example.health965.UI.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.auth0.android.jwt.JWT;
import com.example.health965.Common.Common;
import com.example.health965.Models.LoginClient.LoginClient;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.R;
import com.example.health965.UI.ClinicRequests.ClinicRequestsActivity;
import com.example.health965.UI.Home.Home_Activity;
import com.example.health965.UI.Login_In.Login_Activity;
import com.example.health965.UI.Main.MainActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash_Activity extends AppCompatActivity {
    final static String TAG = "Splash_Activity";
    SplashViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        viewModel = ViewModelProviders.of(this).get(SplashViewModel.class);

        final SharedPreferences preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);

        if (preferences.contains(Common.Token)&&preferences.contains(Common.ID)&&preferences.contains(Common.Type)){
            if (preferences.getInt(Common.Type,0) == 0) {
                if (checkToken(preferences.getString(Common.Token, ""))) {
                    Log.i("TTTTTTT", "Token is not EX");
                    viewModel.getDataOfClient(preferences.getString(Common.Token, ""),
                            preferences.getString(Common.ID, ""), this)
                            .observe(this, new Observer<LoginClient>() {
                                @Override
                                public void onChanged(LoginClient loginClient) {
                                    Common.CurrentUser = loginClient;
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(2800);
                                                startActivity(new Intent(Splash_Activity.this, MainActivity.class));
                                                finish();
                                            } catch (Exception e) {
                                                Log.d(TAG, e.getMessage());
                                            }
                                        }
                                    }.start();
                                }
                            });
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3500);
                                startActivity(new Intent(Splash_Activity.this, Home_Activity.class));
                                finish();
                            } catch (Exception e) {
                                Log.d(TAG, e.getMessage());
                            }
                        }
                    }.start();
                }
            }else {
                if (checkToken(preferences.getString(Common.Token, ""))) {
                    Log.i("TTTTTTT", "Token is not EX");
                    viewModel.getDataOfClinic(preferences.getString(Common.Token, ""),
                            preferences.getString(Common.ID, ""), this)
                            .observe(this, new Observer<LoginClinc>() {
                                @Override
                                public void onChanged(LoginClinc loginClinc) {
                                    Common.CurrentClinic = loginClinc;
                                    Log.i("TTTTTT",loginClinc.getData().getClinic().getId()+"");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(2800);
                                                startActivity(new Intent(Splash_Activity.this, ClinicRequestsActivity.class));
                                                finish();
                                            } catch (Exception e) {
                                                Log.d(TAG, e.getMessage());
                                            }
                                        }
                                    }.start();
                                }
                            });
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3500);
                                startActivity(new Intent(Splash_Activity.this, Home_Activity.class));
                                finish();
                            } catch (Exception e) {
                                Log.d(TAG, e.getMessage());
                            }
                        }
                    }.start();
                }
            }
        }else {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3500);
                        startActivity(new Intent(Splash_Activity.this, Home_Activity.class));
                        finish();
                    } catch (Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
            }.start();
        }
    }

    private boolean checkToken(String Token){
        JWT jwt = new JWT(Token);
        Date expiresAt = jwt.getExpiresAt();
        return new Date().before(expiresAt) || new Date().equals(expiresAt);
    }

}
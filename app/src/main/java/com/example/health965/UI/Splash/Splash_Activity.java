package com.example.health965.UI.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.health965.R;
import com.example.health965.UI.Home.Home_Activity;
import com.google.firebase.iid.FirebaseInstanceId;

public class Splash_Activity extends AppCompatActivity {
    final static String TAG = "Splash_Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.i("TTTTTTTTT",FirebaseInstanceId.getInstance().getToken());
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                    startActivity(new Intent(Splash_Activity.this, Home_Activity.class));
                    finish();
                }catch (Exception e){
                    Log.d(TAG,e.getMessage());
                }
            }
        }.start();
    }
}
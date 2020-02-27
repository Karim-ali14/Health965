package com.example.health965.UI.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.health965.R;
import com.example.health965.UI.Login.Login_Activity;

public class Splash_Activity extends AppCompatActivity {
    final static String TAG = "Splash_Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                    startActivity(new Intent(Splash_Activity.this, Login_Activity.class));
                    finish();
                }catch (Exception e){
                    Log.d(TAG,e.getMessage());
                }
            }
        }.start();
    }
}
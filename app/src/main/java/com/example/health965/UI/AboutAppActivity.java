package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.health965.Common.Common;
import com.example.health965.Models.Setting.Setting;
import com.example.health965.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutAppActivity extends AppCompatActivity {
    ProgressDialog dialog;
    ImageView Twitter,LinkedIn,Instagram,GooglePlus,Facebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        dialog = new ProgressDialog(this);
        Twitter = findViewById(R.id.twitter);
        LinkedIn = findViewById(R.id.linkedin);
        Instagram = findViewById(R.id.instagram);
        GooglePlus =  findViewById(R.id.googleplus);
        Facebook = findViewById(R.id.facebook);
        getDataOfSetting();

    }

    public void Back(View view) {
        finish();
    }

    private void getDataOfSetting(){
        dialog.show();
        Common.getAPIRequest().getSetting().enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                dialog.dismiss();
                if (response.code() == 200){
                    purLinks(response.body());
                }else{
                    try {
                        Toast.makeText(AboutAppActivity.this, new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Setting> call, Throwable t) {

            }
        });
    }

    private void purLinks(final Setting setting) {
        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(setting.getData().getTwitter());
            }
        });
        LinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(setting.getData().getLinkedIn());
            }
        });
        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(setting.getData().getInstagram());
            }
        });
        GooglePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(setting.getData().getGooglePlus());
            }
        });
        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(setting.getData().getFacebook());
            }
        });
    }

    private void openBrowser(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}

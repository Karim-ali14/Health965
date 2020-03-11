package com.example.health965.UI.AboutApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
    AboutViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        viewModel = new ViewModelProviders().of(this).get(AboutViewModel.class);
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
        viewModel.getSettings(this).observe(this, new Observer<Setting>() {
            @Override
            public void onChanged(Setting setting) {
                dialog.dismiss();
                purLinks(setting);
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

package com.example.health965.UI.ClinicRequests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.health965.Common.Common;
import com.example.health965.R;
import com.example.health965.UI.ClinicRequests.Fragments.ClinicFragment;
import com.example.health965.UI.ClinicRequests.Fragments.ReservationFragment;
import com.example.health965.UI.ClinicRequests.Fragments.NotifyPageFragment;
import com.example.health965.UI.Login_In.Login_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class ClinicRequestsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView ImageBar;
    RelativeLayout LeftImageLayout;
    ClinicRequestsViewModel viewModel;
    String type = "Reservations";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_requests);
        init();
    }
    private void init(){
        viewModel = ViewModelProviders.of(this).get(ClinicRequestsViewModel.class);
        bottomNavigationView = findViewById(R.id.BarClinic);
        ImageBar = findViewById(R.id.ImageBar);
        LeftImageLayout = findViewById(R.id.LeftImageLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout,new ReservationFragment(viewModel)).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.Reservations : {
                        type = "Reservations";
                        fragment = new ReservationFragment(viewModel);
                        ImageBar.setVisibility(View.GONE);
                        LeftImageLayout.setVisibility(View.GONE);
                    }
                    break;
                    case R.id.Notification: {
                        type = "Notification";
                        fragment = new NotifyPageFragment(viewModel);
                        ImageBar.setImageResource(R.drawable.trash);
                        ImageBar.setVisibility(View.VISIBLE);
                        LeftImageLayout.setVisibility(View.VISIBLE);
                    }
                    break;
                    case R.id.Clinics: {
                        type = "Clinics";
                        fragment = new ClinicFragment();
                        LeftImageLayout.setVisibility(View.VISIBLE);
                        ImageBar.setVisibility(View.VISIBLE);
                        ImageBar.setImageResource(R.drawable.logout);
                    }
                    break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout,fragment).commit();
                return true;
            }
        });
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void LogOut(View view) {
        if (type.equals("Notification")){

        }else if (type.equals("Clinics")){
            Common.CurrentClinic = null;
            SharedPreferences preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
            preferences.edit().clear().apply();
            startActivity(new Intent(this, Login_Activity.class).putExtra("type","main"));
            finish();
        }
    }
}

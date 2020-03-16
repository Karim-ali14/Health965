package com.example.health965.UI.ClinicRequests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.health965.R;
import com.example.health965.UI.ClinicRequests.Fragments.ClinicFragment;
import com.example.health965.UI.ClinicRequests.Fragments.ReservationFragment;
import com.example.health965.UI.ClinicRequests.Fragments.NotifyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class ClinicRequestsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView ImageBar;
    ClinicRequestsViewModel viewModel;
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
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout,new ReservationFragment(viewModel)).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.Reservations : {
                        fragment = new ReservationFragment(viewModel);
                        ImageBar.setVisibility(View.GONE);
                    }
                    break;
                    case R.id.Notification: {
                        fragment = new NotifyPageFragment(viewModel);
                        ImageBar.setImageResource(R.drawable.trash);
                        ImageBar.setVisibility(View.VISIBLE);
                    }
                    break;
                    case R.id.Clinics: {
                        fragment = new ClinicFragment();
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
}

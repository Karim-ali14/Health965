package com.example.health965.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.health965.Common.Common;
import com.example.health965.Fragments.NotifyPage;
import com.example.health965.Models.FireBaseToken.FireBaseToken;
import com.example.health965.Models.FireBaseToken.FireBaseTokenRespons;
import com.example.health965.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicRequestsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView ImageBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_requests);
        bottomNavigationView = findViewById(R.id.BarClinic);
        ImageBar = findViewById(R.id.ImageBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout,new ReservationFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.Reservations : {
                        fragment = new ReservationFragment();
                        ImageBar.setVisibility(View.GONE);
                    }
                        break;
                    case R.id.Notification: {
                        fragment = new NotifyPage(getStringList());
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
        if (Common.CurrentClinic != null) {
            Common.getAPIRequest().onUpDateFireBaseTokenClinic(Common.CurrentClinic.getData().getToken().getAccessToken(),
                    Common.CurrentClinic.getData().getClinic().getId()+"",new FireBaseToken(FirebaseInstanceId.getInstance().getToken()))
                    .enqueue(new Callback<FireBaseTokenRespons>() {
                        @Override
                        public void onResponse(Call<FireBaseTokenRespons> call, Response<FireBaseTokenRespons> response) {

                        }

                        @Override
                        public void onFailure(Call<FireBaseTokenRespons> call, Throwable t) {

                        }
                    });
        }
    }
    public List<String> getStringList(){
        List<String> list = new ArrayList<>();
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");
        list.add("من فضلك قم بالتسجيل اولا لتتمكن من الحجز");
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");
        list.add("سيتم التواصل معك في خلال ربع ساعة مع مراعاة اوقات العمل");

        return list;
    }
}

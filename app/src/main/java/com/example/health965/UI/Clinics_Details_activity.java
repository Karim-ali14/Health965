package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.health965.Common.Common;
import com.example.health965.Fragments.Details_fragment;
import com.example.health965.Fragments.OfferPage;
import com.example.health965.Models.getClincs.Clinics;
import com.example.health965.Models.getClincs.Row;
import com.example.health965.R;
import com.example.health965.ViewHolders.ViewPageAdapter2;
import com.google.android.material.tabs.TabLayout;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class Clinics_Details_activity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPageAdapter2 viewPageAdapter2;
    TextView[] Points;
    Row clinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics__details_activity);
        clinic = getIntent().getExtras().getParcelable("Clinic");
        ((TextView)findViewById(R.id.Title)).setText(clinic.getName());
        viewPager = findViewById(R.id.ViewPager);
        tabLayout = findViewById(R.id.Tablayout);
        viewPageAdapter2 = new ViewPageAdapter2(getSupportFragmentManager());
        viewPageAdapter2.addFaragment(new Details_fragment(clinic),"تفاصيل العيادة");
        viewPageAdapter2.addFaragment(new OfferPage(),"عروض مميزة");
        viewPager.setAdapter(viewPageAdapter2);
        tabLayout.setupWithViewPager(viewPager);

        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void Back(View view) {
        finish();
    }

}
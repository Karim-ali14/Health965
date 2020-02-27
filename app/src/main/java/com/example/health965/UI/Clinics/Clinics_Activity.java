package com.example.health965.UI.Clinics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.getClincs.Clinics;
import com.example.health965.UI.Area_Activity;
import com.example.health965.UI.Doctor_Page_Activity;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clinics_Activity extends AppCompatActivity implements IClinics, ViewPager.OnPageChangeListener {
    RecyclerView recyclerView;
    ClinicsPresenter presenter;
    TextView points[];
    List<Integer> listImage;
    LinearLayout linearLayout;
    ViewPager viewPager;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);
        presenter = new ClinicsPresenter(this);
        presenter.onInit();
    }

    public void Back(View view) {
        finish();
    }

    @Override
    public void init() {
        id = getIntent().getExtras().getInt("ID");
        recyclerView = findViewById(R.id.RecyclerClinics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setNestedScrollingEnabled(false);

        linearLayout = findViewById(R.id.Points);
        viewPager = findViewById(R.id.ViewPager);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        viewPager.setAdapter(new AdapterForImages(listImage,this,false));
        viewPager.setOnPageChangeListener(this);
        addPoints(0);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Common.getAPIRequest().getAllClinics("image",id+"").enqueue(new Callback<Clinics>() {
            @Override
            public void onResponse(Call<Clinics> call, Response<Clinics> response) {
                if (response.code() == 200)
                    recyclerView.setAdapter(new AdapterForClinics(response.body().getData().getRows(),Clinics_Activity.this));

                Log.i("TTTTTTT",response.code()+"___"+response.body().getData().getCount());
            }

            @Override
            public void onFailure(Call<Clinics> call, Throwable t) {
                Log.d("TTTTTTT",t.getMessage());
            }
        });
    }

    private void addPoints(int position) {
        points = new TextView[listImage.size()];
        linearLayout.removeAllViews();

        for (int i = 0 ; i < points.length ; i++){
            points[i] = new TextView(this);
            points[i].setText(Html.fromHtml("&#8226"));
            points[i].setTextSize(44);
            points[i].setTextColor(getResources().getColor(R.color.colorAccent));
            linearLayout.addView(points[i]);
        }
        if (points.length > 0)
            points[position].setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addPoints(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void Area(View view) {
        startActivity(new Intent(this, Area_Activity.class).putExtra("C_ID",id));
    }

    public void Clinics(View view) {
        startActivity(new Intent(this, Doctor_Page_Activity.class));
    }

    public void DoctorPage(View view) {
        startActivity(new Intent(this, Doctor_Page_Activity.class));
    }
}

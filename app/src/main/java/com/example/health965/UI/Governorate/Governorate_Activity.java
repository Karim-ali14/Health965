package com.example.health965.UI.Governorate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForArea;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.Governorate.Governorate;
import com.example.health965.R;
import com.example.health965.UI.Clinics.Clinics_Activity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Governorate_Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    RecyclerView recyclerView;
    TextView points[];
    LinearLayout linearLayout;
    ViewPager viewPager;
    ProgressDialog dialog;
    int listSize = 0;
    GovernorateViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_governorate);
        init();
    }

    private void init(){
        viewModel = ViewModelProviders.of(this).get(GovernorateViewModel.class);
        dialog = new ProgressDialog(this);
        dialog.show();
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        recyclerView = findViewById(R.id.RecyclerOfArea);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
        linearLayout = findViewById(R.id.Points);
        viewPager = findViewById(R.id.ViewPager);
        listSize = Clinics_Activity.rows.size();
        viewPager.setAdapter(new AdapterForImages(Clinics_Activity.rows, Governorate_Activity.this,false,false));
        viewPager.setOnPageChangeListener(Governorate_Activity.this);
        addPoints(0,listSize);
    }

    public void Back(View view) {
        finish();
    }

    private void getData(){
        viewModel.getGovernorate(this).observe(this, new Observer<Governorate>() {
            @Override
            public void onChanged(Governorate governorate) {
                dialog.dismiss();
                recyclerView.setAdapter(new AdapterForArea(governorate.getData().getRows(),
                        Governorate_Activity.this,getIntent().getExtras().getInt("C_ID")));
            }
        });
    }

    private void addPoints(int position,int Size) {
        points = new TextView[Size];
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
        addPoints(position,listSize);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}

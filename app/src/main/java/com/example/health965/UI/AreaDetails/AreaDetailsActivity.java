package com.example.health965.UI.AreaDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Governorate.Row;
import com.example.health965.Models.Options.Option;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

public class AreaDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    TextView points[];
    List<Integer> listImage;
    LinearLayout linearLayout;
    ViewPager viewPager;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    int listSize = 0;
    AreaDetailsViewModel viewModel;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        init();
    }

    private void init(){
        preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        dialog = new ProgressDialog(this);
        dialog.show();

        Row row = getIntent().getExtras().getParcelable("Row");
        ((TextView)findViewById(R.id.NameOfArea)).setText(row.getName());

        linearLayout = findViewById(R.id.Points);
        viewPager = findViewById(R.id.ViewPager);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        viewPager.setOnPageChangeListener(this);

        recyclerView = findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(AreaDetailsViewModel.class);

        viewModel.getClinicsByGovernorate(this,dialog,getIntent().getExtras().getInt("ID")+"",
                row.getId()+"").observe(this, new Observer<Clinics>() {
            @Override
            public void onChanged(final Clinics clinics) {
                viewModel.getDataOfOption(AreaDetailsActivity.this).observe(AreaDetailsActivity.this, new Observer<Option>() {
                    @Override
                    public void onChanged(Option option) {
                        recyclerView.setAdapter(new AdapterForClinics(clinics.getData().getRows(),AreaDetailsActivity.this,option));
                    }
                });
            }
        });

        viewModel.getBannerForGovernorate(this,row.getId()+"",dialog).observe(this, new Observer<BannerForCategory>() {
            @Override
            public void onChanged(BannerForCategory bannerForCategory) {
                listSize = bannerForCategory.getData().getRows().size();
                viewPager.setAdapter(new AdapterForImages(bannerForCategory.getData().getRows(),AreaDetailsActivity.this,false,false));
                addPoints(0,listSize);
            }
        });
    }
    public void Back(View view) {
        finish();
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

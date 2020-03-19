package com.example.health965.UI.Clinics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.BannerForCategory.Row;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Options.Option;
import com.example.health965.UI.Governorate.Governorate_Activity;
import com.example.health965.UI.Doctor_Page.Doctor_Page_Activity;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clinics_Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    RecyclerView recyclerView;
    TextView points[];
    List<Integer> listImage;
    LinearLayout linearLayout;
    ViewPager viewPager;
    int id;
    public static List<Row> rows;
    int listSize = 0;
    RelativeLayout LayoutSpecial;
    Clinics_ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);
        viewModel = ViewModelProviders.of(this).get(Clinics_ViewModel.class);
        init();
        rows = new ArrayList<>();
        LayoutSpecial = findViewById(R.id.LayoutSpecial);
    }

    public void Back(View view) {
        finish();
    }

    public void init() {
        id = getIntent().getExtras().getInt("ID");
        recyclerView = findViewById(R.id.RecyclerClinics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        linearLayout = findViewById(R.id.Points);
        viewPager = findViewById(R.id.ViewPager);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);

        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // انا عملت الركوست الي فاضل اني احط في الاضبطر انه ياخد المودل الخاص بالابشنل
        viewModel.getAllClinics(id+"",this).observe(this,
                new androidx.lifecycle.Observer<Clinics>() {
            @Override
            public void onChanged(final Clinics clinics) {
                viewModel.getDataOfOption(Clinics_Activity.this).observe(Clinics_Activity.this, new Observer<Option>() {
                    @Override
                    public void onChanged(Option option) {
                        recyclerView.setAdapter(new AdapterForClinics(clinics.getData().getRows(),Clinics_Activity.this,option));
                    }
                });
            }
        });

        viewModel.getBannerForCategory(id+"",this).observe(this,
                new androidx.lifecycle.Observer<BannerForCategory>() {
            @Override
            public void onChanged(BannerForCategory bannerForCategory) {
                if (bannerForCategory.getData().getRows().size() != 0){
                    listSize = bannerForCategory.getData().getRows().size();
                    rows = bannerForCategory.getData().getRows();
                    Log.i("TTTTTTTTTG",rows.get(0).getImage().getName());
                    viewPager.setAdapter(new AdapterForImages(bannerForCategory.getData().getRows(),Clinics_Activity.this,false,false));
                    viewPager.setOnPageChangeListener(Clinics_Activity.this);
                    addPoints(0,bannerForCategory.getData().getRows().size());
                }
            }
        });
    }

    private void addPoints(int position,int listSize) {
        points = new TextView[listSize];
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

    public void Area(View view) {
        startActivity(new Intent(this, Governorate_Activity.class).putExtra("C_ID",id));
    }

    public void Clinics(View view) {
        startActivity(new Intent(this, Doctor_Page_Activity.class).putExtra("C_ID",id));
    }

    public void DoctorPage(View view) {

        startActivity(new Intent(this, Doctor_Page_Activity.class).putExtra("C_ID",id));


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LayoutSpecial.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim));
    }
}

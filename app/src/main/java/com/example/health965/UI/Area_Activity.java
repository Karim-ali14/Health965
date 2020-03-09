package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Governorate.Governorate;
import com.example.health965.Models.Governorate.Row;
import com.example.health965.R;
import com.example.health965.UI.Clinics.Clinics_Activity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Area_Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    RecyclerView recyclerView;
    TextView points[];
    List<Integer> listImage;
    LinearLayout linearLayout;
    ViewPager viewPager;
    ProgressDialog dialog;
    int listSize = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
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
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listSize = Clinics_Activity.rows.size();
        viewPager.setAdapter(new AdapterForImages(Clinics_Activity.rows,Area_Activity.this,false,false));
        viewPager.setOnPageChangeListener(Area_Activity.this);
        addPoints(0,listSize);

    }

    public void Back(View view) {
        finish();
    }

    private void getData(){
//        Observable<BannerForCategory> bannerForCategoryObservable = Common.getAPIRequest().getBannerForCategory(true, getIntent().getExtras().getInt("C_ID") + "");
//        bannerForCategoryObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(final BannerForCategory bannerForCategory) {
//                if (bannerForCategory.getData().getRows().size() != 0){
//                    listSize = bannerForCategory.getData().getRows().size();
//                    viewPager.setAdapter(new AdapterForImages(Clinics_Activity.rows,Area_Activity.this,false));
//                    viewPager.setOnPageChangeListener(Area_Activity.this);
//                    addPoints(0,listSize);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
        Common.getAPIRequest().getAllGovernorate().enqueue(new Callback<Governorate>() {
            @Override
            public void onResponse(Call<Governorate> call, Response<Governorate> response) {
                if (response.code() == 200) {
                    dialog.dismiss();
                    recyclerView.setAdapter(new AdapterForArea(response.body().getData().getRows(), Area_Activity.this,getIntent().getExtras().getInt("C_ID")));
                }else
                    Log.i("TTTTTT",response.code()+"");
            }

            @Override
            public void onFailure(Call<Governorate> call, Throwable t) {
                Log.i("TTTTTT",t.getMessage());
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

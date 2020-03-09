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
import android.widget.Toast;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.Governorate.Row;
import com.example.health965.Models.ModelsForCilinics;
import com.example.health965.R;

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

public class AreaDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    TextView points[];
    List<Integer> listImage;
    LinearLayout linearLayout;
    ViewPager viewPager;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    int listSize = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
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
        Common.getAPIRequest().getClinicsByGovernorate("image","clinicOptions","clinicCertificate",getIntent().getExtras().getInt("ID")+"",
                row.getId()+"").enqueue(new Callback<Clinics>() {
            @Override
            public void onResponse(Call<Clinics> call, Response<Clinics> response) {
                if (response.code() == 200) {
                    if (response.body().getData().getCount() == 0){
                        dialog.dismiss();
                        Toast.makeText(AreaDetailsActivity.this, "لا يوجد عيادات في هذه المنطقة", Toast.LENGTH_LONG).show();
                    }
                    else {
                        dialog.dismiss();
                        recyclerView.setAdapter(new AdapterForClinics(response.body().getData().getRows(), AreaDetailsActivity.this));
                    }
                }
            }

            @Override
            public void onFailure(Call<Clinics> call, Throwable t) {
                Log.d("TTTTTTT",t.getMessage());
            }
        });
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Observable<BannerForCategory> bannerForGovernorate = Common.getAPIRequest().getBannerForGovernorate(true,
                row.getId()+"");
        bannerForGovernorate.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BannerForCategory bannerForCategory) {
                if (bannerForCategory.getData().getRows().size() != 0){
                    listSize = bannerForCategory.getData().getRows().size();
                    viewPager.setAdapter(new AdapterForImages(bannerForCategory.getData().getRows(),AreaDetailsActivity.this,false,false));
                    addPoints(0,listSize);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

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
    private List<ModelsForCilinics> getdata(){
        List<ModelsForCilinics> list = new ArrayList<>();
        list.add(new ModelsForCilinics(R.drawable.hebaclinic,"عيادة هبة دينتال كلينيك",
                "السالمية , حي الامير صباح الأحمد",
                ", زراعة اسنان , تبييض , تلميع , تنظيف , تقويم بوليش , تركيبات"));
        list.add(new ModelsForCilinics(R.drawable.hebaclinic,"عيادة هبة دينتال كلينيك",
                "السالمية , حي الامير صباح الأحمد",
                ", زراعة اسنان , تبييض , تلميع , تنظيف , تقويم بوليش , تركيبات"));
        return list;
    }
}

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
import com.example.health965.Models.Governorate.Governorate;
import com.example.health965.Models.Governorate.Row;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

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
        viewPager.setAdapter(new AdapterForImages(listImage,this,false));
        viewPager.setOnPageChangeListener(this);
        addPoints(0);
    }

    public void Back(View view) {
        finish();
    }

    private void getData(){
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


}

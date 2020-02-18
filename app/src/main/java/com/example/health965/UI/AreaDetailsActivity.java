package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Models.ModelsForCilinics;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

public class AreaDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    TextView points[];
    List<Integer> listImage;
    LinearLayout linearLayout;
    ViewPager viewPager;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        ((TextView)findViewById(R.id.NameOfArea)).setText(getIntent().getExtras().getString("Name"));
        linearLayout = findViewById(R.id.Points);
        viewPager = findViewById(R.id.ViewPager);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        viewPager.setAdapter(new AdapterForImages(listImage,this));
        viewPager.setOnPageChangeListener(this);
        addPoints(0);
        recyclerView = findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterForClinics(getdata(),this));
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void Back(View view) {
        finish();
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

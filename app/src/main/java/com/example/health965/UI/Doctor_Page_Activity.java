package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health965.Adapters.AdapterForDoctorCard;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.DoctorsWithClinics.DoctorsWithClinics;
import com.example.health965.Models.ModelOfCardDoctor;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_Page_Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static EditText SearchBar;
    public static TextView SearchText;
    public static ImageView SearchImage;
    RecyclerView RecyclerOfCard;
    TextView points[];
    List<Integer> listImage;
    LinearLayout linearLayout;
    ViewPager viewPager;
    ConstraintLayout Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__page);
        SearchImage = findViewById(R.id.ImageOfSearch);
        SearchText = findViewById(R.id.TextSearch);
        SearchBar = findViewById(R.id.searchBar);
        RecyclerOfCard = findViewById(R.id.RecyclerOfCard);
        RecyclerOfCard.setHasFixedSize(true);
        RecyclerOfCard.setLayoutManager(new LinearLayoutManager(this));
        getDoctorsWithClinics();
        linearLayout = findViewById(R.id.Points);
        viewPager = findViewById(R.id.ViewPager);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        viewPager.setAdapter(new AdapterForImages(listImage,this,true));
        viewPager.setOnPageChangeListener(this);
        addPoints(0);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Layout = findViewById(R.id.Layout);
        Layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!(v instanceof EditText)) {
                    closeKeyBoard(Doctor_Page_Activity.this);
                }
                return false;
            }
        });
    }
    public static void closeKeyBoard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
        SearchBar.setVisibility(View.GONE);
        SearchText.setVisibility(View.VISIBLE);
        SearchImage.setVisibility(View.VISIBLE);
    }

    public void Back(View view) {
        finish();
    }

    public void OpenEditText(View view) {
        SearchText.setVisibility(View.GONE);
        SearchImage.setVisibility(View.GONE);
        SearchBar.setVisibility(View.VISIBLE);
        SearchBar.requestFocus();
        SearchBar.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(SearchBar, InputMethodManager.SHOW_FORCED);
    }


    private List<ModelOfCardDoctor> getDara(){
        List<ModelOfCardDoctor> list = new ArrayList<>();
        list.add(new ModelOfCardDoctor(R.drawable.doctorw,R.drawable.hebaclinic,"د / أحمد محمد السالم","عيادة هبة دينتال كلينيك"
        ,"السالمية , حي الأمير صباح الأحمد","زراعة اسنان , تبييض , تلميع , تنظيف , تقويم بوليش , تركيبات","ماجستير طب وجراحة الفم والأسنان"));
        list.add(new ModelOfCardDoctor(R.drawable.doctorw,R.drawable.hebaclinic,"د / أحمد محمد السالم","عيادة هبة دينتال كلينيك"
        ,"السالمية , حي الأمير صباح الأحمد","زراعة اسنان , تبييض , تلميع , تنظيف , تقويم بوليش , تركيبات","ماجستير طب وجراحة الفم والأسنان"));
        list.add(new ModelOfCardDoctor(R.drawable.doctorw,R.drawable.hebaclinic,"د / أحمد محمد السالم","عيادة هبة دينتال كلينيك"
        ,"السالمية , حي الأمير صباح الأحمد","زراعة اسنان , تبييض , تلميع , تنظيف , تقويم بوليش , تركيبات","ماجستير طب وجراحة الفم والأسنان"));
        return list;
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

    private void getDoctorsWithClinics(){
        Common.getAPIRequest().getDoctorsWithClinics("image").enqueue(new Callback<DoctorsWithClinics>() {
            @Override
            public void onResponse(Call<DoctorsWithClinics> call, Response<DoctorsWithClinics> response) {
                if (response.code() == 200)
                    RecyclerOfCard.setAdapter(new AdapterForDoctorCard(response.body().getData().getRows(),Doctor_Page_Activity.this));
                else
                    Toast.makeText(Doctor_Page_Activity.this,response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DoctorsWithClinics> call, Throwable t) {

            }
        });
    }
}

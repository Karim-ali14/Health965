package com.example.health965.UI.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.health965.Adapters.AdapterForSlider;
import com.example.health965.UI.Registration.NewAccountActivity;
import com.example.health965.R;
import com.example.health965.UI.Main.MainActivity;


import java.util.List;

public class Login_Activity extends AppCompatActivity implements ILogin{
    ViewPager2 viewPager;
    LinearLayout slider;
    TextView listOfPints[];
    public List<Integer> listImage;
    Handler handler = new Handler();
    boolean live = true;
    LoginPresenter presenter;
    LinearLayout ButtonsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        presenter = new LoginPresenter(this);
        presenter.onInit();
            getWindow().getDecorView().setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    @Override
    public void addPoints(int position){
        listOfPints = new TextView[listImage.size()];
        slider.removeAllViews();
        for (int i = 0;i<listOfPints.length;i++){
            listOfPints[i] = new TextView(this);
            listOfPints[i].setText(Html.fromHtml("&#8226"));
            listOfPints[i].setTextSize(40);
            listOfPints[i].setTextColor(getResources().getColor(R.color.colorAccent));
            slider.addView(listOfPints[i]);
        }
        if (listOfPints.length > 0)
            listOfPints[position].setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == listImage.size()-1 && live)
                viewPager.setCurrentItem(0);
            else if (live)
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }
    };
    public void login(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void Init() {
        viewPager = findViewById(R.id.ViewPager);
        listImage = presenter.getImageList();

        AdapterForSlider adapter = new AdapterForSlider(listImage,this);
        viewPager.setAdapter(adapter);
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float i = 1 - Math.abs(position);
                page.setScaleY(0.85f + i * 0.15f);
            }
        });
        viewPager.setPageTransformer(transformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
                presenter.OnAddPoints(position);
            }
        });
        slider = findViewById(R.id.slider);
        presenter.OnAddPoints(0);

        ButtonsLayout = findViewById(R.id.Buttons);
    }

    public void LoginIn(View view) {
        startActivity(new Intent(this, com.example.health965.UI.Login_Activity.class));
    }

    public void createNewAccount(View view) {
        startActivity(new Intent(this, NewAccountActivity.class).putExtra("type",0));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ButtonsLayout.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_above_bown));
    }
}

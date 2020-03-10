package com.example.health965.UI.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.health965.Adapters.AdapterForSlider;
import com.example.health965.Common.Common;
import com.example.health965.Models.Offers.Background;
import com.example.health965.Models.Offers.Icon;
import com.example.health965.Models.Offers.Offers;
import com.example.health965.Models.Offers.Row;
import com.example.health965.UI.Registration.NewAccountActivity;
import com.example.health965.R;
import com.example.health965.UI.Main.MainActivity;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Activity extends AppCompatActivity implements ILogin{
    ViewPager2 viewPager;
    LinearLayout slider;
    TextView listOfPints[];
    Handler handler = new Handler();
    boolean live = true;
    HomePresenter presenter;
    LinearLayout ButtonsLayout;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        presenter = new HomePresenter(this);
        presenter.onInit();
            getWindow().getDecorView().setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    @Override
    public void addPoints(int position,int listSize){
        listOfPints = new TextView[listSize];
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


    public void login(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void Init() {
        dialog = new ProgressDialog(this);
        dialog.show();
        viewPager = findViewById(R.id.ViewPager);
        getBanners();
        ButtonsLayout = findViewById(R.id.Buttons);
    }

    public void LoginIn(View view) {
        startActivity(new Intent(this, com.example.health965.UI.Login_Activity.class).putExtra("type","main"));
    }

    public void createNewAccount(View view) {
        startActivity(new Intent(this, NewAccountActivity.class).putExtra("type",0));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ButtonsLayout.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_above_bown));
    }

    private void getBanners(){
       /* Common.getAPIRequest().getOffersForHome(true,true,true).enqueue(new Callback<Offers>() {
            @Override
            public void onResponse(Call<Offers> call, final Response<Offers> response) {
                dialog.dismiss();
                if (response.code() == 200){
                    AdapterForSlider adapter = new AdapterForSlider(response.body().getData().getRows(),Home_Activity.this);
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
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (viewPager.getCurrentItem() == response.body().getData().getRows().size()-1 && live)
                                viewPager.setCurrentItem(0);
                            else if (live)
                                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        }
                    };
                    viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            handler.removeCallbacks(runnable);
                            handler.postDelayed(runnable,3000);
                            presenter.OnAddPoints(position,response.body().getData().getRows().size());
                        }
                    });
                    slider = findViewById(R.id.slider);
                    if (response.body().getData().getRows().size() != 0){
                        if (response.body().getData().getRows().size() % 2 == 0){
                            presenter.OnAddPoints(response.body().getData().getRows().size() / 2,response.body().getData().getRows().size());
                        }else {

                            presenter.OnAddPoints((response.body().getData().getRows().size()+1) / 2,response.body().getData().getRows().size());
                        }
                    }
//                    presenter.OnAddPoints(0,response.body().getData().getRows().size());
                }
                else if (response.code() == 422) {
                    try {
                        Log.i("TTTTTT", new JSONObject(response.errorBody().string()).getJSONObject("error").getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (response.code() == 500) {
                    try {
                        Log.i("TTTTTT", new JSONObject(response.errorBody().string()).getJSONObject("error").getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {
                Log.i("TTTTTT",t.getMessage());
            }
        });*/
        Common.getAPIRequest().getOffersForHome(true,true,true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Offers>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final Offers offers) {
                        dialog.dismiss();
                        AdapterForSlider adapter = new AdapterForSlider(offers.getData().getRows(),Home_Activity.this);
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
                        final Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                if (viewPager.getCurrentItem() == offers.getData().getRows().size()-1 && live)
                                    viewPager.setCurrentItem(0);
                                else if (live)
                                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                            }
                        };
                        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                            @Override
                            public void onPageSelected(int position) {
                                super.onPageSelected(position);
                                handler.removeCallbacks(runnable);
                                handler.postDelayed(runnable,3000);
                                presenter.OnAddPoints(position,offers.getData().getRows().size());
                            }
                        });
                        slider = findViewById(R.id.slider);
                        if (offers.getData().getRows().size() != 0){
                            if (offers.getData().getRows().size() % 2 == 0){
                                presenter.OnAddPoints(offers.getData().getRows().size() / 2,offers.getData().getRows().size());
                            }else {

                                presenter.OnAddPoints((offers.getData().getRows().size()+1) / 2,offers.getData().getRows().size());
                            }
                        }
//                    presenter.OnAddPoints(0,response.body().getData().getRows().size());

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                        final AlertDialog.Builder Adialog = new AlertDialog.Builder(Home_Activity.this);
                        View view = LayoutInflater.from(Home_Activity.this).inflate(R.layout.error_dialog, null);
                        TextView Title = view.findViewById(R.id.Title);
                        TextView Message = view.findViewById(R.id.Message);
                        Button button = view.findViewById(R.id.Again);
                        Adialog.setView(view);
                        final AlertDialog dialog1 = Adialog.create();
                        dialog1.setCanceledOnTouchOutside(false);
                        dialog1.setCancelable(false);
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                                dialog.show();
                                getBanners();
                            }
                        });
                        if(e instanceof SocketTimeoutException) {
                            Title.setText("تعذر الأتصال بالخادم");
                            Message.setText("خطأ في تحميل البيانات من الخادم اضغط علي زر لأعاده تحميل البيانات");
                            dialog1.show();
                        }

                        else if (e instanceof UnknownHostException) {
                            Title.setText("لا يوجد اتصال بالانترنت");
                            Message.setText("تأكد من أتصالك بالأنترنت ثم أعد المحاولة");
                            dialog1.show();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

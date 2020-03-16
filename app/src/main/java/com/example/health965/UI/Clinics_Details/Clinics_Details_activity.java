package com.example.health965.UI.Clinics_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForClinics;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.UI.Fragments.Details_fragment;
import com.example.health965.UI.Fragments.OfferClinic;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.Clinics.Clinics;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.R;
import com.example.health965.ViewHolders.ViewPageAdapter2;
import com.google.android.material.tabs.TabLayout;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clinics_Details_activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPageAdapter2 viewPageAdapter2;
    TextView[] Points;
    LinearLayout linearLayout;
    ViewPager viewPager2;
    int listSize = 0;
    ProgressDialog dialog;
    Clinics_DetailsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics__details_activity);
        viewModel = ViewModelProviders.of(this).get(Clinics_DetailsViewModel.class);
        dialog = new ProgressDialog(this);
        dialog.show();
        viewPager = findViewById(R.id.ViewPager);
        tabLayout = findViewById(R.id.Tablayout);
        viewPageAdapter2 = new ViewPageAdapter2(getSupportFragmentManager());
        linearLayout = findViewById(R.id.Points);
        viewPager2 = findViewById(R.id.ViewPager2);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (AdapterForClinics.CLINIC != null) {
            dialog.dismiss();
            ((TextView)findViewById(R.id.Title)).setText(AdapterForClinics.CLINIC.getName());
            /*Common.getAPIRequest().getBannerForClinic(true, AdapterForClinics.CLINIC.getId() + ""
                    , "clinic").subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(final BannerForCategory bannerForCategory) {
                    if (bannerForCategory.getData().getRows().size() != 0) {
                        listSize = bannerForCategory.getData().getRows().size();
                        viewPager2.setAdapter(new AdapterForImages(bannerForCategory.getData().getRows(), Clinics_Details_activity.this, false,true));
                        viewPager2.setOnPageChangeListener(Clinics_Details_activity.this);
                        addPoints(0, listSize);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });*/

            viewModel.getBannerForClinic(AdapterForClinics.CLINIC.getId() + "")
                    .observe(this, new androidx.lifecycle.Observer<BannerForCategory>() {
                @Override
                public void onChanged(BannerForCategory bannerForCategory) {
                    listSize = bannerForCategory.getData().getRows().size();
                    viewPager2.setAdapter(new AdapterForImages(bannerForCategory.getData().getRows(), Clinics_Details_activity.this, false,true));
                    viewPager2.setOnPageChangeListener(Clinics_Details_activity.this);
                    addPoints(0, listSize);
                }
            });

            /*Common.getAPIRequest().getOffersForClinic(AdapterForClinics.CLINIC.getId() + "", true, true).
                    enqueue(new Callback<OfferForClinic>() {
                        @Override
                        public void onResponse(Call<OfferForClinic> call, Response<OfferForClinic> response) {
                            if (response.code() == 200) {
                                viewPageAdapter2.addFaragment(new OfferClinic(response.body().getData().getRows()), "عروض مميزة");
                                viewPageAdapter2.addFaragment(new Details_fragment(AdapterForClinics.CLINIC), "تفاصيل العيادة");
                                viewPager.setAdapter(viewPageAdapter2);
                                tabLayout.setupWithViewPager(viewPager);
                                tabLayout.getTabAt(1).select();

                            }
                        }

                        @Override
                        public void onFailure(Call<OfferForClinic> call, Throwable t) {

                        }
                    });*/
            viewModel.getOffersForClinic(AdapterForClinics.CLINIC.getId()+"",this)
                    .observe(this, new androidx.lifecycle.Observer<OfferForClinic>() {
                        @Override
                        public void onChanged(OfferForClinic offerForClinic) {
                            viewPageAdapter2.addFaragment(new OfferClinic(offerForClinic.getData().getRows()), "عروض مميزة");
                            viewPageAdapter2.addFaragment(new Details_fragment(AdapterForClinics.CLINIC), "تفاصيل العيادة");
                            viewPager.setAdapter(viewPageAdapter2);
                            tabLayout.setupWithViewPager(viewPager);
                            tabLayout.getTabAt(1).select();
                        }
                    });
        }else {
            getDataOfClinic();
        }
    }

    public void Back(View view) {
        finish();
    }

    private void addPoints(int position,int Size) {
        Points = new TextView[Size];
        linearLayout.removeAllViews();

        for (int i = 0 ; i < Points.length ; i++){
            Points[i] = new TextView(this);
            Points[i].setText(Html.fromHtml("&#8226"));
            Points[i].setTextSize(44);
            Points[i].setTextColor(getResources().getColor(R.color.colorAccent));
            linearLayout.addView(Points[i]);
        }
        if (Points.length > 0)
            Points[position].setTextColor(getResources().getColor(R.color.colorPrimary));
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

    @Override
    protected void onStop() {
        super.onStop();
        AdapterForClinics.CLINIC = null;
    }

    private void getDataOfClinic(){
        Common.getAPIRequest().getClinicInfo("image",getIntent().getExtras()
                        .getString("Clinic_Id"),
                "clinicOptions",
                "clinicCertificate").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Clinics>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Clinics clinics) {
                AdapterForClinics.CLINIC = clinics.getData().getRows().get(0);
                ((TextView)findViewById(R.id.Title)).setText(AdapterForClinics.CLINIC.getName());
                Observable<BannerForCategory> bannerForClinic = Common.getAPIRequest().getBannerForClinic(true, AdapterForClinics.CLINIC.getId() + ""
                        , "clinic");
                bannerForClinic.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BannerForCategory bannerForCategory) {
                        if (bannerForCategory.getData().getRows().size() != 0) {
                            listSize = bannerForCategory.getData().getRows().size();
                            viewPager2.setAdapter(new AdapterForImages(bannerForCategory.getData().getRows(), Clinics_Details_activity.this, false,true));
                            viewPager2.setOnPageChangeListener(Clinics_Details_activity.this);
                            addPoints(0, listSize);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dialog.dismiss();
                    }
                });
                Common.getAPIRequest().getOffersForClinic(AdapterForClinics.CLINIC.getId() + "", true, true).
                        enqueue(new Callback<OfferForClinic>() {
                            @Override
                            public void onResponse(Call<OfferForClinic> call, Response<OfferForClinic> response) {
                                if (response.code() == 200) {
                                    viewPageAdapter2.addFaragment(new OfferClinic(response.body().getData().getRows()), "عروض مميزة");
                                    viewPageAdapter2.addFaragment(new Details_fragment(AdapterForClinics.CLINIC), "تفاصيل العيادة");
                                    viewPager.setAdapter(viewPageAdapter2);
                                    tabLayout.setupWithViewPager(viewPager);
                                    tabLayout.getTabAt(1).select();

                                }
                            }

                            @Override
                            public void onFailure(Call<OfferForClinic> call, Throwable t) {

                            }
                        });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
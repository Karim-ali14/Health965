package com.example.health965.UI.Clinics_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.example.health965.Models.IsReserved.IsReserved;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.R;
import com.example.health965.UI.Clinics_Details.Fragment.Details_fragment;
import com.example.health965.UI.Clinics_Details.Fragment.OfferClinic;
import com.example.health965.ViewHolders.ViewPageAdapter2;
import com.google.android.material.tabs.TabLayout;

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
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics__details_activity);
        preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
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
        if (AdapterForClinics.CLINIC != null && Common.CurrentUser != null) {
            ((TextView)findViewById(R.id.Title)).setText(AdapterForClinics.CLINIC.getName());

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

            viewModel.getOffersForClinic(AdapterForClinics.CLINIC.getId()+"",this)
                    .observe(this, new androidx.lifecycle.Observer<OfferForClinic>() {
                        @Override
                        public void onChanged(OfferForClinic offerForClinic) {
                            viewPageAdapter2.addFaragment(new OfferClinic(offerForClinic.getData().getRows()), "عروض مميزة");
                            viewPageAdapter2.addFaragment(new Details_fragment(AdapterForClinics.CLINIC,viewModel), "تفاصيل العيادة");
                            viewPager.setAdapter(viewPageAdapter2);
                            tabLayout.setupWithViewPager(viewPager);
                            tabLayout.getTabAt(1).select();
                            viewModel.isReserved(preferences.getString(Common.Token, ""),
                                    preferences.getString(Common.ID, ""),
                                    AdapterForClinics.CLINIC.getId()+"",Clinics_Details_activity.this)
                                    .observe(Clinics_Details_activity.this, new Observer<IsReserved>() {
                                @Override
                                public void onChanged(IsReserved isReserved) {
                                    dialog.dismiss();
                                    if (isReserved.getData().getIsReserved()){
                                        if (AdapterForClinics.CLINIC.getContacts() != null) {
                                            Details_fragment.ReservationButton.setVisibility(View.GONE);
                                            Details_fragment.ButtonsLayout.setVisibility(View.VISIBLE);
                                            Details_fragment.PhoneButton.setText(AdapterForClinics.CLINIC.getContacts().getPhoneNumber1());
                                            Details_fragment.WhatsAppButton.setText(AdapterForClinics.CLINIC.getContacts().getWhatsApp());

                                            Details_fragment.WhatsAppButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String url = "https://api.whatsapp.com/send?phone="+Details_fragment.WhatsAppButton.getText().toString();
                                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                                    i.setData(Uri.parse(url));
                                                    startActivity(i);
                                                }
                                            });

                                            Details_fragment.PhoneButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String Phone = Details_fragment.PhoneButton.getText().toString();
                                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                                    intent.setData(Uri.parse("tel:" +Phone));
                                                    startActivity(intent);
                                                }
                                            });
                                        }else {
                                            Details_fragment.ReservationButton.setVisibility(View.GONE);
                                            Details_fragment.ButtonsLayout.setVisibility(View.VISIBLE);
                                            Details_fragment.PhoneButton.setText("-----");
                                            Details_fragment.WhatsAppButton.setText("-----");
                                        }
                                    }else {
                                        Details_fragment.ReservationButton.setVisibility(View.VISIBLE);
                                        Details_fragment.ButtonsLayout.setVisibility(View.GONE);
                                    }
                                }
                            });
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

    private void getDataOfClinic() {
        viewModel.getDataOfClinic(getIntent().getExtras()
                .getString("Clinic_Id")).observe(this, new Observer<Clinics>() {
            @Override
            public void onChanged(Clinics clinics) {
                AdapterForClinics.CLINIC = clinics.getData().getRows().get(0);

                ((TextView) findViewById(R.id.Title)).setText(AdapterForClinics.CLINIC.getName());

                viewModel.getBannerForClinic(AdapterForClinics.CLINIC.getId() + "")
                        .observe(Clinics_Details_activity.this, new androidx.lifecycle.Observer<BannerForCategory>() {
                            @Override
                            public void onChanged(BannerForCategory bannerForCategory) {
                                listSize = bannerForCategory.getData().getRows().size();
                                viewPager2.setAdapter(new AdapterForImages(bannerForCategory.getData().getRows(), Clinics_Details_activity.this, false, true));
                                viewPager2.setOnPageChangeListener(Clinics_Details_activity.this);
                                addPoints(0, listSize);
                            }
                        });

                viewModel.getOffersForClinic(AdapterForClinics.CLINIC.getId() + "", Clinics_Details_activity.this)
                        .observe(Clinics_Details_activity.this, new androidx.lifecycle.Observer<OfferForClinic>() {
                            @Override
                            public void onChanged(OfferForClinic offerForClinic) {
                                viewPageAdapter2.addFaragment(new OfferClinic(offerForClinic.getData().getRows()), "عروض مميزة");
                                viewPageAdapter2.addFaragment(new Details_fragment(AdapterForClinics.CLINIC, viewModel), "تفاصيل العيادة");
                                viewPager.setAdapter(viewPageAdapter2);
                                tabLayout.setupWithViewPager(viewPager);
                                tabLayout.getTabAt(1).select();
                                if (Common.CurrentUser != null) {
                                    viewModel.isReserved(preferences.getString(Common.Token, ""),
                                            preferences.getString(Common.ID, ""),
                                            AdapterForClinics.CLINIC.getId() + "", Clinics_Details_activity.this).observe(
                                            Clinics_Details_activity.this, new Observer<IsReserved>() {
                                                @Override
                                                public void onChanged(IsReserved isReserved) {
                                                    dialog.dismiss();
                                                    if (isReserved.getSuccess()){
                                                        if (AdapterForClinics.CLINIC.getContacts() != null) {
                                                            Details_fragment.ReservationButton.setVisibility(View.GONE);
                                                            Details_fragment.ButtonsLayout.setVisibility(View.VISIBLE);
                                                            Details_fragment.PhoneButton.setText(AdapterForClinics.CLINIC.getContacts().getPhoneNumber1());
                                                            Details_fragment.WhatsAppButton.setText(AdapterForClinics.CLINIC.getContacts().getWhatsApp());

                                                            Details_fragment.WhatsAppButton.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    String url = "https://api.whatsapp.com/send?phone="+AdapterForClinics.CLINIC.getContacts().getWhatsApp();
                                                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                                                    i.setData(Uri.parse(url));
                                                                    startActivity(i);
                                                                }
                                                            });

                                                            Details_fragment.PhoneButton.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    String Phone = AdapterForClinics.CLINIC.getContacts().getPhoneNumber1();
                                                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                                                    intent.setData(Uri.parse("tel:" +Phone));
                                                                    startActivity(intent);
                                                                }
                                                            });
                                                        }else {
                                                            Details_fragment.ReservationButton.setVisibility(View.GONE);
                                                            Details_fragment.ButtonsLayout.setVisibility(View.VISIBLE);
                                                            Details_fragment.PhoneButton.setText("-----");
                                                            Details_fragment.WhatsAppButton.setText("-----");
                                                        }
                                                    }else {
                                                        Details_fragment.ReservationButton.setVisibility(View.VISIBLE);
                                                        Details_fragment.ButtonsLayout.setVisibility(View.GONE);
                                                    }
                                                }
                                            });
                                }else
                                    dialog.dismiss();
                            }
                        });
            }
        });
    }

}
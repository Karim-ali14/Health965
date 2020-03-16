package com.example.health965.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.Row;
import com.example.health965.R;
import com.example.health965.UI.Clinics_Details.Clinics_Details_activity;
import com.example.health965.UI.Doctor_Page.Doctor_Page_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForImages extends PagerAdapter {
    List<Row> list;
    Context context;
    boolean search,insideClinic;
    public AdapterForImages(List<Row> list, Context context,boolean search,boolean insideClinic) {
        this.list = list;
        this.context = context;
        this.search = search;
        this.insideClinic = insideClinic;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Row row = list.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.model_of_image,container,false);
        ImageView imageView = view.findViewById(R.id.ImageSilder);
        Picasso.with(context).load(Common.BaseURL+"images/"+row.getImage().getFor()+"/"+ Uri.encode(row.getImage().getName())).into(imageView);
        Log.i("TTTTTTTTT",Common.BaseURL+"images/"+row.getImage().getFor()+"/"+ Uri.encode(row.getImage().getName()));

        if (search) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Doctor_Page_Activity.closeKeyBoard((Activity) context);
                    context.startActivity(new Intent(context, Clinics_Details_activity.class).putExtra("Clinic_Id",row.getClinicId()));
                }
            });
        }else if (!insideClinic){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Clinics_Details_activity.class).putExtra("Clinic_Id",row.getClinicId()));
                }
            });
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

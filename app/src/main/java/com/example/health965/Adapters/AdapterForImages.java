package com.example.health965.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.health965.R;

import java.util.List;

public class AdapterForImages extends PagerAdapter {
    List<Integer> list;
    Context context;

    public AdapterForImages(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_of_image,container,false);
        ImageView imageView = view.findViewById(R.id.ImageSilder);
        imageView.setImageResource(list.get(position));
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

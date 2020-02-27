package com.example.health965.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.health965.R;
import com.example.health965.UI.Doctor_Page_Activity;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AdapterForImages extends PagerAdapter {
    List<Integer> list;
    Context context;
    boolean search;
    public AdapterForImages(List<Integer> list, Context context,boolean search) {
        this.list = list;
        this.context = context;
        this.search = search;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_of_image,container,false);
        ImageView imageView = view.findViewById(R.id.ImageSilder);
        imageView.setImageResource(list.get(position));
        if (search) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Doctor_Page_Activity.closeKeyBoard((Activity) context);
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

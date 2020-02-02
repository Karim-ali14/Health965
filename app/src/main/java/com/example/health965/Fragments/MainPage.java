package com.example.health965.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterFor;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Models.Model;
import com.example.health965.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainPage extends Fragment implements ViewPager.OnPageChangeListener{

    List<Model> list;
    LinearLayout linearLayout;
    ViewPager viewPager2;
    TextView points[];
    List<Integer> listImage;
    boolean start;
    public MainPage(List<Model> list,boolean start) {
        this.list = list;
        this.start  = start;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        viewPager2 = view.findViewById(R.id.ViewPager2);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);

        linearLayout = view.findViewById(R.id.Layout);
        viewPager2.setAdapter(new AdapterForImages(listImage,getContext()));
        viewPager2.setOnPageChangeListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.Rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterFor(getContext(),list));
        addPoints(0);
        if (start)
            linearLayout.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anim));
        return view;
    }

    private void addPoints(int position) {
        points = new TextView[listImage.size()];
        linearLayout.removeAllViews();

        for (int i = 0 ; i < points.length ; i++){
            points[i] = new TextView(getContext());
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

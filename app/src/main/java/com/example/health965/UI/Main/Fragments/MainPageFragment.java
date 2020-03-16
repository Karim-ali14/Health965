package com.example.health965.UI.Main.Fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health965.Adapters.AdapterForCategory;
import com.example.health965.Adapters.AdapterForImages;
import com.example.health965.Common.Common;
import com.example.health965.Models.BannerForCategory.BannerForCategory;
import com.example.health965.Models.BannerForCategory.Row;
import com.example.health965.Models.Category.Category;
import com.example.health965.R;
import com.example.health965.UI.Main.MainViewModel;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment implements ViewPager.OnPageChangeListener{
    LinearLayout linearLayout;
    ViewPager viewPager2;
    TextView points[];
    RecyclerView recyclerView;
    ProgressDialog dialog;
    int listSize = 0;
    boolean start;
    List<Row> rows;
    MainViewModel viewModel;
    public MainPageFragment(boolean start, MainViewModel viewModel) {
        this.start  = start;
        this.viewModel = viewModel;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        init(view);
        return view;
    }
    private void init(View view){
        dialog = new ProgressDialog(getContext());
        dialog.show();
        viewPager2 = view.findViewById(R.id.ViewPager2);
        linearLayout = view.findViewById(R.id.Layout);
        getBanners();
        viewPager2.setOnPageChangeListener(this);
        recyclerView = view.findViewById(R.id.Rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getCategory();

        if (start)
            linearLayout.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anim));
    }

    private void addPoints(int position,int Size) {
        points = new TextView[Size];
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
        addPoints(position,listSize);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void getCategory(){
        viewModel.getCategories(getActivity(),dialog).observe(getActivity(), new androidx.lifecycle.Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                dialog.dismiss();
                recyclerView.setAdapter(new AdapterForCategory(getContext(),category.getData().getRows()));
            }
        });
    }

    private void getBanners(){
        viewModel.getBanners(getActivity(),dialog).observe(getActivity(), new androidx.lifecycle.Observer<BannerForCategory>() {
            @Override
            public void onChanged(BannerForCategory bannerForCategory) {
                dialog.dismiss();
                if (bannerForCategory.getData().getRows().size() != 0){
                    listSize = bannerForCategory.getData().getRows().size();
                    rows = bannerForCategory.getData().getRows();
                    viewPager2.setAdapter(new AdapterForImages(rows,getContext(),false,false));
                    addPoints(0,bannerForCategory.getData().getRows().size());
                }

            }
        });
    }
}

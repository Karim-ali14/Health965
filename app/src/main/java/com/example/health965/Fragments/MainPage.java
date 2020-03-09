package com.example.health965.Fragments;


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
public class MainPage extends Fragment implements ViewPager.OnPageChangeListener{
    LinearLayout linearLayout;
    ViewPager viewPager2;
    TextView points[];
    List<Integer> listImage;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    int listSize = 0;
    boolean start;
    List<Row> rows;
    public MainPage(boolean start) {
        this.start  = start;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        dialog = new ProgressDialog(getContext());
        dialog.show();
        viewPager2 = view.findViewById(R.id.ViewPager2);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        listImage.add(R.drawable.addclinic);
        linearLayout = view.findViewById(R.id.Layout);
        getBanners();
        viewPager2.setOnPageChangeListener(this);
        recyclerView = view.findViewById(R.id.Rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getCategory();

        if (start)
            linearLayout.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anim));
        return view;
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
        Observable<Category> allCategory = Common.getAPIRequest().getAllCategory(true);
        allCategory.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final Category category) {
                        dialog.dismiss();
                        recyclerView.setAdapter(new AdapterForCategory(getContext(),category.getData().getRows()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                        final AlertDialog.Builder Adialog = new AlertDialog.Builder(getActivity());
                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.error_dialog, null);
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
                                getCategory();
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
    private void getBanners(){
        Observable<BannerForCategory> bannerForCategoryObservable = Common.getAPIRequest().getBannerForCategories(true, "home");
        bannerForCategoryObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerForCategory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BannerForCategory bannerForCategory) {
                dialog.dismiss();
                if (bannerForCategory.getData().getRows().size() != 0){
                    listSize = bannerForCategory.getData().getRows().size();
                    rows = bannerForCategory.getData().getRows();
                    Log.i("TTTTTTTTTG",rows.get(0).getImage().getName());
                    viewPager2.setAdapter(new AdapterForImages(rows,getContext(),false,false));
                    addPoints(0,bannerForCategory.getData().getRows().size());
                }
            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
                final AlertDialog.Builder Adialog = new AlertDialog.Builder(getActivity());
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.error_dialog, null);
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

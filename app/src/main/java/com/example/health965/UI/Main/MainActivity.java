package com.example.health965.UI.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.health965.Common.Common;
import com.example.health965.Fragments.AccountPage;
import com.example.health965.Fragments.NotifyPage;
import com.example.health965.Fragments.MainPage;
import com.example.health965.Models.Category.Category;
import com.example.health965.Models.Category.Row;
import com.example.health965.Models.Model;
import com.example.health965.Fragments.OfferPage;
import com.example.health965.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView {

    BottomNavigationView bottomNavigationView;
    MainPresenter presenter;
    TextView title;
    ImageView ImageBar,ImageBack;
    View AboveLine;
    ProgressDialog progressDialog;
    RelativeLayout BackLayout,LeftImageLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        presenter.OnPutDAta();

        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onInit() {
        bottomNavigationView = findViewById(R.id.Bar);
        title = findViewById(R.id.Title);
        ImageBar = findViewById(R.id.ImageBar);
        ImageBack = findViewById(R.id.back);
        AboveLine = findViewById(R.id.AboveLine);
        BackLayout = findViewById(R.id.BackLayout);
        LeftImageLayout = findViewById(R.id.LeftImageLayout);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
    }

    @Override
    public void putData(final List<Model> list, final List<String> stringList) {
        getAllCategory(stringList);
    }

    public void Back(View view) {
        finish();
    }

    public void getAllCategory(final List<String> stringList){
        Observable<Category> allCategory = Common.getAPIRequest().getAllCategory(true);
                allCategory.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final Category category) {
                        progressDialog.dismiss();
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,new MainPage(category.getData().getRows(),true)).commit();
                        bottomNavigationView.setSelectedItemId(R.id.home);
                        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                                Fragment fragment = null;
                                switch (menuItem.getItemId()){
                                    case R.id.account:{
                                        fragment = new AccountPage(MainActivity.this);
                                        title.setText(menuItem.getTitle());
                                        ImageBar.setVisibility(View.VISIBLE);
                                        LeftImageLayout.setVisibility(View.VISIBLE);
                                        ImageBack.setVisibility(View.GONE);
                                        BackLayout.setVisibility(View.GONE);
                                        AboveLine.setVisibility(View.VISIBLE);
                                        ImageBar.setImageResource(R.drawable.logout);
                                    }
                                    break;
                                    case R.id.notify:{
                                        fragment = new NotifyPage(stringList);
                                        title.setText(menuItem.getTitle());
                                        AboveLine.setVisibility(View.VISIBLE);
                                        ImageBar.setVisibility(View.VISIBLE);
                                        LeftImageLayout.setVisibility(View.VISIBLE);
                                        ImageBack.setVisibility(View.GONE);
                                        BackLayout.setVisibility(View.GONE);
                                        ImageBar.setImageResource(R.drawable.trash);
                                    }
                                    break;
                                    case R.id.offer:{
                                        fragment = new OfferPage();
                                        title.setText(menuItem.getTitle());
                                        AboveLine.setVisibility(View.VISIBLE);
                                        ImageBack.setVisibility(View.GONE);
                                        BackLayout.setVisibility(View.GONE);
                                        ImageBar.setVisibility(View.GONE);
                                        LeftImageLayout.setVisibility(View.GONE);
                                    }
                                    break;
                                    case R.id.home:{
                                        fragment = new MainPage(category.getData().getRows(),false);
                                        title.setText(menuItem.getTitle());
                                        ImageBar.setVisibility(View.GONE);
                                        LeftImageLayout.setVisibility(View.GONE);
                                        AboveLine.setVisibility(View.GONE);
                                        ImageBack.setVisibility(View.VISIBLE);
                                        BackLayout.setVisibility(View.VISIBLE);

                                    }
                                    break;
                                }
                                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,fragment).commit();
                                return true;
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.error_dialog, null);
                        TextView Title = view.findViewById(R.id.Title);
                        TextView Message = view.findViewById(R.id.Message);
                        Button button = view.findViewById(R.id.Again);
                        dialog.setView(view);
                        final AlertDialog dialog1 = dialog.create();
                        dialog1.setCanceledOnTouchOutside(false);
                        dialog1.setCancelable(false);
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                                progressDialog.show();
                                getAllCategory(stringList);
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

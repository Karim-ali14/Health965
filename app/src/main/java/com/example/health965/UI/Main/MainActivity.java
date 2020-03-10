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
import com.example.health965.Models.FireBaseToken.FireBaseToken;
import com.example.health965.Models.FireBaseToken.FireBaseTokenRespons;
import com.example.health965.Models.Model;
import com.example.health965.Fragments.OfferPage;
import com.example.health965.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

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
        if (Common.CurrentUser != null) {
            Common.getAPIRequest().onUpDateFireBaseTokenClient(Common.CurrentUser.getData().getToken().getAccessToken(),"application/json",
                    Common.CurrentUser.getData().getUser().getId()+"",new FireBaseToken(FirebaseInstanceId.getInstance().getToken()))
                    .enqueue(new Callback<FireBaseTokenRespons>() {
                        @Override
                        public void onResponse(Call<FireBaseTokenRespons> call, Response<FireBaseTokenRespons> response) {

                        }

                        @Override
                        public void onFailure(Call<FireBaseTokenRespons> call, Throwable t) {

                        }
                    });
        }
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
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,new MainPage(true)).commit();
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
                        fragment = new NotifyPage(presenter.getStringList());
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
                        fragment = new MainPage(false);
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
    public void putData(final List<Model> list, final List<String> stringList) {
        getAllCategory(stringList);
    }

    public void Back(View view) {
        finish();
    }

    public void getAllCategory(final List<String> stringList){


    }
}

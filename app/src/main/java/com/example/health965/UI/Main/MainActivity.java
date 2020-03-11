package com.example.health965.UI.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.health965.Common.Common;
import com.example.health965.UI.Fragments.AccountPage;
import com.example.health965.UI.Fragments.NotifyPage;
import com.example.health965.UI.Fragments.MainPage;
import com.example.health965.Models.FireBaseToken.FireBaseToken;
import com.example.health965.Models.FireBaseToken.FireBaseTokenRespons;
import com.example.health965.UI.Fragments.OfferPage;
import com.example.health965.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView title;
    ImageView ImageBar,ImageBack;
    View AboveLine;
    RelativeLayout BackLayout,LeftImageLayout;
    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        onInit();
    }

    public void onInit() {
        bottomNavigationView = findViewById(R.id.Bar);
        title = findViewById(R.id.Title);
        ImageBar = findViewById(R.id.ImageBar);
        ImageBack = findViewById(R.id.back);
        AboveLine = findViewById(R.id.AboveLine);
        BackLayout = findViewById(R.id.BackLayout);
        LeftImageLayout = findViewById(R.id.LeftImageLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,new MainPage(true,viewModel)).commit();
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
                        fragment = new NotifyPage(viewModel);
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
                        fragment = new OfferPage(viewModel);
                        title.setText(menuItem.getTitle());
                        AboveLine.setVisibility(View.VISIBLE);
                        ImageBack.setVisibility(View.GONE);
                        BackLayout.setVisibility(View.GONE);
                        ImageBar.setVisibility(View.GONE);
                        LeftImageLayout.setVisibility(View.GONE);
                    }
                    break;
                    case R.id.home:{
                        fragment = new MainPage(false,viewModel);
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

    public void Back(View view) {
        finish();
    }

}

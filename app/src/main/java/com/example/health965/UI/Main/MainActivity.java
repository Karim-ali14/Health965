package com.example.health965.UI.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
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
import com.example.health965.UI.Fragments.OfferPage;
import com.example.health965.R;
import com.example.health965.UI.Login_In.Login_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView title;
    ImageView ImageBar,ImageBack;
    View AboveLine;
    RelativeLayout BackLayout,LeftImageLayout;
    MainViewModel viewModel;
    boolean logOut = false;
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
                        logOut = true;
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
                        logOut = false;
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
                        logOut = false;
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
                        logOut = false;
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
    }

    public void Back(View view) {
        finish();
    }

    public void LogOut(View view) {
        if (logOut) {
            Common.CurrentUser = null;
            startActivity(new Intent(this, Login_Activity.class).putExtra("type","main"));
            finish();
        }
    }
}

package com.example.health965.UI.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.health965.Fragments.AccountPage;
import com.example.health965.Fragments.NotifyPage;
import com.example.health965.Fragments.MainPage;
import com.example.health965.Models.Model;
import com.example.health965.Fragments.OfferPage;
import com.example.health965.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    BottomNavigationView bottomNavigationView;
    MainPresenter presenter;
    TextView title;
    ImageView ImageBar,ImageBack;
    View AboveLine;
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
    }

    @Override
    public void putData(final List<Model> list, final List<String> stringList) {
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,new MainPage(list,true)).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.home:{
                        fragment = new MainPage(list,false);
                        title.setText(menuItem.getTitle());
                        ImageBar.setVisibility(View.GONE);
                        AboveLine.setVisibility(View.GONE);
                        ImageBack.setVisibility(View.VISIBLE);
                    }
                        break;
                    case R.id.offer:{
                        fragment = new OfferPage();
                        title.setText(menuItem.getTitle());
                        AboveLine.setVisibility(View.VISIBLE);
                        ImageBack.setVisibility(View.GONE);
                        ImageBar.setVisibility(View.GONE);
                    }
                        break;
                    case R.id.notify:{
                        fragment = new NotifyPage(stringList);
                        title.setText(menuItem.getTitle());
                        AboveLine.setVisibility(View.VISIBLE);
                        ImageBar.setVisibility(View.VISIBLE);
                        ImageBack.setVisibility(View.GONE);
                        ImageBar.setImageResource(R.drawable.trash);
                    }
                    break;
                    case R.id.account:{
                        fragment = new AccountPage(MainActivity.this);
                        title.setText(menuItem.getTitle());
                        ImageBar.setVisibility(View.VISIBLE);
                        ImageBack.setVisibility(View.GONE);
                        AboveLine.setVisibility(View.VISIBLE);
                        ImageBar.setImageResource(R.drawable.logout);
                    }
                    break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,fragment).commit();
                return true;
            }
        });

    }

    public void Back(View view) {
        finish();
    }
}

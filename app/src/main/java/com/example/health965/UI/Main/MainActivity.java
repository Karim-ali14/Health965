package com.example.health965.UI.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health965.Adapters.AdapterForSpinner;
import com.example.health965.Common.Common;
import com.example.health965.Models.Category.Category;
import com.example.health965.Models.Category.Row;
import com.example.health965.Models.OfferForClinic.OfferForClinic;
import com.example.health965.R;
import com.example.health965.UI.Login_In.Login_Activity;
import com.example.health965.UI.Main.Fragments.AccountPageFragment;
import com.example.health965.UI.Main.Fragments.MainPageFragment;
import com.example.health965.UI.Main.Fragments.NotifyPageFragment;
import com.example.health965.UI.Main.Fragments.OfferPageFragment;
import com.example.health965.UI.Main.Fragments.ReservationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView title;
    ImageView ImageBar,ImageBack;
    View AboveLine;
    RelativeLayout BackLayout,LeftImageLayout;
    MainViewModel viewModel;
    String TypeOfClick = null;
    CallBack callBack;
    Spinner spinner;
    boolean showSpinner = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();

        viewModel.getCategories(this,new ProgressDialog(this)).observe(this, new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                category.getData().getRows().add(0,new Row(0,"اختر الفئة","",null));
                AdapterForSpinner adapter = new AdapterForSpinner(MainActivity.this,
                        0,category.getData().getRows());
                spinner.setAdapter(adapter);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Row item = (Row) parent.getSelectedItem();
                if (!item.getName().equals("اختر الفئة")) {
                    viewModel.getOfferByCategory(new ProgressDialog(MainActivity.this), item.getId() + "")
                            .observe(MainActivity.this, new Observer<OfferForClinic>() {
                                @Override
                                public void onChanged(OfferForClinic offerForClinic) {
                                    callBack.filterByCategory(offerForClinic.getData().getRows());
                                }
                            });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,new MainPageFragment(true,viewModel)).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.account:{
                        TypeOfClick = "account";
                        fragment = new AccountPageFragment(MainActivity.this    );
                        title.setText(menuItem.getTitle());
                        ImageBar.setVisibility(View.VISIBLE);
                        LeftImageLayout.setVisibility(View.VISIBLE);
                        ImageBack.setVisibility(View.GONE);
                        BackLayout.setVisibility(View.GONE);
                        AboveLine.setVisibility(View.VISIBLE);
                        ImageBar.setImageResource(R.drawable.logout);
                        spinner.setVisibility(View.GONE);
                        showSpinner = false;
                        spinner.setSelection(0);
                    }
                    break;
                    case R.id.notify:{
                        TypeOfClick = "notify";
                        fragment = new NotifyPageFragment(viewModel);
                        title.setText(menuItem.getTitle());
                        AboveLine.setVisibility(View.VISIBLE);
                        ImageBar.setVisibility(View.VISIBLE);
                        LeftImageLayout.setVisibility(View.VISIBLE);
                        ImageBack.setVisibility(View.GONE);
                        BackLayout.setVisibility(View.GONE);
                        ImageBar.setImageResource(R.drawable.trash);
                        spinner.setVisibility(View.GONE);
                        showSpinner = false;
                        spinner.setSelection(0);
                    }
                    break;
                    case R.id.offer:{
                        TypeOfClick = "offer";
                        fragment = new OfferPageFragment(viewModel);
                        title.setText(menuItem.getTitle());
                        AboveLine.setVisibility(View.VISIBLE);
                        ImageBack.setVisibility(View.GONE);
                        BackLayout.setVisibility(View.GONE);
                        ImageBar.setVisibility(View.VISIBLE);
                        LeftImageLayout.setVisibility(View.VISIBLE);
                        ImageBar.setImageResource(R.drawable.trash);
                    }
                    break;
                    case R.id.home:{
                        TypeOfClick = "home";
                        fragment = new MainPageFragment(false,viewModel);
                        title.setText(menuItem.getTitle());
                        ImageBar.setVisibility(View.GONE);
                        LeftImageLayout.setVisibility(View.GONE);
                        AboveLine.setVisibility(View.GONE);
                        ImageBack.setVisibility(View.VISIBLE);
                        BackLayout.setVisibility(View.VISIBLE);
                        spinner.setVisibility(View.GONE);
                        showSpinner = false;
                        spinner.setSelection(0);
                    }
                    break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,fragment).commit();
                return true;
            }
        });

    }

    public void onInit() {
        spinner = findViewById(R.id.spinner);
        bottomNavigationView = findViewById(R.id.Bar);
        title = findViewById(R.id.Title);
        ImageBar = findViewById(R.id.ImageBar);
        ImageBack = findViewById(R.id.back);
        AboveLine = findViewById(R.id.AboveLine);
        BackLayout = findViewById(R.id.BackLayout);
        LeftImageLayout = findViewById(R.id.LeftImageLayout);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void Back(View view) {
        finish();
    }

    public void LogOut(View view) {
        if (TypeOfClick.equals("account")) {
            Common.CurrentUser = null;
            SharedPreferences preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
            preferences.edit().clear().apply();
            startActivity(new Intent(this, Login_Activity.class).putExtra("type","main"));
            finish();
        }
        else if(TypeOfClick.equals("offer")){
            if (showSpinner) {
                showSpinner = false;
                spinner.setVisibility(View.GONE);
            }else {
                showSpinner = true;
                spinner.setVisibility(View.VISIBLE);
            }
        }
    }

    public void passRef(CallBack callBack) {
        this.callBack = callBack;

    }
}

package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.health965.R;

public class PersonalInformationActivity extends AppCompatActivity {
    Button modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        modify = findViewById(R.id.modify);
    }

    public void Back(View view) {
        finish();
    }

    public void modify(View view) {
        startActivity(new Intent(this,ModifyPersonalInformationActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        modify.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim2));
    }
}

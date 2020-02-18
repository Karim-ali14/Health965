package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.example.health965.R;

public class ModifyPersonalInformationActivity extends AppCompatActivity {
    EditText PhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_personal_information);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        PhoneNumber.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim2));

    }

    public void Back(View view) {
        finish();
    }

}

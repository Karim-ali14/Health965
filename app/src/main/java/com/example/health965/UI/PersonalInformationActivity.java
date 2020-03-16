package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.health965.Common.Common;
import com.example.health965.R;
import com.example.health965.UI.ModifyPersonalInformation.ModifyPersonalInformationActivity;

public class PersonalInformationActivity extends AppCompatActivity {
    Button modify;
    TextView Name,Email,Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        modify = findViewById(R.id.modify);
        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Phone = findViewById(R.id.Phone);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Common.CurrentUser != null)
            putDataOfUser();
    }

    public void Back(View view) {
        finish();
    }

    public void modify(View view) {
        startActivity(new Intent(this, ModifyPersonalInformationActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        modify.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim2));
    }

    private void putDataOfUser(){
        if (Common.CurrentUser != null) {
            Name.setText(Common.CurrentUser.getData().getUser().getFullName());
            Email.setText(Common.CurrentUser.getData().getUser().getEmail());
            Phone.setText(Common.CurrentUser.getData().getUser().getMobilePhone());
        }
    }
}

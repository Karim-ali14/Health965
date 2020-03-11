package com.example.health965.UI.ClinicInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.health965.Common.Common;
import com.example.health965.R;

public class ClinicInformationActivity extends AppCompatActivity {
    TextView Name,Email,Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_information);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Phone = findViewById(R.id.Phone);
        putDataOfClinic();
    }

    public void Back(View view) {
        finish();
    }

    private void putDataOfClinic(){
        if (Common.CurrentClinic != null) {
            Name.setText(Common.CurrentClinic.getData().getClinic().getName());
            Email.setText(Common.CurrentClinic.getData().getClinic().getEmail());
            Phone.setText(Common.CurrentClinic.getData().getClinic().getMobilePhone());
        }
    }
}

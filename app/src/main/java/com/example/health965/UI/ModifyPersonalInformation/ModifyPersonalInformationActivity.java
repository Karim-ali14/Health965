package com.example.health965.UI.ModifyPersonalInformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health965.Common.Common;
import com.example.health965.Models.UpdateClientInfo.RequestUpdateClientInfo;
import com.example.health965.Models.UpdateClientInfo.ResponseUpdateClientInfo.ResponseUpdateClientInfo;
import com.example.health965.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyPersonalInformationActivity extends AppCompatActivity {
    TextInputEditText FullName,PhoneNumber,Email;
    TextInputLayout PhoneNumberLayOut;
    ConstraintLayout Layout;
    ModifyPersonalInformationViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_personal_information);
        init();
    }

    private void init(){
        viewModel = ViewModelProviders.of(this).get(ModifyPersonalInformationViewModel.class);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        FullName = findViewById(R.id.FullName);
        Email = findViewById(R.id.Email);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        PhoneNumberLayOut = findViewById(R.id.PhoneNumberLayOut);
        PhoneNumberLayOut.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim2));
        Layout = findViewById(R.id.Layout);
        Layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!(v instanceof EditText))
                    closeKeyBoard();
                return false;
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void Back(View view) {
        finish();
    }

    public void UpdateInfo(View view) {
        final SharedPreferences preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
        if (!FullName.getText().toString().isEmpty()||!Email.getText().toString().isEmpty()||!PhoneNumber.getText().toString().isEmpty()) {
            RequestUpdateClientInfo model = new RequestUpdateClientInfo(null, null, null);
            if (FullName.getText().toString().isEmpty())
                model.setFullName(Common.CurrentUser.getData().getUser().getFullName());
            else
                model.setFullName(FullName.getText().toString());

            if (Email.getText().toString().isEmpty())
                model.setEmail(Common.CurrentUser.getData().getUser().getEmail());
            else
                model.setEmail(Email.getText().toString());

            if (PhoneNumber.getText().toString().isEmpty())
                model.setMobilePhone(Common.CurrentUser.getData().getUser().getMobilePhone());
            else
                model.setMobilePhone(PhoneNumber.getText().toString());

            viewModel.onUpdateClientInfo(model,this).observe(this, new Observer<ResponseUpdateClientInfo>() {
                @Override
                public void onChanged(ResponseUpdateClientInfo responseUpdateClientInfo) {
                    preferences.edit().putString(Common.Email,responseUpdateClientInfo.getData().getEmail());
                    preferences.edit().putString(Common.Name,responseUpdateClientInfo.getData().getFullName());
                    preferences.edit().putString(Common.Phone,responseUpdateClientInfo.getData().getMobilePhone());
                    finish();
                }
            });
        }else {
            Toast.makeText(this, "ليتم عملية التحديث بنجاح عليك تغير عنصر واحد علي الاقل", Toast.LENGTH_LONG).show();
        }
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        FullName.clearFocus();
        PhoneNumber.clearFocus();
        Email.clearFocus();
    }

}

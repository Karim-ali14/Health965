package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.example.health965.Models.ChangePassword.ResponseChangePassword.ResponseChangePassword;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_personal_information);
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

            Common.getAPIRequest().onUpdateClientInfo(Common.CurrentUser.getData().getToken().getAccessToken(),"application/json",
                    Common.CurrentUser.getData().getUser().getId().toString(),model).enqueue(new Callback<ResponseUpdateClientInfo>() {
                @Override
                public void onResponse(Call<ResponseUpdateClientInfo> call, Response<ResponseUpdateClientInfo> response) {
                    if (response.code() == 200){
                        Common.CurrentUser.getData().getUser().setEmail(response.body().getData().getEmail());
                        Common.CurrentUser.getData().getUser().setFullName(response.body().getData().getFullName());
                        Common.CurrentUser.getData().getUser().setMobilePhone(response.body().getData().getMobilePhone());
                        finish();
                    }else
                        Log.i("TTTTTT",response.code()+"");
                }

                @Override
                public void onFailure(Call<ResponseUpdateClientInfo> call, Throwable t) {
                    Toast.makeText(ModifyPersonalInformationActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
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

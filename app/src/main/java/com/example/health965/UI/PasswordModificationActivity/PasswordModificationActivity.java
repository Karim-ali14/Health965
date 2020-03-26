package com.example.health965.UI.PasswordModificationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health965.Common.Common;
import com.example.health965.Models.ChangePassword.ResponseChangePassword.ResponseChangePassword;
import com.example.health965.Models.ChangePassword.ChangePassword;
import com.example.health965.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordModificationActivity extends AppCompatActivity {
    TextInputEditText OldPassword,NewPassword,PasswordConfirmation;
    ProgressDialog dialog;
    ConstraintLayout layout;
    PasswordModificationViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_modification);
        init();
    }

    private void init(){
        viewModel = ViewModelProviders.of(this).get(PasswordModificationViewModel.class);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        OldPassword =findViewById(R.id.OldPassword);
        NewPassword =findViewById(R.id.NewPassword);
        PasswordConfirmation =findViewById(R.id.PasswordConfirmation);
        dialog = new ProgressDialog(this);
        layout = findViewById(R.id.Layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
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

    private void changeClinicPassword(){
        dialog.show();
        viewModel.onChangeClinicPass(Common.CurrentClinic.getData().getToken().getAccessToken()
                ,Common.CurrentClinic.getData().getClinic().getId().toString(),new ChangePassword(OldPassword.getText().toString(),
                        NewPassword.getText().toString()),this,dialog)
                .observe(this, new Observer<ResponseChangePassword>() {
            @Override
            public void onChanged(ResponseChangePassword responseChangePassword) {
                finish();
            }
        });
    }

    private void changeClientPassword(){
        dialog.show();
        SharedPreferences preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
        viewModel.onChangeClientPass(preferences.getString(Common.Token, "")
                ,Common.CurrentUser.getData().getUser().getId().toString(),new ChangePassword(OldPassword.getText().toString(),
                        NewPassword.getText().toString()),this,dialog).observe(this,
                new Observer<ResponseChangePassword>() {
                    @Override
                    public void onChanged(ResponseChangePassword responseChangePassword) {
                        finish();
                    }
                });
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        OldPassword.clearFocus();
        NewPassword.clearFocus();
        PasswordConfirmation.clearFocus();
    }

    public void Save(View view) {
        switch (getIntent().getExtras().getString("Type")){
            case "User":
                changeClientPassword();
                break;
            case "Clinic" :
                changeClinicPassword();
                break;
        }
    }
}

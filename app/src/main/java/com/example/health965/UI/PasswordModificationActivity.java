package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health965.Common.Common;
import com.example.health965.Models.ChangePassword.ResponseChangePassword.ResponseChangePassword;
import com.example.health965.Models.ChangePassword.ChangePassword;
import com.example.health965.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordModificationActivity extends AppCompatActivity {
    EditText OldPassword,NewPassword,PasswordConfirmation;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_modification);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        OldPassword =findViewById(R.id.OldPassword);
        NewPassword =findViewById(R.id.NewPassword);
        PasswordConfirmation =findViewById(R.id.PasswordConfirmation);
        dialog = new ProgressDialog(this);
    }

    public void Back(View view) {
        switch (getIntent().getExtras().getString("Type")){
            case "User":
                changeClientPassword();
                break;
            case "Clinic" :
                changeClinicPassword();
                break;
        }
    }

    private void changeClinicPassword(){
        dialog.show();
        Common.getAPIRequest().onChangeClinicPass(Common.CurrentClinic.getData().getToken().getAccessToken(),"application/json"
                ,Common.CurrentClinic.getData().getClinic().getId().toString(),new ChangePassword(OldPassword.getText().toString(),
                NewPassword.getText().toString())).enqueue(new Callback<ResponseChangePassword>() {
            @Override
            public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                dialog.dismiss();
                if (response.code() == 200)
                    finish();
                else {
                    try {
                        Toast.makeText(PasswordModificationActivity.this,new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseChangePassword> call, Throwable t) {

            }
        });
    }

    private void changeClientPassword(){
        dialog.show();
        Common.getAPIRequest().onChangeClientPass(Common.CurrentUser.getData().getToken().getAccessToken(),"application/json"
                ,Common.CurrentUser.getData().getUser().getId().toString(),new ChangePassword(OldPassword.getText().toString(),
                NewPassword.getText().toString())).enqueue(new Callback<ResponseChangePassword>() {
            @Override
            public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                dialog.dismiss();
                if (response.code() == 200)
                    finish();
                else {
                    try {
                        Toast.makeText(PasswordModificationActivity.this,new JSONObject(response.errorBody().string()).getString("message")
                                , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseChangePassword> call, Throwable t) {

            }
        });
    }
}

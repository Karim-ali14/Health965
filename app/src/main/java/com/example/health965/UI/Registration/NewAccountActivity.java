package com.example.health965.UI.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.health965.API.APIRequest;
import com.example.health965.Common.Common;
import com.example.health965.Models.Regestration.DataUserRegistration;
import com.example.health965.Models.Regestration.Registration;
import com.example.health965.R;
import com.example.health965.UI.ActivateYourAccountActivity;
import com.example.health965.UI.Login_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountActivity extends AppCompatActivity {
    EditText FullName,Email,Password,PhoneNumber,PasswordConfirmation;
    ImageView image;
    View Line;
    ConstraintLayout Layout;
    ProgressDialog dialog;
    public final static String TAG = "NewAccountActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        dialog = new ProgressDialog(this);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        FullName = findViewById(R.id.FullName);
        Email = findViewById(R.id.Email);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        Password = findViewById(R.id.Password);
        PasswordConfirmation = findViewById(R.id.PasswordConfirmation);
        image = findViewById(R.id.image);
        Line = findViewById(R.id.LineImage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (getIntent().getExtras().getInt("type")==1){
                getWindow().getSharedElementEnterTransition().setDuration(400);
                getWindow().getSharedElementReturnTransition().setDuration(400)
                        .setInterpolator(new DecelerateInterpolator());
            }else
                Password.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        } else
            Password.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
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
    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FullName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
    }

    public void Back(View view) {
        finish();
    }

    public void LoginIn(View view) {
        startActivity(new Intent(this, Login_Activity.class));
    }

    public void createAccount(View view) {
        dialog.show();
        if (FullName.getText().toString().isEmpty()){
            dialog.dismiss();
            FullName.setError("ادخل الأسم");
        }else if (Email.getText().toString().isEmpty()){
            dialog.dismiss();
            Email.setError("ادخل الأيميل");
        }if (!Email.getText().toString().contains(".") || !Email.getText().toString().contains("@") ) {
            dialog.dismiss();
            Email.setError("يجد ان يحتوي الايميل علي . @");
        }
        else if (PhoneNumber.getText().toString().isEmpty()) {
            dialog.dismiss();
            PhoneNumber.setError("ادخل رقم الهاتف");
        }
        else if (Password.getText().toString().isEmpty()) {
            dialog.dismiss();
            Password.setError("ادخل كلمة السر");
        }
        else if (PasswordConfirmation.getText().toString().isEmpty()) {
            dialog.dismiss();
            PasswordConfirmation.setError("ادخل كلمة السر");
        }
        else if (!Password.getText().toString().equals(PasswordConfirmation.getText().toString())) {
            dialog.dismiss();
            Toast.makeText(this, "كلمة السره غير متطابقه", Toast.LENGTH_SHORT).show();
            Password.setError("كلمة السر غير متطابقه");
            PasswordConfirmation.setError("كلمة السر غير متطابقه");
        } else {
            onRegistration(FullName.getText().toString(),PhoneNumber.getText().toString(),
                    Email.getText().toString(),Password.getText().toString());
        }
    }

    private void onRegistration(String ...C){
        APIRequest apiRequest = Common.getAPIRequest();
        apiRequest.onRegistration(new DataUserRegistration(C[0],C[1],C[2],C[3]))
                .enqueue(new Callback<Registration>() {
                    @Override
                    public void onResponse(Call<Registration> call, Response<Registration> response) {
                        dialog.dismiss();
                        if (response.code() == 201){
                            Intent intent = new Intent(NewAccountActivity.this, ActivateYourAccountActivity.class);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                Pair pair[] = new Pair[2];
                                pair[0] = new Pair<View,String>(image,"imageHealth");
                                pair[1] = new Pair<View,String>(Line,"Line");
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NewAccountActivity.this,pair).toBundle());
                            }else
                                startActivity(intent);
                        } else {
                            Log.i("TTTTTT",response.code()+"");
                            try {
                                JSONObject object = new JSONObject(response.errorBody().string());
                                Toast.makeText(NewAccountActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Registration> call, Throwable t) {
                        dialog.dismiss();
                        Log.i(TAG,t.getMessage());
                    }
                });
    }
}
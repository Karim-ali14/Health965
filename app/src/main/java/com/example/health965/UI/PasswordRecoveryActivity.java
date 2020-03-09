package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.health965.Common.Common;
import com.example.health965.Models.ReSetPassword;
import com.example.health965.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordRecoveryActivity extends AppCompatActivity {
    Button ButtonSure;
    ImageView image;
    ConstraintLayout Layout;
    TextInputEditText NewPassword,PasswordConfirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        image = findViewById(R.id.image);
        ButtonSure = findViewById(R.id.ButtonSure);
        NewPassword = findViewById(R.id.NewPassword);
        PasswordConfirmation = findViewById(R.id.PasswordConfirmation);
        ButtonSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NewPassword.getText().toString().isEmpty() && !PasswordConfirmation.getText().toString().isEmpty()
                        && PasswordConfirmation.getText().toString().equals(NewPassword.getText().toString())) {
                    if (getIntent().getExtras().getInt("Type")==0) {
                        Common.getAPIRequest().onVerficationClient(getIntent().getExtras().getString("Email"),
                                getIntent().getExtras().getString("Code"),
                                NewPassword.getText().toString()).enqueue(new Callback<ReSetPassword>() {
                            @Override
                            public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(PasswordRecoveryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PasswordRecoveryActivity.this, Login_Activity.class).putExtra("type", "getPass"));
                                } else {
                                    try {
                                        Toast.makeText(PasswordRecoveryActivity.this
                                                , new JSONObject(response.errorBody().string()).getString("message")
                                                , Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ReSetPassword> call, Throwable t) {

                            }
                        });
                    }else {
                        Common.getAPIRequest().onVerficationClinic(getIntent().getExtras().getString("Email"),
                                getIntent().getExtras().getString("Code"),
                                NewPassword.getText().toString()).enqueue(new Callback<ReSetPassword>() {
                            @Override
                            public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(PasswordRecoveryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PasswordRecoveryActivity.this, Login_Activity.class).putExtra("type", "getPass"));
                                } else {
                                    try {
                                        Toast.makeText(PasswordRecoveryActivity.this
                                                , new JSONObject(response.errorBody().string()).getString("message")
                                                , Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ReSetPassword> call, Throwable t) {

                            }
                        });
                    }

                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().setDuration(400);
            getWindow().getSharedElementReturnTransition().setDuration(400)
                    .setInterpolator(new DecelerateInterpolator());
        }
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
        NewPassword.clearFocus();
        PasswordConfirmation.clearFocus();
    }

    public void Back(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ButtonSure.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
            image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
        }
    }
}

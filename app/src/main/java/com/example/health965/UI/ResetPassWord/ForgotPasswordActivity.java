package com.example.health965.UI.ResetPassWord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    Button ButtonActive;
    ImageView image;
    TextInputEditText PhoneNumber;
    View Line;
    ConstraintLayout Layout;
    TextInputLayout PhoneNumberLayOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ButtonActive = findViewById(R.id.ButtonActive);
        image = findViewById(R.id.image);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        PhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!PhoneNumber.getText().toString().isEmpty()){
                    PhoneNumberLayOut.setErrorEnabled(false);
                    PhoneNumberLayOut.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Line = findViewById(R.id.LineImage);
        PhoneNumberLayOut = findViewById(R.id.PhoneNumberLayOut);
        ButtonActive.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
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
    }
    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        PhoneNumber.clearFocus();
        PhoneNumberLayOut.setErrorEnabled(false);
        PhoneNumberLayOut.setError(null);
    }

    public void Back(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
            ButtonActive.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
        }
    }

    public void Sure(View view) {
        if (!PhoneNumber.getText().toString().isEmpty()) {
            if (getIntent().getExtras().getInt("Type") == 0) {
                Common.getAPIRequest().onSendCodeVerficationClient(getIntent().getExtras().getString("Email")).enqueue(new Callback<ReSetPassword>() {
                    @Override
                    public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                        if (response.code() == 200) {
                            Toast.makeText(ForgotPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this, EnterCodeActivity.class);
                            intent.putExtra("Email", PhoneNumber.getText().toString());
                            intent.putExtra("Type", getIntent().getExtras().getInt("Type"));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Pair pair[] = new Pair[4];
                                pair[0] = new Pair<View, String>(image, "imageHealth");
                                pair[1] = new Pair<View, String>(PhoneNumberLayOut, "EditText");
                                pair[2] = new Pair<View, String>(ButtonActive, "Button");
                                pair[3] = new Pair<View, String>(Line, "Line");
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordActivity.this, pair).toBundle());

                            } else
                                    startActivity(intent);

                        } else {
                            try {
                                Toast.makeText(ForgotPasswordActivity.this, new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReSetPassword> call, Throwable t) {

                    }
                });
            }
            else {
                Common.getAPIRequest().onSendCodeVerficationClinic(getIntent().getExtras().getString("Email")).enqueue(new Callback<ReSetPassword>() {
                    @Override
                    public void onResponse(Call<ReSetPassword> call, Response<ReSetPassword> response) {
                        if (response.code() == 200) {
                            Toast.makeText(ForgotPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this, EnterCodeActivity.class);
                            intent.putExtra("Email", PhoneNumber.getText().toString());
                            intent.putExtra("Type", getIntent().getExtras().getInt("Type"));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Pair pair[] = new Pair[4];
                                pair[0] = new Pair<View, String>(image, "imageHealth");
                                pair[1] = new Pair<View, String>(PhoneNumberLayOut, "EditText");
                                pair[2] = new Pair<View, String>(ButtonActive, "Button");
                                pair[3] = new Pair<View, String>(Line, "Line");
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordActivity.this, pair).toBundle());

                            } else
                                startActivity(intent);
                        } else {
                            try {
                                Toast.makeText(ForgotPasswordActivity.this, new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReSetPassword> call, Throwable t) {

                    }
                });
            }
        }else {
            PhoneNumberLayOut.setErrorEnabled(true);
            PhoneNumberLayOut.setError("ادخل الايميل");
        }
    }
}

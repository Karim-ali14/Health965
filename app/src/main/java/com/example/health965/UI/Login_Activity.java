package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health965.Common.Common;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.Models.LoginUser.LoginUser;
import com.example.health965.R;
import com.example.health965.UI.Main.MainActivity;
import com.example.health965.UI.Registration.NewAccountActivity;
import com.example.health965.UI.ResetPassWord.ForgotPasswordActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {
    Button LoginButton,SkipButton;
    TextInputEditText Phone,Password;
    TextInputLayout PhoneNumberLayout,PasswordLayout;
    ImageView image;
    View Line;
    TextView LoginAsPartner,LoginAsUser;
    RelativeLayout LayoutForgottenPassword;
    RelativeLayout LayoutOfEmail;
    ConstraintLayout Layout;
    int typeUser = 0;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        dialog = new ProgressDialog(this);
        LoginButton = findViewById(R.id.LoginButton);
        SkipButton = findViewById(R.id.Skip);
        LoginAsUser = findViewById(R.id.LoginAsUser);
        LoginAsPartner = findViewById(R.id.LoginAsPartner);
        LayoutForgottenPassword    = findViewById(R.id.LayoutForgottenPassword);
        Password = findViewById(R.id.Password);
        Password.setImeOptions(EditorInfo.IME_ACTION_DONE);
        PhoneNumberLayout = findViewById(R.id.PhoneNumberLayOut);
        PasswordLayout = findViewById(R.id.PasswordLayout);
        Phone = findViewById(R.id.PhoneNumber);
        Phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Phone.getText().toString().isEmpty()){
                    PhoneNumberLayout.setErrorEnabled(false);
                    PhoneNumberLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Password.getText().toString().isEmpty()){
                    PasswordLayout.setErrorEnabled(false);
                    PasswordLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        image = findViewById(R.id.image);
        Line = findViewById(R.id.LineImage);
        LayoutOfEmail = findViewById(R.id.LayoutOfEmail);
        LoginButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        if (getIntent().getExtras().getString("type").equals("main"))
            PasswordLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        else if (getIntent().getExtras().getString("type").equals("getPass"))
            PhoneNumberLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
        LoginAsPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeUser = 1;
               AnimationPartner();
            }
        });
        LoginAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeUser = 0;
                AnimationUser();
            }
        });
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
        Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onValidation(typeUser);
                return false;
            }
        });
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        Phone.clearFocus();
        Password.clearFocus();
        PhoneNumberLayout.setErrorEnabled(false);
        PhoneNumberLayout.setError(null);
        PasswordLayout.setErrorEnabled(false);
        PasswordLayout.setError(null);
    }

    public void Back(View view) {
        finish();
    }

    public void LoginIn(View view) {
        onValidation(typeUser);
    }

    private void onValidation(int typeUser){
        if (Phone.getText().toString().isEmpty())
            PhoneNumberLayout.setError("ادخل رقم الهاتف");
        else if (Password.getText().toString().isEmpty())
            PasswordLayout.setError("ادخل الرقم السري");
        else{
            if (typeUser == 0)
                loginAsUser();
            else
                loginAsPartner();
        }
    }

    private void loginAsUser(){
        dialog.show();
        Common.getAPIRequest().onLoginAsUser(Phone.getText().toString(),
                Password.getText().toString()).enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                dialog.dismiss();
                if (response.code() == 200) {
                    Common.CurrentUser = response.body();
                    startActivity(new Intent(Login_Activity.this, MainActivity.class));
                    finish();
                }
                else if (response.code() == 401) {
                    try {
                        Toast.makeText(Login_Activity.this,new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {

            }
        });
    }
    private void loginAsPartner(){
        Common.getAPIRequest().onLoginAsPartner(Phone.getText().toString(),
                Password.getText().toString()).enqueue(new Callback<LoginClinc>() {
            @Override
            public void onResponse(Call<LoginClinc> call, Response<LoginClinc> response) {
                if (response.code() == 200) {
                    Log.i("TTTTTT",response.body().getData().getToken().getAccessToken());
                    Common.CurrentClinic = response.body();
                    startActivity(new Intent(Login_Activity.this, ClinicRequestsActivity.class));
                    finish();
                }
                else if (response.code() == 401) {
                    try {
                        Toast.makeText(Login_Activity.this,new JSONObject(response.errorBody().string()).getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginClinc> call, Throwable t) {

            }
        });
    }
    public void Skip(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void createNewAccount(View view) {
        Intent intent = new Intent(this, NewAccountActivity.class);
        intent.putExtra("type",1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Pair pair[] = new Pair[2];
            pair[0] = new Pair<View,String>(PasswordLayout,"Pass");
            pair[1] = new Pair<View,String>(PhoneNumberLayout,"Phone");
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(Login_Activity.this,pair);
            getWindow().getSharedElementReturnTransition().setDuration(400) // this For Make Animation Slow
                    .setInterpolator(new DecelerateInterpolator());
            startActivity(intent,options.toBundle());
        }else
            startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LoginButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
        PhoneNumberLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
    }
    public void getPassword(View view) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        intent.putExtra("Type",typeUser);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Pair pair[] = new Pair[2];
            pair[0] = new Pair<View,String>(image,"imageHealth");
            pair[1] = new Pair<View,String>(Line,"Line");
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(Login_Activity.this,pair);
            getWindow().getSharedElementReturnTransition().setDuration(400) // this For Make Animation Slow
                    .setInterpolator(new DecelerateInterpolator());
            startActivity(intent,options.toBundle());
        }else
            startActivity(intent);
    }
    private void AnimationPartner(){
        LoginAsPartner.setBackground(getResources().getDrawable(R.drawable.style_button));
        LoginAsPartner.setTextColor(getResources().getColor(R.color.White));
        LoginAsUser.setBackgroundColor(getResources().getColor(R.color.White));
        LoginAsUser.setTextColor(getResources().getColor(R.color.colorLine));
        ObjectAnimator animationAboveToBownAsPartner = ObjectAnimator.ofFloat(LoginAsPartner, "translationY", 100f);
        animationAboveToBownAsPartner.setDuration(500);
        ObjectAnimator animationLeftToRightAsPartner = ObjectAnimator.ofFloat(LoginAsPartner, "translationX", 30f);
        animationLeftToRightAsPartner.setDuration(500);
        ObjectAnimator animationAboveToBownAsUser = ObjectAnimator.ofFloat(LoginAsUser, "translationY", 100f);
        animationAboveToBownAsPartner.setDuration(300);
        ObjectAnimator animationLeftToRightAsUser = ObjectAnimator.ofFloat(LoginAsUser, "translationX", 30f);
        animationLeftToRightAsPartner.setDuration(300);

        ObjectAnimator animationAboveToBownAsImage = ObjectAnimator.ofFloat(image, "translationY", 100f);
        animationAboveToBownAsImage.setDuration(300);

        ObjectAnimator animationAboveToBownAsLine = ObjectAnimator.ofFloat(Line, "translationY", 100f);
        animationAboveToBownAsLine.setDuration(300);

//        ObjectAnimator animationAboveToBownAsPhone = ObjectAnimator.ofFloat(Phone, "translationY", 100f);
//        animationAboveToBownAsPhone.setDuration(300);

        ObjectAnimator animationAboveToBownAsPhoneLayout = ObjectAnimator.ofFloat(PhoneNumberLayout, "translationY", 100f);
        animationAboveToBownAsPhoneLayout.setDuration(300);

        ObjectAnimator animationAboveToBownAsPass = ObjectAnimator.ofFloat(PasswordLayout, "translationY", 100f);
        animationAboveToBownAsPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsForgotPass = ObjectAnimator.ofFloat(LayoutForgottenPassword, "translationY", 100f);
        animationAboveToBownAsForgotPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsButton = ObjectAnimator.ofFloat(LoginButton, "translationY", 100f);
        animationAboveToBownAsButton.setDuration(300);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animationAboveToBownAsPartner,animationLeftToRightAsPartner,
                animationAboveToBownAsUser,animationLeftToRightAsUser,animationAboveToBownAsImage,
                animationAboveToBownAsLine,animationAboveToBownAsPass,animationAboveToBownAsForgotPass,
                animationAboveToBownAsPhoneLayout,animationAboveToBownAsButton);
        set.start();
        SkipButton.setVisibility(View.GONE);
        LayoutOfEmail.setAlpha(0);
    }
    private void AnimationUser(){
        LoginAsUser.setBackground(getResources().getDrawable(R.drawable.style_button));
        LoginAsUser.setTextColor(getResources().getColor(R.color.White));
        LoginAsPartner.setBackgroundColor(getResources().getColor(R.color.White));
        LoginAsPartner.setTextColor(getResources().getColor(R.color.colorLine));
        ObjectAnimator animationAboveToBownAsPartner = ObjectAnimator.ofFloat(LoginAsPartner, "translationY", 0f);
        animationAboveToBownAsPartner.setDuration(500);
        ObjectAnimator animationLeftToRightAsPartner = ObjectAnimator.ofFloat(LoginAsPartner, "translationX", 0f);
        animationLeftToRightAsPartner.setDuration(500);
        ObjectAnimator animationAboveToBownAsUser = ObjectAnimator.ofFloat(LoginAsUser, "translationY", 0f);
        animationAboveToBownAsPartner.setDuration(300);
        ObjectAnimator animationLeftToRightAsUser = ObjectAnimator.ofFloat(LoginAsUser, "translationX", 0f);
        animationLeftToRightAsPartner.setDuration(300);

        ObjectAnimator animationAboveToBownAsImage = ObjectAnimator.ofFloat(image, "translationY", 0f);
        animationAboveToBownAsImage.setDuration(300);

        ObjectAnimator animationAboveToBownAsLine = ObjectAnimator.ofFloat(Line, "translationY", 0f);
        animationAboveToBownAsLine.setDuration(300);

        ObjectAnimator animationAboveToBownAsPhone = ObjectAnimator.ofFloat(Phone, "translationY", 0f);
        animationAboveToBownAsPhone.setDuration(300);

        ObjectAnimator animationAboveToBownAsPhoneLayout = ObjectAnimator.ofFloat(PhoneNumberLayout, "translationY", 0f);
        animationAboveToBownAsPhone.setDuration(300);

        ObjectAnimator animationAboveToBownAsPass = ObjectAnimator.ofFloat(PasswordLayout, "translationY", 0f);
        animationAboveToBownAsPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsForgotPass = ObjectAnimator.ofFloat(LayoutForgottenPassword, "translationY", 0f);
        animationAboveToBownAsForgotPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsButton = ObjectAnimator.ofFloat(LoginButton, "translationY", 0f);
        animationAboveToBownAsButton.setDuration(300);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animationAboveToBownAsPartner,animationLeftToRightAsPartner,
                animationAboveToBownAsUser,animationLeftToRightAsUser,animationAboveToBownAsImage,
                animationAboveToBownAsLine,animationAboveToBownAsPass,animationAboveToBownAsForgotPass
                ,animationAboveToBownAsPhoneLayout,animationAboveToBownAsButton);
        set.start();
        SkipButton.setVisibility(View.VISIBLE);
        LayoutOfEmail.setAlpha(1);
    }
}

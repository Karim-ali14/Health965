package com.example.health965.UI.Login_In;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.example.health965.Common.Common;
import com.example.health965.Models.FireBaseToken.FireBaseToken;
import com.example.health965.Models.FireBaseToken.FireBaseTokenRespons;
import com.example.health965.Models.LoginClient.LoginClient;
import com.example.health965.Models.LoginClinc.LoginClinc;
import com.example.health965.R;
import com.example.health965.UI.ClinicRequests.ClinicRequestsActivity;
import com.example.health965.UI.EnterYourPhone.EnterYouPhone;
import com.example.health965.UI.Main.MainActivity;
import com.example.health965.UI.Registration.Registration.RegistrationActivity;
import com.example.health965.UI.ResetPassWord.ForgotPassword.ForgotPasswordActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;

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
    Login_InViewModel viewModel;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        preferences = getSharedPreferences(Common.FileName,MODE_PRIVATE);
        viewModel= ViewModelProviders.of(this).get(Login_InViewModel.class);
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
        else if (getIntent().getExtras().getString("type").equals("getPass")
        || getIntent().getExtras().getString("type").equals("Login")) // if user was do any thing that not can do unless login in first
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().setDuration(400);
            getWindow().getSharedElementReturnTransition().setDuration(400)
                    .setInterpolator(new DecelerateInterpolator());
        }
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
                loginAsClinic();
        }
    }

    private void loginAsUser(){
        dialog.show();
        viewModel.onLoginAsClient(Phone.getText().toString(),
                Password.getText().toString(),dialog,this).observe(this,
                new Observer<LoginClient>() {
            @Override
            public void onChanged(LoginClient loginClient) {
                Common.CurrentUser = loginClient;
                saveTokenWithId(Common.CurrentUser.getData().getToken().getAccessToken(),
                        Common.CurrentUser.getData().getUser().getId()+"",
                        Common.CurrentUser.getData().getUser().getFullName()+"",
                        Common.CurrentUser.getData().getUser().getEmail()+"",
                        Common.CurrentUser.getData().getUser().getMobilePhone()+"",
                        typeUser);
                dialog.dismiss();
                if (getIntent().getExtras().getString("type").equals("Login"))
                    finish();
                else {
                    startActivity(new Intent(Login_Activity.this, MainActivity.class));
                    finish();

                    viewModel.onUpDateFireBaseTokenClient(preferences.getString(Common.Token, ""),
                            preferences.getString(Common.ID, ""),
                            new FireBaseToken(FirebaseInstanceId.getInstance().getToken())
                            , Login_Activity.this).observe(Login_Activity.this, new Observer<FireBaseTokenRespons>() {
                        @Override
                        public void onChanged(FireBaseTokenRespons fireBaseTokenRespons) {
                        }
                    });
                }
            }
        });
    }

    private void saveTokenWithId(String Token,
                                 String Id,
                                 String Name,
                                 String Email,
                                 String Phone,
                                 int type) {
        preferences.edit()
                .putString(Common.Token,Token)
                .putString(Common.ID,Id)
                .putInt(Common.Type,type)
                .putString(Common.Name,Name)
                .putString(Common.Email,Email)
                .putString(Common.Phone,Phone)
                .apply();
    }

    private void loginAsClinic(){
        dialog.show();
        viewModel.onLoginAsClinic(Phone.getText().toString(),
                Password.getText().toString(),dialog,this).observe(this,
                new Observer<LoginClinc>() {
                    @Override
                    public void onChanged(LoginClinc loginClinc) {
                        Common.CurrentClinic = loginClinc;
                        saveTokenWithId(Common.CurrentClinic.getData().getToken().getAccessToken(),
                                Common.CurrentClinic.getData().getClinic().getId()+"",
                                Common.CurrentClinic.getData().getClinic().getName()+"",
                                Common.CurrentClinic.getData().getClinic().getEmail()+"",
                                Common.CurrentClinic.getData().getClinic().getMobilePhone()+"",
                                typeUser);
                        dialog.dismiss();
                        startActivity(new Intent(Login_Activity.this, ClinicRequestsActivity.class));
                        finish();
                        viewModel.onUpDateFireBaseTokenClinic(preferences.getString(Common.Token,""),
                                preferences.getString(Common.ID,""),
                                new FireBaseToken(FirebaseInstanceId.getInstance().getToken()),
                                Login_Activity.this).observe(Login_Activity.this
                                , new Observer<FireBaseTokenRespons>() {
                            @Override
                            public void onChanged(FireBaseTokenRespons fireBaseTokenRespons) {

                            }
                        });
                    }
                });
    }

    public void Skip(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void createNewAccount(View view) {
        Intent intent = new Intent(this, EnterYouPhone.class);
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

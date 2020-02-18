package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health965.R;
import com.example.health965.UI.Main.MainActivity;

public class Login_Activity extends AppCompatActivity {
    Button LoginButton,SkipButton;
    EditText Password,Phone;
    ImageView image;
    View Line;
    TextView LoginAsPartner,LoginAsUser;
    RelativeLayout LayoutForgottenPassword;
    RelativeLayout LayoutOfEmail;
    ConstraintLayout Layout;
    int typeUser = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        LoginButton = findViewById(R.id.LoginButton);
        SkipButton = findViewById(R.id.Skip);
        LoginAsUser = findViewById(R.id.LoginAsUser);
        LoginAsPartner = findViewById(R.id.LoginAsPartner);
        LayoutForgottenPassword    = findViewById(R.id.LayoutForgottenPassword);
        Password = findViewById(R.id.Password);
        Phone = findViewById(R.id.PhoneNumber);
        image = findViewById(R.id.image);
        Line = findViewById(R.id.LineImage);
        LayoutOfEmail = findViewById(R.id.LayoutOfEmail);
        LoginButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        Password.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
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
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void Back(View view) {
        finish();
    }

    public void LoginIn(View view) {
        if (typeUser == 0)
            startActivity(new Intent(this,MainActivity.class));
        else
            startActivity(new Intent(this,ClinicRequestsActivity.class));
    }

    public void Skip(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void createNewAccount(View view) {
        Intent intent = new Intent(this, NewAccountActivity.class);
        intent.putExtra("type",1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Pair pair[] = new Pair[2];
            pair[0] = new Pair<View,String>(Password,"Pass");
            pair[1] = new Pair<View,String>(Phone,"Phone");
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
        Phone.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
    }

    public void getPassword(View view) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
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

        ObjectAnimator animationAboveToBownAsPhone = ObjectAnimator.ofFloat(Phone, "translationY", 100f);
        animationAboveToBownAsPhone.setDuration(300);

        ObjectAnimator animationAboveToBownAsPass = ObjectAnimator.ofFloat(Password, "translationY", 100f);
        animationAboveToBownAsPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsForgotPass = ObjectAnimator.ofFloat(LayoutForgottenPassword, "translationY", 100f);
        animationAboveToBownAsForgotPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsButton = ObjectAnimator.ofFloat(LoginButton, "translationY", 100f);
        animationAboveToBownAsButton.setDuration(300);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animationAboveToBownAsPartner,animationLeftToRightAsPartner,
                animationAboveToBownAsUser,animationLeftToRightAsUser,animationAboveToBownAsImage,
                animationAboveToBownAsLine,animationAboveToBownAsPhone,animationAboveToBownAsPass,animationAboveToBownAsForgotPass,animationAboveToBownAsButton);
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

        ObjectAnimator animationAboveToBownAsPass = ObjectAnimator.ofFloat(Password, "translationY", 0f);
        animationAboveToBownAsPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsForgotPass = ObjectAnimator.ofFloat(LayoutForgottenPassword, "translationY", 0f);
        animationAboveToBownAsForgotPass.setDuration(300);

        ObjectAnimator animationAboveToBownAsButton = ObjectAnimator.ofFloat(LoginButton, "translationY", 0f);
        animationAboveToBownAsButton.setDuration(300);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animationAboveToBownAsPartner,animationLeftToRightAsPartner,
                animationAboveToBownAsUser,animationLeftToRightAsUser,animationAboveToBownAsImage,
                animationAboveToBownAsLine,animationAboveToBownAsPhone,animationAboveToBownAsPass,animationAboveToBownAsForgotPass,animationAboveToBownAsButton);
        set.start();
        SkipButton.setVisibility(View.VISIBLE);
        LayoutOfEmail.setAlpha(1);
    }
}

package com.example.health965.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.health965.R;

public class NewAccountActivity extends AppCompatActivity {
    EditText Password,FullName;
    ImageView image;
    View Line;
    ConstraintLayout Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        FullName = findViewById(R.id.FullName);
        Password = findViewById(R.id.Password);
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
        Intent intent = new Intent(this, ActivateYourAccountActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Pair pair[] = new Pair[2];
            pair[0] = new Pair<View,String>(image,"imageHealth");
            pair[1] = new Pair<View,String>(Line,"Line");
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NewAccountActivity.this,pair).toBundle());
        }else
            startActivity(intent);
    }
}

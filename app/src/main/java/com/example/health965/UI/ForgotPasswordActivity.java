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
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.health965.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    Button ButtonActive;
    ImageView image;
    EditText PhoneNumber;
    View Line;
    ConstraintLayout Layout;
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
        Line = findViewById(R.id.LineImage);
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
        Intent intent = new Intent(this, EnterCodeActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Pair pair[] = new Pair[4];
            pair[0] = new Pair<View,String>(image,"imageHealth");
            pair[1] = new Pair<View,String>(PhoneNumber,"EditText");
            pair[2] = new Pair<View,String>(ButtonActive,"Button");
            pair[3] = new Pair<View,String>(Line,"Line");
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordActivity.this,pair).toBundle());
        }else
            startActivity(intent);
    }
}

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

public class EnterCodeActivity extends AppCompatActivity {
    EditText EnterCode;
    ImageView image;
    Button PasswordBack;
    View Line;
    ConstraintLayout Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        EnterCode = findViewById(R.id.EnterCode);
        image = findViewById(R.id.image);
        PasswordBack = findViewById(R.id.PasswordBack);
        Line = findViewById(R.id.LineImage);
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
            PasswordBack.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
            image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
            EnterCode.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        }
    }

    public void getPassword(View view) {
        Intent intent = new Intent(this, PasswordRecoveryActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Pair pair[] = new Pair[2];
            pair[0] = new Pair<View,String>(image,"imageHealth");
            pair[1] = new Pair<View,String>(Line,"Line");
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(EnterCodeActivity.this,pair);
            getWindow().getSharedElementReturnTransition().setDuration(400) // this For Make Animation Slow
                    .setInterpolator(new DecelerateInterpolator());
            startActivity(intent,options.toBundle());
        }else
            startActivity(intent);
    }
}

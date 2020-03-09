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
import com.example.health965.UI.PasswordRecoveryActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterCodeActivity extends AppCompatActivity {
    TextInputEditText EnterCode;
    ImageView image;
    Button PasswordBack;
    View Line;
    ConstraintLayout Layout;
    TextInputLayout EnterCodeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        EnterCode = findViewById(R.id.EnterCode);
        EnterCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!EnterCode.getText().toString().isEmpty()){
                    EnterCodeLayout.setErrorEnabled(false);
                    EnterCodeLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        image = findViewById(R.id.image);
        PasswordBack = findViewById(R.id.PasswordBack);
        Line = findViewById(R.id.LineImage);
        EnterCodeLayout = findViewById(R.id.EnterCodeLayOut);
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
        EnterCode.clearFocus();
        EnterCodeLayout.setErrorEnabled(false);
        EnterCodeLayout.setError(null);
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
            EnterCodeLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        }
    }

    public void getPassword(View view) {
        if (!EnterCode.getText().toString().isEmpty()) {
            Intent intent = new Intent(this, PasswordRecoveryActivity.class);
            intent.putExtra("Email",getIntent().getExtras().getString("Email"));
            intent.putExtra("Type",getIntent().getExtras().getInt("Type"));
            intent.putExtra("Code",EnterCode.getText().toString());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair pair[] = new Pair[3];
                pair[0] = new Pair<View, String>(image, "imageHealth");
                pair[1] = new Pair<View, String>(Line, "Line");
                pair[2] = new Pair<View, String>(EnterCodeLayout, "EditText");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(EnterCodeActivity.this, pair);
                getWindow().getSharedElementReturnTransition().setDuration(400) // this For Make Animation Slow
                        .setInterpolator(new DecelerateInterpolator());
                startActivity(intent, options.toBundle());
            } else
                startActivity(intent);
        }else {
            EnterCodeLayout.setErrorEnabled(true);
            EnterCodeLayout.setError("ادخل الايميل");
        }
    }


}

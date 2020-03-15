package com.example.health965.UI.Registration.ActivateYourAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.health965.R;
import com.google.firebase.auth.FirebaseAuth;

public class ActivateYourAccountActivity extends AppCompatActivity {
    EditText EnterCode;
    ImageView image;
    Button ButtonActive;
    ConstraintLayout Layout;
    FirebaseAuth mAuth;
    ActivateYourAccountViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_your_account);
        init();
    }

    private void init(){
        viewModel = ViewModelProviders.of(this).get(ActivateYourAccountViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        EnterCode = findViewById(R.id.EnterCode);
        image = findViewById(R.id.image);
        ButtonActive = findViewById(R.id.ButtonActive);
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
        viewModel.onSendVerificationMessage(getIntent().getExtras().getString("phone"),this);
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void Back(View view) {
        finish();
    }

    public void active(View view) {
        viewModel.onActive(EnterCode.getText().toString(),mAuth,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ButtonActive.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_above_bown));
            EnterCode.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        }
    }

    public void sendCode(View view) {
        viewModel.onSendVerificationMessage(getIntent().getExtras().getString("phone"),this);
    }

}

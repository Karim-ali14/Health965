package com.example.health965.UI.Registration.ActivateYourAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.health965.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ActivateYourAccountActivity extends AppCompatActivity {
    EditText EnterCode;
    ImageView image;
    Button ButtonActive;
    ConstraintLayout Layout;
    FirebaseAuth mAuth;
    ActivateYourAccountViewModel viewModel;
    TextInputLayout EnterCodeLayOut;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_your_account);
        init();
    }

    private void init(){
        dialog = new ProgressDialog(this);
        dialog.show();
        viewModel = ViewModelProviders.of(this).get(ActivateYourAccountViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        EnterCode = findViewById(R.id.EnterCode);
        EnterCodeLayOut = findViewById(R.id.EnterCodeLayOut);
        EnterCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!EnterCode.getText().toString().isEmpty()){
                    EnterCodeLayOut.setErrorEnabled(false);
                    EnterCodeLayOut.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        viewModel.onSendVerificationMessage(getIntent().getExtras().getString("phone"),this,dialog);
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        EnterCodeLayOut.setErrorEnabled(false);
        EnterCodeLayOut.setError(null);
        EnterCode.clearFocus();
    }

    public void Back(View view) {
        finish();
    }

    public void active(View view) {
        if (!EnterCode.getText().toString().isEmpty()) {
            viewModel.onActive(EnterCode.getText().toString(), mAuth, this, getIntent().getExtras().getString("phone"));
        }else {
            EnterCodeLayOut.setError("ادخل الكود");
        }
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
        dialog.show();
        viewModel.onSendVerificationMessage(getIntent().getExtras().getString("phone"),this,dialog);
    }

}

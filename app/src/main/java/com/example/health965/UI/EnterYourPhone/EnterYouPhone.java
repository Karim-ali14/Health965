package com.example.health965.UI.EnterYourPhone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.health965.R;
import com.example.health965.UI.Registration.ActivateYourAccount.ActivateYourAccountActivity;
import com.example.health965.UI.Registration.Registration.RegistrationActivity;
import com.google.android.material.textfield.TextInputLayout;

public class EnterYouPhone extends AppCompatActivity {
    EditText Phone;
    TextInputLayout PhoneNumberLayOut;
    ProgressDialog dialog;
    ImageView image;
    View Line;
    ConstraintLayout Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_you_phone);
        dialog = new ProgressDialog(this);
        Phone = findViewById(R.id.PhoneNumber);
        PhoneNumberLayOut = findViewById(R.id.PhoneNumberLayOut);
        image = findViewById(R.id.image);
        Line = findViewById(R.id.LineImage);
        PhoneNumberLayOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        Layout = findViewById(R.id.Layout);
        Layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!(v instanceof EditText))
                    closeKeyBoard();
                return false;
            }
        });
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void Back(View view) {
    }

    public void active(View view) {
        dialog.dismiss();
        if (Phone.getText().toString().isEmpty()){
            dialog.dismiss();
            PhoneNumberLayOut.setError("ادخل رقم الهاتف");
        }else {
            Intent intent = new Intent(EnterYouPhone.this, ActivateYourAccountActivity.class);
            //intent.putExtra("phone","+20"+Phone.getText().toString());
            intent.putExtra("phone",Phone.getText().toString());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                Pair pair[] = new Pair[2];
                pair[0] = new Pair<View,String>(image,"imageHealth");
                pair[1] = new Pair<View,String>(Line,"Line");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(EnterYouPhone.this,pair).toBundle());
            }else
                startActivity(intent);
        }
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        PhoneNumberLayOut.setErrorEnabled(false);
        PhoneNumberLayOut.setError(null);
        Phone.clearFocus();
    }
}

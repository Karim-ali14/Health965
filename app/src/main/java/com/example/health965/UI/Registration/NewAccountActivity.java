package com.example.health965.UI.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.health965.API.APIRequest;
import com.example.health965.Common.Common;
import com.example.health965.Models.Regestration.DataUserRegistration;
import com.example.health965.Models.Regestration.Registration;
import com.example.health965.R;
import com.example.health965.UI.Login_Activity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.PhoneAuthCredential;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountActivity extends AppCompatActivity {
    TextInputEditText FullName,Email,Password,PhoneNumber,PasswordConfirmation;
    ImageView image;
    View Line;
    ConstraintLayout Layout;
    ProgressDialog dialog;
    TextInputLayout FullNameLayOut,EmailLayOut,PhoneNumberLayOut,PasswordLayout,PasswordConfirmationLayOut;
    public final static String TAG = "NewAccountActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        dialog = new ProgressDialog(this);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        FullName = findViewById(R.id.FullName);
        Email = findViewById(R.id.Email);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        Password = findViewById(R.id.Password);
        PasswordConfirmation = findViewById(R.id.PasswordConfirmation);
        image = findViewById(R.id.image);
        Line = findViewById(R.id.LineImage);
        FullNameLayOut = findViewById(R.id.FullNameLayOut);
        EmailLayOut = findViewById(R.id.EmailLayOut);
        PhoneNumberLayOut = findViewById(R.id.PhoneNumberLayOut);
        PasswordLayout = findViewById(R.id.PasswordLayout);
        PasswordConfirmationLayOut = findViewById(R.id.PasswordConfirmationLayOut);
        onChangeText();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (getIntent().getExtras().getInt("type")==1){
                getWindow().getSharedElementEnterTransition().setDuration(400);
                getWindow().getSharedElementReturnTransition().setDuration(400)
                        .setInterpolator(new DecelerateInterpolator());
                FullNameLayOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
            }else
                PasswordLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
        } else
            PasswordLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
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
        FullName.clearFocus();
        Email.clearFocus();
        PhoneNumber.clearFocus();
        Password.clearFocus();
        PasswordConfirmation.clearFocus();
        FullNameLayOut.setErrorEnabled(false);
        FullNameLayOut.setError(null);
        EmailLayOut.setErrorEnabled(false);
        EmailLayOut.setError(null);
        PhoneNumberLayOut.setErrorEnabled(false);
        PhoneNumberLayOut.setError(null);
        PasswordLayout.setErrorEnabled(false);
        PasswordLayout.setError(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FullNameLayOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));
    }

    public void Back(View view) {
        finish();
    }

    public void LoginIn(View view) {
        startActivity(new Intent(this, Login_Activity.class).putExtra("type","main"));
    }

    public void createAccount(View view) {
        dialog.show();
        if (FullName.getText().toString().isEmpty()){
            dialog.dismiss();
            FullNameLayOut.setError("ادخل الأسم");
        }else if (Email.getText().toString().isEmpty()){
            dialog.dismiss();
            EmailLayOut.setError("ادخل الأيميل");
        }else if (!Email.getText().toString().contains(".") || !Email.getText().toString().contains("@") ) {
            dialog.dismiss();
            EmailLayOut.setError("يجد ان يحتوي الايميل علي . @");
        }
        else if (PhoneNumber.getText().toString().isEmpty()) {
            dialog.dismiss();
            PhoneNumberLayOut.setError("ادخل رقم الهاتف");
        }
        else if (Password.getText().toString().isEmpty()) {
            dialog.dismiss();
            PasswordLayout.setError("ادخل كلمة السر");
        }
        else if (PasswordConfirmation.getText().toString().isEmpty()) {
            dialog.dismiss();
            PasswordConfirmationLayOut.setError("ادخل كلمة السر");
        }
        else if (!Password.getText().toString().equals(PasswordConfirmation.getText().toString())) {
            dialog.dismiss();
            Toast.makeText(this, "كلمة السره غير متطابقه", Toast.LENGTH_SHORT).show();
            PasswordLayout.setError("كلمة السر غير متطابقه");
            PasswordConfirmationLayOut.setError("كلمة السر غير متطابقه");
        } else {
            onRegistration(FullName.getText().toString(),PhoneNumber.getText().toString(),
                    Email.getText().toString(),Password.getText().toString());
        }
/*
        if (PhoneNumber.getText().toString().isEmpty()) {
            dialog.dismiss();
            PhoneNumberLayOut.setError("ادخل رقم الهاتف");
        }else{
            startActivity(new Intent(NewAccountActivity.this,
                    ActivateYourAccountActivity.class).putExtra("phone","+20"+PhoneNumber.getText().toString()));
        }*/

    }

    private void onRegistration(String ...C){
        APIRequest apiRequest = Common.getAPIRequest();
        apiRequest.onRegistration(new DataUserRegistration(C[0],C[1],C[2],C[3]))
                .enqueue(new Callback<Registration>() {
                    @Override
                    public void onResponse(Call<Registration> call, Response<Registration> response) {
                        dialog.dismiss();
                        if (response.code() == 201){
                            Intent intent = new Intent(NewAccountActivity.this, ActivateYourAccountActivity.class);
                            intent.putExtra("phone","+20"+PhoneNumber.getText().toString());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                Pair pair[] = new Pair[2];
                                pair[0] = new Pair<View,String>(image,"imageHealth");
                                pair[1] = new Pair<View,String>(Line,"Line");
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NewAccountActivity.this,pair).toBundle());
                            }else
                                startActivity(intent);
                        } else {
                            Log.i("TTTTTT",response.code()+"");
                            try {
                                JSONObject object = new JSONObject(response.errorBody().string());
                                Toast.makeText(NewAccountActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Registration> call, Throwable t) {
                        dialog.dismiss();
                        Log.i(TAG,t.getMessage());
                    }
                });
    }
    private void onChangeText(){
        FullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!FullName.getText().toString().isEmpty()){
                    FullNameLayOut.setErrorEnabled(false);
                    FullNameLayOut.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Email.getText().toString().isEmpty()){
                    EmailLayOut.setErrorEnabled(false);
                    EmailLayOut.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        PhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!PhoneNumber.getText().toString().isEmpty()){
                    PhoneNumberLayOut.setErrorEnabled(false);
                    PhoneNumberLayOut.setError(null);
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
        PasswordConfirmation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!PasswordConfirmation.getText().toString().isEmpty()){
                    PasswordConfirmationLayOut.setErrorEnabled(false);
                    PasswordConfirmationLayOut.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
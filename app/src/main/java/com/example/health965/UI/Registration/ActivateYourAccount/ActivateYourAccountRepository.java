package com.example.health965.UI.Registration.ActivateYourAccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.health965.UI.Login_In.Login_Activity;
import com.example.health965.UI.Registration.Registration.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ActivateYourAccountRepository {
    String mVerificationId,CodeOfVerify;
    ProgressDialog dialog;
    Context context;
    public static ActivateYourAccountRepository aYARepository;
    public static ActivateYourAccountRepository getInstance(){
        if (aYARepository == null)
            aYARepository = new ActivateYourAccountRepository();
        return aYARepository;
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.

            //signInWithPhoneAuthCredential(credential);
            Log.i("TTTTTT",credential.getSmsCode());
            CodeOfVerify = credential.getSmsCode();
            if (CodeOfVerify != null){
                dialog.dismiss();
            }else {
                dialog.dismiss();
                Toast.makeText(context,"حاول ارسال رمز التحقق ثانية بعد 10ثواني",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
            }

            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            // mResendToken = token;

            // ...
        }
    };

    public void sendVerificationMessage(String Phone, Context context, ProgressDialog dialog){
        this .dialog = dialog;
        this.context = context;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                Phone,        // Phone number to verify
                30,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                (Activity) context,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential, FirebaseAuth mAuth, final Context context, final String Phone){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, RegistrationActivity.class).putExtra("type", "1").putExtra("phone", Phone)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(context.getApplicationContext(), "رمز التحقق غير صحيح", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void active(String code, FirebaseAuth mAuth, final Context context,String Phone){
       // if (CodeOfVerify != null) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            signInWithPhoneAuthCredential(credential, mAuth, context, Phone);
       /* }else {
        Toast.makeText(context, "الكود لم يرسل بعد", Toast.LENGTH_SHORT).show();
    }*/
    }


}

package com.horseman.flamingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class otpAuthentication extends AppCompatActivity {
    TextView changeNumber;
    EditText getOtp;
    Button verifyOTP;
    String enterOTP;

    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_authentication);

        progressBar = findViewById(R.id.progressBarOfOtpAuth);
        changeNumber = findViewById(R.id.changeNumber);
        getOtp = findViewById(R.id.getOTP);
        verifyOTP = findViewById(R.id.verifyBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        changeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(otpAuthentication.this,MainActivity.class));

            }
        });
        verifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterOTP = getOtp.getText().toString();
                if (enterOTP.isEmpty()){
                    getOtp.setError("OTP");
                    getOtp.requestFocus();
                    return;
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    String codereceived = getIntent().getStringExtra("otp");
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codereceived,enterOTP);
                    signinWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    private void signinWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(otpAuthentication.this, "Logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(otpAuthentication.this,setProfile.class));
                    finish();
                }else{
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(otpAuthentication.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
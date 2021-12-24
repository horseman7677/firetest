package com.horseman.flamingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText getPhoneNumber;
    Button sendOTP;
    CountryCodePicker countryCodePicker;
    String countryCode;
    String phoneNumber;

    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    String codesent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPhoneNumber = findViewById(R.id.getPhoneNumber);
        sendOTP = findViewById(R.id.sendOtpBtm);
        countryCodePicker = findViewById(R.id.countryCodePicker);
        progressBar = findViewById(R.id.progressBarOfMain);

        firebaseAuth = FirebaseAuth.getInstance();

        countryCode = countryCodePicker.getDefaultCountryCodeWithPlus();

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryCode = countryCodePicker.getDefaultCountryCodeWithPlus();
            }
        });

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number;

                number = getPhoneNumber.getText().toString();
                if (number.isEmpty()) {
                    getPhoneNumber.setError("provide Ph.No.");
                    getPhoneNumber.requestFocus();
                    //Toast.makeText(MainActivity.this, "Please enter Ph.No.", Toast.LENGTH_SHORT).show();
                } else if (number.length() != 10) {
                    getPhoneNumber.setError("check Ph.No.");
                    getPhoneNumber.requestFocus();
//                    Toast.makeText(MainActivity.this, "Please enter valid Ph.No.", Toast.LENGTH_SHORT).show();

                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    phoneNumber = countryCode + number;
                    //  Toast.makeText(MainActivity.this, phoneNumber, Toast.LENGTH_SHORT).show();

                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(phoneNumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(MainActivity.this)
                            .setCallbacks(callbacks)
                            .build();

                    PhoneAuthProvider.verifyPhoneNumber(options);


                }
            }
        });
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                // how to fetch code automatically
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toast.makeText(getApplicationContext(), "OTP sent", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                codesent = s;
                startActivity(new Intent(MainActivity.this, otpAuthentication.class).putExtra("otp", codesent));
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
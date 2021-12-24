package com.example.firetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView data;
    EditText email,phone,text;
    Button btn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    //firetest 3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.data);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        text = findViewById(R.id.ptext);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString().trim();
                String phone1 = phone.getText().toString().trim();
                String text1 = text.getText().toString().trim();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                retriveData();

                FetcherBase fetcherBase = new FetcherBase(email1,phone1,text1);
                reference.child(phone1).setValue(fetcherBase);

                Toast.makeText(getApplicationContext(), "uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retriveData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String val = snapshot.getValue(String.class);

                data.setText(val);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "error...", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
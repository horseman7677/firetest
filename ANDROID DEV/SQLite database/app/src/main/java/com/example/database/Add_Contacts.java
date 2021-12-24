package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.database.data.MyDbHandler;

public class Add_Contacts extends AppCompatActivity {
    EditText name ,number;
    Button add_btn;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(Add_Contacts.this,MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        name = findViewById(R.id.in_name);
        number = findViewById(R.id.in_number);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHandler mdb = new MyDbHandler(Add_Contacts.this);
                mdb.addContact(name.getText().toString().trim(),
                               number.getText().toString().trim());
            }
        });

    }
}
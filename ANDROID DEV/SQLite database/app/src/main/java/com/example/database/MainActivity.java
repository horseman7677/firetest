package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.database.Adapter.CustomAdapter;
import com.example.database.data.MyDbHandler;
import com.example.database.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_btn;
    RecyclerView recyclerView;
    MyDbHandler mdb;
    CustomAdapter customAdapter;
    ArrayList<String> id, name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all variable here....
        recyclerView = findViewById(R.id.recylerview);

        add_btn = findViewById(R.id.floatingActionButton);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Add_Contacts.class));
            }
        });

        // initialize arraylist
        mdb = new MyDbHandler(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        number = new ArrayList<>();

        storeDataInArray();
        customAdapter = new CustomAdapter(MainActivity.this, id, name, number);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // 9th insert elements to database

//        Contact contact = new Contact();
//        contact.setName("Rahul");
//        contact.setPhoneNumber("7004467209");
//
//        db.addContact(contact);

        // 10th show retrive data

//        List<Contact> showContact = db.getAllContact();
//        for (Contact print : showContact){
//            Log.d("contact", "Id:"+ print.getId() + "\n" + "name:" + print.getName() + "\n" + "phon:" + print.getPhoneNumber());
//        }

    }

    public void storeDataInArray() {
        Cursor cursor = mdb.readAllData();
        if (cursor == null) Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                number.add(cursor.getString(2));
            }
        }



    }
}
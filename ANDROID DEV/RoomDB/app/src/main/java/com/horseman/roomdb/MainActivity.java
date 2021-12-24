package com.horseman.roomdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.horseman.roomdb.database.AppDatabase;
import com.horseman.roomdb.database.RvAdapter;
import com.horseman.roomdb.database.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txtView1, txtView2;
    Button addBtn;
    RecyclerView recyclerView;
    RvAdapter rvAdapter;
    AppDatabase database;
    LinearLayoutManager linearLayoutManager;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        txtView1 = findViewById(R.id.get_txt1);
        txtView2 = findViewById(R.id.get_txt2);
        addBtn = findViewById(R.id.addBtn);

        database = AppDatabase.getDbInstance(this);

        List<User> userList = database.userDao().getAllUser();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        rvAdapter = new RvAdapter(MainActivity.this, userList);

        recyclerView.setAdapter(rvAdapter);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = txtView1.getText().toString().trim();

                if (!sText.equals("")) {
                    User user = new User();
                    user.firstName = sText;

                    database.userDao().insert(user);
                    txtView1.setText("");
                    userList.clear();
                    userList.addAll(database.userDao().getAllUser());
                    rvAdapter.notifyDataSetChanged();

                }
            }
        });


    }


}
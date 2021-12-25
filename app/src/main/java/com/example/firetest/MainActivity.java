package com.example.firetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    upload retrieve 5
    EditText email,phone,text;
    Button btn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    RecyclerView recyclerView;
    MyAdaptar myAdaptar;
//    ArrayList<FetcherBase> list;



    //firetest 5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FetcherBase> options =
              new FirebaseRecyclerOptions.Builder<FetcherBase>()
                .setQuery(reference,FetcherBase.class).build();

//        list = new ArrayList<>();




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



                FetcherBase fetcherBase = new FetcherBase(email1,phone1,text1);
                reference.child(phone1).setValue(fetcherBase);

                Toast.makeText(getApplicationContext(), "uploaded", Toast.LENGTH_SHORT).show();
            }
        });

//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
//                    FetcherBase fetcherBase = dataSnapshot.getValue(FetcherBase.class);
//                    list.add(fetcherBase);
//                }
//                myAdaptar.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//            }
//        });
        myAdaptar = new MyAdaptar(options);
        recyclerView.setAdapter(myAdaptar);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdaptar.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdaptar.stopListening();
    }

}
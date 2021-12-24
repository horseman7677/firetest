package com.horseman.crew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Details extends AppCompatActivity {
    private int postionPerson;

    TextView name, agency, status, wikipedia;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setTitle("Crew Member");

        Intent intent = getIntent();
        postionPerson = intent.getIntExtra("position", 0);

        name = findViewById(R.id.dName);
        agency = findViewById(R.id.dAgency);
        status = findViewById(R.id.dStatus);
        image = findViewById(R.id.dImage);
        wikipedia = findViewById(R.id.dwikipedia);

        String url = MainActivity.personModelList.get(postionPerson).getImage();
        Glide.with(this).load(url).into(image);
        name.setText(MainActivity.personModelList.get(postionPerson).getName());
        agency.setText(MainActivity.personModelList.get(postionPerson).getAgency());
        status.setText(MainActivity.personModelList.get(postionPerson).getStatus());
        wikipedia.setText(MainActivity.personModelList.get(postionPerson).getWikipedia());

        String wikiUrl = MainActivity.personModelList.get(postionPerson).getWikipedia();
        wikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(wikiUrl);
            }
        });


    }

    private void gotoUrl(String u) {
        Uri uri = Uri.parse(u);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
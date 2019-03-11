package com.example.macbookpro.mobilehci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class RideActivity extends AppCompatActivity {

    ImageButton home;

    Button park;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);
        final Chronometer chronometer = findViewById(R.id.chronometer);

        chronometer.start();
        home = findViewById(R.id.homeBTN2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RideActivity.this,Profile.class);
                startActivity(intent);

            }
        });

        park = findViewById(R.id.parkButton);
        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RideActivity.this,Park.class);
                chronometer.stop();
                startActivity(intent);

            }
        });
    }
}

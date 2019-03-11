package com.example.macbookpro.mobilehci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {
    Button previous, start, park;
    ImageButton logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logout = (ImageButton)findViewById(R.id.logoutBTN);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,Login.class);
                startActivity(intent);

            }
        });

        park = (Button) findViewById(R.id.ParkBTN);
        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,Park.class);
                startActivity(intent);

            }
        });

        start = (Button) findViewById(R.id.startBTN);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,RideActivity.class);
                startActivity(intent);

            }
        });

        previous = (Button) findViewById(R.id.previousBTN);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,Previous_graph.class);
                startActivity(intent);

            }
        });


    }
}

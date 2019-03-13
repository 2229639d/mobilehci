package com.example.macbookpro.mobilehci;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class RideActivity extends AppCompatActivity {

    ImageButton home;

    Button park;

    int count = 1;

    TextView distance, calories, speed;

    final Handler myHandler = new Handler();
    final Timer myTimer = new Timer();

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
                Intent intent = new Intent(RideActivity.this, Profile.class);
                startActivity(intent);

            }
        });

        park = findViewById(R.id.parkButton);
        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RideActivity.this, Park.class);
                chronometer.stop();
                startActivity(intent);

            }
        });
        distance = findViewById(R.id.distance);
        calories = findViewById(R.id.calories);
        speed = findViewById(R.id.speed);

        // Timer
        TimerTask myTask = new TimerTask() {
            public void run() {
                updateUI(); // updateUI method
            }
        };
        myTimer.schedule(myTask,0,1000); // TimerTask, delay, period
    }

    // Runnable method
    final Runnable myRunnable = new Runnable() {
        public void run() {
            distance.setText(String.format("Distance:   %s meters", String.valueOf(count*2))); // update your text
            calories.setText(String.format("Calories:   %s kcal", String.valueOf(count*4))); // update your text
            speed.setText(String.format("Speed:   %s mph", String.valueOf(count/3))); // update your text
        }
    };

    // updateUI method related to a Runnable
    private void updateUI() {
        if(count < 100) {
            count++;
            // num.setText(String.valueOf(i)); = avoid the RunTime error
            myHandler.post(myRunnable); // relate this to a Runnable
        } else {
            myTimer.cancel(); // stop the timer
            return;
        }
    }
}

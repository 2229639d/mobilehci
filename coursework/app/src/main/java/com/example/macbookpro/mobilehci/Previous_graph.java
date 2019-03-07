package com.example.macbookpro.mobilehci;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Previous_graph extends AppCompatActivity {


    BarChart barChart;
    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;
    ImageButton homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_previous_graph);

        homeBtn =(ImageButton)  findViewById(R.id.homeBTN);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Previous_graph.this, Profile.class);
                startActivity(intent);
                //  map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,10));
            }
        });



        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(33f,1));
        barEntries.add(new BarEntry(22f,2));
        barEntries.add(new BarEntry(11f,3));
        barEntries.add(new BarEntry(66f,4));
        barEntries.add(new BarEntry(77f,5));
        barEntries.add(new BarEntry(100f,6));
        barEntries.add(new BarEntry(20f,7));


        BarDataSet barDataSet = new BarDataSet(barEntries,"Miles");
        barDataSet.setValueTextSize(20f);
        barDataSet.getStackLabels();
        barDataSet.setLabel("Miles");

        barDataSet.setBarSpacePercent(10f);
        barDataSet.setValueTextColor(Color.rgb(42, 171, 207));
        barDataSet.setColor(Color.rgb(100, 251, 208));
        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("28/02");
        theDates.add("01/03");
        theDates.add("02/03");
        theDates.add("03/03");
        theDates.add("04/03");
        theDates.add("05/03");
        theDates.add("06/03");
        theDates.add("07/03");

        BarData theData = new BarData(theDates,barDataSet);
        barChart.setDescription(null);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.invalidate();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(100f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


    }




}
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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Previous_graph extends AppCompatActivity {


    BarChart barChart;
    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;
    ImageButton homeBtn;

    public static ArrayList<String> theDates;
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
        barEntries.add(new BarEntry(44f,0, "44"));
        barEntries.add(new BarEntry(32f,1,"32"));
        barEntries.add(new BarEntry(22f,2,"22"));
        barEntries.add(new BarEntry(12f,3,"12"));
        barEntries.add(new BarEntry(11f,4,"11"));
        barEntries.add(new BarEntry(22f,5,"22"));
        barEntries.add(new BarEntry(34f,6,"34"));





        BarDataSet barDataSet = new BarDataSet(barEntries,"Miles");
        barDataSet.setValueTextSize(20f);
        barDataSet.getStackLabels();
        barDataSet.setLabel("Miles");

        barDataSet.setValueFormatter(new ValueFormatter() {
                                         @Override
                                         public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                                             return entry.getData().toString();
                                         }
                                     });




                barDataSet.setValueTextColor(Color.rgb(42, 171, 207));
        barDataSet.setColor(Color.rgb(100, 251, 208));
        theDates = new ArrayList<>();
        theDates = new ArrayList<>();

        theDates.add("01 Mar");
        theDates.add("02 Mar");
        theDates.add("03 Mar");
        theDates.add("04 Mar");
        theDates.add("05 Mar");
        theDates.add("06 Mar");
        theDates.add("07 Mar");


        final BarData theData = new BarData(theDates,barDataSet);
        barChart.getLegend().setTextSize(100f);
        barChart.setDescription(null);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.invalidate();

        barChart.getAxisLeft().setTextSize(100f);
        barChart.getAxisRight().setDrawLabels(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(15f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValues(theDates);
        xAxis.setSpaceBetweenLabels(0);



    }




}
package com.example.macbookpro.mobilehci;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.formatter.ValueFormatter;
//import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
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

    public static ArrayList<String> days;
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
        barEntries.add(new BarEntry(1,990));
        barEntries.add(new BarEntry(2,1200));
        barEntries.add(new BarEntry(3,1500));
        barEntries.add(new BarEntry(4,990));
        barEntries.add(new BarEntry(5,500));
        barEntries.add(new BarEntry(6,2000));
        barEntries.add(new BarEntry(7,1800));


       final ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(1,170));
        barEntries2.add(new BarEntry(2,230));
        barEntries2.add(new BarEntry(3,270));
        barEntries2.add(new BarEntry(4,170));
        barEntries2.add(new BarEntry(5,80));
        barEntries2.add(new BarEntry(6,380));
        barEntries2.add(new BarEntry(7,360));


        BarDataSet barDataSet = new BarDataSet(barEntries,"meters");
        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"kcal");
        barDataSet.setValueFormatter(new MyValueFormatter());
        barDataSet2.setValueFormatter(new MyValueFormatter());
        barDataSet.setValueTextSize(20f);
        barDataSet.getStackLabels();
        barDataSet.setLabel("Meters");
        barDataSet.setColor(Color.GREEN);
//

        barDataSet2.setValueTextSize(20f);

        barDataSet2.getStackLabels();
        barDataSet2.setLabel("Kcal");
        barDataSet2.setColor(Color.RED);

        barDataSet.setValueTextColor(Color.rgb(24, 78, 0));
        barDataSet.setColor(Color.rgb(29, 142, 12));

        barDataSet2.setValueTextColor(Color.rgb(208, 17, 17));
        barDataSet2.setColor(Color.rgb(220, 93, 38));

        BarData theData = new BarData(barDataSet,barDataSet2);




        barChart.getLegend().setTextSize(100f);
        barChart.setDescription(null);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.invalidate();

        barChart.getAxisLeft().setTextSize(100f);
        barChart.getAxisRight().setDrawLabels(false);

        days = new ArrayList<>();

        //  final BarData theData = new BarData(theDates,barDataSet);
        days.add("01 Mar");
        days.add("02 Mar");
        days.add("03 Mar");
        days.add("04 Mar");
        days.add("05 Mar");
        days.add("06 Mar");
        days.add("07 Mar");


        XAxis xAxis = barChart.getXAxis();

        xAxis.setCenterAxisLabels(true);
        xAxis.setTextSize(12f);
        xAxis.setAxisLineWidth(0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));


        barChart.setExtraBottomOffset(50);


        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(7);

        float barSpace = 0f;
        float groupSpace = 0.2f;

        theData.setBarWidth(0.4f);
        barChart.getXAxis().setAxisMinimum(0+barChart.getBarData().getGroupWidth(groupSpace,barSpace)*7);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.groupBars(0,groupSpace,barSpace);
       // barChart.invalidate();

        //   xAxis.setValues(theDates);
        //   xAxis.setSpaceBetweenLabels(0);

  //  barChart.setViewPortOffsets(1f, 1f, 10f, 10f);

    }
    public class MyValueFormatter implements IValueFormatter {


        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here

    if(value<100 && value >=10){
        return String.format("%.2g%n", value);
    }else if(value<1000&&value>=100){
                return String.format("%.3g%n", value);
            }
    else if(value<10000&&value>=1000){
        return String.format("%.4g%n", value);
    }
    else{
        return String.format("%.1g%n", value);
    }

        }
    }

    public class MyValueFormatter2 implements IValueFormatter {

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return String.format("%.2g%n", value);
        }
    }
}
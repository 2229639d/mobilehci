package com.example.macbookpro.mobilehci;


import android.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class Park extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener

{

    ArrayList<bicyclePostion> bicycle_park_positin =new ArrayList<bicyclePostion>();
    public LatLng startLatLng = new LatLng(55.872503, -4.289119);       //polyline 시작점

    GoogleMap map;
    Polyline line1;
    Polyline line2;
    Polyline line3;
    Polyline line4;
    Polyline line5;
    ImageButton homeBtn;
    ImageButton plusBtn;
    ImageButton minusBtn;
    ImageButton currentBtn;
    TextView mileText;
    TextView timeText;
    int zoom = 16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bicycle_park_positin.add(new bicyclePostion(55.874957, -4.292568,"bicycle park 1"));
        bicycle_park_positin.add(new bicyclePostion(55.872678, -4.292146,"bicycle park 2"));
        bicycle_park_positin.add(new bicyclePostion(55.870885, -4.288744,"bicycle park 3"));
        bicycle_park_positin.add(new bicyclePostion(55.874534, -4.289007,"bicycle park 4"));
        bicycle_park_positin.add(new bicyclePostion(55.872104, -4.285789,"bicycle park 5"));

        initial_builder();
    }

    public void initial_builder(){

        homeBtn =(ImageButton)  findViewById(R.id.homeBTN);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Park.this, Profile.class);
                startActivity(intent);
                //  map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,10));
            }
        });

        plusBtn =(ImageButton)  findViewById(R.id.plusBTN);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom=zoom+1;
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,zoom));
            }
        });

        minusBtn =(ImageButton)  findViewById(R.id.minusBTN);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom=zoom-1;
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,zoom));
            }
        });

        currentBtn =(ImageButton)  findViewById(R.id.current_positionBTN);
        currentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,16));
            }
        });

        mileText = (TextView) findViewById(R.id.mileTxtView);
        timeText = (TextView) findViewById(R.id.timeTxtView);

        mileText.setVisibility(View.GONE);
        timeText.setVisibility(View.GONE);
    }
    @Override
    public void onMapReady(GoogleMap _map) {
        map = _map;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,zoom));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(startLatLng);
        markerOptions.title("Glasgow");
        markerOptions.snippet("This is Glasgow");

        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.current_position);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 200, 200, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        map.addMarker(markerOptions);

        map.setOnMarkerClickListener(this);

        for(int i= 0; i<5; i++){

            markerOptions.position(new LatLng(bicycle_park_positin.get(i).getV(),bicycle_park_positin.get(i).getV2()));
            markerOptions.title(bicycle_park_positin.get(i).getParkName());
            bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.bicycle_park);
            b=bitmapdraw.getBitmap();
            smallMarker = Bitmap.createScaledBitmap(b, 200, 200, true);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));


            map.addMarker(markerOptions);
        }
        init_draw_polyline();
    }
public void init_draw_polyline(){
    line1 =  map.addPolyline(new PolylineOptions()
            .add(startLatLng,
                    new LatLng(55.872677, -4.290650),
                    new LatLng(55.873466, -4.291661),
                    new LatLng(55.873516, -4.291712),
                    new LatLng(55.873888, -4.292219),
                    new LatLng(55.873973, -4.292306),
                    new LatLng(55.874079, -4.292492),
                    new LatLng(55.874178, -4.292570),
                    new LatLng(55.874261, -4.292583),
                    new LatLng(55.874291, -4.292578),
                    new LatLng(55.874320, -4.292565),
                    new LatLng(55.874447, -4.292452),
                    new LatLng(55.874473, -4.292539),
                    new LatLng(55.874547, -4.292620),
                    new LatLng(55.874571, -4.292620),
                    new LatLng(55.874888, -4.292304),
                    new LatLng(55.874940, -4.292469),
                    new LatLng(55.874960, -4.292523),
                    new LatLng(55.874951, -4.292600),
                    new LatLng(55.874958, -4.292669),
                    new LatLng(55.874976, -4.292735),
                    new LatLng(bicycle_park_positin.get(0).getV(),bicycle_park_positin.get(0).getV2()))
            .width(5)
            .color(Color.RED));
    line1.setVisible(false);

    line2 =  map.addPolyline(new PolylineOptions()
            .add(startLatLng,
                    new LatLng(55.872671, -4.290667),
                    new LatLng(55.872752, -4.291641),
                    new LatLng(55.872853, -4.292183),
                    new LatLng(55.872795, -4.292225),
                    new LatLng(55.872758, -4.292117),
                    new LatLng(55.872696, -4.292133),
                    new LatLng(bicycle_park_positin.get(1).getV(),bicycle_park_positin.get(1).getV2()))
            .width(5)
            .color(Color.RED));

    line2.setVisible(false);

    line3 =  map.addPolyline(new PolylineOptions()
            .add(startLatLng,
                    new LatLng(55.872060, -4.289242),
                    new LatLng(55.872041, -4.289384),
                    new LatLng(55.872079, -4.289751),
                    new LatLng(55.871452, -4.290051),
                    new LatLng(55.871375, -4.290021),
                    new LatLng(55.871083, -4.290141),
                    new LatLng(55.870885, -4.288744),
                    new LatLng(bicycle_park_positin.get(2).getV(),bicycle_park_positin.get(2).getV2()))
            .width(5)
            .color(Color.RED));
    line3.setVisible(false);

    line4 =  map.addPolyline(new PolylineOptions()
            .add(startLatLng,
                    new LatLng(55.874369, -4.287667),
                    new LatLng(55.874679, -4.288873),
                    new LatLng(bicycle_park_positin.get(3).getV(),bicycle_park_positin.get(3).getV2()))
            .width(5)
            .color(Color.RED));
    line4.setVisible(false);

    line5 =  map.addPolyline(new PolylineOptions()
            .add(startLatLng,
                    new LatLng(55.872209, -4.287120),

                    new LatLng(bicycle_park_positin.get(4).getV(),bicycle_park_positin.get(4).getV2()))
            .width(5)
            .color(Color.RED));
    line5.setVisible(false);
}

    @Override
    public boolean onMarkerClick(Marker marker) {
        //Toast.makeText(this, marker.getTitle(),Toast.LENGTH_LONG).show();
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        line4.setVisible(false);
        line5.setVisible(false);

        mileText.setVisibility(View.GONE);
        timeText.setVisibility(View.GONE);
        String name = marker.getTitle();
        switch (name){
            case "bicycle park 1":
                line1.setVisible(true);
                mileText.setVisibility(View.VISIBLE);
                timeText.setVisibility(View.VISIBLE);
                mileText.setText("1600 meters");
                timeText.setText("20 minutes");
              //  Toast.makeText(getApplicationContext(), "bicycle park 1", Toast.LENGTH_LONG).show();
                break;

            case "bicycle park 2":
                line2.setVisible(true);
                mileText.setVisibility(View.VISIBLE);
                timeText.setVisibility(View.VISIBLE);
                mileText.setText("800 meters");
                timeText.setText("10 minutes");
               // Toast.makeText(getApplicationContext(), "bicycle park 2", Toast.LENGTH_LONG).show();
                break;

            case "bicycle park 3":
                line3.setVisible(true);
                mileText.setVisibility(View.VISIBLE);
                timeText.setVisibility(View.VISIBLE);
                mileText.setText("1200 meters");
                timeText.setText("14 minutes");
            //    Toast.makeText(getApplicationContext(), "bicycle park 3", Toast.LENGTH_LONG).show();
                break;
            case "bicycle park 4":
                line4.setVisible(true);
                mileText.setVisibility(View.VISIBLE);
                timeText.setVisibility(View.VISIBLE);
                mileText.setText("1050 meters");
                timeText.setText("12 minutes");
             //   Toast.makeText(getApplicationContext(), "bicycle park 4", Toast.LENGTH_LONG).show();
                break;
            case "bicycle park 5":
               line5.setVisible(true);
                mileText.setVisibility(View.VISIBLE);
                timeText.setVisibility(View.VISIBLE);
                mileText.setText("800 meters");
                timeText.setText("10 minutes");
                //Toast.makeText(getApplicationContext(), "bicycle park 5", Toast.LENGTH_LONG).show();
                break;
            default:


     }



        return true;

    }




    class bicyclePostion{
        double v;
        double v2;
        String parkName;

        public bicyclePostion(Double _v, Double _v2, String _parkName) {
         this.v=_v;
         this.v2=_v2;
         this.parkName = _parkName;

        }
        public double getV(){
            return v;
        }
        public double getV2(){
            return v2;
        }
        public String getParkName(){
            return parkName;
        }

    }
}

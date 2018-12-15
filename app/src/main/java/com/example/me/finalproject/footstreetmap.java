package com.example.me.finalproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class footstreetmap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double toLat;
    Double toLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footstreetmap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(25.043446, 121.531813);
        LatLng lauchbox = new LatLng(25.043335, 121.531259);
        LatLng landi = new LatLng(25.043624, 121.531733);
        LatLng trashnoddle = new LatLng(25.043497, 121.531270);
        LatLng beefnoddle579 = new LatLng(25.043540, 121.531897);
        LatLng sakadon = new LatLng(25.043527, 121.531557);
        LatLng beefnoodle95 = new LatLng(25.043754, 121.531975);
        LatLng satoseiniku = new LatLng(25.043556, 121.531983);
        LatLng yangdumplin = new LatLng(25.043556, 121.531844);
        LatLng noodlericefull = new LatLng(25.043534, 121.531708);
        LatLng seafoodnoodle = new LatLng(25.043641, 121.531834 );
        LatLng oldnoodle = new LatLng(25.043618, 121.531672);
        LatLng impasta = new LatLng(25.043607, 121.531609);
        LatLng matsunoodle = new LatLng(25.043596, 121.531517);
        LatLng karirice = new LatLng(25.043595, 121.531468);
        LatLng slivenoodle = new LatLng(25.043592, 121.531379);
        LatLng indiacari = new LatLng(25.043497, 121.531502);
        LatLng sfridericeydney = new LatLng(25.043411, 121.531295);
        LatLng vietname = new LatLng(25.043359, 121.531493);

        mMap.addMarker(new MarkerOptions().position(lauchbox).title("光華飯包"));
        mMap.addMarker(new MarkerOptions().position(landi).title("藍迪義大利麵館"));
        mMap.addMarker(new MarkerOptions().position(trashnoddle).title("光華垃圾面"));
        mMap.addMarker(new MarkerOptions().position(beefnoddle579).title("伍柒玖牛肉麵"));
        mMap.addMarker(new MarkerOptions().position(sakadon).title("佐賀丼飯"));
        mMap.addMarker(new MarkerOptions().position(beefnoodle95).title("玖伍牛肉麵"));
        mMap.addMarker(new MarkerOptions().position(satoseiniku).title("佐藤精肉店"));
        mMap.addMarker(new MarkerOptions().position(yangdumplin).title("楊記大餛飩"));
        mMap.addMarker(new MarkerOptions().position(noodlericefull).title("麵足飯飽便當"));
        mMap.addMarker(new MarkerOptions().position(seafoodnoodle).title("鼎吉粥棧"));
        mMap.addMarker(new MarkerOptions().position(oldnoodle).title("老德記牛肉麵"));
        mMap.addMarker(new MarkerOptions().position(impasta).title("I'm PASTA"));
        mMap.addMarker(new MarkerOptions().position(matsunoodle).title("馬祖麵館"));
        mMap.addMarker(new MarkerOptions().position(karirice).title("咖食堂"));
        mMap.addMarker(new MarkerOptions().position(slivenoodle).title("銀記牛肉麵"));
        mMap.addMarker(new MarkerOptions().position(indiacari).title("印度風咖哩"));
        mMap.addMarker(new MarkerOptions().position(sfridericeydney).title("喬喜蛋炒飯"));
        mMap.addMarker(new MarkerOptions().position(vietname).title("好滋味越南美食"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMinZoomPreference(18.0f);


    }

}

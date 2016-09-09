package com.djcanadastudio.appifymap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.djcanadastudio.appifymap.Model.Phones;
import com.djcanadastudio.appifymap.Model.Trip;
import com.djcanadastudio.appifymap.Service.AppifyServer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Geocoder mGeocoder;
    private Marker pickMarker;
    private Marker destinationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        // instantiate
        mGeocoder = new Geocoder( this );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
        setUpMapIfNeeded();

        final EditText userPickupLocation = (EditText) findViewById(R.id.userPickupLocation);
        Drawable x = getResources().getDrawable(R.drawable.x);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        userPickupLocation.setCompoundDrawables(null, null, x, null);
        userPickupLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    String hint = userPickupLocation.getText().toString();
                    MapsActivity.this.pinPickup(hint);
//                    userLocation.setText("");
                    return true;
                } else {

                }
                return false;
            }
        });

        userPickupLocation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getX() > (view.getWidth() - 100)) {
                        ((EditText) view).setText("");
                    }
                }
                return false;
            }
        });

        final EditText userDestinationLocation = (EditText) findViewById(R.id.userDestinationLocation);
        userDestinationLocation.setCompoundDrawables(null, null, x, null);
        userDestinationLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    String hint = userDestinationLocation.getText().toString();
                    MapsActivity.this.pinDestination(hint);
//                    userLocation.setText("");
                    return true;
                } else {

                }
                return false;
            }
        });

        userDestinationLocation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getX() > (view.getWidth() - 100)) {
                        ((EditText) view).setText("");
                    }
                }
                return false;
            }
        });


//

//

        // pin each of Algonquin College's campuses to the map
//        pin( Constants.JAZAN);
//        pin( Constants.PEMBROKE );
//        pin( Constants.PERTH );
//        pin( Constants.WOODROFFE );

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    /** Locate and pin locationName to the map. */
    private void pin( String locationName ) {
        try {
            Address address = mGeocoder.getFromLocationName(locationName, 1).get(0);
            LatLng ll = new LatLng( address.getLatitude(), address.getLongitude() );
            mMap.clear();
            mMap.addMarker( new MarkerOptions().position(ll).title(locationName) );
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
            Toast.makeText(this, "Pinned: " + locationName, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Not found: " + locationName + "E :"+e, Toast.LENGTH_LONG).show();
        }
    }

    /** Locate and pin locationName to the map. */
    private void pinPickup( String locationName ) {
        try {
            Address address = mGeocoder.getFromLocationName(locationName, 1).get(0);
            LatLng ll = new LatLng( address.getLatitude(), address.getLongitude() );
            if(pickMarker!=null){ pickMarker.remove(); }
            pickMarker=mMap.addMarker( new MarkerOptions().position(ll).title(locationName).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("dest_maker",50,50))));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
            Toast.makeText(this, "Pinned: " + locationName, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Not found: " + locationName + "E :"+e, Toast.LENGTH_LONG).show();
        }
    }

    /** Locate and pin locationName to the map. */
    private void pinDestination( String locationName ) {
        try {
            Address address = mGeocoder.getFromLocationName(locationName, 1).get(0);
            LatLng ll = new LatLng( address.getLatitude(), address.getLongitude() );
            if(destinationMarker!=null){ destinationMarker.remove(); }
            destinationMarker=mMap.addMarker( new MarkerOptions().position(ll).title(locationName).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("pickup_maker",50,50))) );
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
            Toast.makeText(this, "Pinned: " + locationName, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Not found: " + locationName + "E :"+e, Toast.LENGTH_LONG).show();
        }
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void sendOrder(View view) {
        Trip testTrip=new Trip();
        testTrip.setPickup_address("124 ROBERTSON ROAD");
        testTrip.setPickup_city("Nepean, ONTARIO");
        testTrip.setPickup_country("Canada");
        testTrip.setPickup_time("Immediate");
        testTrip.setPickup_time_zone("US/Eastern");
        testTrip.setPickup_latitude(45.000);
        testTrip.setPickup_longitude(-75.000);
        testTrip.setNum_passengers(1);
        testTrip.setPayment_method("credit");
        testTrip.setPhones(new Phones("5435435435", ""));
        testTrip.setNotes("");
        testTrip.setAccount_number("5435435435");
        testTrip.setPassenger_name("");
        AppifyServer.getInstance().createNewTrip(testTrip,null,null);
    }
}


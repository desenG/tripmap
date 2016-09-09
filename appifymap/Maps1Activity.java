package com.djcanadastudio.appifymap;


import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.djcanadastudio.appifymap.locationsearchdialog.PlaceProvider;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps1Activity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mGoogleMap = fragment.getMap();

        handleIntent(getIntent());

        onSearchRequested();

    }

    private void handleIntent(Intent intent){
        if(intent.getAction().equals(Intent.ACTION_SEARCH)){
            doSearch(intent.getStringExtra(SearchManager.QUERY));
        }else if(intent.getAction().equals(Intent.ACTION_VIEW)){
            getPlace(intent.getStringExtra(SearchManager.EXTRA_DATA_KEY));
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void doSearch(String query){
        Bundle data = new Bundle();
        data.putString("query", query);
        getSupportLoaderManager().restartLoader(0, data, this);
    }

    private void getPlace(String query){
        Bundle data = new Bundle();
        data.putString("query", query);
        getSupportLoaderManager().restartLoader(1, data, this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor c) {
        showLocations(c);
    }

    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle query) {
        CursorLoader cLoader = null;
        if(arg0==0)
            cLoader = new CursorLoader(getBaseContext(), PlaceProvider.SEARCH_URI, null, null, new String[]{ query.getString("query") }, null);
        else if(arg0==1)
            cLoader = new CursorLoader(getBaseContext(), PlaceProvider.DETAILS_URI, null, null, new String[]{ query.getString("query") }, null);
        return cLoader;
    }



    private void showLocations(Cursor c){
        MarkerOptions markerOptions = null;
        LatLng position = null;
        mGoogleMap.clear();
        while(c.moveToNext()){
            markerOptions = new MarkerOptions();
            position = new LatLng(Double.parseDouble(c.getString(1)),Double.parseDouble(c.getString(2)));
            markerOptions.position(position);
            markerOptions.title(c.getString(0));
            mGoogleMap.addMarker(markerOptions);
        }
        if(position!=null){
            CameraUpdate cameraPosition = CameraUpdateFactory.newLatLng(position);
            mGoogleMap.animateCamera(cameraPosition);
        }
    }
}

//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
//    private Geocoder mGeocoder;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        setUpMapIfNeeded();
//        // instantiate
//        mGeocoder = new Geocoder( this );
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
////        setUpMapIfNeeded();
//    }
//
//    /**
//     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
//     * installed) and the map has not already been instantiated.. This will ensure that we only ever
//     * call {@link #setUpMap()} once when {@link #mMap} is not null.
//     * <p/>
//     * If it isn't installed {@link SupportMapFragment} (and
//     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
//     * install/update the Google Play services APK on their device.
//     * <p/>
//     * A user can return to this FragmentActivity after following the prompt and correctly
//     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
//     * have been completely destroyed during this process (it is likely that it would only be
//     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
//     * method in {@link #onResume()} to guarantee that it will be called.
//     */
//    private void setUpMapIfNeeded() {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
//                    .getMap();
//            // Check if we were successful in obtaining the map.
//            if (mMap != null) {
//                setUpMap();
//            }
//        }
//    }
//
//    /**
//     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
//     * just add a marker near Africa.
//     * <p/>
//     * This should only be called once and when we are sure that {@link #mMap} is not null.
//     */
//    private void setUpMap() {
////        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
////        mMap = googleMap;
//        setUpMapIfNeeded();
//
//        final EditText userLocation = (EditText) findViewById(R.id.userLocation);
//        Drawable x = getResources().getDrawable(R.drawable.x);
//        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
//        userLocation.setCompoundDrawables(null, null, x, null);
//        userLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//
//                    String hint = userLocation.getText().toString();
//                    MapsActivity.this.pin(hint);
////                    userLocation.setText("");
//                    return true;
//                } else {
//
//                }
//                return false;
//            }
//        });
//
//        userLocation.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    if (motionEvent.getX() > (view.getWidth() - 100)) {
//                        ((EditText) view).setText("");
//                    }
//                }
//                return false;
//            }
//        });
//
//
////
//
////
//
//        // pin each of Algonquin College's campuses to the map
////        pin( Constants.JAZAN);
////        pin( Constants.PEMBROKE );
////        pin( Constants.PERTH );
////        pin( Constants.WOODROFFE );
//
//        // Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(-34, 151);
////        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//
//    /** Locate and pin locationName to the map. */
//    private void pin( String locationName ) {
//        try {
//            Address address = mGeocoder.getFromLocationName(locationName, 1).get(0);
//            LatLng ll = new LatLng( address.getLatitude(), address.getLongitude() );
//            mMap.clear();
//            mMap.addMarker( new MarkerOptions().position(ll).title(locationName) );
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
//            Toast.makeText(this, "Pinned: " + locationName, Toast.LENGTH_SHORT).show();
//
//        } catch (Exception e) {
//            Toast.makeText(this, "Not found: " + locationName + "E :"+e, Toast.LENGTH_LONG).show();
//        }
//    }
//}


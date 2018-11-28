package com.example.isaac.metrolinqclientapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityd extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "PlacesTest";
    private GoogleMap mMap;

    private LatLng testLatlng;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activityd);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng pom = new LatLng(-9.4438, 147.1803);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pom, 13));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pom));


        // places Search
        originSearch();
        destinationSearch();


    }

    private void destinationSearch() {

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_destination);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.



                testLatlng = place.getLatLng();
                textView = findViewById(R.id.textTV);

                textView.setText(place.getName());

                LatLng sydney = new LatLng(testLatlng.latitude, testLatlng.longitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in "+ place.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.d(TAG, "An error occurred: " + status);
            }
        });

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("PG")
                .build();

        autocompleteFragment.setFilter(typeFilter);
    }

    private void originSearch() {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                mMap.clear();


                testLatlng = place.getLatLng();
                textView = findViewById(R.id.textTV);

                textView.setText(place.getName());

                LatLng sydney = new LatLng(testLatlng.latitude, testLatlng.longitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in "+ place.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.d(TAG, "An error occurred: " + status);
            }
        });

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("PG")
                .build();

        autocompleteFragment.setFilter(typeFilter);

    }
}

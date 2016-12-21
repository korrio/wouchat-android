package com.module.candychat.net.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.module.candychat.net.R;

public class WhereMapFragment extends SupportMapFragment implements OnMapReadyCallback, LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnMapClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static int MAP_LOADER = 3;

    public static final String KEY_PLACE_ID = "KEY_PLACE_ID";
    public static final String KEY_PLACE_LATITUDE = "KEY_PLACE_LATITUDE";
    public static final String KEY_PLACE_LONGITUDE = "KEY_PLACE_LONGITUDE";
    public static final String KEY_MAP_VIEW = "KEY_MAP_VIEW";
    public static final String KEY_MAP_CLICKABLE = "KEY_MAP_CLICKABLE";

    public static int MAP_BY_ID = 0;
    public static int MAP_BY_LATLNG = 1;
    public static int MAP_ALL = 2;

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 1000;

    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private MarkerOptions editableMarker;
    private CircleOptions editableRadius;

    private int viewMapBy;
    private long id = -1;
    private double latitude, longitude;
    private boolean isMapClickable;

    public WhereMapFragment() {
        // Required empty public constructor

    }

    public static WhereMapFragment newInstance(long id, int viewMapBy, boolean isMapClickable) {
        Bundle args = new Bundle();
        args.putLong(KEY_PLACE_ID, id);
        args.putInt(KEY_MAP_VIEW, viewMapBy);
        args.putBoolean(KEY_MAP_CLICKABLE, isMapClickable);
        WhereMapFragment fragment = new WhereMapFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static WhereMapFragment newInstance(double latitude, double longitude, int viewMapBy) {
        Bundle args = new Bundle();
        args.putDouble(KEY_PLACE_LATITUDE, latitude);
        args.putDouble(KEY_PLACE_LONGITUDE, longitude);
        args.putInt(KEY_MAP_VIEW, viewMapBy);

        WhereMapFragment fragment = new WhereMapFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);

        buildGoogleApiClient();
        editableMarker = new MarkerOptions();
        editableRadius = new CircleOptions();

        Bundle args = getArguments();
        if (args != null) {
            id = args.getLong(KEY_PLACE_ID, -1);
            latitude = args.getDouble(KEY_PLACE_LATITUDE);
            longitude = args.getDouble(KEY_PLACE_LONGITUDE);
            viewMapBy
                    = args.getInt(KEY_MAP_VIEW);
            isMapClickable = args.getBoolean(KEY_MAP_CLICKABLE);
        }

        if (isMapClickable) {
            setHasOptionsMenu(true);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getLoaderManager().initLoader(MAP_LOADER, null, this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

//        if (isMapClickable) {
//            mGoogleMap.setOnMapClickListener(this);
//        }

//        DownloadGeoJsonFile downloadChatHistory = new DownloadGeoJsonFile();
//        downloadChatHistory.execute("http://www.folkrice.com:9000/api/features/all");

        mGoogleMap.setOnMapClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // String[] projection = new String[]{PlaceTable.COLUMN_ID, PlaceTable.COLUMN_PLACE_NAME, PlaceTable.COLUMN_LATITUDE, PlaceTable.COLUMN_LONGITUDE};
        // CursorLoader cursorLoader = new CursorLoader(getActivity(), WhereContentProvider.CONTENT_URI_PLACES, projection, null, null, "date desc");
        CursorLoader cursorLoader = null;
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (viewMapBy == MAP_BY_LATLNG)
            moveCamera(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


//    private class DownloadGeoJsonFile extends AsyncTask<String, Void, JSONObject> {
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            try {
//                // Open a stream from the URL
//                InputStream stream = new URL(params[0]).openStream();
//
//                String line;
//                StringBuilder result = new StringBuilder();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
//                while ((line = reader.readLine()) != null) {
//                    // Read and save each line of the stream
//                    result.append(line);
//                }
//
//                // Close the stream
//                reader.close();
//                stream.close();
//
//                // Convert result to JSONObject
//                return new JSONObject(result.toString());
//            } catch (IOException e) {
//                Log.e(mLogTag, "GeoJSON file could not be read");
//            } catch (JSONException e) {
//                Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            if (jsonObject != null) {
//                // Create a new GeoJsonLayer, pass in downloaded GeoJSON file as JSONObject
//                geoJsonLayer = new GeoJsonLayer(mGoogleMap, jsonObject);
//                // Add the geoJsonLayer onto the map
//                addColorsToMarkers();
//                geoJsonLayer.addLayerToMap();
//            }
//        }
//
//
//    }

//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }

//    private void addColorsToMarkers() {
//        // Iterate over all the features stored in the layer
//        for (GeoJsonFeature feature : geoJsonLayer.getFeatures()) {
//            // Check if the magnitude property exists
//            if (feature.hasProperty("type")) {
//
//                // Get the icon for the feature
//                BitmapDescriptor pointIcon = BitmapDescriptorFactory
//                        .defaultMarker(magnitudeToColor(feature.getProperty("type")));
//
//                // Create a new point style
//                GeoJsonPointStyle pointStyle = new GeoJsonPointStyle();
//
//                // Set options for the point style
//                pointStyle.setIcon(pointIcon);
//                pointStyle.setTitle(feature.getProperty("owner"));
//                pointStyle.setSnippet("FARM: " + feature.getProperty("name"));
//
//                // Assign the point style to the feature
//                feature.setPointStyle(pointStyle);
//            }
//        }
//    }

    private static float magnitudeToColor(String type) {
        if (type.equals("FARMER")) {
            return BitmapDescriptorFactory.HUE_RED;
        } else if (type.equals("GREEN_MARKET")) {
            return BitmapDescriptorFactory.HUE_GREEN;
        } else {
            return BitmapDescriptorFactory.HUE_CYAN;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        showErrorDialog();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    private void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    private void addMarker(LatLng latLng, String title) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title(title);
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.strokeColor(0xFF0000FF);
        circleOptions.strokeWidth(2);
        circleOptions.fillColor(0x110000FF);
        circleOptions.radius(100);
        mGoogleMap.addCircle(circleOptions);
        mGoogleMap.addMarker(options);
    }

    private void moveCamera(LatLng latLng) {
        CameraUpdate movement = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        mGoogleMap.animateCamera(movement);
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.internet_connection_error);
        builder.setPositiveButton(R.string.ok, null);

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        LatLng currentLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        editableMarker.position(currentLatLng);
        editableRadius.center(currentLatLng);
        editableRadius.strokeColor(0xFF0000FF);
        editableRadius.strokeWidth(2);
        editableRadius.fillColor(0x110000FF);
        editableRadius.radius(100);
        mGoogleMap.addMarker(editableMarker);
        mGoogleMap.addCircle(editableRadius);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 8));
        stopLocationUpdates();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mGoogleMap.clear();
        mCurrentLocation.setLatitude(latLng.latitude);
        mCurrentLocation.setLongitude(latLng.longitude);
        editableMarker.position(latLng);
        editableRadius.center(latLng);
        mGoogleMap.addMarker(editableMarker);
        mGoogleMap.addCircle(editableRadius);
    }
}

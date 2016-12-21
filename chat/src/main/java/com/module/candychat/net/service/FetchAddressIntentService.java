package com.module.candychat.net.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Clarence on 4/23/2015.
 */
public class FetchAddressIntentService extends IntentService {

    private static final String TAG = "com.module.candychat.net.services.FetchAddressIntentService";

    public static final String ADDRESS_RECEIVER = "com.module.candychat.net.RECEIVER";
    public static final String LOCATION_EXTRA = "com.module.candychat.net.LOCATION_EXTRA";
    public static final String RESULT_DATA_KEY = "com.module.candychat.net.RESULT_DATA_KEY";
    public static final String LATITUDE_KEY = "FetchAddressIntentService.LATITUDE_KEY";
    public static final String LONGITUDE_KEY = "FetchAddressIntentService.LONGITUDE_KEY";
    public static final String ADDRESS_KEY = "FetchAddressIntentService.ADDRESS_KEY";
    public static final String PLACENAME_KEY = "FetchAddressIntentService.PLACENAME_KEY";

    public static int SUCCESS_RESULT = 0;
    public static int FAILURE_RESULT = 1;

    private ResultReceiver mReceiver;

    public FetchAddressIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String errorMessage = "";

        mReceiver = intent.getParcelableExtra(ADDRESS_RECEIVER);

        if (mReceiver == null) {
            Log.e("FetchAddressService", "No receiver received. There is nowhere to send results");
            return;
        }

        Location location = intent.getParcelableExtra(LOCATION_EXTRA);


        if (location == null) {
            errorMessage = "No location data provided.";
            Log.e("FetchAddressService", errorMessage);
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            errorMessage = "Please check your internet connection.";
            Log.e("FetchAddressService", errorMessage, e);
        } catch (IllegalArgumentException e) {
            errorMessage = "Invalid latitude or longitude value.";
            Log.e("FetchAddressService", errorMessage + ". " + "Latitude = " + location.getLatitude() + ", Longitude = " + location.getLongitude(), e);
        }

        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = "No address found.";
                Log.e("FetchAddressService", errorMessage);
            }
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i("FetchAddressService", "Address found.");
            deliverResultToReceiver(SUCCESS_RESULT, TextUtils.join(", ", addressFragments));
        }

    }

    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY, message);
        mReceiver.send(resultCode, bundle);
    }

}

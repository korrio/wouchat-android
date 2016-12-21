package com.candychat.net.utils;

import android.widget.Toast;

import com.candychat.net.WOUApp;

/**
 * Created by korrio on 1/28/16.
 */
public class Utils {
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void theToastL(CharSequence msg) {
        Toast.makeText(WOUApp.getAppContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void theToast(CharSequence msg) {
        Toast.makeText(WOUApp.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

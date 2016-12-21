package com.candychat.net.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.activity2.AboutActivity;
import com.candychat.net.activity2.UpdateProfileActivity2;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.view.CustomTypefaceSpan;
import com.module.candychat.net.activity.ChatWallpaperActivity;
import com.wouchat.messenger.R;

/**
 * Created by korrio on 2/9/16.
 */
public class MainSettingActivity  extends PreferenceActivity {

    String appVersion;

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        AppBarLayout bar;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
            bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);
            root.addView(bar, 0);
        } else {
            ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
            ListView content = (ListView) root.getChildAt(0);
            root.removeAllViews();
            bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);

            int height;
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
                height = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            }else{
                height = bar.getHeight();
            }

            content.setPadding(0, height, 0, 0);

            root.addView(content);
            root.addView(bar);
        }

        Toolbar Tbar = (Toolbar) bar.getChildAt(0);

        Tbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        if (Tbar != null) {
            Tbar.setTitle(Spanny.spanText("Settings", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = pInfo.versionName;
        } catch(PackageManager.NameNotFoundException e) {
            appVersion = "unknown";
        }

        setupSimplePreferencesScreen();
    }

    Context mContext;
    PrefManager mPref;

    private void setupSimplePreferencesScreen() {
        mContext = this;
        addPreferencesFromResource(R.xml.main_setting);

        mPref = WOUApp.mPref;

        Preference profilePref = (Preference) findPreference("setting_profile");
        profilePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(mContext,UpdateProfileActivity2.class));
                return false;
            }
        });

//        final EditTextPreference profileNamePref = (EditTextPreference) findPreference("profile_name");
//        profileNamePref.setDefaultValue(mPref.name().getOr(""));
//        profileNamePref.setText(mPref.name().getOr(""));
//        profileNamePref.setSummary(mPref.name().getOr(""));
//        profileNamePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object o) {
//                mPref.name().put(o.toString()).commit();
//                profileNamePref.setSummary(o.toString());
//                return false;
//            }
//        });
//
//        final EditTextPreference profileUsernamePref = (EditTextPreference) findPreference("profile_username");
//        profileUsernamePref.setDefaultValue(mPref.username().getOr(""));
//        profileUsernamePref.setText(mPref.username().getOr(""));
//        profileUsernamePref.setSummary(mPref.username().getOr(""));
//        profileUsernamePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object o) {
//                mPref.username().put(o.toString()).commit();
//                profileUsernamePref.setSummary(o.toString());
//                return false;
//            }
//        });
//
//        final EditTextPreference profilePhonePref = (EditTextPreference) findPreference("profile_phone");
//        profilePhonePref.setDefaultValue(mPref.phone().getOr(""));
//        profilePhonePref.setText(mPref.phone().getOr(""));
//        profilePhonePref.setSummary(mPref.phone().getOr(""));
//        profilePhonePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object o) {
//                mPref.phone().put(o.toString()).commit();
//                profilePhonePref.setSummary(o.toString());
//                return false;
//            }
//        });
//
//        final EditTextPreference profileEmailPref = (EditTextPreference) findPreference("profile_email");
//        profileEmailPref.setDefaultValue(mPref.email().getOr(""));
//        profileEmailPref.setSummary(mPref.email().getOr(""));
//        profileEmailPref.setText(mPref.email().getOr(""));
//        profileEmailPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object o) {
//                mPref.email().put(o.toString()).commit();
//                profileEmailPref.setSummary(o.toString());
//                return false;
//            }
//        });
//
//        final EditTextPreference profileStatusPref = (EditTextPreference) findPreference("profile_status");
//        profileStatusPref.setDefaultValue(mPref.about().getOr(""));
//        profileStatusPref.setSummary(mPref.about().getOr(""));
//        profileStatusPref.setText(mPref.about().getOr(""));
//        profileStatusPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object o) {
//                mPref.about().put(o.toString()).commit();
//                profileStatusPref.setSummary(o.toString());
//                return false;
//            }
//        });

        SwitchPreference notiPref = (SwitchPreference) findPreference("setting_notification");
        notiPref.setDefaultValue(mPref.notification().getOr(true));
        notiPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                mPref.notification().put(o.equals(true));
                Toast.makeText(mContext,"Notification " + o.toString(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference chatBgPref = (Preference) findPreference("change_wallpaper");
        chatBgPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(mContext,ChatWallpaperActivity.class));
                return false;
            }
        });

        Preference chatPref = (Preference) findPreference("setting_chat");
        chatPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //startActivity(new Intent(mContext,StatusSettingActivity.class));
                return false;
            }
        });

        Preference helpPref = (Preference) findPreference("setting_help");
        helpPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //startActivity(new Intent(mContext,AboutActivity.class));
                return false;
            }
        });

        Preference aboutPref = (Preference) findPreference("setting_about");
        aboutPref.setSummary("Version " + appVersion);
        aboutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(mContext,AboutActivity.class));
                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

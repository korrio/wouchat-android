package com.candychat.net.activity2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.view.CustomTypefaceSpan;
import com.wouchat.messenger.R;

/**
 * Created by root1 on 2/5/16.
 */
public class UpdateStatusActivity extends PreferenceActivity {

    Context mContext;
    PrefManager mPref;

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
            Tbar.setTitle(Spanny.spanText("My Status", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = pInfo.versionName;
        } catch(PackageManager.NameNotFoundException e) {
            appVersion = "unknown";
        }

        setupSimplePreferencesScreen();
    }

    private void setupSimplePreferencesScreen() {
        mContext = this;
        mPref = WOUApp.mPref;
        addPreferencesFromResource(R.xml.status_setting);

        final EditTextPreference profileStatusPref = (EditTextPreference) findPreference("profile_status");
        String defaultStatus = mPref.about().getOr("");
        if(defaultStatus.equals(""))
            defaultStatus = "None";
        profileStatusPref.setDefaultValue(defaultStatus);
        profileStatusPref.setTitle(defaultStatus);
        profileStatusPref.setText(defaultStatus);
        profileStatusPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                mPref.about().put(o.toString()).commit();
                //profileStatusPref.setSummary(o.toString());
                profileStatusPref.setTitle(o.toString());
                profileStatusPref.setText(o.toString());
                profileStatusPref.getEditText().setHint("Available");
                return false;
            }
        });

        final ListPreference profileStatusSelect = (ListPreference) findPreference ("profile_status_select");
        profileStatusSelect.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                mPref.about().put(o.toString()).commit();
                profileStatusPref.setTitle(o.toString());
                profileStatusPref.setText(o.toString());
                return false;
            }

        });
    }
}

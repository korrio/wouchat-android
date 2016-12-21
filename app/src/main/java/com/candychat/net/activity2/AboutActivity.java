package com.candychat.net.activity2;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.view.CustomTypefaceSpan;
import com.wouchat.messenger.BuildConfig;
import com.wouchat.messenger.R;


public class AboutActivity extends BaseActivity {

    TextView appVersionTv;
    TextView appNameTv;
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        appVersionTv = (TextView) findViewById(R.id.app_version);
        appNameTv = (TextView) findViewById(R.id.app_name);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("About", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo( this.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

        String versionName = BuildConfig.VERSION_NAME;
        appVersionTv.setText("Version " + versionName);
        appNameTv.setText(applicationName);
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
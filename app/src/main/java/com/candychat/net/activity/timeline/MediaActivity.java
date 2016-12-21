package com.candychat.net.activity.timeline;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.view.CustomTypefaceSpan;
import com.wouchat.messenger.R;

public class MediaActivity extends BaseActivity {

    private int cid;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_qr_code,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_media;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if(getIntent() != null)
            cid = getIntent().getIntExtra("cid", 0);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("Media", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

            MediaFragment threeFragment = MediaFragment.newInstance(cid);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, threeFragment);
            transaction.commit();

    }

}

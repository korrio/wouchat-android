package com.module.candychat.net.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.module.candychat.net.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 5/7/16.
 */
public class SampleGridViewActivity extends ToolbarActivity {
    List<String> listUrls;
    SampleGridViewAdapter adapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.sample_gridview_activity;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Photos");
        //setContentView();

        listUrls = new ArrayList<>();

        if(getIntent() != null)
            listUrls = getIntent().getStringArrayListExtra("URLS");

        GridView gv = (GridView) findViewById(R.id.grid_view);
        adapter = new SampleGridViewAdapter(this,listUrls);
        gv.setAdapter(adapter);
        gv.setOnScrollListener(new SampleScrollListener(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = PictureActivity.newIntent(SampleGridViewActivity.this, listUrls.get(i),
                         "");
                ActivityOptionsCompat optionsCompat
                        = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        SampleGridViewActivity.this, view, PictureActivity.TRANSIT_PIC);

                try {
                    ActivityCompat.startActivity(SampleGridViewActivity.this, intent,
                            optionsCompat.toBundle());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    startActivity(intent);
                }
            }
        });
    }
}

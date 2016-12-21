package com.candychat.net.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.adapter.TattooStoreDetailAdapter;
import com.candychat.net.model.TheUser;
import com.wouchat.messenger.R;

import org.parceler.Parcels;


public class StickerDetailActivity extends ActionBarActivity {


    TattooStoreDetailAdapter adapterTattooStroe;
    ImageView sticker;
    TextView title_candychat;
    TextView name_sticker;
    TextView date;
    TextView price;
    GridView gridView;
    TheUser tattoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_detail);

        sticker = (ImageView) findViewById(R.id.sticker);
        title_candychat = (TextView) findViewById(R.id.title_candy_chat);
        name_sticker = (TextView) findViewById(R.id.name_sticker);
        date = (TextView) findViewById(R.id.date);



        gridView = (GridView) findViewById(R.id.gridView);
        Intent intent = getIntent();

        TheUser person = Parcels.unwrap(intent.getParcelableExtra("tattoo"));

    }


}


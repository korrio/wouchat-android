package com.module.candychat.net.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.module.candychat.net.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mac on 5/7/16.
 */
final class SampleGridViewAdapter extends BaseAdapter {
    private final Context context;
    private List<String> urls = new ArrayList<String>();

    public SampleGridViewAdapter(Context context,List<String> urls) {
        this.context = context;
        this.urls = urls;

        Collections.addAll(urls);

    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        SquaredImageView view = (SquaredImageView) convertView;
        if (view == null) {
            view = new SquaredImageView(context);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        //Picasso.with(context).load(url).resize(200,200).transform(new RoundedTransformation(100,0)).centerCrop().into(viewHolder.contactAvatarImageView);

        Picasso.with(context) //
                .load(url) //
                .resize(200,200)
                .centerCrop()
                .placeholder(R.drawable.placeholder) //
                //.error(R.drawable.error) //
                //.fit() //
                .tag(context) //
                .into(view);

        return view;
    }

    @Override public int getCount() {
        return urls.size();
    }

    @Override public String getItem(int position) {
        return urls.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }
}

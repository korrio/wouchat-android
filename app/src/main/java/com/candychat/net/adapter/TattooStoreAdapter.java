package com.candychat.net.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.model.TheUser;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;


public class TattooStoreAdapter extends BaseAdapter {
    private Activity activity;
    public List<TheUser> list = new ArrayList<TheUser>();

    public TattooStoreAdapter(Activity a, List<TheUser> list) {
        activity = a;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        TheUser item = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_sticker, null);
            holder.txt_sticker_name = (TextView) convertView.findViewById(R.id.txt_sticker_name);
            holder.sticker = (ImageView) convertView.findViewById(R.id.sticker);
            holder.cat_by = (TextView) convertView.findViewById(R.id.cat_by);
            holder.txt_free = (TextView) convertView.findViewById(R.id.txt_free);
            holder.download = (ImageView) convertView.findViewById(R.id.download);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txt_sticker_name.setText("Candy Chat");
        holder.cat_by.setText(item.getName());
        holder.txt_free.setText("Free");

        String path = item.getImage();



        Picasso.with(activity)
                .load(path)
                .fit()
                .into(holder.sticker);
        return convertView;
    }


    public static class ViewHolder {
        TextView txt_sticker_name,txt_free;
        TextView txt_day_sticker;
        ImageView sticker;
        ImageView download;
        TextView cat_by;

    }
}


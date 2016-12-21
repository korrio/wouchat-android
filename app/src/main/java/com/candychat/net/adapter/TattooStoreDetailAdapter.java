package com.candychat.net.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.candychat.net.model.TheUser;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;


public class TattooStoreDetailAdapter extends BaseAdapter {
    private Context activity;
    public List<TheUser> list = new ArrayList<TheUser>();
    String[] strName;

    public TattooStoreDetailAdapter(Context a, List<TheUser> list) {
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
        ViewHolder holder;
        TheUser path = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_member_avatar, null);
            holder.sticker = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Picasso.with(activity)
                .load(path.getImage())
                .into(holder.sticker);

        return convertView;
    }


    public static class ViewHolder {
        ImageView sticker;

    }
}


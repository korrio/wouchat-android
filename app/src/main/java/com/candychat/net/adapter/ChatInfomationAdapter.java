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

public class ChatInfomationAdapter extends BaseAdapter {
    Context mContext;
   List<TheUser> list = new ArrayList<>();

    public ChatInfomationAdapter(Context context, List<TheUser> list) {
        this.mContext= context;
        this.list = list;

    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {

        TheUser i = list.get(position);
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.item_infomaton, parent, false);


        ImageView ivUserAvatar = (ImageView)view.findViewById(R.id.img_item);

        Picasso.with(mContext)
                .load(i.getImage())
                .into(ivUserAvatar);

        return view;
    }
}
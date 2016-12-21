package com.candychat.net.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.model.TheUser;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewChatInfomationAdapter extends BaseAdapter {
    Context mContext;
   List<TheUser> list = new ArrayList<>();

    public ListViewChatInfomationAdapter(Context context, List<TheUser> list) {
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
            view = mInflater.inflate(R.layout.item_chat_infomation, parent, false);

        TextView txt_name = (TextView)view.findViewById(R.id.title);
        txt_name.setText(i.getName());

        TextView txt_msg = (TextView)view.findViewById(R.id.message);
        txt_msg.setText(i.getName());


        ImageView ivUserAvatar = (ImageView)view.findViewById(R.id.list_image);



        Picasso.with(mContext)
                .load(i.getImage())
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(ivUserAvatar);

        return view;
    }
}
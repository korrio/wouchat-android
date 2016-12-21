package com.candychat.net.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.model.UserModel;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class OfficialFriendListViewAdapter extends BaseAdapter {
    Context mContext;
    List<UserModel> listFriendSuggestions = new ArrayList<>();

    public OfficialFriendListViewAdapter(Context context, List<UserModel> list) {
        this.mContext = context;
        this.listFriendSuggestions = list;
    }

    public int getCount() {
        return listFriendSuggestions.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        UserModel i = listFriendSuggestions.get(position);
        if (i != null) {
            ViewHolder viewHolder;
            LayoutInflater mInflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (view == null) {
                view = mInflater.inflate(R.layout.item_add_user, parent, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

                viewHolder.txt_name.setText(i.name);
                Picasso.with(mContext)
                        .load(i.getAvatarUrl())
                        .placeholder(R.drawable.ic_launcher)
                        .resize(200,200)
                        .transform(new RoundedTransformation(100, 4))
                        .into(viewHolder.ivUserAvatar);

        }
        return view;
    }

    private class ViewHolder {
        public TextView txt_name;
        public ImageView ivUserAvatar;

        public ViewHolder(View convertView) {
            txt_name = (TextView) convertView.findViewById(R.id.title);
            ivUserAvatar = (ImageView) convertView.findViewById(R.id.list_image);

        }
    }
}
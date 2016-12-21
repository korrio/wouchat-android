package com.candychat.net.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.WOUApp;
import com.candychat.net.view.RoundedTransformation;
import com.module.candychat.net.model.Relations;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;

public class MemberGroupAdapter extends BaseAdapter {

    private ArrayList<Relations.GroupBean.ConversationMembersBean> groupList = new ArrayList<>();

    Context context;

    public MemberGroupAdapter(Context context, ArrayList<Relations.GroupBean.ConversationMembersBean> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_member_group, parent, false);

            Relations.GroupBean.ConversationMembersBean movie = groupList.get(position);
            viewHolder.txt_topic = (TextView) convertView.findViewById(R.id.txt_topic);
            viewHolder.imageView24 = (ImageView) convertView.findViewById(R.id.imageView24);

            if(movie.getName() != ""){
                viewHolder.txt_topic.setText(movie.getName());
            }else{
                viewHolder.txt_topic.setText(movie.getUsername());
            }


            Log.e("dddd",movie.getAvatar());
            Picasso.with(context)
                    .load(WOUApp.SOCIAL_ENDPOINT + "/" + movie.getAvatar())
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(viewHolder.imageView24);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private static class ViewHolder {
        TextView txt_topic;
        ImageView imageView24;
    }
}
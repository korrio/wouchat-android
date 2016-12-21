package com.module.candychat.net.adapter;

/**
 * Created by Phuc on 7/18/15.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.module.candychat.net.R;
import com.module.candychat.net.model.RelationsGroup;
import com.module.candychat.net.model.RelationsTopic;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopicListViewAdapter extends BaseAdapter {

    private List<RelationsTopic.ContentEntity> groupList = new ArrayList<>();

    Context context;

    public TopicListViewAdapter(Context context, List<RelationsTopic.ContentEntity> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_topic_chat, parent, false);

            RelationsTopic.ContentEntity movie = groupList.get(position);
            String thumbUrl = "http://candychat.net:1313" + "/" + movie.getAvatar();
            viewHolder.avatar  = (ImageView)convertView.findViewById(R.id.imageView3);
            viewHolder.txt_topic = (TextView) convertView.findViewById(R.id.txt_topic);
            viewHolder.txt_topic.setText(movie.getName());


            //Picasso.with(getActivity()).load(yourAvatarUrl).resize(200, 200)
            //.transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);
            Picasso.with(context).load(thumbUrl).resize(200, 200).transform(new RoundedTransformation(100, 0)).centerCrop().into(viewHolder.avatar);

            Log.e("thumbUrl",thumbUrl);


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
        ImageView avatar ;
    }
}

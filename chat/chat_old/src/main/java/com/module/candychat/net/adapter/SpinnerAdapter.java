package com.module.candychat.net.adapter;

/**
 * Created by Phuc on 7/18/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.module.candychat.net.R;

public class SpinnerAdapter extends BaseAdapter {

    Context context;

    LayoutInflater inflater;
    private String[] lis;
    public SpinnerAdapter(Context context, String[] li) {
        this.context = context;
        lis = li;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.item_emoji, parent, false);

            viewHolder.emoji = (TextView) convertView.findViewById(R.id.emoji);
            viewHolder.emoji.setText(lis[position]);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return lis.length;
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
        TextView emoji;
    }
}

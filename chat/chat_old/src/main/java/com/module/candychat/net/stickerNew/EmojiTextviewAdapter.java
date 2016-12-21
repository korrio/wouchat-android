package com.module.candychat.net.stickerNew;

/**
 * Created by Phuc on 7/18/15.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.module.candychat.net.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmojiTextviewAdapter extends BaseAdapter {

    Context context;

    LayoutInflater inflater;
    private List<String> lis;
    public EmojiTextviewAdapter(Context context, List<String> li) {
        this.context = context;
        lis = li;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        final ViewHolder viewHolder;
        String strPath = lis.get(position).toString();
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.item_emoji, parent, false);

            viewHolder.emoji = (TextView) convertView.findViewById(R.id.emoji);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return lis.size();
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

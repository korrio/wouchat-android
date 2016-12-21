package com.module.candychat.net.stickerNew;

/**
 * Created by Phuc on 7/18/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.module.candychat.net.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmoAdapter extends BaseAdapter {

    Context context;

    LayoutInflater inflater;
    private List<String> lis;
    public EmoAdapter(Context context, List<String> li) {
        this.context = context;
        lis = li;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        final ViewHolder viewHolder;
        String strPath = lis.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.item_sticker_grid, parent, false);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgSticker = (ImageView) convertView.findViewById(R.id.imgSticker);

        Picasso.with(context).load("file:///android_asset/"+strPath).into(viewHolder.imgSticker);

        return convertView;
    }

    @Override
    public int getCount() {
        return lis.size();
    }

    @Override
    public Object getItem(int i) {
        return lis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder {
        ImageView imgSticker;
    }
}

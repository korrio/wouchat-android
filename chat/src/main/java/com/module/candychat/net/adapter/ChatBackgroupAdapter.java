package com.module.candychat.net.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.module.candychat.net.R;

import java.util.Random;


public class ChatBackgroupAdapter extends RecyclerView.Adapter<ChatBackgroupAdapter.ViewHolder> {


    private OnItemClickListener mItemClickListener;

    private final Context context;
    int[] res;

    public ChatBackgroupAdapter(Context context, int[] res) {
        this.context = context;
        this.res = res;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.item_background, parent, false);
        final ViewHolder holder = new ViewHolder(sView);

        return holder;
    }


    int selectedPos;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);


//        UrlPhotoInfo item = list.get(position);
//        Picasso.with(context)
//                .load(item.getUrlPhoto())
//                .fit().centerCrop()
//                .into(holder.ivUserAvatar);
        //holder.ivUserAvatar.setBackgroundResource(res[position]);
        holder.ivUserAvatar.setImageResource(res[position]);

    }

    @Override
    public int getItemCount() {
        return res.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView ivUserAvatar;


        public ViewHolder(View view) {
            super(view);
            ivUserAvatar = (ImageView) view.findViewById(R.id.img_item);
            ivUserAvatar.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.img_item) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }

            }

        }

    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    /*
     * Snippet from http://stackoverflow.com/a/363692/1008278
     */
    public static int randInt(int min, int max) {
        final Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /* ==========This Part is not necessary========= */

}
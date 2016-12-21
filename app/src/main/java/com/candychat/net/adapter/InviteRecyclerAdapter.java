package com.candychat.net.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.model.TheUser;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class InviteRecyclerAdapter extends RecyclerView.Adapter<InviteRecyclerAdapter.ViewHolder> {
    private OnItemClickListener mItemClickListener;

    private final Context context;
    List<TheUser> list = new ArrayList<>();

    public InviteRecyclerAdapter(Context context, List<TheUser> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.item_invite_friend, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TheUser item = list.get(position);
        holder.person_name.setText(item.getName());
        holder.person_age.setText(item.getDescription());

        Picasso.with(context)
                .load(item.getImage())
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(holder.ivUserAvatar);
        holder.cbSelected.setChecked(item.isSelected());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemSelected(int position, boolean isSelected) {
        if (position != -1) {
            list.get(position).setSelected(isSelected);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView ivUserAvatar;
        TextView person_name;
        TextView person_age;
        CheckBox cbSelected;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ivUserAvatar = (ImageView) view.findViewById(R.id.person_photo);
            person_name = (TextView) view.findViewById(R.id.person_name);
            person_age  = (TextView) view.findViewById(R.id.person_age);
            cbSelected = (CheckBox) view.findViewById(R.id.cbSelected);

        }

        @Override
        public void onClick(View v) {

            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }

        }

    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
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

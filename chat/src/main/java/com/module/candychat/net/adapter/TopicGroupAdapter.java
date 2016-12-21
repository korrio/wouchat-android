package com.module.candychat.net.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.module.candychat.net.R;
import com.module.candychat.net.model.RelationsGroup;

import java.util.ArrayList;
import java.util.List;

public class TopicGroupAdapter extends RecyclerView.Adapter<TopicGroupAdapter.MyViewHolder> {

    private List<RelationsGroup.GroupEntity> groupList = new ArrayList<>();

    Context context;
    private static OnItemClickListener mItemClickListener;
    public TopicGroupAdapter(Context context, List<RelationsGroup.GroupEntity> moviesList) {
        this.context = context;
        this.groupList = moviesList;
    }


    @Override
    public int getItemCount() {
        return groupList.size();
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic_chat, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RelationsGroup.GroupEntity movie = groupList.get(position);
        Log.e("หกหกหกหกห",movie.getName());
        holder.txt_topic.setText(movie.getName());

    }
 


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_topic;
        private LinearLayout card_view;

        public MyViewHolder(View view) {
            super(view);
            txt_topic = (TextView) view.findViewById(R.id.txt_topic);
            card_view = (LinearLayout) view.findViewById(R.id.card_view);
            card_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.card_view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, getPosition());
                }

            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
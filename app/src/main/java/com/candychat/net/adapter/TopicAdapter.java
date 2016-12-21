package com.candychat.net.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.candychat.net.woumodel.Relations;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;
 
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private List<Relations.GroupEntity> groupList = new ArrayList<>();
    Context context;
    private static OnItemClickListener mItemClickListener;
    public TopicAdapter(Context context, List<Relations.GroupEntity> moviesList) {
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
                .inflate(R.layout.item_topic, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Relations.GroupEntity movie = groupList.get(position);
        Log.e("หกหกหกหกห",movie.getName());
        holder.txt_topic.setText(movie.getName());

    }
 


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_topic;
        private CardView card_view;

        public MyViewHolder(View view) {
            super(view);
            txt_topic = (TextView) view.findViewById(R.id.txt_topic);
            card_view = (CardView) view.findViewById(R.id.card_view);
            card_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_view:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                    break;

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
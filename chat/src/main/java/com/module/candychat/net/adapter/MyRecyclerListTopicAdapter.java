package com.module.candychat.net.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.candychat.net.R;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.model.Item_menu;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerListTopicAdapter extends RecyclerView.Adapter<MyRecyclerListTopicAdapter.ContactViewHolder> {

    private Context mContext;
    private ArrayList<ChatInfo.ConversationMembersEntity> listMembers = new ArrayList<>();
    private static OnItemClickListener mItemClickListener;
    private static OnItemClickListener mItemClickListenerShare;
    private static OnItemClickListener mItemClickListenerRead;


    public MyRecyclerListTopicAdapter(Context mContext, ArrayList<ChatInfo.ConversationMembersEntity> listMembers) {
        this.mContext = mContext;
        this.listMembers = listMembers;

    }


    @Override
    public int getItemCount() {
        return listMembers.size();
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int i) {
        final ChatInfo.ConversationMembersEntity m = listMembers.get(i);
        holder.txt_title.setText(m.getName());
        //contactViewHolder.imageView.setImageResource(res[i]);


        String thumbUrl = "http://www.candychat.net" + "/" + m.getAvatar() + "." + m.getExtension();

        Picasso.with(mContext).load(thumbUrl).resize(200, 200).transform(new RoundedTransformation(100, 0)).centerCrop().into(holder.imageView);

        Log.e("thumbUrl", thumbUrl);


        holder.checkbox.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        holder.checkbox.setChecked(m.isSelected());

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                item_menu.get(holder.getAdapterPosition()).setSelected(isChecked);

                m.setSelected(isChecked);

            }
        });

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_reycler_main_list_check, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_title, txt_cal;
        ImageView imageView;
        LinearLayout ls;
        CheckBox checkbox;

        public ContactViewHolder(View v) {
            super(v);
            txt_title = (TextView) v.findViewById(R.id.txt_title);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            ls = (LinearLayout) v.findViewById(R.id.ls);
            checkbox = (CheckBox) v.findViewById(R.id.checkbox);
            ls.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.ls:
//                    if (mItemClickListener != null) {
//                        mItemClickListener.onItemClick(v, getPosition());
//                    }
//                    break;
//
//            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListenerShare {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListenerShare(final OnItemClickListener mItemClickListener) {
        this.mItemClickListenerShare = mItemClickListener;
    }

    public interface OnItemClickListenerRead {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListenerRead(final OnItemClickListener mItemClickListener) {
        this.mItemClickListenerRead = mItemClickListener;
    }


}
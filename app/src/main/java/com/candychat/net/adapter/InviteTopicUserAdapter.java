package com.candychat.net.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.module.candychat.net.R;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.model.UserModel;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpizarro
 */
public class InviteTopicUserAdapter extends RecyclerView.Adapter<InviteTopicUserAdapter.PersonViewHolder> {

    ArrayList<ChatInfo.ConversationMembersEntity> people;
    OnItemClickListener mItemClickListener;

    Context context;

    public InviteTopicUserAdapter(Context context, ArrayList<ChatInfo.ConversationMembersEntity> persons) {
        this.context = context;
        this.people = persons;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_invite_friend, viewGroup, false);
        return new PersonViewHolder(v);
    }


    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        if (!people.get(i).getName().equals("")) {
            personViewHolder.personName.setText(people.get(i).getName());
            personViewHolder.personAge.setText("" + people.get(i).getUsername());
        } else {
            personViewHolder.personName.setText(people.get(i).getUsername());
            personViewHolder.personAge.setVisibility(View.GONE
            );
        }

        String thumbUrl = "http://www.candychat.net" + "/" + people.get(i).getAvatar() + "." + people.get(i).getExtension();

        Picasso.with(context).load(thumbUrl).resize(200, 200).transform(new RoundedTransformation(100,0)).centerCrop().into(personViewHolder.personPhoto);
        personViewHolder.cbSelected.setChecked(people.get(i).isSelected());

    }

    public void setItemSelected(View view,int position, boolean isSelected) {
        if (position != -1) {
            people.get(position).setSelected(isSelected);
            if(view.getId() == R.id.cbSelected)
                ((CheckBox) view).setChecked(isSelected);
            view.setSelected(isSelected);
            notifyDataSetChanged();
        }
    }

    ArrayList<ChatInfo.ConversationMembersEntity> getPeople(){
        return people;
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setPeople(ArrayList<ChatInfo.ConversationMembersEntity> people) {
        this.people = people;
    }




    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        //CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        CheckBox cbSelected;

        PersonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
           // cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personAge = (TextView) itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            cbSelected = (CheckBox) itemView.findViewById(R.id.cbSelected);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }




}

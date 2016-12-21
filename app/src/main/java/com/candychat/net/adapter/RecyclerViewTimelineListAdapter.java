package com.candychat.net.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.model.feed.User;
import com.candychat.net.viewholder.RecyclerViewSimpleTextViewHolder;
import com.candychat.net.viewholder.ViewHolder1;
import com.candychat.net.viewholder.ViewHolder2;
import com.candychat.net.viewholder.ViewHolder3;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.List;

public class RecyclerViewTimelineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The items to display in your RecyclerView
    private List<Object> items;
    Context context;
    private final int USER = 0, IMAGE = 1, CLIP = 3;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewTimelineListAdapter(List<Object> items, Context context) {
        this.items = items;
        this.context = context;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof User) {
            return USER;
        } else if (items.get(position) instanceof String) {
            return IMAGE;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case USER:
                View v1 = inflater.inflate(R.layout.item_feed_text, viewGroup, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case IMAGE:
                View v2 = inflater.inflate(R.layout.item_feed_photo, viewGroup, false);
                viewHolder = new ViewHolder2(v2);
                break;
            case CLIP:
                View v3 = inflater.inflate(R.layout.item_feed_clip, viewGroup, false);
                viewHolder = new ViewHolder3(v3);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //More to come
        switch (viewHolder.getItemViewType()) {
            case USER:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case IMAGE:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2);
                break;
            case CLIP:
                ViewHolder3 vh3 = (ViewHolder3) viewHolder;
                configureViewHolder3(vh3);
                break;
            default:
                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureDefaultViewHolder(RecyclerViewSimpleTextViewHolder vh, int position) {
        vh.getLabel().setText((CharSequence) items.get(position));
    }

    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        User user = (User) items.get(position);
        if (user != null) {
            vh1.getLabel1().setText("Name: " + user.getName1());
            vh1.getLabel2().setText("Hometown: " + user.getName2());

            Picasso.with(context)
                    .load("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcR68KCTZYkkqWeuPZhBgbxVxq7F34ZY3dLQsD8u3jqgDKXRRbBq")
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(vh1.getProfile_avatar());

            vh1.getComment_view_1().setVisibility(View.VISIBLE);
            vh1.getComment_view_2().setVisibility(View.GONE);

            Picasso.with(context)
                    .load("http://men.mthai.com/uploads/manager/smile28/daniel04.jpg")
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(vh1.getIvUserAvatar1());

            vh1.getBtn_comment().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"comment",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void configureViewHolder2(ViewHolder2 vh2) {
        //vh2.getThumb().setImageResource(R.drawable.imge);


        Picasso.with(context)
                .load("http://assets.lfcimages.com/uploads/page%20thumbnails/5363__3400__poppyss.jpg")
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(vh2.getImageView());

        Picasso.with(context)
                .load("http://assets.lfcimages.com/uploads/page%20thumbnails/5363__3400__poppyss.jpg")
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(vh2.getProfile_avatar());

        vh2.getLn_comment().setVisibility(View.GONE);
    }

    private void configureViewHolder3(ViewHolder3 vh3) {
        Picasso.with(context)
                .load("http://assets3.lfcimages.com/uploads/7544__7435__can1000.jpg")
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(vh3.getImageView());


        //vh3.getThumb().setImageResource(R.drawable.imge);
        vh3.getLn_comment().setVisibility(View.GONE);
    }
}

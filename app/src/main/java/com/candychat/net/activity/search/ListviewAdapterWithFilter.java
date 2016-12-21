package com.candychat.net.activity.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.candychat.net.activity.contact.Contact;
import com.candychat.net.view.RoundedTransformation;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.Collections;
import java.util.List;

public class ListviewAdapterWithFilter extends BaseAdapter implements IFilteredListListener<Contact>, IFilterableItem<Contact> {

    private Context context;
    private List<Contact> list;
    private ListFilter<Contact> filter;
    private Boolean filterByTitle;

    public ListviewAdapterWithFilter(Context context, List<Contact> listContent) {
        this.context = context;
        this.list = listContent;
        this.filterByTitle = true;
        this.filter = new ListFilter<Contact>(list, this, this, this);
    }

    // ------------------------
    // --------BaseAdapter
    // ------------------------

    public void reverseList() {
        Collections.reverse(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contact contact = list.get(position);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_child_add, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_name = (TextView) convertView.findViewById(R.id.contact_name_tv);
        holder.txt_status = (TextView) convertView.findViewById(R.id.contact_status_tv);
        holder.ivUserAvatar = (ImageView) convertView.findViewById(R.id.contact_avatar_iv);
        holder.btnInvite = (ImageView) convertView.findViewById(R.id.btn_invite);

        if (contact.numbers != null && contact.numbers.size() != 0) {
            String first = contact.name.substring(0, 1).toUpperCase();
            holder.txt_status.setText(contact.numbers.get(0).number);
            holder.txt_name.setText(contact.name);


            ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

            int color1 = generator.getRandomColor();
            TextDrawable drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(90)  // width in px
                    .height(90) // height in px
                    .endConfig()
                    .buildRound(first, color1);

            holder.ivUserAvatar.setImageDrawable(drawable);

        } else if (contact.emails != null && contact.emails.size() != 0) {
            holder.txt_status.setText(contact.emails.get(0).address);
            //holder.txt_name.setText(contact.emails.get(0).address);
            String first = contact.name.substring(0, 1).toUpperCase();
            holder.txt_name.setText(contact.name);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

            int color1 = generator.getRandomColor();
// generate color based on a key (same key returns the same color), useful for list/grid views
            int color2 = generator.getColor(contact.emails.get(0).address);

            TextDrawable drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(90)  // width in px
                    .height(90) // height in px
                    .endConfig()
                    .buildRound(first, color2);

            holder.ivUserAvatar.setImageDrawable(drawable);
        } else {
            holder.txt_name.setText(contact.name);
            if(contact.phoneNo != null) {
                if(contact.phoneNo.length() != 0)
                    holder.txt_status.setText(contact.phoneNo);
                else
                    holder.txt_status.setVisibility(View.GONE);
            } else {
                holder.txt_status.setVisibility(View.GONE);
            }

        }

        if(contact.isFriend) {
            holder.btnInvite.setVisibility(View.INVISIBLE);
            if(!contact.avatarUrl.equals(""))
            Picasso.with(context)
                    .load(contact.avatarUrl)
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(holder.ivUserAvatar);
        }

        return convertView;
    }

    public void changeFilterType(Boolean filterByTitle) {
        //We change the criterion for filtering
        this.filterByTitle = filterByTitle;
        //We have to notify filter component that filters has changed
        this.filter.filterTypeChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public void onSearchResult(List<Contact> objects) {
        list = objects;
    }

    @Override
    public String getStringForFilter(Contact item) {
        //This gets the String for filters.
        //Depending on our UI events, business logic etc. it should
        //return the appropriate String to filter for.

        if (filterByTitle) {
            return item.name;
        } else {
            return item.name;
        }
    }

    static class ViewHolder {
        TextView txt_name;
        TextView txt_status;
        ImageView ivUserAvatar;
        ImageView btnInvite;

    }



}
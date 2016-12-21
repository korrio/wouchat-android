package com.candychat.net.activity.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.candychat.net.WOUApp;
import com.wouchat.messenger.R;

import java.util.ArrayList;

public class ContactsAdapter2 extends ArrayAdapter<Contact2> {

    public ContactsAdapter2(Context context, ArrayList<Contact2> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item
        Contact2 contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_contact_list, parent, false);
        }
        // Populate the data into the template view using the data object
        TextView tvName = (TextView) view.findViewById(R.id.ColName);
        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        TextView tvPhone = (TextView) view.findViewById(R.id.ColPhoneNo);
        tvName.setText(contact.name);
        ImageView imgPhoto = (ImageView) view.findViewById(R.id.ColPhoto);

        String firstLetter = contact.name.substring(0, 1);

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
// generate random color
        int color1 = generator.getRandomColor();
// generate color based on a key (same key returns the same color), useful for list/grid views
        int color2 = generator.getColor(contact.name);

// declare the builder object once.
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .useFont(WOUApp.CustomFontTypeFace())
                .endConfig()
                .round();

// reuse the builder specs to create multiple drawables

        TextDrawable ic1 = builder.build(firstLetter.toUpperCase(), color1);
        TextDrawable ic2 = builder.build(firstLetter.toUpperCase(), color2);

        tvEmail.setText("");
        tvPhone.setText("");

        imgPhoto.setImageDrawable(ic1);

        tvPhone.setText(contact.getName());

//        if (contact.emails.size() > 0 && contact.emails.get(0) != null) {
//            tvEmail.setText(contact.emails.get(0).address);
//        }
//        if (contact.numbers.size() > 0 && contact.numbers.get(0) != null) {
//            tvPhone.setText(contact.numbers.get(0).number);
//        }
        return view;
    }

}

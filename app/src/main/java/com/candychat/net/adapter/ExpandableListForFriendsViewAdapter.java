package com.candychat.net.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.candychat.net.activity.contact.Contact;
import com.candychat.net.activity.contact.Contact2;
import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.WOUApp;
import com.candychat.net.model.UserModel;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListForFriendsViewAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;

//    private List<Relations.MeEntity> meList = new ArrayList<>();
//    private List<Relations.FriendsEntity> friendsList = new ArrayList<>();
//    private List<Relations.GroupEntity> groupList = new ArrayList<>();

    List<UserModel> listFriendSuggestions = new ArrayList<>();
    ArrayList<Contact2> listContacts = new ArrayList<>();

    public ExpandableListForFriendsViewAdapter(Context context, List<String> listDataHeader, List<UserModel> listFriendSuggestions, ArrayList<Contact2> listContacts) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.listFriendSuggestions = listFriendSuggestions;
        this.listContacts = listContacts;

//        meList = relations.getMe();
//        friendsList = relations.getFriends();
//        groupList = relations.getGroup();

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        if (groupPosition == 0) {
            return listFriendSuggestions.get(childPosititon);
        } else if (groupPosition == 1) {
            return listContacts.get(childPosititon);
        } else {
            return null;
        }
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child_add, null);
        }


        ImageView avatar = (ImageView) convertView.findViewById(R.id.contact_avatar_iv);
        TextView name = (TextView) convertView.findViewById(R.id.contact_name_tv);
        TextView status = (TextView) convertView.findViewById(R.id.contact_status_tv);
        //TextView txt_profile = (TextView) convertView.findViewById(R.id.txt_profile);

        if (groupPosition == 0) {
            //txt_profile.setText("My Profile");
            if (listFriendSuggestions.size() > 0 && listFriendSuggestions.get(childPosition) != null) {
                Picasso.with(_context)
                        .load(WOUApp.SOCIAL_ENDPOINT + "/" + listFriendSuggestions.get(childPosition).avatar)
                        .resize(200, 200)
                        .transform(new RoundedTransformation(100, 4))
                        .into(avatar);

                status.setVisibility(View.GONE);

                name.setText(listFriendSuggestions.get(childPosition).name);
                if(!listFriendSuggestions.get(childPosition).username.equals("")) {
                    status.setText("@" + listFriendSuggestions.get(childPosition).username);
                    status.setVisibility(View.VISIBLE);
                }
                else if(!listFriendSuggestions.get(childPosition).phone.equals("")) {
                    status.setText("+" + listFriendSuggestions.get(childPosition).phoneCode + listFriendSuggestions.get(childPosition).phone);
                    status.setVisibility(View.VISIBLE);
                }
//                if (!listFriendSuggestions.get(childPosition).getAbout().equals(""))
//                    status.setText(listFriendSuggestions.get(childPosition).getAbout());
//                else
//                    status.setVisibility(View.GONE);
            }
            //
        } else if (groupPosition == 1) {
            if (listContacts.size() > 0)
                if (listContacts.get(childPosition) != null) {

                    Contact2 contact = listContacts.get(childPosition);

                    status.setVisibility(View.GONE);

                    String first = contact.name.substring(0,1).toUpperCase();

                    name.setText(contact.name);

                    ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

                    int color1 = generator.getRandomColor();
// generate color based on a key (same key returns the same color), useful for list/grid views
                    //int color2 = generator.getColor(contact.numbers.get(0).number);

                    TextDrawable drawable = TextDrawable.builder()
                            .beginConfig()
                            .width(90)  // width in px
                            .height(90) // height in px
                            .endConfig()
                            .buildRound(first, color1);

                    avatar.setImageDrawable(drawable);

//                        Picasso.with(_context)
//                                .load(drawable)
//                                .resize(200, 200)
//                                .transform(new RoundedTransformation(100, 4))
//                                .into(avatarIv);
//                    if(contact.getPhone().size() > 0) {
//                        status.setText(contact.numbers.get(0).number);
//                        status.setVisibility(View.VISIBLE);
//
//
//                        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
//
//                        int color1 = generator.getRandomColor();
//// generate color based on a key (same key returns the same color), useful for list/grid views
//                        //int color2 = generator.getColor(contact.numbers.get(0).number);
//
//                        TextDrawable drawable = TextDrawable.builder()
//                                .beginConfig()
//                                .width(90)  // width in px
//                                .height(90) // height in px
//                                .endConfig()
//                                .buildRound(first, color1);
//
//                        avatar.setImageDrawable(drawable);
//
////                        Picasso.with(_context)
////                                .load(drawable)
////                                .resize(200, 200)
////                                .transform(new RoundedTransformation(100, 4))
////                                .into(avatarIv);
//
//                    } else if(contact.emails.size() > 0) {
//                        status.setText(contact.emails.get(0).address);
//                        status.setVisibility(View.VISIBLE);
//
//                        status.setText(contact.emails.get(0).address);
//                        status.setVisibility(View.VISIBLE);
//
//
//                        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
//
//                        int color1 = generator.getRandomColor();
//// generate color based on a key (same key returns the same color), useful for list/grid views
//                        int color2 = generator.getColor(contact.emails.get(0).address);
//
//                        TextDrawable drawable = TextDrawable.builder()
//                                .beginConfig()
//                                .width(90)  // width in px
//                                .height(90) // height in px
//                                .endConfig()
//                                .buildRound(first, color2);
//
//                        avatar.setImageDrawable(drawable);
//                    }





                }

        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0) {
            return listFriendSuggestions.size();
        } else if (groupPosition == 1) {
            return listContacts.size();
        }  else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }


        TextView lblListHeader = (TextView) convertView.findViewById(R.id.ExListHeader);
        ImageView imgListGroup = (ImageView) convertView.findViewById(R.id.img_header);
        TextView txt_count = (TextView) convertView.findViewById(R.id.txt_count);

        if (groupPosition == 0) {
            imgListGroup.setVisibility(View.GONE);
            txt_count.setVisibility(View.GONE);
        }

        imgListGroup.setImageResource(R.drawable.ic_friend_friends);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
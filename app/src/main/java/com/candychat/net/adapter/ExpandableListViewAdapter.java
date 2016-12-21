package com.candychat.net.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.main.FriendsFragment;
import com.candychat.net.model.UserModel;
import com.candychat.net.woumodel.Relations;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;

    private List<Relations.MeEntity> meList = new ArrayList<>();
    private List<Relations.FriendsEntity> friendsList = new ArrayList<>();
    private List<Relations.FavoriteEntity> favoriteList = new ArrayList<>();
    private List<Relations.GroupEntity> groupList = new ArrayList<>();

    List<UserModel> listFriendSuggestions = new ArrayList<>();

    public ExpandableListViewAdapter(Context context, List<String> listDataHeader,
                                     Relations relations) {
        this._context = context;
        this._listDataHeader = listDataHeader;

        meList = relations.getMe();
        friendsList = relations.getFriends();
        groupList = relations.getGroup();
        favoriteList = relations.getFavorite();

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        if(groupPosition == 0) {
            return meList.get(childPosititon);
        } else if(groupPosition == 1) {
            return groupList.get(childPosititon);
        } else if(groupPosition == 2) {
            return favoriteList.get(childPosititon);
        } else if(groupPosition == 3) {
            return friendsList.get(childPosititon);
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
            convertView = infalInflater.inflate(R.layout.list_child, null);
        }

        ImageView avatar = (ImageView) convertView.findViewById(R.id.contact_avatar_iv);
        TextView name = (TextView) convertView.findViewById(R.id.contact_name_tv);
        TextView status = (TextView) convertView.findViewById(R.id.contact_status_tv);
        //TextView txt_profile = (TextView) convertView.findViewById(R.id.txt_profile);

        if (groupPosition == 0) {
            //txt_profile.setText("My Profile");
            if(meList.size() == 1 && meList.get(childPosition) != null) {
                Picasso.with(_context)
                        .load(WOUApp.SOCIAL_ENDPOINT + "/" + meList.get(childPosition).getAvatar())
                        .resize(200, 200)
                        .transform(new RoundedTransformation(100, 4))
                        .into(avatar);

                if(!meList.get(childPosition).getName().equals(""))
                    name.setText(meList.get(childPosition).getName());
                else
                    name.setText("" + meList.get(childPosition).getUsername());

                if(!WOUApp.mPref.about().getOr("").equals(""))
                    status.setText(WOUApp.mPref.about().getOr(""));
                else
                    status.setVisibility(View.GONE);
            }


        } else if (groupPosition == 1) {
            if(groupList.size() != 0)
                if(groupList.get(childPosition) != null) {
                Picasso.with(_context)
                        .load(WOUApp.CHAT_ENDPOINT + groupList.get(childPosition).getAvatar())
                        .resize(200, 200)
                        .transform(new RoundedTransformation(100, 4))
                        .into(avatar);

                    if(!groupList.get(childPosition).getName().equals(""))
                        name.setText(groupList.get(childPosition).getName());
                    else
                        name.setText("@GroupChat");

                    status.setVisibility(View.GONE);

            }

        }
        else if (groupPosition == 2) {
            //txt_profile.setVisibility(View.GONE);
            if(favoriteList.size() != 0)
                if(favoriteList.get(childPosition) != null) {
                    String avatarUrl = WOUApp.SOCIAL_ENDPOINT + "/" + favoriteList.get(childPosition).getAvatar();
                    Picasso.with(_context)
                            .load(avatarUrl)
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(avatar);

                    //name.setText(favoriteList.get(childPosition).getName());

                    if(!favoriteList.get(childPosition).getName().equals(""))
                        name.setText(favoriteList.get(childPosition).getName());
                    else
                        name.setText("" + favoriteList.get(childPosition).getUsername());

                    if(!favoriteList.get(childPosition).getAbout().equals(""))
                        status.setText(favoriteList.get(childPosition).getAbout());
                    else
                        status.setVisibility(View.GONE);
                }
        }
        else if (groupPosition == 3) {
            //txt_profile.setVisibility(View.GONE);
            if(friendsList.size() != 0)
            if(friendsList.get(childPosition) != null) {
                String avatarUrl = WOUApp.SOCIAL_ENDPOINT + "/" + friendsList.get(childPosition).getAvatar();
                    Picasso.with(_context)
                            .load(avatarUrl)
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(avatar);

                if(!friendsList.get(childPosition).getName().equals(""))
                    name.setText(friendsList.get(childPosition).getName());
                else
                    name.setText("@" + friendsList.get(childPosition).getUsername());

                //name.setText(friendsList.get(childPosition).getName());
                if(!friendsList.get(childPosition).getAbout().equals(""))
                    status.setText(friendsList.get(childPosition).getAbout());
                else
                    status.setVisibility(View.GONE);
            }


        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(groupPosition == 0) {
            return meList.size();
        } else if(groupPosition == 1) {
            return groupList.size();
        }
        else if(groupPosition == 2) {
            return favoriteList.size();
        }
        else if(groupPosition == 3) {
            return friendsList.size();
        } else {
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

        imgListGroup.setImageResource(FriendsFragment.images[groupPosition]);

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
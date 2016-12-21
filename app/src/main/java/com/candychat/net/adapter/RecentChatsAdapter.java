package com.candychat.net.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.model.Conversation;
import com.candychat.net.utils.StringUtil;
import com.candychat.net.view.RoundedTransformation;
import com.module.candychat.net.emoji.EmojiTextView;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecentChatsAdapter extends BaseAdapter {
    Activity mContext;
    ArrayList<Conversation> list = new ArrayList<>();

    public RecentChatsAdapter(Activity context, ArrayList<Conversation> list) {
        this.mContext = context;
        this.list = list;

    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        Conversation c = list.get(position);
        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = mInflater.inflate(R.layout.item_recent_chats, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(c != null && !c.avatarUrl.equals(""))
        {
            Date date = null;
            try {
                date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(c.time.replaceAll("Z$", "+0000"));
                Date dateToday = new Date();

                String dateString = DateFormat.format("d/M", date).toString();
                String timeString = DateFormat.format("h:mm", date).toString();

                if(inSameDay(date, dateToday)){
                    holder.timeTv.setText(timeString);
                } else if(inYesterday(date, dateToday)) {
                    // yesterday
                    holder.timeTv.setText("Yesterday");
                } else {
                    holder.timeTv.setText(dateString);
                }



            } catch (ParseException e) {
                e.printStackTrace();
            }


//            Date date = null;
//            try {
//                date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(c.time.replaceAll("Z$", "+0000"));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            PrettyTime p = new PrettyTime();
//            String ago = p.format(date);

            if(!c.name.equals(""))
                holder.nameTv.setText(Html.fromHtml(c.name));
            else
                holder.nameTv.setText(Html.fromHtml("" + c.username));

            //message.spanned = CustomHtml.fromHtml(message.htmlStringColor, fontLoader);

            String theRecentText = StringUtil.unescape_perl_string(c.msg);

           // holder.msgTv.setText(c.msg, TextView.BufferType.SPANNABLE);
//            JSONObject data = null;
//            try {
//                data = new JSONObject("");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            String theStyle;
//            int theSize;
//            String htmlStringColor = "";
//
//            for (ListRecentChat.DataBean ent : c.data) {
//                if (ent.getStyle() != null) {
//                    theStyle = ent.getStyle().getStyle().replace(".ttf", "").replace(".otf", "");
//                    if (ent.getStyle().getSize().equals("16"))
//                        theSize = 22;
//                    else
//                        theSize = Integer.parseInt(ent.getStyle().getSize()) + 6;
//
//                    htmlStringColor += "<font face='" + theStyle + "' color ='#" + ent.getStyle().getColor() + "' size='" + theSize + "'>" + ent.getMessage() + "</font>";
//                }
//            }

            //ChatActivity.interpretTheMessage(mContext, data);

            String regex = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";

            boolean isEmoji = false;

            Matcher matcher = Pattern.compile(regex).matcher(theRecentText);
            while (matcher.find()) {
                isEmoji = true;
            }

            if(!isEmoji) {
                if(c.msg != null && c.msg.length() > 30)
                    holder.msgTv.setText(theRecentText.substring(0,29) + "...");
                else
                    holder.msgTv.setText(theRecentText);
            } else {
                holder.msgTv.setMaxLines(1);
                holder.msgTv.setText(theRecentText);
            }






            if(c.badge == 0) {
                holder.badgeTv.setVisibility(View.GONE);
            } else {
                holder.badgeTv.setVisibility(View.VISIBLE);
                holder.badgeTv.setText(String.valueOf(c.badge));
            }

            Picasso.with(mContext)
                    .load(c.avatarUrl)
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(holder.avatarIv);
        }
        return view;
    }

    public static boolean inYesterday(Date date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(Date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        if ((year1 == year2) && (day1 == day2-1)) {
            return true;
        }
        return false;
    }

    public static boolean inSameDay(Date date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(Date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        if ((year1 == year2) && (day1 == day2)) {
            return true;
        }
        return false;
    }

    private class ViewHolder {
        public TextView nameTv, msgTv, timeTv,badgeTv;
        public ImageView avatarIv;

        public ViewHolder(View convertView) {
            badgeTv = (TextView) convertView.findViewById(R.id.badge);
            nameTv = (TextView) convertView.findViewById(R.id.name);
            msgTv = (EmojiTextView) convertView.findViewById(R.id.message);
            msgTv.setEllipsize(TextUtils.TruncateAt.END);
            msgTv.setMaxLines(2);
            //msgTv.setText("text");
            timeTv = (TextView) convertView.findViewById(R.id.time);
            avatarIv = (ImageView) convertView.findViewById(R.id.list_image);
        }
    }
}
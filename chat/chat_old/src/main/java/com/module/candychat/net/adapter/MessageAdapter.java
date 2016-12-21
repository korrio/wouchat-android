package com.module.candychat.net.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.module.candychat.net.R;
import com.module.candychat.net.wouclass.TheMessageObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MessageAdapter extends BaseAdapter {
    private Context context;
    private List<TheMessageObject> msgList = null;
    private int mMinItemWidth;
    private int mMaxItemWidth;

    int userId;

    public MessageAdapter(Context context, List<TheMessageObject> messages, int mUserId) {
        super();
        userId = mUserId;
        this.context = context;
        this.msgList = messages;

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mMaxItemWidth = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);


    }

    @Override
    public int getCount() {
        return msgList != null ? msgList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (this.msgList == null || this.msgList.get(position) == null) {
            return 0;
        }
        return msgList.get(position).onRight ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public View lastItem;

    public View getLastItem() {
        return lastItem;
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint("InflateParams")
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TheMessageObject m = msgList.get(position);
        boolean isSend;

        JSONArray dataArr = null;
        JSONObject dataObj = null;

        if (m != null && isJSONValid(m.dataString)) {

                try {
                    dataArr = new JSONArray(m.dataString);
                    dataObj = (JSONObject) dataArr.opt(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }

        if (m != null) {
            ViewHolder viewHolder = null;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                if (m.onRight) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_list_right, null);
                } else {
                    convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_list_left, null);
                }
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.sendDateTextView = (TextView) convertView.findViewById(R.id.sendDateTextView);
            viewHolder.sendTimeTextView = (TextView) convertView.findViewById(R.id.time_text);
            viewHolder.userAvatarImageView = (ImageView) convertView.findViewById(R.id.userAvatarImageView);
            viewHolder.userNameTextView = (TextView) convertView.findViewById(R.id.userNameTextView);
            viewHolder.textTextView = (TextView) convertView.findViewById(R.id.message_text);
            viewHolder.photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
            viewHolder.faceImageView = (ImageView) convertView.findViewById(R.id.faceImageView);
            viewHolder.failImageView = (ImageView) convertView.findViewById(R.id.failImageView);
            viewHolder.sendingProgressBar = (ProgressBar) convertView.findViewById(R.id.sendingProgressBar);
            viewHolder.logo_clip = (ImageView) convertView.findViewById(R.id.logo_clip);
            viewHolder.chat_item_layout_content = (RelativeLayout) convertView.findViewById(R.id.chat_item_layout_content);
            viewHolder.loction = (LinearLayout) convertView.findViewById(R.id.layout_invite);
            viewHolder.user_reply_status = (TextView) convertView.findViewById(R.id.user_reply_status);
            viewHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.logo_avatra = (ImageView) convertView.findViewById(R.id.logo_avatra);
            viewHolder.seconds = (TextView) convertView.findViewById(R.id.id_recorder_time);
            viewHolder.length = convertView.findViewById(R.id.id_recorder_length);
            viewHolder.mAnimView = convertView.findViewById(R.id.id_recorder_anim);
            convertView.setTag(viewHolder);

            switch (m.getMessageType()) {
                case 0://text
                    if (m.getMessage() != null) {
                        viewHolder.seconds.setVisibility(View.GONE);
                        viewHolder.mAnimView.setVisibility(View.GONE);
                        viewHolder.length.setVisibility(View.GONE);
                        viewHolder.faceImageView.setVisibility(View.GONE);
                        viewHolder.loction.setVisibility(View.GONE);
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                        viewHolder.logo_clip.setVisibility(View.GONE);
                        viewHolder.txt_name.setVisibility(View.GONE);
                        viewHolder.logo_avatra.setVisibility(View.GONE);
                        viewHolder.textTextView.setVisibility(View.VISIBLE);
                        if(m.spanned != null)
                            viewHolder.textTextView.setText(m.spanned);
                        else {
                            viewHolder.textTextView.setText(m.getMessage());
                            viewHolder.textTextView.setTextColor(Color.BLACK);
                            viewHolder.textTextView.setTypeface(Typeface.DEFAULT);
                            //viewHolder.textTextView.setTypeface(Constant.getTypeface(context,Constant.TYPEFACE_OPENSANS));
                        }

                        viewHolder.textTextView.setTextSize(16);


                        viewHolder.photoImageView.setVisibility(View.GONE);
                    } else {
                        viewHolder.textTextView.setText("");
                        viewHolder.textTextView.setVisibility(View.VISIBLE);
                        viewHolder.photoImageView.setVisibility(View.GONE);
                        viewHolder.faceImageView.setVisibility(View.GONE);
                    }
                    if (false) {
                        //sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                        LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                        layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.message_text);
                        if (false) {
                            //viewHolder.failImageView.setVisibility(View.VISIBLE);
                            viewHolder.failImageView.setLayoutParams(layoutParams);
                        } else {
                            viewHolder.failImageView.setVisibility(View.GONE);
                        }

                        if (false) {
                            viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                            viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                        } else {
                            viewHolder.sendingProgressBar.setVisibility(View.GONE);
                        }

                    } else {
                        viewHolder.failImageView.setVisibility(View.GONE);
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);

                        //sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.message_text);
                        //viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                    }

                    break;

                case 1://face
                    viewHolder.seconds.setVisibility(View.GONE);
                    viewHolder.mAnimView.setVisibility(View.GONE);
                    viewHolder.length.setVisibility(View.GONE);
                    viewHolder.loction.setVisibility(View.GONE);
                    viewHolder.photoImageView.setVisibility(View.GONE);
                    viewHolder.textTextView.setVisibility(View.GONE);
                    viewHolder.faceImageView.setVisibility(View.VISIBLE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    viewHolder.sendDateTextView.setVisibility(View.GONE);
                    viewHolder.logo_clip.setVisibility(View.GONE);
                    viewHolder.txt_name.setVisibility(View.GONE);
                    viewHolder.logo_avatra.setVisibility(View.GONE);
                    viewHolder.textTextView.setVisibility(View.GONE);
                    viewHolder.photoImageView.setVisibility(View.GONE);


                    if (dataObj != null && !dataObj.optString("tattooUri").equals("")) {
                        String image = dataObj.optString("tattooUri");
                        AssetManager mngr = context.getAssets();
                        InputStream input;
                        try {
                            input = mngr.open(image);
                            //Bitmap bm = BitmapFactory.decodeStream(input);

                            BitmapFactory.Options options = new BitmapFactory.Options();
                            //options.inSampleSize = 1;
                            Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);

                            viewHolder.faceImageView.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }


                    break;

                default:
                    viewHolder.textTextView.setText(m.getMessage());
                    viewHolder.photoImageView.setVisibility(View.GONE);
                    viewHolder.faceImageView.setVisibility(View.GONE);
                    break;
            }

            viewHolder.textTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            lastItem = viewHolder.textTextView;
        }

        return convertView;
    }


    public List<TheMessageObject> getData() {
        return msgList;
    }

    public void setData(List<TheMessageObject> data) {
        this.msgList = data;
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


    static class ViewHolder {

        public ImageView userAvatarImageView;
        public TextView sendDateTextView;
        public TextView userNameTextView;

        public TextView textTextView;
        public ImageView photoImageView;
        public ImageView faceImageView;
        public ImageView failImageView;
        public TextView user_reply_status;
        public ImageView logo_avatra;
        public ImageView logo_clip;
        public TextView txt_name;
        public TextView sendTimeTextView;
        public ProgressBar sendingProgressBar;
        public LinearLayout loction;
        public RelativeLayout chat_item_layout_content;
        public TextView seconds;
        public View length;
        public View mAnimView;
    }


}

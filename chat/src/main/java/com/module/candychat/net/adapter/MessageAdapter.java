package com.module.candychat.net.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.module.candychat.net.ChatActivity;
import com.module.candychat.net.R;
import com.module.candychat.net.activity.VideoActivity;
import com.module.candychat.net.fragment.WhereMapFragment;
import com.module.candychat.net.activity.PictureActivity;
import com.module.candychat.net.model.Message;
import com.module.candychat.net.util.MediaManager;
import com.module.candychat.net.view.SpinKitDrawable2;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import co.moonmonkeylabs.realmsearchview.model.TheMessageObject;


public class MessageAdapter extends BaseAdapter {
    private Context context;
    private List<TheMessageObject> msgList = null;
    private Map<String,TheMessageObject> msgMap = null;

    private static final int MESSAGE_TYPE_RECV_TXT = 0;
    private static final int MESSAGE_TYPE_SENT_TXT = 1;
    private static final int MESSAGE_TYPE_SENT_IMAGE = 2;
    private static final int MESSAGE_TYPE_SENT_LOCATION = 3;
    private static final int MESSAGE_TYPE_RECV_LOCATION = 4;
    private static final int MESSAGE_TYPE_RECV_IMAGE = 5;
    private static final int MESSAGE_TYPE_SENT_VOICE = 6;
    private static final int MESSAGE_TYPE_RECV_VOICE = 7;
    private static final int MESSAGE_TYPE_SENT_VIDEO = 8;
    private static final int MESSAGE_TYPE_RECV_VIDEO = 9;
    private static final int MESSAGE_TYPE_SENT_CONTACT = 10;
    private static final int MESSAGE_TYPE_RECV_CONTACT = 11;
    private static final int MESSAGE_TYPE_SENT_VOICE_CALL = 12;
    private static final int MESSAGE_TYPE_RECV_VOICE_CALL = 13;
    private static final int MESSAGE_TYPE_SENT_VIDEO_CALL = 14;
    private static final int MESSAGE_TYPE_RECV_VIDEO_CALL = 15;

    private int mMinItemWidth;
    private int mMaxItemWidth;

    int userId;
    int chatType;
    private ChatActivity activity;
    private LayoutInflater inflater;

    public MessageAdapter(Context context, List<TheMessageObject> messages, int mUserId, int chatType) {
        super();
        userId = mUserId;
        this.chatType = chatType;
        this.context = context;
        this.msgList = messages;
        activity = (ChatActivity) context;
        inflater = LayoutInflater.from(context);

        msgMap = new HashMap<String, TheMessageObject>();
        for (TheMessageObject m : messages) {
            msgMap.put(m.getId() + "", m);
        }

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

//    public int getItemViewType(int position) {
//        TheMessageObject message = msgList.get(position);
//        if (message.getType() == TheMessageObject.Type.TXT) {
//            return message.direct == TheMessageObject.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TXT
//                    : MESSAGE_TYPE_SENT_TXT;
//        }
//        if (message.getType() == TheMessageObject.Type.IMAGE) {
//            return message.direct == TheMessageObject.Direct.RECEIVE ? MESSAGE_TYPE_RECV_IMAGE
//                    : MESSAGE_TYPE_SENT_IMAGE;
//
//        }
//        if (message.getType() == TheMessageObject.Type.LOCATION) {
//            return message.direct == TheMessageObject.Direct.RECEIVE ? MESSAGE_TYPE_RECV_LOCATION
//                    : MESSAGE_TYPE_SENT_LOCATION;
//        }
//        if (message.getType() == TheMessageObject.Type.VOICE) {
//            return message.direct == TheMessageObject.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VOICE
//                    : MESSAGE_TYPE_SENT_VOICE;
//        }
//        if (message.getType() == TheMessageObject.Type.VIDEO) {
//            return message.direct == TheMessageObject.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VIDEO
//                    : MESSAGE_TYPE_SENT_VIDEO;
//        }
//        if (message.getType() == TheMessageObject.Type.CONTACT) {
//            return message.direct == TheMessageObject.Direct.RECEIVE ? MESSAGE_TYPE_RECV_CONTACT
//                    : MESSAGE_TYPE_SENT_CONTACT;
//        }
//
//        return -1;// invalid
//    }

    @Override
    public int getItemViewType(int position) {
        if (this.msgList == null || this.msgList.get(position) == null) {
            return 0;
        }

        return msgList.get(position).onRight ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 12;
    }

    public Map<String, Timer> timers = new Hashtable<String, Timer>();
    public Map<Long, TheMessageObject> sendingMsgObj = new Hashtable<>();

//    private View createViewByMessage(TheMessageObject message, int position) {
//        if(message.getType() != null) {
//            switch (message.getType()) {
//                case LOCATION:
//                    return !message.onRight ? inflater
//                            .inflate(R.layout.chat_item_list_left, null) : inflater
//                            .inflate(R.layout.chat_item_list_right, null);
//                case IMAGE:
//                    return !message.onRight ? inflater
//                            .inflate(R.layout.chat_item_list_left, null) : inflater
//                            .inflate(R.layout.chat_item_list_right, null);
//
//                case VOICE:
//                    return !message.onRight ? inflater
//                            .inflate(R.layout.chat_item_list_left, null) : inflater
//                            .inflate(R.layout.chat_item_list_right, null);
//                case VIDEO:
//                    return !message.onRight ? inflater
//                            .inflate(R.layout.chat_item_list_left, null) : inflater
//                            .inflate(R.layout.chat_item_list_right, null);
//                case CONTACT:
//                    return !message.onRight ? inflater
//                            .inflate(R.layout.chat_item_list_left, null) : inflater
//                            .inflate(R.layout.chat_item_list_right, null);
//                default:
//                    return !message.onRight ? inflater
//                            .inflate(R.layout.chat_item_list_left, null) : inflater
//                            .inflate(R.layout.chat_item_list_right, null);
//            }
//        } else {
//            return !message.onRight ? inflater
//                    .inflate(R.layout.chat_item_list_left, null) : inflater
//                    .inflate(R.layout.chat_item_list_right, null);
//        }
//    }

    TheMessageObject m;
    TheMessageObject oldM;

    ViewHolder holder;

    @SuppressLint("InflateParams")
    public View getView(final int position, View convertView, ViewGroup parent) {
        m = msgList.get(position);

        if(m.getMessageType() == 0)
            m.setType(TheMessageObject.Type.TXT);
        else if(m.getMessageType() == 1)
            m.setType(TheMessageObject.Type.STICKER);
        else if(m.getMessageType() == 2)
            m.setType(TheMessageObject.Type.IMAGE);
        else if(m.getMessageType() == 3)
            m.setType(TheMessageObject.Type.VIDEO);
        else if(m.getMessageType() == 4)
            m.setType(TheMessageObject.Type.LOCATION);
        else if(m.getMessageType() == 5)
            m.setType(TheMessageObject.Type.CONTACT);
        else if(m.getMessageType() == 6)
            m.setType(TheMessageObject.Type.VOICE);
        else if(m.getMessageType() == 9)
            m.setType(TheMessageObject.Type.CMD);

        if(position-1 >= 0)
            oldM = msgList.get(position-1);
        //boolean isSend;
        JSONArray ja;
        JSONObject jo = new JSONObject();


        if (m != null) {

            if(m.getDataString() != null && m.getMessageType() !=0) {
                if(isJSONValid(m.getDataString())) {
                    //Log.e("getDataString",m.getDataString());
                    try {
                        ja = new JSONArray(m.getDataString());
                        jo = (JSONObject) ja.get(0);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }


            //Log.e("theM","<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //Log.e("theM",m.toString());
            //Log.e("theM",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            holder = null;


            if (convertView == null) {
                holder = new ViewHolder();
                //convertView = createViewByMessage(m,position);

                int type = getItemViewType(position);
                if (type == 1) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_list_right, null);
                } else {
                    convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_list_left, null);
                }
                convertView.setTag(holder);

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.clickableItemLayout = (RelativeLayout) convertView.findViewById(R.id.clickable_layout);
            holder.chatItemLayout = (RelativeLayout) convertView.findViewById(R.id.chat_item_layout_content);
            holder.contactLayout = (LinearLayout) convertView.findViewById(R.id.layout_contact);

            holder.sendDateTextView = (TextView) convertView.findViewById(R.id.sendDateTextView);
            holder.seeTextView = (TextView) convertView.findViewById(R.id.time_see);
            holder.sendTimeTextView = (TextView) convertView.findViewById(R.id.time_text);

            holder.messageContainer = (LinearLayout) convertView.findViewById(R.id.message_container);

            holder.userAvatarImageView = (ImageView) convertView.findViewById(R.id.userAvatarImageView);
            holder.partnerNameTextView = (TextView) convertView.findViewById(R.id.partner_name);
            holder.textTextView = (TextView) convertView.findViewById(R.id.message_text);
            holder.photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
            holder.faceImageView = (ImageView) convertView.findViewById(R.id.faceImageView);
            holder.failImageView = (ImageView) convertView.findViewById(R.id.failImageView);
            holder.sendingProgressBar = (ProgressBar) convertView.findViewById(R.id.sendingProgressBar);
            holder.contactAvatarImageView = (ImageView) convertView.findViewById(R.id.contact_avatar);
            holder.contactNameTextView = (TextView) convertView.findViewById(R.id.contact_name);

            holder.user_reply_status = (TextView) convertView.findViewById(R.id.user_reply_status);

            holder.voiceLayout = (LinearLayout) convertView.findViewById(R.id.layout_voice);
            holder.seconds = (TextView) convertView.findViewById(R.id.id_recorder_time);
            holder.length = convertView.findViewById(R.id.id_recorder_length);
            holder.mAnimView = convertView.findViewById(R.id.id_recorder_anim);
            convertView.setTag(holder);
            activity.registerForContextMenu(holder.textTextView);
            holder.textTextView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    ClipboardManager cManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData cData = ClipData.newPlainText("text", holder.textTextView.getText());
                    cManager.setPrimaryClip(cData);

                    return true;
                }
            });

            if(chatType == 2 && !m.onRight) {
                holder.messageContainer.setVisibility(View.VISIBLE);
                holder.partnerNameTextView.setVisibility(View.VISIBLE);

                    if(!m.getSender().getName().equals(""))
                        holder.partnerNameTextView.setText(m.getSender().getName());
                    else
                        holder.partnerNameTextView.setText(m.getSender().getUsername());

            } else {
                holder.partnerNameTextView.setVisibility(View.GONE);
            }

            Date date = new Date(m.getTimestamp() * 1000);
            Date dateToday = new Date();

            String dateString = DateFormat.format("EEE MMM dd, yyyy", date).toString();
            String timeString = DateFormat.format("h:mm aa", date).toString();

            holder.seeTextView.setVisibility(View.GONE);

            if(m.onRight && holder.sendTimeTextView != null && holder.seeTextView != null) {
                //TODO: debug for messageId to see sequeces of message
                holder.seeTextView.setVisibility(View.GONE);
                if(m.see) {
                    holder.seeTextView.setVisibility(View.VISIBLE);
                    holder.seeTextView.setText("See");
                }
                else
                    holder.seeTextView.setVisibility(View.GONE);


                holder.sendTimeTextView.setVisibility(View.VISIBLE);
                holder.sendTimeTextView.setText(timeString);

//                if(timeString != null) {
//
//                } else {
//                    viewHolder.sendTimeTextView.setText(getTimestampString(date));
//                }


                //viewHolder.sendTimeTextView.setText(getTimestampString(date));
                //viewHolder.sendTimeTextView.append(" :: " + date.toString());

            } else {
                if(!m.onRight) {
                    holder.sendTimeTextView.setVisibility(View.VISIBLE);
                    holder.sendTimeTextView.setText(timeString);
                    holder.userAvatarImageView.setVisibility(View.VISIBLE);

                    final String thumbUrl =  m.getAvatarUrl();
                    Log.e("thumbUrl",thumbUrl);

                    Picasso.with(context).load(thumbUrl).resize(200,200).transform(new RoundedTransformation(100,0)).centerCrop().into(holder.userAvatarImageView);
                }
            }

            // message header
            holder.sendDateTextView.setVisibility(View.GONE);
            try {
                if(position == 0){
                    holder.sendDateTextView.setVisibility(View.VISIBLE);
                    if(inSameDay(date, dateToday)){
                        holder.sendDateTextView.setText("TODAY");
                    } else {
                        holder.sendDateTextView.setText(dateString);
                    }

                }else{
                    if(oldM != null) {
                        Date lastMsgDate = new Date(oldM.getTimestamp() * 1000);
                        if(inSameDay(date, lastMsgDate)){
                            holder.sendDateTextView.setVisibility(View.GONE);
                            //viewHolder.sendDateTextView.setText(dateString);
                        }else{
                            holder.sendDateTextView.setVisibility(View.VISIBLE);
                            holder.sendDateTextView.setText(dateString);
                        }
                    }


                }

            } catch (Exception e) {
            }

            Log.e("theSize",msgList.size() + "");
            //Log.e("IOIOIOIOIO",m.toString());
            switch (m.getMessageType()) {
                case Message.MSG_TYPE_TEXT://text
                    handleTextMessage(m,holder);
                    break;
                case Message.MSG_TYPE_STICKER://face
                    handleStickerMessage(m,holder);
                    break;
                case Message.MSG_TYPE_PHOTO:
                    handlePhotoMessage(m,jo,holder);
                    break;
                case Message.MSG_TYPE_CLIP:
                    handleClipMessage(m,jo,holder);
                    break;
                case Message.MSG_TYPE_LOCATION:
                    handleLocationMessage(m,jo,holder);
                    break;
                case Message.MSG_TYPE_CONTACT:
                    handleContactMessage(m,jo,holder);
                    break;
                case Message.MSG_TYPE_VOICE:
                    handleVoiceMessage(m,jo,holder);
                    break;
                default:
                    holder.textTextView.setVisibility(View.GONE);
                    holder.photoImageView.setVisibility(View.GONE);
                    holder.faceImageView.setVisibility(View.GONE);

                    if(isJSONValid(m.getDataString())) {
                        Log.e("UNKNOWN_TYPE_MESSAGE", m.getDataString());
                    }
                    break;
            }

            holder.textTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        return convertView;
    }

    public void see() {
        for(TheMessageObject m : msgList) {
            m.see = true;
        }
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

    private void handleTextMessage(TheMessageObject m, final ViewHolder viewHolder) {
        if (m.getMessage() != null) {


            viewHolder.voiceLayout.setVisibility(View.GONE);
            viewHolder.seconds.setVisibility(View.GONE);
            viewHolder.mAnimView.setVisibility(View.GONE);
            viewHolder.length.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
            viewHolder.contactLayout.setVisibility(View.GONE);
            viewHolder.sendingProgressBar.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.contactNameTextView.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.textTextView.setVisibility(View.VISIBLE);
            if (m.spanned != null) {
                viewHolder.textTextView.setText(m.spanned, TextView.BufferType.SPANNABLE);
            } else {
                viewHolder.textTextView.setText(m.getMessage());

                viewHolder.textTextView.setTextColor(Color.BLACK);
                viewHolder.textTextView.setTypeface(Typeface.DEFAULT);
                //viewHolder.textTextView.setTypeface(Constant.getTypeface(context,Constant.TYPEFACE_OPENSANS));
            }

            viewHolder.textTextView.setTextIsSelectable(true);

            viewHolder.textTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData cData = ClipData.newPlainText("text", viewHolder.textTextView.getEditableText());
                    cm.setPrimaryClip(cData);
                    Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            if(m.getMessageColorful() != null && m.getMessageColorful().size() != 0)
            if(m.getMessageColorful().get(0) != null) {
                String theStyle = m.getMessageColorful().get(0).getStyle().getStyle();
                if(theStyle.equals("DEFAULT")
                        || theStyle.equals("Roboto-Regular")
                        || theStyle.equals("Amadeus")
                        || theStyle.equals("AlexBrush-Regular")
                        || theStyle.equals("Pacifico")
                        || theStyle.equals("LobsterTwo")
                        || theStyle.equals("KaushanScript-Regular")
                        || theStyle.equals("Sofia")
                        || theStyle.equals("GrandHotel-Regular")
                        || theStyle.equals("FFFTusj-Bold")
                        || theStyle.equals("MountainsofChristmas-Regular")
                        || theStyle.equals("DancingScriptOT")
                        || theStyle.equals("Amandeus")
                        || theStyle.equals("ComicaBD")
                        || theStyle.equals("VeggieBurger")
                        || theStyle.equals("Sniglet")
                        || theStyle.equals("BlackJack")
                        )
                    viewHolder.textTextView.setTextSize(16);
                else if(theStyle.toLowerCase().contains("lipgo") || theStyle.equals("LayijiSarangheyo"))
                    viewHolder.textTextView.setTextSize(18);
                else if(theStyle.equals("kt_smarn_piyatida") || theStyle.equals("kt_smarn_saiparn"))
                    viewHolder.textTextView.setTextSize(32);
                else
                    viewHolder.textTextView.setTextSize(28);
            }



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

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.failImageView.getLayoutParams();
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
    }

    private void handleStickerMessage(TheMessageObject m, ViewHolder viewHolder) {
        viewHolder.voiceLayout.setVisibility(View.GONE);
        viewHolder.seconds.setVisibility(View.GONE);
        viewHolder.mAnimView.setVisibility(View.GONE);
        viewHolder.length.setVisibility(View.GONE);
        viewHolder.contactLayout.setVisibility(View.GONE);
        viewHolder.photoImageView.setVisibility(View.GONE);
        viewHolder.textTextView.setVisibility(View.GONE);
        viewHolder.faceImageView.setVisibility(View.VISIBLE);
        viewHolder.sendingProgressBar.setVisibility(View.GONE);
        viewHolder.sendDateTextView.setVisibility(View.GONE);
        viewHolder.contactAvatarImageView.setVisibility(View.GONE);
        viewHolder.contactNameTextView.setVisibility(View.GONE);
        viewHolder.contactAvatarImageView.setVisibility(View.GONE);
        viewHolder.textTextView.setVisibility(View.GONE);
        viewHolder.photoImageView.setVisibility(View.GONE);
        viewHolder.messageContainer.setVisibility(View.GONE);

        if(isJSONValid(m.getDataString())) {
            JSONArray ja = null;
            try {
                ja = new JSONArray(m.getDataString());
                JSONObject dataObj = (JSONObject)  ja.get(0);

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

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    ArrayList<String> listUrls = new ArrayList<>();

    public ArrayList<String> getPhotoUrls() {

        JSONObject jo;
        JSONArray ja;


        for(TheMessageObject m : msgList) {
            if (m != null) {

                if (m.getDataString() != null && m.getMessageType() != 0) {
                    if (isJSONValid(m.getDataString())) {
                        //Log.e("getDataString",m.getDataString());
                        try {
                            ja = new JSONArray(m.getDataString());
                            jo = (JSONObject) ja.get(0);
                            if(m.messageType == Message.MSG_TYPE_PHOTO)
                                listUrls.add(jo.optString("full_path"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return listUrls;
    }

    private void handlePhotoMessage(TheMessageObject m, final JSONObject dataObj, ViewHolder viewHolder) {
        if (m.getMessage() != null) {

            viewHolder.voiceLayout.setVisibility(View.GONE);
            viewHolder.seconds.setVisibility(View.GONE);
            viewHolder.mAnimView.setVisibility(View.GONE);
            viewHolder.length.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
            viewHolder.contactLayout.setVisibility(View.GONE);
            viewHolder.sendingProgressBar.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.contactNameTextView.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.textTextView.setVisibility(View.GONE);


            viewHolder.textTextView.setVisibility(View.GONE);
            viewHolder.photoImageView.setVisibility(View.VISIBLE);
            viewHolder.faceImageView.setVisibility(View.GONE);



            if(m.state == Message.MSG_STATE_SUCCESS) {
                viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);

                String thumbUrl = dataObj.optString("thumb");
                //Log.e("thumbUrl",thumbUrl);

                if(!thumbUrl.equals("")) {
                    Picasso.with(context).load(thumbUrl).resize(240, 240).transform(new RoundedTransformation(180, 0)).centerCrop().into(viewHolder.photoImageView);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);

                } else {
                    m.state = Message.MSG_STATE_SENDING;
//                    viewHolder.photoImageView.setImageResource(R.color.white_transparent_more);
//                    viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
//                    SpinKitDrawable2 spinKitDrawable2=new SpinKitDrawable2(context);
//                    viewHolder.sendingProgressBar.setIndeterminateDrawable(spinKitDrawable2);
                }

                viewHolder.photoImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fileType = "";

                        if(dataObj != null)
                            fileType = ((JSONObject) dataObj).optString("fileType");

                        Log.e("clipPath",dataObj.optString("thumb"));

                        if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {

                            Intent intent = PictureActivity.newIntent(activity, dataObj.optString("full_path"),
                                    dataObj.optInt("id") + "");
                            ActivityOptionsCompat optionsCompat
                                    = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    activity, view, PictureActivity.TRANSIT_PIC);

                            try {
                                ActivityCompat.startActivity(activity, intent,
                                        optionsCompat.toBundle());
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                                activity.startActivity(intent);
                            }

//                            Intent intent =  new Intent(activity,ViewPagerActivity.class);
//
//                        ArrayList<String> urls = new ArrayList<>();
//                        String imageUrl = dataObj.optString("url");
//                        urls.add(0, imageUrl);
//
//                        intent.putExtra("current_item", 1);
//                        intent.putStringArrayListExtra("photos", urls);
//
//                            activity.startActivity(intent);

//                        Intent intent = new Intent(activity, PhotoPagerActivity.class);
//
//                        ArrayList<String> urls = new ArrayList<>();
//                        String imageUrl = dataObj.optString("url");
//                        urls.add(0, imageUrl);
//
//                        intent.putExtra("current_item", 1);
//                        intent.putStringArrayListExtra("photos", urls);
//
//                        activity.startActivity(intent);
                        }
                    }
                });


            }

            if(m.state == Message.MSG_STATE_SENDING) {
                viewHolder.photoImageView.setImageResource(R.color.white_transparent_more);
                viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                SpinKitDrawable2 spinKitDrawable2=new SpinKitDrawable2(context);
                viewHolder.sendingProgressBar.setIndeterminateDrawable(spinKitDrawable2);
            }


        } else {
            viewHolder.textTextView.setText("");
            viewHolder.textTextView.setVisibility(View.VISIBLE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        }
    }

    private void handleClipMessage(TheMessageObject m, final JSONObject dataObj, ViewHolder viewHolder) {
        if (m.getMessage() != null) {

            viewHolder.voiceLayout.setVisibility(View.GONE);
            viewHolder.seconds.setVisibility(View.GONE);
            viewHolder.mAnimView.setVisibility(View.GONE);
            viewHolder.length.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
            viewHolder.contactLayout.setVisibility(View.GONE);
            viewHolder.sendingProgressBar.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.contactNameTextView.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.textTextView.setVisibility(View.GONE);

            String thumbUrl = dataObj.optString("thumb");
            //Log.e("thumbUrl",thumbUrl);

            if(!thumbUrl.equals("")) {
                Picasso.with(context).load(thumbUrl).resize(240, 240).transform(new RoundedTransformation(180, 0)).centerCrop().placeholder(R.drawable.placeholder).into(viewHolder.photoImageView);
                viewHolder.sendingProgressBar.setVisibility(View.GONE);
            } else {
                m.state = Message.MSG_STATE_SENDING;
//                    viewHolder.photoImageView.setImageResource(R.color.white_transparent_more);
//                    viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
//                    SpinKitDrawable2 spinKitDrawable2=new SpinKitDrawable2(context);
//                    viewHolder.sendingProgressBar.setIndeterminateDrawable(spinKitDrawable2);
            }

            if(m.state == Message.MSG_STATE_SENDING) {

                viewHolder.photoImageView.setImageResource(R.color.white_transparent_more);
                viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                SpinKitDrawable2 spinKitDrawable2=new SpinKitDrawable2(context);
                viewHolder.sendingProgressBar.setIndeterminateDrawable(spinKitDrawable2);

            } else {
                viewHolder.photoImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fileType = "";
                        if(dataObj.optString("full_path") != null) {
                            Log.e("clipPath",dataObj.optString("full_path"));

                            Intent i = new Intent(activity,VideoActivity.class);
                            i.putExtra("PATH",dataObj.optString("full_path"));
                            activity.startActivity(i);
                        }

                    }
                });
            }



            viewHolder.textTextView.setVisibility(View.GONE);
            viewHolder.photoImageView.setVisibility(View.VISIBLE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        } else {
            viewHolder.textTextView.setText("");
            viewHolder.textTextView.setVisibility(View.VISIBLE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        }
    }

    @SuppressLint("NewApi")
    public static Bitmap retriveVideoFrameFromVideo(String p_videoPath)
            throws Throwable
    {
        Bitmap m_bitmap = null;
        MediaMetadataRetriever m_mediaMetadataRetriever = null;
        try
        {
            m_mediaMetadataRetriever = new MediaMetadataRetriever();
            m_mediaMetadataRetriever.setDataSource(p_videoPath);
            m_bitmap = m_mediaMetadataRetriever.getFrameAtTime();
        }
        catch (Exception m_e)
        {
            throw new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String p_videoPath)"
                            + m_e.getMessage());
        }
        finally
        {
            if (m_mediaMetadataRetriever != null)
            {
                m_mediaMetadataRetriever.release();
            }
        }
        return m_bitmap;
    }

    private void handleLocationMessage(TheMessageObject m, final JSONObject dataObj, ViewHolder viewHolder) {
        if (m.getMessage() != null) {
            viewHolder.voiceLayout.setVisibility(View.GONE);
            viewHolder.seconds.setVisibility(View.GONE);
            viewHolder.mAnimView.setVisibility(View.GONE);
            viewHolder.length.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
            viewHolder.contactLayout.setVisibility(View.GONE);
            viewHolder.sendingProgressBar.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.contactNameTextView.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.textTextView.setVisibility(View.GONE);

            String thumbUrl = dataObj.optString("imageUrl");
            if(!thumbUrl.equals(""))
                Picasso.with(context).load(thumbUrl).resize(320,240).transform(new RoundedTransformation(300,0)).centerCrop().into(viewHolder.photoImageView);


            viewHolder.textTextView.setVisibility(View.GONE);
            viewHolder.photoImageView.setVisibility(View.VISIBLE);
            viewHolder.faceImageView.setVisibility(View.GONE);
            viewHolder.photoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Double lat = dataObj.optDouble("latitude");
                    Double lon = dataObj.optDouble("longtitude");
                    if(lat != null && lon != null) {
                        Log.e("lat",lat + "");
                        Log.e("lon",lon + "");

                        Uri gmmIntentUri = Uri.parse("geo:"+lat+"," + lon + "?q=" +lat+"," + lon );
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(mapIntent);
                        }

                        //viewMap(lat, lon);
                    } else {
                        Toast.makeText(activity,"Lat long are null",Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {
            viewHolder.textTextView.setText("");
            viewHolder.textTextView.setVisibility(View.VISIBLE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        }
    }

    private void handleContactMessage(TheMessageObject m, final JSONObject dataObj, ViewHolder viewHolder) {
        if (m.getMessage() != null) {

            viewHolder.voiceLayout.setVisibility(View.GONE);
            viewHolder.seconds.setVisibility(View.GONE);
            viewHolder.mAnimView.setVisibility(View.GONE);
            viewHolder.length.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);

            viewHolder.sendingProgressBar.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);

            viewHolder.contactLayout.setVisibility(View.VISIBLE);
            viewHolder.contactNameTextView.setVisibility(View.VISIBLE);
            viewHolder.contactAvatarImageView.setVisibility(View.VISIBLE);
            viewHolder.textTextView.setVisibility(View.GONE);
//
//            Gson gson = new GsonBuilder().create();
//            final UserModel user = gson.fromJson(dataObj.toString(), UserModel.class);// obj is your object



            final String thumbUrl = "http://candychat.net/" + dataObj.optString("avatar_url");
            final String contactName = dataObj.optString("name");

            Picasso.with(context).load(thumbUrl).resize(200,200).transform(new RoundedTransformation(100,0)).centerCrop().into(viewHolder.contactAvatarImageView);
            viewHolder.contactNameTextView.setText(contactName);

            viewHolder.contactLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: intent to Profile Page
                    Toast.makeText(activity,"Send contact: " + dataObj.optString("name") + "",Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.textTextView.setVisibility(View.GONE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        } else {
            viewHolder.textTextView.setText("");
            viewHolder.textTextView.setVisibility(View.VISIBLE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        }
    }

    private void handleVoiceMessage(final TheMessageObject m, final JSONObject dataObj, final ViewHolder viewHolder) {
        if (m.getMessage() != null) {

            viewHolder.voiceLayout.setVisibility(View.VISIBLE);
            viewHolder.seconds.setVisibility(View.VISIBLE);
            viewHolder.mAnimView.setVisibility(View.VISIBLE);
            viewHolder.length.setVisibility(View.VISIBLE);
            viewHolder.faceImageView.setVisibility(View.GONE);
            viewHolder.contactLayout.setVisibility(View.GONE);
            viewHolder.sendingProgressBar.setVisibility(View.GONE);
            viewHolder.contactNameTextView.setVisibility(View.GONE);
            viewHolder.contactAvatarImageView.setVisibility(View.GONE);
            viewHolder.textTextView.setVisibility(View.GONE);

            viewHolder.voiceLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAnimView != null) {
                        mAnimView.setBackgroundResource(R.drawable.adj);
                        mAnimView = null;
                    }

                    final View theAnimView = viewHolder.mAnimView;

                    viewHolder.mAnimView.setBackgroundResource(R.drawable.play_anim);
                    AnimationDrawable anim = (AnimationDrawable) viewHolder.mAnimView.getBackground();
                    anim.start();
                    pathTest = dataObj.optString("full_path");

                    if(m.recorder != null) {
                        int millis = Math.round(m.recorder.getTime()* 1000) ;

                        Date date=new Date(millis);
                        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
                        String dateFormatted = formatter.format(date);
                        viewHolder.seconds.setText(dateFormatted + "");
                    }


//                    if(!dataObj.optString("thumb").equals("")) {
//
//                        viewHolder.seconds.setText(dataObj.optString("thumb") + "");
//                    }




                    if(pathTest != null) {
                        MediaManager.playSound(pathTest, new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                theAnimView.setBackgroundResource(R.drawable.adj);

                                int millis = mp.getDuration();
                                Date date=new Date(millis);
                                SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
                                String dateFormatted = formatter.format(date);
                                viewHolder.seconds.setText(dateFormatted + "");

                            }
                        });
                    }
                }
            });

            viewHolder.textTextView.setVisibility(View.GONE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        } else {
            viewHolder.textTextView.setText("");
            viewHolder.textTextView.setVisibility(View.VISIBLE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.faceImageView.setVisibility(View.GONE);
        }
    }


    // voice params
    private View mAnimView;
    String pathTest;
    float timeTest;

    public void viewMap(Double lat, Double lon) {

        WhereMapFragment fragment = WhereMapFragment.newInstance(lat,lon,1);;
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
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

    public List<TheMessageObject> getData() {
        return msgList;
    }

    public void setData(List<TheMessageObject> data) {
        this.msgList = data;
        for (TheMessageObject m : data) {
            msgMap.put(m.getId() + "", m);
        }
    }


    static class ViewHolder {

        public RelativeLayout clickableItemLayout;

        public ImageView userAvatarImageView;
        public TextView sendDateTextView;
        //public TextView userNameTextView;

        public LinearLayout messageContainer;
        public TextView partnerNameTextView;
        public TextView textTextView;
        public ImageView photoImageView;
        public ImageView faceImageView;
        public ImageView failImageView;
        public TextView user_reply_status;

        public ImageView contactAvatarImageView;
        public TextView contactNameTextView;

        public TextView seeTextView;
        public TextView sendTimeTextView;

        public ProgressBar sendingProgressBar;
        public LinearLayout contactLayout;
        public RelativeLayout chatItemLayout;
        public TextView seconds;
        public View length;
        public View mAnimView;

        public LinearLayout voiceLayout;
    }


}

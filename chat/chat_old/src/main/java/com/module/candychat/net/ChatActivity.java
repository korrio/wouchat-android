package com.module.candychat.net;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.formatter.message.json.DefaultJsonFormatter;
import com.elvishew.xlog.formatter.message.method.DefaultMethodFormatter;
import com.elvishew.xlog.formatter.message.throwable.DefaultThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.DefaultXmlFormatter;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.candychat.net.activity.ContactInviteActivity;
import com.module.candychat.net.activity.VideoActivity;
import com.module.candychat.net.activity.VoiceActivity;
import com.module.candychat.net.adapter.ListViewDefaultFontAdapter;
import com.module.candychat.net.adapter.MessageAdapter;
import com.module.candychat.net.adapter.SpinnerAdapter;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.event.GetChatInfoSuccess;
import com.module.candychat.net.event.GetUserInfoSuccessEvent;
import com.module.candychat.net.event.InterpretMessageEvent;
import com.module.candychat.net.handler.ApiBus;
import com.module.candychat.net.model.Message;
import com.module.candychat.net.service.TheChatApiService;
import com.module.candychat.net.stickerNew.FragmentPageAdapter;
import com.module.candychat.net.stickerNew.SS0Fragment;
import com.module.candychat.net.stickerNew.SS1Fragment;
import com.module.candychat.net.stickerNew.SS2Fragment;
import com.module.candychat.net.stickerNew.SS3Fragment;
//import com.module.candychat.net.stickerNew.SS4EmojiFragment;
import com.module.candychat.net.stickerNew.SlidingTabLayout;
import com.module.candychat.net.upload.EndpointManager;
import com.module.candychat.net.upload.MainApiService;
import com.module.candychat.net.upload.UploadCallback;
import com.module.candychat.net.upload.UserProfileDataResponse;
import com.module.candychat.net.util.ChatUtil;
import com.module.candychat.net.util.MediaManager;
import com.module.candychat.net.view.AudioRecorderButton;
import com.module.candychat.net.view.CustomEditTextOriginal;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.module.candychat.net.wouclass.ChatInfoTo;
import com.module.candychat.net.wouclass.TheHistoryDataResponse;
import com.module.candychat.net.wouclass.TheHtml;
import com.module.candychat.net.wouclass.TheMessageObject;
import com.module.candychat.net.wouclass.WFont;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import info.degois.damien.android.CustomFontHtml.CaseInsensitiveAssetFontLoader;
import info.degois.damien.android.CustomFontHtml.CustomHtml;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;


public class ChatActivity extends BaseChatActivity implements SS0Fragment.OnChildInteractionListener, SS1Fragment.OnChildInteractionListener2,
        SS2Fragment.OnChildInteractionListener3, SS3Fragment.OnChildInteractionListener4{


    @Override
    public void onBackPressed() {
        if(!isLayoutAttachmentShow && !isLayoutColorfulShow && !isLayoutStickerShow) {
            super.onBackPressed();
            return;
        }

                customFontlayout.setVisibility(View.GONE);
                isLayoutColorfulShow = false;

                holder.setVisibility(View.GONE);
                isLayoutStickerShow = false;

                chatAttachmentLayout.setVisibility(View.GONE);
                isLayoutAttachmentShow = false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Chat Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.module.candychat.net/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public static final String USER_ID_1 = "USER_ID_1";
    public static final String USER_ID_2 = "USER_ID_2";
    public static final String CONVERSATION_ID = "CONVERSATION_ID";
    public static final String CHAT_TYPE = "CHAT_TYPE";

    public static void startChat(Context context, int cId, int userId, int partnerId, int chatType) {
        Intent i = new Intent(context, ChatActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(CONVERSATION_ID, cId);
        i.putExtra(USER_ID_1, userId);
        i.putExtra(USER_ID_2, partnerId);
        i.putExtra(CHAT_TYPE, chatType);
        context.startActivity(i);
    }

    private MenuDrawer mDrawer;
    private ListView chatListView;
    private CustomEditTextOriginal chatEt;
    private ImageView chatEnterBtn;

    private Context mContext;
    private FragmentManager fragmentManager;

    private Button btn_sticker;

    private ImageView btn_reset;
    private TextView txt_preview;
    private ImageView enter_camera;

    private RelativeLayout chat_layout;

    private TextView txt_take_photo, txt_gallery, txt_video, txt_location, txt_contact, txt_voice, txt_font_history;

    //private List<Message> listMessages = new ArrayList<>();
    private List<TheMessageObject> listMsgObj = new ArrayList<>();
    private List<String> listFontNames = new ArrayList<>();
    private MessageAdapter adapter;
    private LineColorPicker lineColorPicker;

    RelativeLayout customFontlayout, chatAttachmentLayout;

    boolean isLayoutColorfulShow;
    boolean isLayoutAttachmentShow;
    private ToggleButton mBtnItalic;
    private ToggleButton mBtnBold;
    private Uri mFileURI = null;

    private String mName = "";
    private String mUsername = "";
    private String mAvatarUrl = "";

    private String mPartnerName = "";
    private String mPartnerUsername = "";
    private String mPartnerAvatarUrl = "";

    //Sticker
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    private Animation mAnimationOut;
    private Animation mAnimationIn;
    private View horizontalBar;

    private Timer timer;
    protected int increate = 0;
    private static final long TIMER_PERIOD = 500;
    private static final long TIMER_DELAY = 0;

    int postionTh;
    int postionEn;

    String currentFontLocale = "en";
    String currentFontName = "DEFAULT";
    String currentFontColor = "000000";
    String dir;

    Toolbar toolbar;

    boolean isLayoutStickerShow = false;
    TextView txt_en;
    TextView txt_th;
    AudioRecorderButton mAudioRecorderButton;

    private View mAnimView;

    String pathTest;
    float timeTest;

    ListViewDefaultFontAdapter adapterFontListViewTh;
    ListViewDefaultFontAdapter adapterFontListViewEn;
    ListViewDefaultFontAdapter adapterFontListViewDefault;
    ListViewDefaultFontAdapter adapterFontListViewHistory;
    HListView fontListView;

    RelativeLayout holder;
    FragmentPageAdapter pageAdapter;
    TextView txt_view_emoji;
    TextView txt_view_sticker;
    TabLayout mTabs;

    Spinner fontSizeSpinner;
    String[] fontSizeArr = {"16","18","24", "28", "32", "36"};
    String size;
    int currentFontSize = 16;
    SpinnerAdapter fontSizeSelector;
    WFont wFont;

    public static void dumpIntent(Intent i) {
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            Log.e(LOG_TAG, "Dumping Intent start");
            while (it.hasNext()) {
                String key = it.next();
                Log.e(LOG_TAG, "[" + key + "=" + bundle.get(key) + "]");
            }
            Log.e(LOG_TAG, "Dumping Intent end");
        }
    }

    public static final String LOG_TAG = "WOULOG";

    List<TheMessageObject.MessageColorfulEntity> listColorfulObj = new ArrayList<>();
    int colorfulIndex = 0;

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        XLog.init(LogLevel.ALL,
                new LogConfiguration                                             // If LogConfiguration not specified, will use new LogConfiguration.Builder().build()
                        .Builder()                                               // The log configuration used when logging
                        .tag("AQ")                                           // Default: "XLOG"
                        .jsonFormatter(new DefaultJsonFormatter())               // Default: DefaultJsonFormatter
                        .xmlFormatter(new DefaultXmlFormatter())                 // Default: DefaultXmlFormatter
                        .methodFormatter(new DefaultMethodFormatter())           // Default: DefaultMethodFormatter
                        .throwableFormatter(new DefaultThrowableFormatter())     // Default: DefaultThrowableFormatter
                        .build(),
                new AndroidPrinter()
        );

        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/WOUchat/";
        File newdir = new File(dir);
        newdir.mkdirs();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Chat Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.module.candychat.net/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    RelativeLayout chatBox;
    String toHtml;

    public void initCustomEditTextOriginal() {

        chatBox = (RelativeLayout) findViewById(R.id.chat_box_wrapper);
        chatEt = (CustomEditTextOriginal) findViewById(R.id.chat_edit_text1);
        chatEt.setSingleLine(false);
        chatEt.setMinLines(2);
        //customEditor.setBoldToggleButton(boldToggle);
        //customEditor.setItalicsToggleButton(italicsToggle);
        //customEditor.setUnderlineToggleButton(underlinedToggle);
        chatEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    lnl.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    lnl.setVisibility(View.GONE);
//                }
            }
        });
        chatEt.setEventBack(eventBack);
        chatEt.setOnClickListener(chatEtClickListener);

        int selectionStart = chatEt.getSelectionStart();
        int selectionEnd = chatEt.getSelectionEnd();

        wFont = new WFont("000000","DEFAULT",currentFontSize);

        chatEt.setColor(Color.BLACK, selectionStart, selectionEnd);
        listFontNames.add("DEFAULT");
        chatEt.setFont(Typeface.DEFAULT,wFont, selectionStart, selectionEnd);
        //chatEt.setTextSize(currentFontSize);

        chatEnterBtn = (ImageView) findViewById(R.id.enter_chat1);
        chatEnterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: PERFECT MESSAGE OBJECT
                //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                //@Expose private Long id;

                final String theChatMessage = chatEt.getStringText();

                if(theChatMessage.equals("") || theChatMessage == null)
                    return;

                isLayoutColorfulShow = false;
                isLayoutAttachmentShow = false;
                customFontlayout.setVisibility(View.GONE);
                chatAttachmentLayout.setVisibility(View.GONE);




//                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                runOnUiThread(new Runnable(){
                    public void run() {

                        toHtml = TheHtml.toHtml(chatEt.getSpannedTextOrigin());

                    }
                });


                TheMessageObject.SenderEntity sender = new TheMessageObject.SenderEntity();
                toSendMsgObj = new TheMessageObject();
                toSendMsgObj.onRight = true;
                toSendMsgObj.setMessage(theChatMessage);
                toSendMsgObj.setMessageType(TheMessageObject.MSG_TYPE_TEXT);
                toSendMsgObj.spanned = chatEt.getSpannedText();
                toSendMsgObj.htmlStringColor = toHtml;
                toSendMsgObj.setSender(sender);
                adapter.getData().add(toSendMsgObj);
                scrollToBottom();
                chatEt.setText("");
                chatEt.setTextSize(16);

                // colorful processing
                List<TheMessageObject.MessageColorfulEntity> theColorfulList = new ArrayList<TheMessageObject.MessageColorfulEntity>();
                String theHtml2 = toHtml;

                chatEt.setText("");

                theHtml2 = theHtml2.replace("><font","").replace("</font></font color","</font><font color").replace("</font></font face","</font><font face");

                //Log.e("theHtml2",theHtml2);

                Document doc = Jsoup.parse(theHtml2);
                Elements links = doc.getElementsByTag("font");

                for (Element link : links) {
                    String color = link.attr("color").replace("#","");

                    String font = link.attr("face");
                    String size = "16";
                    if(font.contains("DEFAULT"))
                        size = "16";
                    else
                        size = "24";

                    //TheElement theLink = ((TheElement)link);
                    String message = link.ownText();

                    //theChatMessage += message;

                    TheMessageObject.MessageColorfulEntity messageColorfulEntity = new TheMessageObject.MessageColorfulEntity();
                    TheMessageObject.MessageColorfulEntity.StyleEntity styleEntity = new TheMessageObject.MessageColorfulEntity.StyleEntity();

                    messageColorfulEntity.setMessage(message);

                    //TO-DO: mak mak
                    styleEntity.setLocale("");
                    styleEntity.setStyle(font);
                    styleEntity.setSize(size);
                    styleEntity.setColor(color);

                    messageColorfulEntity.setStyle(styleEntity);
                    theColorfulList.add(messageColorfulEntity);

                }

                toSendMsgObj.setMessage(theChatMessage);
                toSendMsgObj.setMessageColorful(theColorfulList);


                Gson gson = new GsonBuilder().create();
                String msgObjStr = gson.toJson(toSendMsgObj.getMessageColorful());// obj is your object

                attemptSendMessageToServer(Message.MSG_TYPE_TEXT, theChatMessage, "[]", msgObjStr.toString());

            }
        });


    }

    TheMessageObject toSendMsgObj;

    private void scrollToBottom() {
        if (chatListView != null)
            chatListView.setSelection(chatListView.getBottom());
    }

    private CustomEditTextOriginal.EventBack eventBack = new CustomEditTextOriginal.EventBack() {

        @Override
        public void close() {
            //lnl.setVisibility(View.GONE);
        }

        @Override
        public void show() {
            //lnl.setVisibility(View.VISIBLE);
        }
    };
    private View.OnClickListener chatEtClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            holder.setVisibility(View.GONE);
            isLayoutStickerShow = false;


            if (chatEt.isFocused()) {

                //lnl.setVisibility(View.VISIBLE);
            }
        }
    };

    final Random mRandom = new Random(System.currentTimeMillis());

    public int generateRandomPastelColor() {
        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

    public int generateRandomColor() {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        return Color.rgb(red, green, blue);
    }

    String[] fontEnArr;
    String[] fontThArr;

    CaseInsensitiveAssetFontLoader fontLoader;

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    @Override
    protected void onCreate(Bundle state) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(state);
        setContentView(R.layout.activity_chat);


        //mDrawer = MenuDrawer.attach(this, Position.RIGHT);
       // mDrawer.setContentView(R.layout.activity_chat);
        //mDrawer.setMenuView(R.layout.layout_more_chat);
        //mDrawer.setMenuSize(150);

        mContext = getApplicationContext();

        mCid = getIntent().getExtras().getInt(CONVERSATION_ID, 0);
        mUserId = getIntent().getExtras().getInt(USER_ID_1, 0);
        mPartnerId = getIntent().getExtras().getInt(USER_ID_2, 0);
        mChatType = getIntent().getExtras().getInt(CHAT_TYPE, 0);

        initToolbar();
        initCustomEditTextOriginal();
        dumpIntent(getIntent());

        if (mUserId != 0)
            initConnect(mUserId);
        else
            tt("Can't connect userId:" + mUserId);

        if (mChatType == 0 || mCid == 0)
            getChatInfoTo(mUserId, mPartnerId);
        else
            getChatInfo(mCid);

        chatListView = (ListView) findViewById(R.id.chat_list_view);
        adapter = new MessageAdapter(getApplicationContext(), listMsgObj, mUserId);
        chatListView.setAdapter(adapter);

        //getUserInfoService(mUserId);
        //getUserInfoService(mPartnerId);

        btn_sticker = (Button) findViewById(R.id.btn_sticker);
        btn_reset = (ImageView) findViewById(R.id.btn_reset);
        txt_preview = (TextView) findViewById(R.id.txt_preview);

        customFontlayout = (RelativeLayout) findViewById(R.id.colorful_layout);
        chatAttachmentLayout = (RelativeLayout) findViewById(R.id.attachment_layout);

        fontListView = (HListView) findViewById(R.id.hListView1);
        txt_view_sticker = (TextView) findViewById(R.id.txt_view_sticker);

        fontEnArr = getResources().getStringArray(R.array.font_en);
        fontThArr = getResources().getStringArray(R.array.font_th);
        fontLoader = new CaseInsensitiveAssetFontLoader(getActivity(), "fonts");
        fontLoader.getTypeFace("Roboto-Regular");

        adapterFontListViewTh = new ListViewDefaultFontAdapter(this, fontThArr);
        adapterFontListViewEn = new ListViewDefaultFontAdapter(this, fontEnArr);
        adapterFontListViewDefault = new ListViewDefaultFontAdapter(this, fontEnArr);

        holder = (RelativeLayout) findViewById(R.id.holder);
        fontSizeSpinner = (Spinner) findViewById(R.id.spinner1);
        fragmentManager = getSupportFragmentManager();

        mTabs = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.vpPager);

        fontSizeSelector = new SpinnerAdapter(getActivity(), fontSizeArr);
        fontSizeSpinner.setVisibility(View.GONE);
        fontSizeSpinner.setAdapter(fontSizeSelector);
        fontSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size = fontSizeArr[position];
                currentFontSize = Integer.parseInt(size);
                Log.e("code", currentFontSize + "");
                txt_preview.setTextSize(currentFontSize);
                chatEt.setTextSize(currentFontSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setupViewPager(mViewPager);
        setupTabLayout(mTabs);

        ImageView btnShop = (ImageView) findViewById(R.id.btnRight);
        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Will Open StickerShop", Toast.LENGTH_LONG).show();
            }
        });

        btn_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                onClickStickerEvent();
            }
        });

        txt_en = (TextView) findViewById(R.id.txt_en);
        txt_th = (TextView) findViewById(R.id.txt_th);
        txt_font_history = (TextView) findViewById(R.id.txt_history);

        txt_view_emoji = (TextView) findViewById(R.id.txt_view_emoji);
        enter_camera = (ImageView) findViewById(R.id.enter_camera);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {

                pathTest = filePath;
                timeTest = seconds;
                Log.e("seconds", seconds + "");
                Log.e("filePathVoice", filePath);
                if (filePath != null) {
                    String dataJson = "";
                    dataJson = "{'thumb':'" + filePath + "'}";

                    File clipVoice = new File(filePath);
                    Log.e("Voice", clipVoice + "");
                    uploadFileRetrofit(clipVoice, Message.MSG_TYPE_VOICE, new TheMessageObject());
                }

            }
        });

        chat_layout = (RelativeLayout) findViewById(R.id.chat_layout);
        chat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                chatAttachmentLayout.setVisibility(View.GONE);
                isLayoutAttachmentShow = false;

                customFontlayout.setVisibility(View.GONE);
                isLayoutColorfulShow = false;
            }
        });

        txt_take_photo = (TextView) findViewById(R.id.txt_take_photo);
        txt_gallery = (TextView) findViewById(R.id.txt_gallery);
        txt_video = (TextView) findViewById(R.id.txt_video);
        txt_location = (TextView) findViewById(R.id.txt_location);
        txt_contact = (TextView) findViewById(R.id.txt_contact);
        txt_voice = (TextView) findViewById(R.id.txt_voice);

        chatListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        fontListView.setAdapter(adapterFontListViewEn);
        fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                txt_preview.setTypeface((Typeface) adapterFontListViewEn.getItem(position));
                chatEt.setTypeface((Typeface) adapterFontListViewEn.getItem(position));

                currentFontName = fontEnArr[position];
                adapterFontListViewHistory.getData().add(currentFontName);

                currentFontLocale = "en";
                postionEn = position;

                int selectionStart = chatEt.getSelectionStart();
                int selectionEnd = chatEt.getSelectionEnd();
                //chatEt.setTextSize(currentFontSize);
                if (currentFontName.equals(Constant.DEFAULT)) {
                    wFont.mFace = currentFontName;
                    wFont.mSize = currentFontSize;
                    wFont.mColor = currentFontColor;
                    wFont.typeface = Typeface.DEFAULT;
                    listFontNames.add("DEFAULT");
                    chatEt.setFont(Typeface.DEFAULT,wFont, selectionStart, selectionEnd);
                } else {
                    wFont.mFace = currentFontName;
                    wFont.mSize = currentFontSize;
                    wFont.mColor = currentFontColor;
                    wFont.typeface = fontLoader.getTypeFace(currentFontName);
                    listFontNames.add(currentFontName);
                    chatEt.setFont(fontLoader.getTypeFace(currentFontName),wFont, selectionStart, selectionEnd);
                }

                Log.e("currentFontName", currentFontName);


//                chatEt.setColor(generateRandomColor(),selectionStart,selectionEnd);
//                chatEt.append(currentFontName + " ");

            }
        });

        txt_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontListView.setAdapter(adapterFontListViewEn);
                fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                        txt_preview.setTypeface((Typeface) adapterFontListViewEn.getItem(position));
                        chatEt.setTypeface((Typeface) adapterFontListViewEn.getItem(position));

                        currentFontName = fontEnArr[position];
                        adapterFontListViewHistory.getData().add(currentFontName);
                        currentFontLocale = "en";
                        postionEn = position;

                        int selectionStart = chatEt.getSelectionStart();
                        int selectionEnd = chatEt.getSelectionEnd();
                        //chatEt.setTextSize(currentFontSize);


                        if (currentFontName.equals(Constant.DEFAULT)) {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = Typeface.DEFAULT;
                            listFontNames.add("DEFAULT");
                            chatEt.setFont(Typeface.DEFAULT,wFont, selectionStart, selectionEnd);
                        } else {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = fontLoader.getTypeFace(currentFontName);
                            listFontNames.add(currentFontName);
                            chatEt.setFont(fontLoader.getTypeFace(currentFontName),wFont, selectionStart, selectionEnd);
                        }

                        Log.e("currentFontName", currentFontName);

//                        chatEt.setColor(generateRandomColor(),selectionStart,selectionEnd);
//                        chatEt.append(currentFontName + " ");
                    }
                });
            }
        });
        txt_th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontListView.setAdapter(adapterFontListViewTh);
                fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                        txt_preview.setTypeface((Typeface) adapterFontListViewTh.getItem(position));
                        chatEt.setTypeface((Typeface) adapterFontListViewTh.getItem(position));

                        currentFontName = fontThArr[position];
                        adapterFontListViewHistory.getData().add(currentFontName);
                        currentFontLocale = "th";
                        postionTh = position;

                        int selectionStart = chatEt.getSelectionStart();
                        int selectionEnd = chatEt.getSelectionEnd();
                        if (currentFontName.contains("kt"))
                            chatEt.setTextSize(26);
                        else if(currentFontName.toLowerCase().contains("alex")) {
                            currentFontSize = 18;
                            chatEt.setTextSize(currentFontSize);
                        } else
                            chatEt.setTextSize(currentFontSize);
                        if (currentFontName.equals(Constant.DEFAULT)) {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = Typeface.DEFAULT;
                            listFontNames.add("DEFAULT");
                            chatEt.setFont(Typeface.DEFAULT,wFont, selectionStart, selectionEnd);
                        } else {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = fontLoader.getTypeFace(currentFontName);
                            listFontNames.add(currentFontName);
                            chatEt.setFont(fontLoader.getTypeFace(currentFontName),wFont, selectionStart, selectionEnd);
                        }

                        Log.e("currentFontName", currentFontName);

//                        chatEt.setColor(generateRandomColor(),selectionStart,selectionEnd);
//                        chatEt.append(currentFontName + " ");
                    }
                });
            }
        });


        String[] historyArr = new String[1];
        historyArr[0] = Constant.DEFAULT;

        adapterFontListViewHistory = new ListViewDefaultFontAdapter(getApplicationContext(), historyArr);

        txt_font_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontListView.setAdapter(adapterFontListViewHistory);
                fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                        txt_preview.setTypeface((Typeface) adapterFontListViewDefault.getItem(position));
                        chatEt.setTypeface((Typeface) adapterFontListViewDefault.getItem(position));

                        int selectionStart = chatEt.getSelectionStart();
                        int selectionEnd = chatEt.getSelectionEnd();

                        currentFontName = adapterFontListViewHistory.getData().get(position);
                        if (currentFontName.equals(Constant.DEFAULT)) {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = Typeface.DEFAULT;
                            listFontNames.add("DEFAULT");
                            chatEt.setFont(Typeface.DEFAULT,wFont, selectionStart, selectionEnd);
                        } else {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = fontLoader.getTypeFace(currentFontName);
                            listFontNames.add(currentFontName);
                            chatEt.setFont(fontLoader.getTypeFace(currentFontName),wFont, selectionStart, selectionEnd);
                        }
                        //currentFontLocale = "th";
                        //postionTh = position;


                        if (currentFontName.toLowerCase().contains("kt"))
                            chatEt.setTextSize(26);
                        else
                            chatEt.setTextSize(currentFontSize);


                        Log.e("currentFontName", currentFontName);

//                        chatEt.setColor(generateRandomColor(),selectionStart,selectionEnd);
//                        chatEt.append(currentFontName + " ");
                    }
                });
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface typeface = Typeface.DEFAULT;
                txt_preview.setTextColor(Color.BLACK);
                txt_preview.setTypeface(typeface);
                chatEt.setTextColor(Color.BLACK);
                chatEt.setTypeface(typeface);
                chatEt.setTextSize(16);
                currentFontColor = "000000";
                currentFontName = "DEFAULT";

                int selectionStart = chatEt.getSelectionStart();
                int selectionEnd = chatEt.getSelectionEnd();
                if (currentFontName.equals(Constant.DEFAULT)) {
                    wFont.mFace = currentFontName;
                    wFont.mSize = currentFontSize;
                    wFont.mColor = currentFontColor;
                    wFont.typeface = Typeface.DEFAULT;
                    listFontNames.add("DEFAULT");
                    chatEt.setFont(Typeface.DEFAULT,wFont, selectionStart, selectionEnd);
                } else {
                    wFont.mFace = currentFontName;
                    wFont.mSize = currentFontSize;
                    wFont.mColor = currentFontColor;
                    wFont.typeface = fontLoader.getTypeFace(currentFontName);
                    listFontNames.add(currentFontName);
                    chatEt.setFont(fontLoader.getTypeFace(currentFontName),wFont, selectionStart, selectionEnd);
                }
            }
        });

        txt_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhoto();
                isLayoutColorfulShow = false;
                isLayoutAttachmentShow = false;
                chatAttachmentLayout.setVisibility(View.GONE);
            }
        });

        txt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickPhoto();
                isLayoutColorfulShow = false;
                isLayoutAttachmentShow = false;
                chatAttachmentLayout.setVisibility(View.GONE);

            }
        });

        txt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildVideoDialog();
                isLayoutColorfulShow = false;
                isLayoutAttachmentShow = false;
            }
        });

        txt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txt_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactInviteActivity.class);
                startActivityForResult(intent, 700);
                isLayoutColorfulShow = false;
                isLayoutAttachmentShow = false;
                chatAttachmentLayout.setVisibility(View.GONE);
            }
        });
        txt_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoiceActivity.class);
                startActivityForResult(intent, 800);
                isLayoutColorfulShow = false;
                isLayoutAttachmentShow = false;
                chatAttachmentLayout.setVisibility(View.GONE);

            }
        });

        lineColorPicker = (LineColorPicker) findViewById(R.id.line_color_picker);

        // Create palette from HEX values
        int[] colors = new int[Constant.pallete.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.parseColor(Constant.pallete[i]);
        }

        // Set palette
        lineColorPicker.setColors(colors);

        // Set selected color [optional]
        lineColorPicker.setSelectedColor(colors[0]);

        // Get selected color
        final int color = lineColorPicker.getColor();

        //updateColor(color);

        OnColorChangedListener onColorChangeListener = new OnColorChangedListener() {

            @Override
            public void onColorChanged(int c) {

                currentFontColor = Integer.toHexString(c).substring(2, 8);
                currentFontColor = currentFontColor.toUpperCase();
                chatEt.setTextColor(c);
                txt_preview.setTextColor(c);

                int selectionStart = chatEt.getSelectionStart();
                int selectionEnd = chatEt.getSelectionEnd();
                chatEt.setColor(c, selectionStart, selectionEnd);

            }
        };

        lineColorPicker.setOnColorChangedListener(onColorChangeListener);
        mBtnItalic = (ToggleButton) findViewById(R.id.btn_italic);
        mBtnBold = (ToggleButton) findViewById(R.id.btn_bold);

        chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TheMessageObject m = listMsgObj.get(i);
                int msgType = m.getMessageType();
                JSONArray dataArr = null;
                JSONObject dataObj = null;
                try {
                    dataArr = new JSONArray(m.dataString);
                    dataObj = (JSONObject) dataArr.opt(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (msgType) {
                    case 0:
                        break;
                    case 1:

                        break;
                    case 2:
                        String fileType = "";
                        if(dataObj != null)
                            fileType = ((JSONObject) dataObj).optString("fileType");

                        if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
//                            Intent intent = new Intent(getActivity(), PhotoPagerActivity.class);
//
//                            ArrayList<String> urls = new ArrayList<>();
//                            String imageUrl = dataObj.optString("url");
//                            urls.add(0, imageUrl);
//
//                            intent.putExtra("current_item", 1);
//                            intent.putStringArrayListExtra("photos", urls);
//
//                            getActivity().startActivity(intent);
                        }

                        break;
                    case 3:
                        // intent video player
                        String clipPath = "";
                        if(dataObj != null)
                            clipPath = ((JSONObject) dataObj).optString("url");;

                        Intent ii = new Intent(getApplicationContext(), VideoActivity.class);
                        ii.putExtra("videoPath", clipPath);
                        startActivity(ii);
                        break;
                    case 6:
                        if(dataObj != null) {

                            String lat = ((JSONObject) dataObj).optString("latitude");
                            String lon = ((JSONObject) dataObj).optString("longtitude");
                            viewLocation(lat, lon);
                        }

                        break;

                    case 8:
                        if (mAnimView != null) {
                            mAnimView.setBackgroundResource(R.drawable.adj);
                            mAnimView = null;
                        }
                        //play video
                        mAnimView = view.findViewById(R.id.id_recorder_anim);
                        mAnimView.setBackgroundResource(R.drawable.play_anim);
                        AnimationDrawable anim = (AnimationDrawable) mAnimView.getBackground();
                        anim.start();
                        //Log.e("pathTest",pathTest);
                        //play audio
                        MediaManager.playSound(pathTest, new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mAnimView.setBackgroundResource(R.drawable.adj);

                            }
                        });
                        break;
                }
            }
        });

        enter_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //takePhoto();
            }
        });

        chatEnterBtn.setVisibility(View.VISIBLE);
        enter_camera.setVisibility(View.GONE);

        chatEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && !"".equals(s.toString().trim())) {
                    enter_camera.setVisibility(View.GONE);
                    chatEnterBtn.setEnabled(true);
                    chatEnterBtn.setVisibility(View.VISIBLE);
                }
//                else {
//                    enter_camera.setVisibility(View.VISIBLE);
//                    if (enter_camera.getVisibility() == View.VISIBLE) {
//                        chatEnterBtn.setVisibility(View.GONE);
//                    }
//                }

                if (null == mUsername) return;
                if (!mSocket.connected()) return;

                if (!mTyping) {
                    mTyping = true;
                    JSONObject jObj = new JSONObject();
                    try {
                        jObj.put("conversationId", mCid);
                        jObj.put("isTyping", mTyping);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mSocket.emit("Typing", jObj);
                }

                mTypingHandler.removeCallbacks(onTypingTimeout);
                mTypingHandler.postDelayed(onTypingTimeout, TYPING_TIMER_LENGTH);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txt_view_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupViewPagerEmoji(mViewPager);
                setupTabLayout(mTabs);

            }
        });

        txt_view_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupViewPager(mViewPager);
                setupTabLayout(mTabs);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }


    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main_chat);//changed
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_attach) {
                    onClickAttachmentsEvent();
                    return true;
                } else if (item.getItemId() == R.id.action_color) {
                    onClickColorfulEvent();
                    return true;
                }

//                else if (item.getItemId() == R.id.context_menu) {
//                    if(mDrawer != null)
//                        mDrawer.toggleMenu();
//                    return true;
//                } else {
//
//                }

                return false;
            }
        });
    }


    public void setupViewPager(ViewPager viewPager) {
        pageAdapter = new FragmentPageAdapter(getActivity(), fragmentManager);
        pageAdapter.addFragment(SS0Fragment.getInstance("set2"), "set2", R.drawable.ic_set_2, "");
        pageAdapter.addFragment(SS1Fragment.getInstance("set1"), "set1", R.drawable.ic_set_1, "");
        pageAdapter.addFragment(SS2Fragment.getInstance("set3"), "set3", R.drawable.ic_set_3, "9");
        pageAdapter.addFragment(SS3Fragment.getInstance("set4"), "set4", R.drawable.ic_set_4, "");
        viewPager.setAdapter(pageAdapter);

    }

    public void setupViewPagerEmoji(ViewPager viewPager) {
       // pageAdapter = new FragmentPageAdapter(getActivity(), fragmentManager);
        //pageAdapter.addFragment(SS4EmojiFragment.getInstance("null"), "null", R.drawable.pen_ww, "asdf");
        //viewPager.setAdapter(pageAdapter);
    }

    public void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pageAdapter.getTabView(i));
        }
        tabLayout.requestFocus();
    }

    public void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        // intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", Constant.PHOTO_SIZE_WIDTH);
        intent.putExtra("outputY", Constant.PHOTO_SIZE_HEIGHT);
        startActivityForResult(intent, Constant.SELECT_PHOTO);
    }

    public void pickVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, Constant.RESULT_PICK_VIDEO);
    }

    public void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, Constant.RESULT_VIDEO_CAP);
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        // intent.putExtra("crop", "true");
        startActivityForResult(intent, Constant.CAMERA_REQUEST);
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    public void viewLocation(String lat, String lon) {
        String uri = MessageFormat.format("geo:{0},{1}?q={0},{1}", lat, lon);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.view_via)));
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private Activity getActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_attach) {
            onClickAttachmentsEvent();
            return true;
        } else if (item.getItemId() == R.id.action_color) {
            onClickColorfulEvent();
            return true;
        }
//        else if (item.getItemId() == R.id.context_menu) {
//            mDrawer.toggleMenu();
//            return true;
//        }
        return super.onOptionsItemSelected(item);


    }

    public void onClickColorfulEvent() {
        if (!isLayoutColorfulShow) {
            customFontlayout.setVisibility(View.VISIBLE);
            isLayoutColorfulShow = true;
        } else {
            customFontlayout.setVisibility(View.GONE);
            isLayoutColorfulShow = false;
        }
        if (isLayoutAttachmentShow) {
            chatAttachmentLayout.setVisibility(View.GONE);
            isLayoutAttachmentShow = false;
        }
    }

    public void onClickAttachmentsEvent() {
        if (!isLayoutAttachmentShow) {
            chatAttachmentLayout.setVisibility(View.VISIBLE);
            isLayoutAttachmentShow = true;
        } else {

            chatAttachmentLayout.setVisibility(View.GONE);
            isLayoutAttachmentShow = false;
        }
        if (isLayoutColorfulShow) {
            customFontlayout.setVisibility(View.GONE);
            isLayoutColorfulShow = false;
        }
    }

    public void onClickStickerEvent() {
        if (!isLayoutStickerShow) {
            holder.setVisibility(View.VISIBLE);
            isLayoutStickerShow = true;
        } else {
            holder.setVisibility(View.GONE);
            isLayoutStickerShow = false;
        }
    }

    public void tt(CharSequence str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void attemptSendMessageToServer(int messageType, String theMessage, String jsonObjStr, String colorfulObjStr) {
        if (!mSocket.connected()) {
            tt("Not connected");
            //initConnect(mUserId);
            return;
        }

        String message = theMessage;


        JSONObject jObj = new JSONObject();
        JSONArray jObjColorful = null;
        try {
            jObjColorful = new JSONArray(colorfulObjStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jArrData = null;
        try {
            jArrData = new JSONArray(jsonObjStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject senderObj = new JSONObject();

        try {
            senderObj.put("senderId", mUserId);
            senderObj.put("id", mUserId);
            senderObj.put("username", mUsername);
            senderObj.put("name", mName);
            senderObj.put("avatarUrl", mAvatarUrl);

            if (messageType == 0) {
                jObj.put("message", message);
            } else {
                jObj.put("message", "");
            }

            jObj.put("senderId", mUserId + "");
            int messageRoomType = 0;
            if (mChatType == 1)
                messageRoomType = 2;
            else
                messageRoomType = 1;

            if (mChatType == 0)
                messageRoomType = mChatType;

            jObj.put("messageRoomType", messageRoomType);
            jObj.put("conversationId", mCid);
            jObj.put("messageType", messageType);
            jObj.put("data", jArrData);
            jObj.put("sender", senderObj);
            jObj.put("senderId",mUserId);
            jObj.put("messageColorful", jObjColorful);
            jObj.put("dataString",jsonObjStr);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mSocket.emit("SendMessage", jObj);
    }

    @Subscribe
    public void onInterpretMessageEvent(InterpretMessageEvent event) {
        interpretTheMessage(event.mMessageListObject);
    }

    @Subscribe
    public void onTypingEvent(TypingEvent event) {
        if(friendUsernameTv != null) {
            friendUsernameTv.append(" ...");

//            JumpingBeans jumpingBeans2 = JumpingBeans.with(friendUsernameTv)
//                    .makeTextJump(0, friendUsernameTv.getText().toString().indexOf(' '))
//                    .setIsWave(false)
//                    .setLoopDuration(1000)  // ms
//                    .build();
        }

    }

    @Subscribe
    public void onNotTypingEvent(NotTypingEvent event) {
        if(friendUsernameTv != null) {
            friendUsernameTv.setText(mPartnerUsername);

//            JumpingBeans jumpingBeans2 = JumpingBeans.with(friendUsernameTv)
//                    .makeTextJump(0, friendUsernameTv.getText().toString().indexOf(' '))
//                    .setIsWave(false)
//                    .setLoopDuration(1000)  // ms
//                    .build();
        }

    }

    public void interpretTheMessage(final JSONObject obj) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data = obj;

                Gson gson = new GsonBuilder().create();
                Log.e("THEJSON:RECEIVED", data.toString());

                TheMessageObject m = gson.fromJson(data.toString(), TheMessageObject.class);// obj is your object

                String theStyle;
                int theSize;
                if (m.getMessageColorful() != null && m.getMessageColorful().size() != 0) {
                    for (TheMessageObject.MessageColorfulEntity ent : m.getMessageColorful()) {
                        if (ent.getStyle() != null) {
                            theStyle = ent.getStyle().getStyle().replace(".ttf", "").replace(".otf", "");
                            if(ent.getStyle().getSize().equals("16"))
                                theSize = 22;
                            else
                                theSize = Integer.parseInt(ent.getStyle().getSize()) + 6;

                            m.htmlStringColor += "<font face='" + theStyle + "' color ='#" + ent.getStyle().getColor() + "' size='" + theSize + "'>" + ent.getMessage() + "</font>";
                        }
                    }
                    //Log.e("htmlString555",m.htmlStringColor);
                    m.spanned = CustomHtml.fromHtml(m.htmlStringColor, fontLoader);
                } else {
                    theStyle = "DEFAULT";
                    m.htmlStringColor = "<font face='" + theStyle + "' color ='#000000' size='22'>" + m.getMessage() + "</font>";
                    m.spanned = CustomHtml.fromHtml(m.htmlStringColor, fontLoader);
                }

                m.onRight = mUserId == m.getSenderId() || mUserId == m.getSender().getId();

                if (!m.onRight) {
                    adapter.getData().add(m);
                    adapter.notifyDataSetChanged();
                    scrollToBottom();
                }

                Log.e("m.onRight",m.onRight  +  "");

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", requestCode + " + " + resultCode);
        if (resultCode == Activity.RESULT_OK) {

            File f = new File(Environment.getExternalStorageDirectory()
                    .toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f = temp;
                    break;
                }
            }

            if (requestCode == Constant.CAMERA_REQUEST) {

                String path = f.getAbsolutePath();
                Uri selectedImageUri = Uri.parse(path);
                File file = imagePathToFile(selectedImageUri, path);

                String dataJson = "[]";
                TheMessageObject msgObj = new TheMessageObject();
                uploadFileRetrofit(file, Message.MSG_TYPE_PHOTO, msgObj);

            } else if (requestCode == Constant.SELECT_PHOTO && null != data) {
                Uri selectedImageUri = data.getData();

                Log.e("selectedImageUri", selectedImageUri + "");

                String path = getRealPath(getApplicationContext(), selectedImageUri);
                File file = imagePathToFile(selectedImageUri, path);

                Log.e("file", file + "");

                String dataJson = "[]";
                TheMessageObject msgObj = new TheMessageObject();
                uploadFileRetrofit(file, Message.MSG_TYPE_PHOTO, msgObj);


            } else if (requestCode == Constant.RESULT_PICK_VIDEO) {

                mFileURI = data.getData();
                Log.e("mFileURI", mFileURI + "");
                if (mFileURI != null) {
                    String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                    String dataJson = "";
                    if (vdoThumb != null)
                        dataJson = "{'thumb':'" + vdoThumb + "'}";

                    TheMessageObject msgObj = new TheMessageObject();
                    String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                    File clip = new File(path);
                    Log.e("Clip", clip + "");
                    uploadFileRetrofit(clip, Message.MSG_TYPE_CLIP, msgObj);

                }

            } else if (requestCode == Constant.RESULT_VIDEO_CAP) {

                mFileURI = data.getData();
                Log.e("mFileURI", mFileURI + "");

                if (mFileURI != null) {
                    String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                    String dataJson = "";
                    if (vdoThumb != null)
                        dataJson = "{'thumb':'" + vdoThumb + "'}";

                    TheMessageObject msgObj = new TheMessageObject();
                    //listMessages.add(sendingMessage);

                    String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                    File clip = new File(path);
                    uploadFileRetrofit(clip, Message.MSG_TYPE_CLIP, msgObj);


                }

            } else if (requestCode == 500) {

                String lat = data.getStringExtra("LAT");
                String lon = data.getStringExtra("LON");
                String locationName = data.getStringExtra("LOCATION_NAME");
                //Location location = data.getParcelableExtra("LOCATION");

                String mapImage = "https://maps.googleapis.com/maps/api/staticmap?zoom=13&size=600x400&maptype=roadmap&markers=color:blue%7Clabel:" + locationName + "%7C" + lat + "," + lon;

                //{"cityName":"??????","regionName":"??????","latitude":"13.837663","longtitude":"100.616730"}

                String dataJson = "[{'cityName':'" + locationName + "'" +
                        ",'imageUrl':'" + mapImage + "'" +
                        ",'regionName':'" + locationName + "'" +
                        ",'latitude':'" + lat + "'" +
                        ",'longtitude':'" + lon + "'}]";

                Log.e("sendDataJson", dataJson);

                attemptSendMessageToServer(Message.MSG_TYPE_LOCATION, "", dataJson, "[]");

            } else if (requestCode == 700) {

                String name = data.getStringExtra("data");

                //{"cityName":"??????","regionName":"??????","latitude":"13.837663","longtitude":"100.616730"}

                String dataJson = "[{'userid':'" + "" + "'" +
                        ",'name':'" + name + "'" +
                        ",'last':'" + "" + "'" +
                        ",'photo':'" + "http://image.dek-d.com/27/0287/9085/114304545" + "'" +
                        ",'fullname':'" + "" + "'}]";

                Log.e("sendDataJson", dataJson);

                attemptSendMessageToServer(Message.MSG_TYPE_CONTACT, "", dataJson, "[]");
            }
            //
            else if (requestCode == 800) {
                String name = data.getStringExtra("data");

                if (name != null) {
                    File clip = new File(name);
                    Log.e("Voice555", clip + "");

                    TheMessageObject msgObj = new TheMessageObject();
                    uploadFileRetrofit(clip, Message.MSG_TYPE_VOICE, msgObj);
                }

            }

            adapter.notifyDataSetChanged();
        }
        scrollToBottom();
    }


    public void buildVideoDialog() {
        final CharSequence[] items = {"Record Video", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Record Video")) {
                    recordVideo();
                    chatAttachmentLayout.setVisibility(View.GONE);
                } else if (items[item].equals("Choose from Library")) {
                    pickVideo();
                    chatAttachmentLayout.setVisibility(View.GONE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public static String getRealPath(Context context, Uri mFileURI) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return ChatUtil.getRealPathFromURIForKitKat(context, mFileURI);
        } else {
            return ChatUtil.getRealPathFromURI(context, mFileURI);
        }
    }

    public void getUserInfoService(int userId) {
        MainApiService service = buildMainApi();
        service.getProfile(userId, new Callback<UserProfileDataResponse>() {
            @Override
            public void success(UserProfileDataResponse userProfileDataResponse, Response response) {
                if (userProfileDataResponse.user != null && userProfileDataResponse.status.equals("1")) {
                    ApiBus.getInstance().postQueue(new GetUserInfoSuccessEvent(userProfileDataResponse.user));
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("getUserInfoServiceError", error.getLocalizedMessage());
            }
        });
    }

    public void getHistoryService(int cid) {
        TheChatApiService service = buildChatApi();
        HashMap<String, Integer> option = new HashMap<String, Integer>();

        service.getTheHistory(cid, option, new Callback<TheHistoryDataResponse>() {
            @Override
            public void success(TheHistoryDataResponse historyDataResponse, Response response) {
                if (historyDataResponse.content.size() > 0)
                    loadTheHistory(historyDataResponse.content);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("getHistoryService", error.getLocalizedMessage());
            }
        });

    }

    public void
    getChatInfo(int cId) {
        TheChatApiService service = buildChatApi();
        service.getChatById(cId, new Callback<ChatInfo>() {
            @Override
            public void success(ChatInfo chatInfo, Response response) {
                if (chatInfo != null) {
                    ApiBus.getInstance().postQueue(new GetChatInfoSuccess(chatInfo));
                }

            }

            @Override
            public void failure(RetrofitError error) {
//            Log.e("error",error.getLocalizedMessage());
            }
        });
    }

    public void getChatInfoTo(int userId, int partnerId) {
        TheChatApiService service = buildChatApi();
        service.getChatByTo(userId, partnerId, new Callback<ChatInfoTo>() {
            @Override
            public void success(ChatInfoTo chatInfoTo, Response response) {
                if (chatInfoTo != null) {

                    mCid = chatInfoTo.getId();
                    getHistoryService(mCid);

                    ChatInfoTo.Member1Entity member1 = chatInfoTo.getMember1().get(0);
                    int chatUserId = member1.getUserId();
                    getUserInfoService(chatUserId);

                    ChatInfoTo.Member2Entity member2 = chatInfoTo.getMember2().get(0);
                    int chatUserId2 = member2.getUserId();
                    getUserInfoService(chatUserId2);

                    if (chatUserId != mUserId) {
                        if (mPartnerId == 0)
                            mPartnerId = chatUserId;
                    }
                }

            }


            @Override
            public void failure(RetrofitError error) {
//            Log.e("error",error.getLocalizedMessage());
            }
        });
    }

    private void uploadFileRetrofit(File file, final int msgType, final TheMessageObject message) {
        MainApiService service = buildMainApi();
        TypedFile typedFile = new TypedFile("multipart/form-data", file);
        String description = "hello, this is description speaking";

        service.upload(typedFile, description, new Callback<UploadCallback>() {
            @Override
            public void success(UploadCallback cb, Response response) {
                Log.e("Upload", "success " + cb.toString());
                String dataJson;
                if (cb.getFileType() != null) {
                    if (cb.getFileType().equals("image/jpeg") || cb.getFileType().equals("image/png") || cb.getFileType().equals("image/gif"))
                        dataJson = "[{'url':'" + cb.getFull_path() + "'" +
                                ",'full_path':'" + cb.getFull_path() + "'" +
                                ",'fileName':'" + cb.getFileName() + "'" +
                                ",'id':'" + cb.getId() + "'" +
                                ",'active':'" + cb.getActive() + "'" +
                                ",'thumb':'" + cb.getThumb() + "'" +
                                ",'fileType':'" + cb.getFileType() + "'}]";
                    else
                        dataJson = "[{'url':'" + cb.getFull_path() + "'" +
                                ",'full_path':'" + cb.getFull_path() + "'" +
                                ",'fileName':'" + cb.getFileName() + "'" +
                                ",'id':'" + cb.getId() + "'" +
                                ",'active':'" + cb.getActive() + "'" +
                                ",'thumb':'" + cb.getThumb() + "'" +
                                ",'fileType':'" + cb.getFileType() + "'}]";

                    message.onRight = true;
                    message.state = Message.MSG_STATE_SUCCESS;
                    List<TheMessageObject.DataEntity> list = new ArrayList<TheMessageObject.DataEntity>();
                    list.add(cb);
                    //message.setData(list);

                    adapter.notifyDataSetChanged();
                    attemptSendMessageToServer(msgType, "", dataJson, "[]");
                } else {
                    Toast.makeText(getActivity(), "Upload is not success, try again", Toast.LENGTH_SHORT).show();
                    message.state = Message.MSG_STATE_FAIL;
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Upload", "error " + error);
            }
        });
    }

    MainApiService buildMainApi() {
        String BASE_URL = "http://api.candychat.net";

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setEndpoint(BASE_URL)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                    }
                })
                .build()
                .create(MainApiService.class);
    }

    TheChatApiService buildChatApi() {
        String BASE_URL = "http://chat.candychat.net";

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setEndpoint(BASE_URL)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                    }
                })
                .build()
                .create(TheChatApiService.class);
    }


    File imagePathToFile(Uri selectedImageUri, String path) {
        Bitmap bm;
        BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

        bm = BitmapFactory.decodeFile(path, btmapOptions);
        OutputStream fOut = null;
        File file = new File(path);
        try {
            fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
            fOut.flush();
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private void loadTheHistory(List<TheMessageObject> msgObjs) {
        Log.e("theHistorySize", msgObjs.size() + "");

        Collections.reverse(msgObjs);
        for (int i = 0; i < msgObjs.size(); i++) {

            TheMessageObject m = msgObjs.get(i);
            m.onRight = mUserId == m.getSenderId() || mUserId == m.getSender().getId();
            m.htmlStringColor = "";
            String theStyle = "";
            if(m.getMessageType() == 0) {
                if (m.getMessageColorful() != null && m.getMessageColorful().size() != 0) {
                    for (TheMessageObject.MessageColorfulEntity ent : m.getMessageColorful()) {
                        if (ent.getStyle() != null) {
                            if (ent.getStyle().getStyle().equals(".SFUIText-Regular"))
                                theStyle = "DEFAULT";
                            else
                                theStyle = ent.getStyle().getStyle().replace(".ttf", "").replace(".otf", "");
                            m.htmlStringColor += "<font face='" + theStyle + "' color ='#" + ent.getStyle().getColor() + "' size='22'>" + ent.getMessage() + "</font>";
                        }

                    }
                    //Log.e("htmlString555",m.htmlStringColor);
                    m.spanned = CustomHtml.fromHtml(m.htmlStringColor, fontLoader);
                } else {
                    theStyle = "DEFAULT";
                    m.htmlStringColor = "<font face='" + theStyle + "' color ='#000000' size='22'>" + m.getMessage() + "</font>";
                    m.spanned = CustomHtml.fromHtml(m.htmlStringColor, fontLoader);
                }
            }

            //if (m.getMessageType() == 0) {
            listMsgObj.add(m);
            //}

        }
        adapter.notifyDataSetChanged();
        scrollToBottom();

    }

    ImageView friendAvatarIv;
    TextView friendNameTv;
    TextView friendUsernameTv;

    @Subscribe
    public void onGetUserInfoSuccess(GetUserInfoSuccessEvent event) {
        if (mPartnerId == event.user.id) {
            mPartnerName = event.user.name;
            mPartnerUsername = event.user.username;
            mPartnerAvatarUrl = event.user.getAvatarUrl();

            if (toolbar != null) {

                if(mChatType == 0) {
                    toolbar.setTitle(mPartnerName);
                    friendAvatarIv = (ImageView) toolbar.findViewById(R.id.avatar);
                    friendNameTv = (TextView) toolbar.findViewById(R.id.title);
                    friendUsernameTv = (TextView) toolbar.findViewById(R.id.sub_title);
                    Picasso.with(getActivity()).load(mPartnerAvatarUrl).resize(200, 200)
                            .transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);
                    friendNameTv.setText(mPartnerName);
                    friendUsernameTv.setText(mPartnerUsername);
                }

            }

            Log.e("mPartnerAvatarUrl", mPartnerAvatarUrl);
            Log.e("mPartnerName", mPartnerName);
            Log.e("mPartnerUsername", mPartnerUsername);
        }

        if (mUserId == event.user.id) {
            mName = event.user.name;
            mUsername = event.user.username;
            mAvatarUrl = event.user.getAvatarUrl();
        }

    }

    @Subscribe
    public void onGetChatInfoSuccess(GetChatInfoSuccess event) {
        List<ChatInfo.ConversationMembersEntity> conversationMembers = event.info.getConversationMembers();
        if (conversationMembers != null)
            for (int i = 0; i < conversationMembers.size(); i++) {
                getUserInfoService(conversationMembers.get(i).getUserId());
                ChatInfo.ConversationMembersEntity member = conversationMembers.get(i);
                int mId = member.getUserId();
                if (mId != mUserId) {
                    if (mPartnerId == 0)
                        mPartnerId = mId;
                    mPartnerName = member.getName();
                    mPartnerUsername = member.getUsername();
                    mPartnerAvatarUrl = EndpointManager.getAvatarPath(member.getAvatar() + "." + member.getExtension());
                    Log.e("mPartnerAvatarUrl", mPartnerAvatarUrl);
                    Log.e("mPartnerName", mPartnerName);
                    Log.e("mPartnerUsername", mPartnerUsername);

                    if(mChatType != 0 && toolbar != null && event.info != null) {
                        toolbar.setTitle(event.info.getName());
                        friendAvatarIv = (ImageView) toolbar.findViewById(R.id.avatar);
                        friendNameTv = (TextView) toolbar.findViewById(R.id.title);
                        friendUsernameTv = (TextView) toolbar.findViewById(R.id.sub_title);

                        String groupAvatarUrl = "http://chat.candychat.net" + event.info.getAvatar();

                        if(!groupAvatarUrl.equals(""))
                            Picasso.with(getActivity()).load(groupAvatarUrl).resize(200, 200)
                                .transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);
                        friendNameTv.setText(event.info.getName());
                        friendUsernameTv.setText(conversationMembers.size() + " members");
                    }

                } else {
                    mName = member.getName();
                    mUsername = member.getUsername();
                    mAvatarUrl = EndpointManager.getAvatarPath(member.getAvatar() + "." + member.getExtension());

                }
            }

        // get History Here
        getHistoryService(event.info.getConversationId());

    }

    boolean isAnimating;

    private void setupHorizontalBarAnimation(Context v) {
        horizontalBar = findViewById(R.id.scroll);
        mAnimationIn = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_in_bottom_up);
        mAnimationOut = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_top_down);
        mAnimationIn.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                horizontalBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                deleteTimer();
            }
        });

        mAnimationOut.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                horizontalBar.setVisibility(View.INVISIBLE);
                isAnimating = false;
                // Start timer
                setupTimer();
            }
        });

    }

    private void deleteTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    /**
     * Setup timer for bottom bar animation
     */
    private void setupTimer() {
        if (horizontalBar.getVisibility() != View.VISIBLE) {
            deleteTimer();
            timer = new Timer();
            // Create timer task for bottom bar
            TimerTask myTimerTask = new TimerTask() {

                @Override
                public void run() {
                    if (increate >= 3) {

                        increate = 0;
                    } else {
                        increate++;
                    }
                }
            };
            timer.schedule(myTimerTask, TIMER_DELAY, TIMER_PERIOD);
        }
    }


    @Override
    public void onScrollDown(boolean isScrollDown) {

    }

    @Override
    public void resetCountUpAnimation() {

    }

    @Override
    public void clickOnItem(String i) {
        Log.e("clickOnItem", i);

        String jsonObjStr = "[{'tattooUri':'" + i + "'}]";
        TheMessageObject msgObj = new TheMessageObject();
        msgObj.onRight = true;
        msgObj.setMessage("");
        msgObj.setMessageType(Message.MSG_TYPE_STICKER);
        msgObj.dataString = jsonObjStr;
        adapter.getData().add(msgObj);
        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, i, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }


    @Override
    public void clickOnItem2(String pathset2) {
        String jsonObjStr = "[{'tattooUri':'" + pathset2 + "'}]";
        TheMessageObject msgObj = new TheMessageObject();
        msgObj.onRight = true;
        msgObj.setMessage("");
        msgObj.setMessageType(Message.MSG_TYPE_STICKER);
        msgObj.dataString = jsonObjStr;
        adapter.getData().add(msgObj);
        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, pathset2, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }


    @Override
    public void clickOnItem3(String ii) {
        String jsonObjStr = "[{'tattooUri':'" + ii + "'}]";
        TheMessageObject msgObj = new TheMessageObject();
        msgObj.onRight = true;
        msgObj.setMessage("");
        msgObj.setMessageType(Message.MSG_TYPE_STICKER);
        msgObj.dataString = jsonObjStr;
        adapter.getData().add(msgObj);

        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, ii, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }

    @Override
    public void clickOnItem4(String pt) {
        String jsonObjStr = "[{'tattooUri':'" + pt + "'}]";
        TheMessageObject msgObj = new TheMessageObject();
        msgObj.onRight = true;
        msgObj.setMessage("");
        msgObj.setMessageType(Message.MSG_TYPE_STICKER);
        msgObj.dataString = jsonObjStr;
        adapter.getData().add(msgObj);
        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, pt, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }


//    @Override
//    public void clickOnItem5(String pt) {
//        chatEt.setText(pt + " ");
////        TheMessageObject msgObj = new TheMessageObject();
////        msgObj.onRight = true;
////        msgObj.setMessage(pt);
////        msgObj.setMessageType(Message.MSG_TYPE_TEXT);
////        adapter.getData().add(msgObj);
////        scrollToBottom();
////        attemptSendMessageToServer(Message.MSG_TYPE_TEXT, pt, "[]", "[]");
//    }
}

package com.module.candychat.net;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.candychat.net.activity.ChatWallpaperActivity;
import com.module.candychat.net.activity.ContactPickerActivity;
import com.module.candychat.net.activity.CreateTopicActivity;
import com.module.candychat.net.activity.InviteActivity;
import com.module.candychat.net.activity.SampleGridViewActivity;
import com.module.candychat.net.activity.SelectPhotosActivity;
import com.module.candychat.net.adapter.EnFontAdapter;
import com.module.candychat.net.adapter.GroupMemberAdapter;
import com.module.candychat.net.adapter.ListViewDefaultFontAdapter;
import com.module.candychat.net.adapter.MessageAdapter;
import com.module.candychat.net.adapter.MyRecyclerListTopicAdapter;
import com.module.candychat.net.adapter.SpinnerAdapter;
import com.module.candychat.net.adapter.ThFontAdapter;
import com.module.candychat.net.adapter.TopicListViewAdapter;
import com.module.candychat.net.emoji.EmojiPopup;
import com.module.candychat.net.emoji.emoji.Emoji;
import com.module.candychat.net.emoji.listeners.OnEmojiBackspaceClickListener;
import com.module.candychat.net.emoji.listeners.OnEmojiClickedListener;
import com.module.candychat.net.emoji.listeners.OnEmojiPopupDismissListener;
import com.module.candychat.net.emoji.listeners.OnEmojiPopupShownListener;
import com.module.candychat.net.emoji.listeners.OnSoftKeyboardCloseListener;
import com.module.candychat.net.emoji.listeners.OnSoftKeyboardOpenListener;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.event.ConversationEventTopicSuccess;
import com.module.candychat.net.event.GetChatInfoSuccess;
import com.module.candychat.net.event.GetTopicSuccess;
import com.module.candychat.net.event.GetUserInfoSuccessEvent;
import com.module.candychat.net.event.InterpretMessageEvent;
import com.module.candychat.net.handler.ApiBus;
import com.module.candychat.net.model.Message;
import com.module.candychat.net.model.RelationsGroup;
import com.module.candychat.net.model.RelationsTopic;
import com.module.candychat.net.service.EndpointManager;
import com.module.candychat.net.service.MainApiService;
import com.module.candychat.net.service.TheChatApiService;
import com.module.candychat.net.service.TheTopicApiService;
import com.module.candychat.net.service.UploadCallback;
import com.module.candychat.net.service.UserProfileDataResponse;
import com.module.candychat.net.stickerNew.FragmentPageAdapter;
import com.module.candychat.net.stickerNew.SS0Fragment;
import com.module.candychat.net.stickerNew.SS1Fragment;
import com.module.candychat.net.stickerNew.SS2Fragment;
import com.module.candychat.net.stickerNew.SS3Fragment;
import com.module.candychat.net.stickerNew.SS4EmojiFragment;
import com.module.candychat.net.stickerNew.SlidingTabLayout;
import com.module.candychat.net.util.ChatUtil;
import com.module.candychat.net.util.MediaManager;
import com.module.candychat.net.util.UnitUtils;
import com.module.candychat.net.view.AudioRecorderButton;
import com.module.candychat.net.view.CustomEditTextOriginal;
import com.module.candychat.net.widgets.AndroidEmoji;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.module.candychat.net.widgets.SizeNotifierRelativeLayout;
import com.module.candychat.net.wouclass.CaseInsensitiveAssetFontLoader;
import com.module.candychat.net.wouclass.ChatInfoTo;
import com.module.candychat.net.wouclass.CustomHtml;
import com.module.candychat.net.wouclass.TheHistoryDataResponse;
import com.module.candychat.net.wouclass.TheHtml;
import com.module.candychat.net.wouclass.WFont;
import com.module.candychat.net.wouclass.event.SeeEvent;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import co.moonmonkeylabs.realmsearchview.RealmManager;
import co.moonmonkeylabs.realmsearchview.model.TheMessageObject;
import io.realm.Realm;
import it.sephiroth.android.library.widget.HListView;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;


public class ChatActivity extends BaseChatActivity implements View.OnClickListener, SS0Fragment.OnChildInteractionListener, SS1Fragment.OnChildInteractionListener2,
        SS2Fragment.OnChildInteractionListener3, SS3Fragment.OnChildInteractionListener4, SS4EmojiFragment.OnChildInteractionListener5 {

    private EmojiPopup emojiPopup;
    //    private EmojiEditText editText;
//    private ImageView     emojiButton;
    private ViewGroup rootView;

    private void setUpEmojiPopup() {
        emojiPopup = EmojiPopup.Builder.fromRootView(rootView).setOnEmojiBackspaceClickListener(new OnEmojiBackspaceClickListener() {
            @Override
            public void onEmojiBackspaceClicked(final View v) {
                Log.d("MainActivity", "Clicked on Backspace");
            }
        }).setOnEmojiClickedListener(new OnEmojiClickedListener() {
            @Override
            public void onEmojiClicked(final Emoji emoji) {
                Log.d("MainActivity", "Clicked on emoji");
            }
        }).setOnEmojiPopupShownListener(new OnEmojiPopupShownListener() {
            @Override
            public void onEmojiPopupShown() {
                btn_sticker.setImageResource(R.drawable.ic_keyboard_grey_500_36dp);
            }
        }).setOnSoftKeyboardOpenListener(new OnSoftKeyboardOpenListener() {
            @Override
            public void onKeyboardOpen(final int keyBoardHeight) {
                Log.d("MainActivity", "Opened soft keyboard");
            }
        }).setOnEmojiPopupDismissListener(new OnEmojiPopupDismissListener() {
            @Override
            public void onEmojiPopupDismiss() {
                btn_sticker.setImageResource(R.drawable.ic_chat_pop_contact);
            }
        }).setOnSoftKeyboardCloseListener(new OnSoftKeyboardCloseListener() {
            @Override
            public void onKeyboardClose() {
                emojiPopup.dismiss();
            }
        }).build(chatEditText);
    }

    public static volatile Handler applicationHandler = null;
    public static ChatActivity activityInstance = null;

    private int keyboardHeight;
    private boolean keyboardVisible;
    private WindowManager.LayoutParams windowLayoutParams;
    private SizeNotifierRelativeLayout sizeNotifierRelativeLayout;

    @Override
    public void onBackPressed() {
        if (!isLayoutAttachmentShow && !isLayoutColorfulShow && !isLayoutStickerShow) {
            super.onBackPressed();
            return;
        }

        customFontlayout.setVisibility(View.GONE);
        isLayoutColorfulShow = false;

        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;

        chatAttachmentLayout.setVisibility(View.GONE);
        isLayoutAttachmentShow = false;

        if (emojiPopup != null && emojiPopup.isShowing()) {
            emojiPopup.dismiss();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
        MediaManager.release();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBg();
        MediaManager.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
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

    public static void startChatBroadcast(Context context, int cId, int userId, ArrayList<Integer> partnerIdArray, int chatType) {
        Intent i = new Intent(context, ChatActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(CONVERSATION_ID, cId);
        i.putExtra(USER_ID_1, userId);
        i.putExtra(USER_ID_2, partnerIdArray);
        i.putExtra(CHAT_TYPE, chatType);
        context.startActivity(i);
    }

    public static Intent startChatIntent(Context context, int cId, int userId, int partnerId, int chatType) {
        Intent i = new Intent(context, ChatActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(CONVERSATION_ID, cId);
        i.putExtra(USER_ID_1, userId);
        i.putExtra(USER_ID_2, partnerId);
        i.putExtra(CHAT_TYPE, chatType);

        return i;
        //context.startActivity(i);
    }

    public static void startChatShareIntent(Context context, int cId, int userId, int partnerId, int chatType, Intent theIntent) {
        Intent i = new Intent(context, ChatActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(CONVERSATION_ID, cId);
        i.putExtra(USER_ID_1, userId);
        i.putExtra(USER_ID_2, partnerId);
        i.putExtra(CHAT_TYPE, chatType);
        i.putExtra(Intent.EXTRA_STREAM, theIntent.getParcelableExtra(Intent.EXTRA_STREAM));

        context.startActivity(i);


        //return i;
        //context.startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/WOUchat/";
        File newdir = new File(dir);
        newdir.mkdirs();
        new AndroidUtilities(this);
        AndroidUtilities.density = getResources().getDisplayMetrics().density;

    }

    private MenuDrawer mDrawer;
    private RelativeLayout chatListViewContainer;
    private ListView chatListView;
    public static CustomEditTextOriginal chatEditText;
    private ImageView enter_chat;

    private Context mContext;
    private FragmentManager fragmentManager;

    private ImageView btn_sticker;

    private ImageView btn_reset;
    private TextView txt_preview;
    private ImageView enter_camera;

    private TextView txt_take_photo, txt_gallery, txt_video, txt_location, txt_contact, txt_voice, txt_font_history;

    //private List<Message> listMessages = new ArrayList<>();
    private List<TheMessageObject> listMsgObj = new ArrayList<>();
    private List<TheMessageObject> listMsgObjPhoto = new ArrayList<>();
    private List<String> listFontNames = new ArrayList<>();
    private MessageAdapter adapter;
    private LineColorPicker lineColorPicker;

    RelativeLayout customFontlayout, chatAttachmentLayout, groupMemberLayout;

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
    Dialog dialog2;
    Toolbar toolbar;

    boolean isLayoutStickerShow = false;
    boolean isLayoutGroupMemberShow = false;
    TextView txt_en;
    TextView txt_th;
    AudioRecorderButton mAudioRecorderButton;

    ThFontAdapter adapterFontListViewTh;
    EnFontAdapter adapterFontListViewEn;
    //EnFontAdapter adapterFontListViewDefault;
    ListViewDefaultFontAdapter adapterFontListViewHistory;
    HListView fontListView;
    HListView memberListView;
    GroupMemberAdapter adapterMemberListView;
    MyRecyclerListTopicAdapter myRecyclerListTopicAdapter;

    ArrayList<ChatInfo.ConversationMembersEntity> listTopic = new ArrayList<>();

    RelativeLayout holder;
    FragmentPageAdapter pageAdapter;
    TextView txt_view_emoji;
    TextView txt_view_sticker;
    TextView text_view_textface;
    TabLayout mTabs;

    Spinner fontSizeSpinner;
    String[] fontSizeArr = {"16", "18", "24", "28", "32", "36"};
    String size;
    int currentFontSize = 16;
    SpinnerAdapter fontSizeSelector;
    WFont wFont;

    public static final String LOG_TAG = "WOULOG";

    List<TheMessageObject.MessageColorfulEntity> listColorfulObj = new ArrayList<>();
    TheMessageObject message;
    public static int resendPos;

    int colorfulIndex = 0;

    RelativeLayout chatBox;
    FrameLayout chatBoxBg;
    String toHtml;

    String[] fontEnArr;
    String[] fontThArr;

    String type;
    String type_click;

    private CaseInsensitiveAssetFontLoader fontLoader;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Do something for JellyBean and above versions
            getMenuInflater().inflate(R.menu.menu_main_chat, menu);
        } else {
            // do something for phones running an SDK before JellyBean
            getMenuInflater().inflate(R.menu.menu_main_chat_422, menu);
        }

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
        } else if (item.getItemId() == R.id.context_menu) {
            if (mDrawer != null)
                mDrawer.toggleMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static ImageView bgView;

    private void initBg() {
        bgView = (ImageView) findViewById(R.id.bg_iv);
        bgView.setImageResource(Constants.idBackground);
    }

    //public static ChatPrefManager mPref;

    @Override
    protected void onCreate(Bundle state) {
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(state);
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectCustomSlowCalls() // API level 11, to use with StrictMode.noteSlowCode
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()
//                .penaltyLog()
//                .penaltyFlashScreen() // API level 11
//                .build());

        Method m = null;
        try {
            m = StrictMode.class.getMethod("incrementExpectedActivityCount", Class.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            m.invoke(null, ChatActivity.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        initDrawer();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setContentView(R.layout.activity_chat);
        mContext = ChatActivity.this;
        fragmentManager = getSupportFragmentManager();

        //mPref = new ChatPrefManager(getSharedPreferences("Chat", MODE_PRIVATE));

        initView();
        initBg();
        initRoomData();
        initToolbar();
        initChatEditText();
        initSticker();
        initColorful();
        initChatListView();
        //initGroupMembersListView();
        initIntent();

        rootView = (ViewGroup) findViewById(R.id.chat_layout);
        //emojiButton = (ImageView) findViewById(R.id.main_activity_emoji);

        setUpEmojiPopup();

    }


    private void initIntent() {
        Uri imageUri = (Uri) getIntent().getParcelableExtra(Intent.EXTRA_STREAM);

        if (imageUri != null) {
            Log.e("imageUriInChatRoom", imageUri.toString());
            sendPhoto(imageUri);
        }
    }

    private void scrollToBottom() {
        if (chatListView != null)
            chatListView.setSelection(chatListView.getBottom());
        //other way
        chatListView.setSelection(chatListView.getCount() - 1);

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

            if (chatEditText.isFocused()) {
                //lnl.setVisibility(View.VISIBLE);
            }
        }
    };

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

    public void initChatEditText() {

        chatBox = (RelativeLayout) findViewById(R.id.chat_box_wrapper);
        chatBoxBg = (FrameLayout) findViewById(R.id.chat_box_bg);

        chatEditText = (CustomEditTextOriginal) findViewById(R.id.chat_edit_text1);
        chatEditText.clearFocus();
        chatEditText.setSingleLine(false);
        chatEditText.setMinLines(2);
        //customEditor.setBoldToggleButton(boldToggle);
        //customEditor.setItalicsToggleButton(italicsToggle);
        //customEditor.setUnderlineToggleButton(underlinedToggle);
        chatEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });
        chatEditText.setEventBack(eventBack);
        chatEditText.setOnClickListener(chatEtClickListener);
        chatEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && !"".equals(s.toString().trim())) {
                    enter_camera.setVisibility(View.GONE);
                    enter_chat.setEnabled(true);
                    enter_chat.setVisibility(View.VISIBLE);
                }
//                else {
//                    enter_camera.setVisibility(View.VISIBLE);
//                    if (enter_camera.getVisibility() == View.VISIBLE) {
//                        enter_chat.setVisibility(View.GONE);
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

                //Log.e("linecount2",chatEditText.getLineCount() + "");

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("linecount", chatEditText.getLineCount() + "");

                if (chatEditText.getLineCount() == 3) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) chatBox.getLayoutParams();
                    params.height = UnitUtils.dpToPx(72);
                    chatBox.setLayoutParams(params);

                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) chatBoxBg.getLayoutParams();
                    params2.height = UnitUtils.dpToPx(70);
                    chatBoxBg.setLayoutParams(params2);
//                    chatBox.setMinimumHeight(62);
//                    chatBoxBg.setMinimumHeight(60);
                } else if (chatEditText.getLineCount() == 4) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) chatBox.getLayoutParams();
                    params.height = UnitUtils.dpToPx(82);
                    chatBox.setLayoutParams(params);

                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) chatBoxBg.getLayoutParams();
                    params2.height = UnitUtils.dpToPx(80);
                    chatBoxBg.setLayoutParams(params2);

//                    chatBox.setMinimumHeight(68);
//                    chatBoxBg.setMinimumHeight(66);
                } else if (chatEditText.getLineCount() >= 5) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) chatBox.getLayoutParams();
                    params.height = UnitUtils.dpToPx(96);
                    chatBox.setLayoutParams(params);

                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) chatBoxBg.getLayoutParams();
                    params2.height = UnitUtils.dpToPx(94);
                    chatBoxBg.setLayoutParams(params2);

//                    chatBox.setMinimumHeight(54);
//                    chatBoxBg.setMinimumHeight(52);
                } else {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) chatBox.getLayoutParams();
                    params.height = UnitUtils.dpToPx(54);
                    chatBox.setLayoutParams(params);

                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) chatBoxBg.getLayoutParams();
                    params2.height = UnitUtils.dpToPx(52);
                    chatBoxBg.setLayoutParams(params2);
                }

            }
        });

        int selectionStart = chatEditText.getSelectionStart();
        int selectionEnd = chatEditText.getSelectionEnd();

        wFont = new WFont("000000", "DEFAULT", currentFontSize);

        chatEditText.setColor(Color.BLACK, selectionStart, selectionEnd);
        listFontNames.add("DEFAULT");
        chatEditText.setFont(Typeface.DEFAULT, wFont, selectionStart, selectionEnd);
        //chatEditText.setTextSize(currentFontSize);

        enter_chat = (ImageView) findViewById(R.id.enter_chat1);
        enter_chat.setOnClickListener(this);
        enter_camera = (ImageView) findViewById(R.id.enter_camera);
        enter_camera.setOnClickListener(this);

        //TODO: toggle camera and chat enter
        enter_camera.setVisibility(View.GONE);
        enter_chat.setVisibility(View.VISIBLE);

        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.btn_record_voice);
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {

                TheMessageObject.Recorder recorder = new TheMessageObject.Recorder(seconds, filePath);
                Log.e("seconds", seconds + "");
                Log.e("filePathVoice", filePath);
                if (filePath != null) {
                    String dataJson = "";
                    dataJson = "[{'thumb':'" + filePath + "'}]";
                    File clipVoice = new File(filePath);
                    Log.e("Voice", clipVoice + "");

                    TheMessageObject msgObj = new TheMessageObject();
                    msgObj.recorder = recorder;
                    uploadFileRetrofit(clipVoice, Message.MSG_TYPE_VOICE, msgObj);
                }

            }
        });

        RelativeLayout touchInterceptor = (RelativeLayout) findViewById(R.id.chat_list_box);
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (chatEditText.isFocused()) {
                        Rect outRect = new Rect();
                        chatEditText.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                            chatEditText.clearFocus();
//                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            hideEverything();
                        }
                    }
                }
                return false;
            }
        });


    }

    private void initDrawer() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        mDrawer = MenuDrawer.attach(this, Position.RIGHT);
        mDrawer.setContentView(R.layout.activity_chat_new);
        mDrawer.setMenuView(R.layout.layout_more_chat);
        mDrawer.setMenuSize((int) Math.floor(width * 0.2));

        mDrawer.getMenuView().findViewById(R.id.drawer_search).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.drawer_info).setOnClickListener(this);
        //mDrawer.getMenuView().findViewById(R.id.drawer_contact).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.drawer_invite).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.drawer_media).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.drawer_wallpaper).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.drawer_notification).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.drawer_photo).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.drawer_chat_setting).setOnClickListener(this);
        mDrawer.getMenuView().findViewById(R.id.imag_create_topic).setOnClickListener(this);

    }

    ArrayList<Integer> broadcastList = new ArrayList<>();

    private void initRoomData() {
        mCid = getIntent().getExtras().getInt(CONVERSATION_ID, 0);
        mUserId = getIntent().getExtras().getInt(USER_ID_1, 0);
        getTopic(mUserId,mCid);
        mChatType = getIntent().getExtras().getInt(CHAT_TYPE, 0);

        if (mChatType == 3) {
            isMultiChat = true;
            broadcastList = getIntent().getExtras().getIntegerArrayList(USER_ID_2);
        } else {
            mPartnerId = getIntent().getExtras().getInt(USER_ID_2, 0);
        }

        //dumpIntent(getIntent());

        if (mUserId != 0) {
            initConnect(mUserId);
        } else {
            tt("Can't connect userId:" + mUserId);

        }
        if (mChatType == 0 || mCid == 0) {
            getChatInfoTo(mUserId, mPartnerId);
        } else if (mUserId == mPartnerId) {
            getChatInfoSelf(mUserId);

        } else
            getChatInfo(mCid);


    }

    private void sendPhoto(Uri uri) {
        TheMessageObject message = new TheMessageObject();
        message.onRight = true;
        message.state = Message.MSG_STATE_SENDING;
        message.setSenderId(mUserId);
        TheMessageObject.SenderEntity senderEntity = new TheMessageObject.SenderEntity();
        senderEntity.setSenderId(mUserId);
        senderEntity.setId(mUserId);
        senderEntity.setUsername(mUsername);
        senderEntity.setName(mName);
        senderEntity.setAvatar(mAvatarUrl);
        message.setSender(senderEntity);
        message.setMessage("");
        message.setMessageType(Message.MSG_TYPE_PHOTO);
        long unixTime = System.currentTimeMillis() / 1000L;
        message.setTimestamp(unixTime);
        //message.setId(safeLongToInt(System.currentTimeMillis()));
        //message.setData(list);
        //message.setDataString(dataJson);
        //message.setSender(new TheMessageObject.SenderEntity());

        listMsgObj.add(message);
        adapter.notifyDataSetChanged();

        //attemptSendMessageToServer(Message.MSG_TYPE_PHOTO, "", "[]", "[]");


        // Log.e("selectedImageUri", selectedImageUri + "");

        //String path = getRealPath(ChatActivity.this, urlPath);
        File file = imagePathToFile(null, uri.getPath());
        Log.e("uri.getPath()", uri.getPath());


        // TheMessageObject msgObj = new TheMessageObject();
        adapter.sendingMsgObj.put(unixTime, message);

        uploadFileRetrofit(file, Message.MSG_TYPE_PHOTO, message);
    }


    private void initView() {
        activityInstance = this;
        txt_take_photo = (TextView) findViewById(R.id.txt_take_photo);
        txt_gallery = (TextView) findViewById(R.id.txt_gallery);
        txt_video = (TextView) findViewById(R.id.txt_video);
        txt_location = (TextView) findViewById(R.id.txt_location);
        txt_contact = (TextView) findViewById(R.id.txt_contact);
        txt_voice = (TextView) findViewById(R.id.txt_voice);

        txt_take_photo.setOnClickListener(this);
        txt_gallery.setOnClickListener(this);
        txt_video.setOnClickListener(this);
        txt_location.setOnClickListener(this);
        txt_contact.setOnClickListener(this);
        txt_voice.setOnClickListener(this);


    }

    private void initSticker() {
        btn_sticker = (ImageView) findViewById(R.id.btn_sticker);
        btn_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
//                mKeyBoardPopWindow.showPopupWindow();
//
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                //onClickStickerEventNew();
                onClickStickerEvent();

                if (emojiPopup.isShowing()) {
                    //holder.setVisibility(View.GONE);
                    isLayoutStickerShow = false;
                    chatEditText.requestFocus();
                }
            }
        });

        mTabs = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.vpPager);

        setupViewPager(mViewPager);
        setupTabLayout(mTabs, true);

        ImageView btnShop = (ImageView) findViewById(R.id.text_sticker_shop);
        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Open Sticker Shop", Toast.LENGTH_LONG).show();
            }
        });

        txt_view_sticker = (TextView) findViewById(R.id.txt_view_sticker);
        txt_view_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emojiPopup.isShowing())
                    emojiPopup.toggle();
                setupViewPager(mViewPager);
                setupTabLayout(mTabs, true);
                scrollToBottom();
//                mTabs.setVisibility(View.VISIBLE);
//                mViewPager.setVisibility(View.VISIBLE);

            }
        });

        txt_view_emoji = (TextView) findViewById(R.id.txt_view_emoji);
        //txt_view_emoji.setVisibility(View.GONE);
        txt_view_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emojiPopup.isShowing())
                    emojiPopup.toggle();

                onClickStickerEvent();

//                mTabs.setVisibility(View.GONE);
//                mViewPager.setVisibility(View.GONE);


            }
        });

        text_view_textface = (TextView) findViewById(R.id.txt_view_textface);

        text_view_textface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideEverything();
                setupViewPagerTextFace(mViewPager);
                setupTabLayout(mTabs, false);
            }
        });


    }

    ListView listView2;

    public void initColorful() {
        lineColorPicker = (LineColorPicker) findViewById(R.id.line_color_picker);
        int[] colors = new int[Constants.pallete.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.parseColor(Constants.pallete[i]);
        }
        lineColorPicker.setColors(colors);
        lineColorPicker.setSelectedColor(colors[0]);
        lineColorPicker.setOnColorChangedListener(new OnColorChangedListener() {

            @Override
            public void onColorChanged(int c) {

                currentFontColor = Integer.toHexString(c).substring(2, 8);
                currentFontColor = currentFontColor.toUpperCase();
                chatEditText.setTextColor(c);
                txt_preview.setTextColor(c);

                int selectionStart = chatEditText.getSelectionStart();
                int selectionEnd = chatEditText.getSelectionEnd();
                chatEditText.setColor(c, selectionStart, selectionEnd);

            }
        });

        mBtnItalic = (ToggleButton) findViewById(R.id.btn_italic);
        mBtnBold = (ToggleButton) findViewById(R.id.btn_bold);

        btn_reset = (ImageView) findViewById(R.id.btn_reset);
        txt_preview = (TextView) findViewById(R.id.txt_preview);

        customFontlayout = (RelativeLayout) findViewById(R.id.colorful_layout);
        chatAttachmentLayout = (RelativeLayout) findViewById(R.id.attachment_layout);
        groupMemberLayout = (RelativeLayout) findViewById(R.id.group_member_layout);
        listView2 = (ListView) findViewById(R.id.listView2);

        fontListView = (HListView) findViewById(R.id.hListView1);

        fontLoader = new CaseInsensitiveAssetFontLoader(getActivity(), "fonts");
        fontLoader.getTypeFace("Roboto-Regular");

        //adapterFontListViewDefault = new EnFontAdapter(this, fontEnArr);

        holder = (RelativeLayout) findViewById(R.id.holder);
        fontSizeSpinner = (Spinner) findViewById(R.id.spinner1);
        fontSizeSpinner.setVisibility(View.GONE);
//        fontSizeSelector = new SpinnerAdapter(getActivity(), fontSizeArr);
//        fontSizeSpinner.setVisibility(View.GONE);
//        fontSizeSpinner.setAdapter(fontSizeSelector);
//        fontSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                size = fontSizeArr[position];
//                currentFontSize = Integer.parseInt(size);
//                Log.e("code", currentFontSize + "");
//                txt_preview.setTextSize(currentFontSize);
//                chatEditText.setTextSize(currentFontSize);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        txt_en = (TextView) findViewById(R.id.txt_en);
        txt_th = (TextView) findViewById(R.id.txt_th);
        txt_font_history = (TextView) findViewById(R.id.txt_history);


        txt_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontEnArr = getResources().getStringArray(R.array.font_en);
                adapterFontListViewEn = new EnFontAdapter(ChatActivity.this, fontEnArr);

                fontListView.setAdapter(adapterFontListViewEn);
                fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                        txt_preview.setTypeface((Typeface) adapterFontListViewEn.getItem(position));
                        chatEditText.setTypeface((Typeface) adapterFontListViewEn.getItem(position));


                        currentFontName = adapterFontListViewEn.getData().get(position);
                        adapterFontListViewHistory.getData().add(currentFontName);
                        adapterFontListViewHistory.notifyDataSetChanged();
                        currentFontLocale = "en";
                        postionEn = position;

                        int selectionStart = chatEditText.getSelectionStart();
                        int selectionEnd = chatEditText.getSelectionEnd();
                        //chatEditText.setTextSize(currentFontSize);


                        if (currentFontName.equals(Constants.DEFAULT) || currentFontName.equals("Roboto-Regular")) {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = Typeface.DEFAULT;
                            listFontNames.add("DEFAULT");
                            chatEditText.setFont(Typeface.DEFAULT, wFont, selectionStart, selectionEnd);


                        } else {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = fontLoader.getTypeFace(currentFontName);
                            listFontNames.add(currentFontName);
                            chatEditText.setFont(fontLoader.getTypeFace(currentFontName), wFont, selectionStart, selectionEnd);
                        }

                        Log.e("currentFontName", currentFontName);

//                        chatEditText.setColor(generateRandomColor(),selectionStart,selectionEnd);
//                        chatEditText.append(currentFontName + " ");
                    }
                });
            }
        });
        txt_th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontThArr = getResources().getStringArray(R.array.font_th);
                adapterFontListViewTh = new ThFontAdapter(ChatActivity.this, fontThArr);

                fontListView.setAdapter(adapterFontListViewTh);
                fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                        txt_preview.setText("วู้แชท ^_^");
                        txt_preview.setTypeface((Typeface) adapterFontListViewTh.getItem(position));
                        txt_preview.setTextSize(26);
                        chatEditText.setTypeface((Typeface) adapterFontListViewTh.getItem(position));

                        currentFontName = adapterFontListViewTh.getData().get(position);
                        adapterFontListViewHistory.getData().add(currentFontName);
                        adapterFontListViewHistory.notifyDataSetChanged();

                        currentFontLocale = "th";
                        postionTh = position;

                        currentFontSize = 26;

                        int selectionStart = chatEditText.getSelectionStart();
                        int selectionEnd = chatEditText.getSelectionEnd();
                        if (currentFontName.contains("kt"))
                            chatEditText.setTextSize(26);
                        else if (currentFontName.toLowerCase().contains("alex") || currentFontName.equals("Roboto-Regular")) {
                            currentFontSize = 18;
                            chatEditText.setTextSize(currentFontSize);
                        } else
                            chatEditText.setTextSize(currentFontSize);
                        if (currentFontName.equals(Constants.DEFAULT) || currentFontName.equals("Roboto-Regular")) {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = Typeface.DEFAULT;
                            listFontNames.add("DEFAULT");
                            chatEditText.setFont(Typeface.DEFAULT, wFont, selectionStart, selectionEnd);
                        } else {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = fontLoader.getTypeFace(currentFontName);
                            listFontNames.add(currentFontName);
                            chatEditText.setFont(fontLoader.getTypeFace(currentFontName), wFont, selectionStart, selectionEnd);
                        }

                        Log.e("currentFontName", currentFontName);

//                        chatEditText.setColor(generateRandomColor(),selectionStart,selectionEnd);
//                        chatEditText.append(currentFontName + " ");
                    }
                });
            }
        });


        String[] historyArr = new String[1];
        historyArr[0] = Constants.DEFAULT;
        adapterFontListViewHistory = new ListViewDefaultFontAdapter(getApplicationContext(), historyArr);

        txt_font_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fontListView.setAdapter(adapterFontListViewHistory);
                fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                        //txt_preview.setTypeface((Typeface) adapterFontListViewDefault.getItem(position));
                        //chatEditText.setTypeface((Typeface) adapterFontListViewDefault.getItem(position));

                        int selectionStart = chatEditText.getSelectionStart();
                        int selectionEnd = chatEditText.getSelectionEnd();

                        currentFontName = adapterFontListViewHistory.getData().get(position);
                        if (currentFontName.equals(Constants.DEFAULT) || currentFontName.equals("Roboto-Regular")) {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = Typeface.DEFAULT;
                            listFontNames.add("DEFAULT");
                            chatEditText.setFont(Typeface.DEFAULT, wFont, selectionStart, selectionEnd);
                        } else {
                            wFont.mFace = currentFontName;
                            wFont.mSize = currentFontSize;
                            wFont.mColor = currentFontColor;
                            wFont.typeface = fontLoader.getTypeFace(currentFontName);
                            listFontNames.add(currentFontName);
                            chatEditText.setFont(fontLoader.getTypeFace(currentFontName), wFont, selectionStart, selectionEnd);
                        }
                        //currentFontLocale = "th";
                        //postionTh = position;


                        if (currentFontName.toLowerCase().contains("kt"))
                            chatEditText.setTextSize(26);
                        else
                            chatEditText.setTextSize(currentFontSize);


                        Log.e("currentFontName", currentFontName);

//                        chatEditText.setColor(generateRandomColor(),selectionStart,selectionEnd);
//                        chatEditText.append(currentFontName + " ");
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
                chatEditText.setTextColor(Color.BLACK);
                chatEditText.setTypeface(typeface);
                chatEditText.setTextSize(16);
                currentFontColor = "000000";
                currentFontName = "DEFAULT";


                int selectionStart = chatEditText.getSelectionStart();
                int selectionEnd = chatEditText.getSelectionEnd();
                if (currentFontName.equals(Constants.DEFAULT)) {
                    wFont.mFace = currentFontName;
                    wFont.mSize = currentFontSize;
                    wFont.mColor = currentFontColor;
                    wFont.typeface = Typeface.DEFAULT;
                    listFontNames.add("DEFAULT");
                    chatEditText.setFont(Typeface.DEFAULT, wFont, selectionStart, selectionEnd);
                } else {
                    wFont.mFace = currentFontName;
                    wFont.mSize = currentFontSize;
                    wFont.mColor = currentFontColor;
                    wFont.typeface = fontLoader.getTypeFace(currentFontName);
                    listFontNames.add(currentFontName);
                    chatEditText.setFont(fontLoader.getTypeFace(currentFontName), wFont, selectionStart, selectionEnd);
                }
            }
        });
    }

    private void initChatListView() {
        adapter = new MessageAdapter(ChatActivity.this, listMsgObj, mUserId, mChatType);
        chatListViewContainer = (RelativeLayout) findViewById(R.id.chat_list_box);
        chatListViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hideKeyboard(view);
                hideEverything();
            }
        });

        chatListView = (ListView) findViewById(R.id.chat_list_view);
        chatListView.setAdapter(adapter);
        chatListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        chatListView.setStackFromBottom(true);
        //chatListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        chatListView.setOnScrollListener(new ListScrollListener());

        int count = chatListView.getCount();
        if (count > 0) {
            chatListView.setSelection(count);
        }

        chatListView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                hideEverything();
                return false;
            }
        });


    }

    private class ListScrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    if (view.getFirstVisiblePosition() == 0 && !isloading
                            && haveMoreData) {
                        currentHistoryPage++;
                        try {
                            fetchChatHistory(mCid, currentHistoryPage, historyPageSize);
                        } catch (Exception e1) {
                            return;
                        }
                    }

//                    switch (scrollState) {
//                        case SCROLL_STATE_FLING:
//                            break;
//                        case SCROLL_STATE_IDLE:
//                            break;
//                        case SCROLL_STATE_TOUCH_SCROLL:
//                            kv_bar.hideAutoView();
//                            break;
//                    }

                    break;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {

        }

    }

    boolean isloading;
    boolean haveMoreData = true;

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
                    fontEnArr = getResources().getStringArray(R.array.font_en);
                    adapterFontListViewEn = new EnFontAdapter(ChatActivity.this, fontEnArr);

                    fontListView.setAdapter(adapterFontListViewEn);
                    fontListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                            txt_preview.setTypeface((Typeface) adapterFontListViewEn.getItem(position));
                            chatEditText.setTypeface((Typeface) adapterFontListViewEn.getItem(position));

                            currentFontName = adapterFontListViewEn.getData().get(position);
                            adapterFontListViewHistory.getData().add(currentFontName);
                            adapterFontListViewHistory.notifyDataSetChanged();

                            currentFontLocale = "en";
                            postionEn = position;

                            int selectionStart = chatEditText.getSelectionStart();
                            int selectionEnd = chatEditText.getSelectionEnd();
                            //chatEditText.setTextSize(currentFontSize);
                            if (currentFontName.equals(Constants.DEFAULT) || currentFontName.equals("Roboto-Regular")) {
                                wFont.mFace = currentFontName;
                                wFont.mSize = currentFontSize;
                                wFont.mColor = currentFontColor;
                                wFont.typeface = Typeface.DEFAULT;
                                listFontNames.add("DEFAULT");
                                chatEditText.setFont(Typeface.DEFAULT, wFont, selectionStart, selectionEnd);
                            } else {
                                wFont.mFace = currentFontName;
                                wFont.mSize = currentFontSize;
                                wFont.mColor = currentFontColor;
                                wFont.typeface = fontLoader.getTypeFace(currentFontName);
                                listFontNames.add(currentFontName);
                                chatEditText.setFont(fontLoader.getTypeFace(currentFontName), wFont, selectionStart, selectionEnd);
                            }

                        }
                    });

                    onClickColorfulEvent();
                    return true;
                } else if (item.getItemId() == R.id.context_menu) {
                    if (mDrawer != null)
                        mDrawer.toggleMenu();
                    return true;
                }
                return false;
            }
        });
    }


    public void setupViewPager(ViewPager viewPager) {
        pageAdapter = new FragmentPageAdapter(ChatActivity.this, fragmentManager);
        pageAdapter.addFragment(SS0Fragment.getInstance("set2"), "set2", R.drawable.ic_set_2, "");
        pageAdapter.addFragment(SS1Fragment.getInstance("set1"), "set1", R.drawable.ic_set_1, "");
        pageAdapter.addFragment(SS2Fragment.getInstance("set3"), "set3", R.drawable.ic_set_3, "");
        pageAdapter.addFragment(SS3Fragment.getInstance("set4"), "set4", R.drawable.ic_set_4, "");
        //pageAdapter.addFragment(EmojiFragment.newInstance(chatEditText, chatEditText.getId()), "set5", R.drawable.b_happy_sticker, "");
        viewPager.setAdapter(pageAdapter);

    }

    public void setupViewPagerTextFace(ViewPager viewPager) {
        pageAdapter = new FragmentPageAdapter(getActivity(), fragmentManager);
        pageAdapter.addFragment(SS4EmojiFragment.getInstance("set1"), "set1", R.drawable.b_ignore_sticker, "set1");
        //pageAdapter.addFragment(SS4EmojiFragment.getInstance("set2"), "null", android.R.drawable.star_off, "asdf");
        viewPager.setAdapter(pageAdapter);
    }

    public void setupTabLayout(TabLayout tabLayout, boolean isShow) {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pageAdapter.getTabView(i));
        }
        tabLayout.requestFocus();

        if (isShow)
            tabLayout.setVisibility(View.VISIBLE);
        else
            tabLayout.setVisibility(View.GONE);
    }

    ArrayList<String> defaultDataArray = new ArrayList<>();

    public void pickPhoto() {


        Intent intent = new Intent(mContext, SelectPhotosActivity.class);
// whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
// default select images (support array list)
        intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, defaultDataArray);
        startActivityForResult(intent, Constants.REQUEST_PICK_PHOTO);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Select Picture"), Constants.REQUEST_PICK_PHOTO);

//        PhotoPickerIntent intent = new PhotoPickerIntent(ChatActivity.this);
//        intent.setPhotoCount(9);
//        intent.setShowCamera(true);
//        intent.setShowGif(true);
//        startActivityForResult(intent, Constants.REQUEST_PICK_PHOTO);

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
//        // intent.putExtra("crop", "true");
//        intent.putExtra("scale", true);
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", Constants.PHOTO_SIZE_WIDTH);
//        intent.putExtra("outputY", Constants.PHOTO_SIZE_HEIGHT);
//        startActivityForResult(intent, Constants.REQUEST_PICK_PHOTO);
    }

    public void pickVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, Constants.REQUEST_PICK_VIDEO);
    }

    public void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, Constants.REQUEST_RECORD_VIDEO);
    }

    public boolean hasImageCaptureBug() {

        // list of known devices that have the bug
        ArrayList<String> devices = new ArrayList<String>();
        devices.add("android-devphone1/dream_devphone/dream");
        devices.add("generic/sdk/generic");
        devices.add("vodafone/vfpioneer/sapphire");
        devices.add("tmobile/kila/dream");
        devices.add("verizon/voles/sholes");
        devices.add("google_ion/google_ion/sapphire");

        return devices.contains(android.os.Build.BRAND + "/" + android.os.Build.PRODUCT + "/"
                + android.os.Build.DEVICE);

    }

    public void takePhoto() {
//        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        if (hasImageCaptureBug()) {
//            i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
//        } else {
//            i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        }
//        startActivityForResult(i, Constants.REQUEST_CAMERA);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        // intent.putExtra("crop", "true");
        startActivityForResult(intent, Constants.REQUEST_CAMERA);
    }

    Uri mCameraTempUri;

    private void doTakePhoto() {
        try {
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            mCameraTempUri = getActivity().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            takePhoto(Constants.REQUEST_CAMERA, mCameraTempUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takePhoto(int token, Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        }
        startActivityForResult(intent, token);
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
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

    public void onClickTitleEvent() {
        hideEverything();
        if (!isLayoutGroupMemberShow) {
            groupMemberLayout.setVisibility(View.VISIBLE);
            isLayoutGroupMemberShow = true;
        } else {
            groupMemberLayout.setVisibility(View.GONE);
            isLayoutGroupMemberShow = false;
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

    public void onClickStickerEventNew() {
        if (!isLayoutStickerShow) {
            //mKeyBoardPopWindow.showPopupWindow();
            isLayoutStickerShow = true;
        } else {
            //mKeyBoardPopWindow.dismiss();
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


//            senderObj.put("avatar", avatar);
//            senderObj.put("extension", ext);
            senderObj.put("extension", mAvatarUrl);

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
            jObj.put("senderId", mUserId);
            jObj.put("messageColorful", jObjColorful);
            jObj.put("dataString", jsonObjStr);
            jObj.put("timestamp", System.currentTimeMillis() / 1000L);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        // ตอนนี้ติด cId ArrayList ที่ return จาก api create/braodcast 
        if (mChatType == 3) {
            // test broadcast here
            // ถ้า emit ใน cid ที่เราไม่ได้ connect room แต่แรก จะเกิดอะไรขึ้น

            if (broadcastList == null) {
                broadcastList = new ArrayList<>();
            }

            if (broadcastList.size() == 0) {
                broadcastList.add(237);
            }

            int l = 0;
            if (l > 0) {
                for (int i = 0; i < broadcastList.size(); i++) {
                    try {
                        jObj.put("conversationId", i);
                        jObj.put("messageRoomType", "BROADCAST");
                        Log.e("jObj", jObj.toString(4));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mSocket.emit("SendMessage", jObj);
                    Log.e("emitting", "cId: " + i);

                }
            }


        } else {
            mSocket.emit("SendMessage", jObj);
        }


        setResult(RESULT_OK);
    }

    @Subscribe
    public void onSeeEvent(SeeEvent event) {
        adapter.see();
        adapter.notifyDataSetChanged();
        Log.e("SeeEvent", "SEE!!");
        HashMap<String, Integer> options = new HashMap<String, Integer>();
        buildChatApi().seeAll(mCid, options, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Log.e("SeeEvent", "SEE DB UPDATED!!");
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    boolean isMultiChat = false;

    @Subscribe
    public void onInterpretMessageEvent(InterpretMessageEvent event) {
        if (mChatType != 3)
            interpretTheMessage(event.mMessageListObject);
    }

    @Subscribe
    public void onTypingEvent(TypingEvent event) {
        // if (friendUsernameTv != null) {
        //friendUsernameTv.append(" ...");

//            JumpingBeans jumpingBeans2 = JumpingBeans.with(friendUsernameTv)
//                    .makeTextJump(0, friendUsernameTv.getText().toString().indexOf(' '))
//                    .setIsWave(false)
//                    .setLoopDuration(1000)  // ms
//                    .build();
        // }

    }

    @Subscribe
    public void onNotTypingEvent(NotTypingEvent event) {
        if (friendUsernameTv != null) {
            friendUsernameTv.setText(mPartnerUsername);

//            JumpingBeans jumpingBeans2 = JumpingBeans.with(friendUsernameTv)
//                    .makeTextJump(0, friendUsernameTv.getText().toString().indexOf(' '))
//                    .setIsWave(false)
//                    .setLoopDuration(1000)  // ms
//                    .build();
        }

    }

    public static Spanned interpretTheMessage(Activity activity, final JSONObject obj) {

        final TheMessageObject[] message = new TheMessageObject[1];

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data = obj;

                Gson gson = new GsonBuilder().create();
                Log.e("THEJSON:RECEIVED", data.toString());

                message[0] = gson.fromJson(data.toString(), TheMessageObject.class);// obj is your object
                message[0].state = Message.MSG_STATE_SUCCESS;

                String theStyle;
                int theSize;
                if (message[0].getMessageColorful() != null && message[0].getMessageColorful().size() != 0) {
                    for (TheMessageObject.MessageColorfulEntity ent : message[0].getMessageColorful()) {
                        if (ent.getStyle() != null) {
                            theStyle = ent.getStyle().getStyle().replace(".ttf", "").replace(".otf", "");
                            if (ent.getStyle().getSize().equals("16"))
                                theSize = 22;
                            else
                                theSize = Integer.parseInt(ent.getStyle().getSize()) + 6;

                            message[0].htmlStringColor += "<font face='" + theStyle + "' color ='#" + ent.getStyle().getColor() + "' size='" + theSize + "'>" + ent.getMessage() + "</font>";
                        }
                    }
                    //Log.e("htmlString555",message.htmlStringColor);
                    //message[0].spanned = CustomHtml.fromHtml(message[0].htmlStringColor);
                } else {
                    theStyle = "DEFAULT";
                    message[0].htmlStringColor = "<font face='" + theStyle + "' color ='#000000' size='22'>" + message[0].getMessage() + "</font>";
                    // message[0].spanned = CustomHtml.fromHtml(message[0].htmlStringColor);
                }


            }
        });

        return CustomHtml.fromHtml(message[0].htmlStringColor);
    }

    public void interpretTheMessage(final JSONObject obj) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data = obj;

                Gson gson = new GsonBuilder().create();
                Log.e("THEJSON:RECEIVED", data.toString());

                TheMessageObject message = gson.fromJson(data.toString(), TheMessageObject.class);// obj is your object
                message.state = Message.MSG_STATE_SUCCESS;

                String theStyle;
                int theSize;
                if (message.getMessageColorful() != null && message.getMessageColorful().size() != 0) {
                    for (TheMessageObject.MessageColorfulEntity ent : message.getMessageColorful()) {
                        if (ent.getStyle() != null) {
                            theStyle = ent.getStyle().getStyle().replace(".ttf", "").replace(".otf", "");
                            if (ent.getStyle().getSize().equals("16"))
                                theSize = 22;
                            else
                                theSize = Integer.parseInt(ent.getStyle().getSize()) + 6;

                            message.htmlStringColor += "<font face='" + theStyle + "' color ='#" + ent.getStyle().getColor() + "' size='" + theSize + "'>" + ent.getMessage() + "</font>";
                        }
                    }
                    //Log.e("htmlString555",message.htmlStringColor);
                    message.spanned = CustomHtml.fromHtml(message.htmlStringColor, fontLoader);
                } else {
                    theStyle = "DEFAULT";
                    message.htmlStringColor = "<font face='" + theStyle + "' color ='#000000' size='22'>" + message.getMessage() + "</font>";
                    message.spanned = CustomHtml.fromHtml(message.htmlStringColor, fontLoader);
                }

                message.onRight = mUserId == message.getSenderId() || mUserId == message.getSender().getId();


                if (!message.onRight) {
                    listMsgObj.add(message);
                    adapter.notifyDataSetChanged();
                    scrollToBottom();
                }

                if (message.messageType == Message.MSG_TYPE_PHOTO) {
                    listMsgObjPhoto.add(message);
                }
//                else {
//                    listMsgObj.add(message);
//                    adapter.notifyDataSetChanged();
//                    scrollToBottom();
//                }

                Log.e("message.onRight", message.onRight + "");

            }
        });
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
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

            if (requestCode == Constants.REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                Bitmap original = thumbnail;
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                ByteArrayOutputStream bytesOrigial = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                if (original != null) {
                    original.compress(Bitmap.CompressFormat.JPEG, 100, bytesOrigial);
                }

                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + "_thumb.jpg");

                File destinationOriginal = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");

                FileOutputStream fo;
                FileOutputStream fo2;
                try {
                    destination.createNewFile();
                    destinationOriginal.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo2 = new FileOutputStream(destinationOriginal);
                    fo.write(bytes.toByteArray());
                    fo2.write(bytesOrigial.toByteArray());
                    fo.close();
                    fo2.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


//                String path = f.getAbsolutePath();
//                Uri selectedImageUri = Uri.parse(path);
//                File file = imagePathToFile(selectedImageUri, path);

                TheMessageObject message = new TheMessageObject();

                message.localPhotoBitmap = original;

                message.onRight = true;
                message.state = Message.MSG_STATE_SENDING;
                message.setSenderId(mUserId);
                TheMessageObject.SenderEntity senderEntity = new TheMessageObject.SenderEntity();
                senderEntity.setSenderId(mUserId);
                senderEntity.setId(mUserId);
                senderEntity.setUsername(mUsername);
                senderEntity.setName(mName);
                senderEntity.setAvatar(mAvatarUrl);
                message.setSender(senderEntity);
                message.setMessage("");
                message.setMessageType(Message.MSG_TYPE_PHOTO);
                long unixTime = System.currentTimeMillis() / 1000L;
                message.setTimestamp(unixTime);
                //message.setId(safeLongToInt(System.currentTimeMillis()));
                //message.setData(list);
                //message.setDataString(dataJson);
                //message.setSender(new TheMessageObject.SenderEntity());

                listMsgObj.add(message);
                adapter.notifyDataSetChanged();

                //attemptSendMessageToServer(Message.MSG_TYPE_PHOTO, "", "[]", "[]");


                // Log.e("selectedImageUri", selectedImageUri + "");

                //String path = getRealPath(ChatActivity.this, urlPath);
                // TheMessageObject msgObj = new TheMessageObject();
                adapter.sendingMsgObj.put(unixTime, message);
                uploadFileRetrofit(destinationOriginal.getAbsoluteFile(), Message.MSG_TYPE_PHOTO, message);

//                String dataJson = "[]";
//                TheMessageObject msgObj = new TheMessageObject();
//                uploadFileRetrofit(file, Message.MSG_TYPE_PHOTO, msgObj);

            } else if (requestCode == Constants.REQUEST_PICK_PHOTO && null != data) {

                List<String> photos = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);

                //List<String> photos = null;


                if (data != null) {
                    //photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);

                    for (String urlPath : photos) {

                        //Uri selectedImageUri = data.getData();
//                        String[] projection = { MediaStore.MediaColumns.DATA };
//                        Cursor cursor = managedQuery(Uri.fromFile(new File(urlPath)), projection, null, null,
//                                null);
//                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//                        cursor.moveToFirst();

                        String selectedImagePath = urlPath;

//                        dataJson = "[{'url':'" + cb.getFull_path() + "'" +
//                                ",'full_path':'" + cb.getFull_path() + "'" +
//                                ",'fileName':'" + cb.getFileName() + "'" +
//                                ",'id':'" + cb.getId() + "'" +
//                                ",'active':'" + cb.getActive() + "'" +
//                                ",'thumb':'" + cb.getThumb() + "'" +
//                                ",'fileType':'" + cb.getFileType() + "'}]";

                        TheMessageObject message = new TheMessageObject();
                        message.onRight = true;
                        message.state = Message.MSG_STATE_SENDING;
                        message.setSenderId(mUserId);
                        TheMessageObject.SenderEntity senderEntity = new TheMessageObject.SenderEntity();
                        senderEntity.setSenderId(mUserId);
                        senderEntity.setId(mUserId);
                        senderEntity.setUsername(mUsername);
                        senderEntity.setName(mName);
                        senderEntity.setAvatar(mAvatarUrl);
                        message.setSender(senderEntity);
                        message.setMessage("");
                        message.setMessageType(Message.MSG_TYPE_PHOTO);
                        long unixTime = System.currentTimeMillis() / 1000L;
                        message.setTimestamp(unixTime);
                        //message.setId(safeLongToInt(System.currentTimeMillis()));
                        //message.setData(list);
                        //message.setDataString(dataJson);
                        //message.setSender(new TheMessageObject.SenderEntity());

                        listMsgObj.add(message);
                        adapter.notifyDataSetChanged();

                        //attemptSendMessageToServer(Message.MSG_TYPE_PHOTO, "", "[]", "[]");


                        // Log.e("selectedImageUri", selectedImageUri + "");

                        //String path = getRealPath(ChatActivity.this, urlPath);
                        message.localPhotoBitmap = getThumbBitmap(selectedImagePath);
                        File file = new File(selectedImagePath);
                        // TheMessageObject msgObj = new TheMessageObject();
                        adapter.sendingMsgObj.put(unixTime, message);

                        uploadFileRetrofit(file.getAbsoluteFile(), Message.MSG_TYPE_PHOTO, message);
                    }

                }

//                Uri selectedImageUri = data.getData();
//
//                Log.e("selectedImageUri", selectedImageUri + "");
//
//                String path = getRealPath(ChatActivity.this, selectedImageUri);
//                File file = imagePathToFile(selectedImageUri, path);
//
//                Log.e("file", file + "");
//
//                String dataJson = "[]";
//                TheMessageObject msgObj = new TheMessageObject();
//                uploadFileRetrofit(file, Message.MSG_TYPE_PHOTO, msgObj);


            } else if (requestCode == Constants.REQUEST_PICK_VIDEO) {

                mFileURI = data.getData();
                Log.e("mFileURI", mFileURI + "");
                if (mFileURI != null) {
                    String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                    String dataJson = "";
                    if (vdoThumb != null)
                        dataJson = "[{'thumb':'" + vdoThumb + "'}]";

                    TheMessageObject message = new TheMessageObject();
                    message.clipUri = mFileURI;
                    message.setDataString(dataJson);
                    message.onRight = true;
                    message.state = Message.MSG_STATE_SENDING;

                    String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                    File clip = new File(path);
                    Log.e("Clip", clip + "");
                    uploadFileRetrofit(clip, Message.MSG_TYPE_CLIP, message);

                }

            } else if (requestCode == Constants.REQUEST_RECORD_VIDEO) {

                mFileURI = data.getData();
                Log.e("mFileURI", mFileURI + "");

                if (mFileURI != null) {
                    String vdoThumb = ChatUtil.getThumbnailPathForLocalFile(getActivity(), mFileURI);
                    String dataJson = "";
                    if (vdoThumb != null)
                        dataJson = "[{'thumb':'" + vdoThumb + "'}]";

                    TheMessageObject msgObj = new TheMessageObject();
                    //listMessages.add(sendingMessage);

                    String path = ChatUtil.getRealPathFromURIVideo(getActivity(), mFileURI);
                    File clip = new File(path);
                    uploadFileRetrofit(clip, Message.MSG_TYPE_CLIP, msgObj);


                }

            } else if (requestCode == Constants.RESULT_PLACE_PICKER) {

                Place place = PlacePicker.getPlace(data, getActivity());
                LatLng latLng = place.getLatLng();

                Double lat = latLng.latitude;
                Double lon = latLng.longitude;
                String locationName = place.getName() + "";
                String address = place.getAddress() + "";
                //Location location = data.getParcelableExtra("LOCATION");

                String mapImage = "https://maps.googleapis.com/maps/api/staticmap?zoom=13&size=600x400&maptype=roadmap&markers=color:blue" + "%7C" + lat + "," + lon;

                //{"cityName":"??????","regionName":"??????","latitude":"13.837663","longtitude":"100.616730"}

                String dataJson = "[{'cityName':'" + locationName + "'" +
                        ",'imageUrl':'" + mapImage + "'" +
                        ",'regionName':'" + address + "'" +
                        ",'latitude':" + lat + "" +
                        ",'longtitude':" + lon + "}]";

                Log.e("sendDataJson", dataJson);

                TheMessageObject message = new TheMessageObject();
                message.onRight = true;
                message.state = Message.MSG_STATE_SENDING;
                message.setSenderId(mUserId);
                TheMessageObject.SenderEntity senderEntity = new TheMessageObject.SenderEntity();
                senderEntity.setSenderId(mUserId);
                senderEntity.setId(mUserId);
                senderEntity.setUsername(mUsername);
                senderEntity.setName(mName);
                senderEntity.setAvatar(mAvatarUrl);
                message.setSender(senderEntity);
                message.setMessage("");
                message.setMessageType(Message.MSG_TYPE_LOCATION);
                long unixTime = System.currentTimeMillis() / 1000L;
                message.setTimestamp(unixTime);
                //message.setData(list);
                message.setDataString(dataJson);
                //message.setSender(new TheMessageObject.SenderEntity());

                listMsgObj.add(message);
                adapter.notifyDataSetChanged();

                attemptSendMessageToServer(Message.MSG_TYPE_LOCATION, "", dataJson, "[]");

            } else if (requestCode == 701) {
                Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
            } else if (requestCode == Constants.REQUEST_CONTACT_PICKER) {

                String friendName = data.getStringExtra("NAME");
                String friendUsername = data.getStringExtra("USERNAME");
                String friendUserId = data.getStringExtra("USER_ID");
                String friendAvatar = data.getStringExtra("AVATAR");
                String friendAvatarFullPath = data.getStringExtra("AVATAR_FULL_PATH");
                String friendAbout = data.getStringExtra("ABOUT");
                String friendEmail = "debug@gmail.com";
                //UserModel selectedFriend = Parcels.unwrap(data.getParcelableExtra("friend"));

                Toast.makeText(this, friendName, Toast.LENGTH_SHORT).show();

//                {
//                    "about": "aaaa",
//                        "avatar_url": "photos/2016/02/jGiTD_365_9be40cee5b0eee1462c82c6964087ff9_100x100.jpeg",
//                        "id": 2,
//                        "name": "Thananon Ngoenthaworn",
//                        "username": "korrio"
//                }

                //Gson gson = new GsonBuilder().create();
//                String dataJson = "[{'url':'" + friendAvatar + "'" +
//                        ",'full_path':'" + friendAvatar + "'" +
//                        ",'friendName':'" + friendName + "'" +
//                        ",'id':'" + friendUserId + "'" +
//                        ",'active':'" + "1" + "'" +
//                        ",'thumb':'" + friendAvatar + "'" +
//                        ",'fileType':'" + "contact" + "'}]";
                String dataJson = "[{'url':'" + friendAvatarFullPath + "'" +
                        ",'full_path':'" + friendAvatarFullPath + "'" +
                        ",'avatar_url':'" + friendAvatar + "'" +
                        ",'name':'" + friendName + "'" +
                        ",'about':'" + friendAbout + "'" +
                        ",'id':" + friendUserId + "" +
                        ",'email':" + friendEmail + "" +
                        ",'phone':" + friendEmail + "" +
                        ",'password':" + friendEmail + "" +
                        ",'avatar':" + friendAvatar + "" +
                        ",'cover':" + friendAvatar + "" +

                        ",'username':'" + friendUsername + "'}]";
                //String dataObjStr = gson.toJson(selectedFriend, UserModel.class);// obj is your object
                //String dataArray = "[" + dataObjStr + "]";
                //Log.d("contactSelected",dataObjStr);

                TheMessageObject message = new TheMessageObject();
                message.onRight = true;
                message.state = Message.MSG_STATE_SUCCESS;

                message.setSenderId(mUserId);
                TheMessageObject.SenderEntity senderEntity = new TheMessageObject.SenderEntity();
                senderEntity.setSenderId(mUserId);
                senderEntity.setId(mUserId);
                senderEntity.setUsername(mUsername);
                senderEntity.setName(mName);
                senderEntity.setAvatar(mAvatarUrl);
                message.setSender(senderEntity);

                List<TheMessageObject.DataEntity> list = new ArrayList<TheMessageObject.DataEntity>();
                list.add(new TheMessageObject.DataEntity());
                message.setMessage("");
                message.setMessageType(Message.MSG_TYPE_CONTACT);
                message.setData(list);
                message.setDataString(dataJson);
                long unixTime = System.currentTimeMillis() / 1000L;
                message.setTimestamp(unixTime);
                //message.setSender(new TheMessageObject.SenderEntity());
                listMsgObj.add(message);
                adapter.notifyDataSetChanged();

                attemptSendMessageToServer(Message.MSG_TYPE_CONTACT, "", dataJson, "[]");
            }
            //
            else if (requestCode == Constants.REQUEST_VOICE_RECORDER) {
                String name = data.getStringExtra("data");

                if (name != null) {
                    File clip = new File(name);
                    Log.e("Voice555", clip + "");

                    TheMessageObject msgObj = new TheMessageObject();
                    uploadFileRetrofit(clip, Message.MSG_TYPE_VOICE, msgObj);
                }

            } else if (requestCode == Constants.REQUEST_VOICE_RECORDER_NATIVE) {
                Uri audioUri = data.getData();

                if (audioUri != null) {
                    File clip = new File(getRealPath(mContext, audioUri));
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            return ChatUtil.getRealPathFromURIForKitKat(context, mFileURI);
//        } else {
        return ChatUtil.getRealPathFromURI(context, mFileURI);
        // }
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
                Log.e("getUserInfoServiceError", "failure");
            }
        });
    }

    public void fetchChatHistory(int cid, int page, int size) {
        Log.e("currentHistoryPage", currentHistoryPage + "");
        TheChatApiService service = buildChatApi();
        HashMap<String, Integer> option = new HashMap<String, Integer>();
        option.put("page", page);
        option.put("size", size);

        service.getTheHistory(cid, option, new Callback<TheHistoryDataResponse>() {
            @Override
            public void success(TheHistoryDataResponse historyDataResponse, Response response) {
                if (historyDataResponse.content.size() > 0)
                    interpretChatHistory(historyDataResponse.content);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("fetchChatHistoryUrl", error.getUrl());
                Log.e("fetchChatHistory", "error:" + error.getLocalizedMessage());
            }
        });
    }


    public void getTopic(int userId, int cid) {
        TheChatApiService service = buildChatApi();
        service.getTopicGriupChat(userId,cid, new Callback<RelationsTopic>() {
            @Override
            public void success(RelationsTopic relationsGroup, Response response) {
//                Log.e("Topic_ffgg",relationsGroup.getContent().get(0).getName());
                ApiBus.getInstance().postQueue(new GetTopicSuccess(relationsGroup));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error_Topic",error.getUrl());
//                Log.e("Error_Topic",error.getLocalizedMessage());
            }
        });
    }





    public void getChatInfoSelf(int userId) {
        TheChatApiService service = buildChatApi();

        service.getSelfConversation(userId, new Callback<ChatInfo>() {
            @Override
            public void success(ChatInfo chatInfo, Response response) {
                if (chatInfo != null) {
                    ApiBus.getInstance().postQueue(new GetChatInfoSuccess(chatInfo));
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }





    public void getChatInfo(int cId) {
        Log.e("OKOKOK",cId+"");
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

    int currentHistoryPage = 1;
    int historyPageSize = 20;

    private void getChatInfoTo(int userId, int partnerId) {
        TheChatApiService service = buildChatApi();
        service.getChatByTo(userId, partnerId, new Callback<ChatInfoTo>() {
            @Override
            public void success(ChatInfoTo chatInfoTo, Response response) {
                if (chatInfoTo != null) {

                    mCid = chatInfoTo.getId();
                    fetchChatHistory(mCid, currentHistoryPage, historyPageSize);

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

    // url = file path or whatever suitable URL you want.
    public static String getMimeTypeFromUrl(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    private String getMimeType(String imagePath) {
        Uri uri = Uri.fromFile(new File(imagePath));
        ContentResolver cR = mContext.getContentResolver();
        String mime = cR.getType(uri);
        return mime;
    }

    private String getMimeType(Uri uri) {
        String mimeType = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = mContext.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }


    private TypedFile getTypedFile(String imagePath) {
        File photoFile = new File(imagePath);// image will be your real path
        String mimeType = getMimeType(Uri.fromFile(new File(imagePath)));
        TypedFile photoTypedFile;
        if (mimeType != null) {
            photoTypedFile = new TypedFile(mimeType, photoFile);
        } else {
            photoTypedFile = new TypedFile("image/jpg", photoFile);
        }
        return photoTypedFile;
    }


    private void uploadFileRetrofit(File file, final int msgType, final TheMessageObject message) {
        MainApiService service = buildMainApi();
        //TypedFile typedFile = new TypedFile("multipart/form-data", file);
        TypedFile typedFile = getTypedFile(file.getAbsolutePath());
        String description = getMimeType(file.getAbsolutePath());

        Log.e("typedFileAbsolute", file.getAbsolutePath());
        //Toast.makeText(this,file.getAbsolutePath(),Toast.LENGTH_LONG).show();
        Log.e("typedFile", typedFile.fileName());

        service.upload(typedFile, description, new Callback<UploadCallback>() {
            @Override
            public void success(UploadCallback cb, Response response) {
                Log.e("Upload", "success " + cb.toString());
                String dataJson;
                int theType;
                if (cb.getFileType() != null) {
                    if (cb.getFileType().equals("image/jpeg") || cb.getFileType().equals("image/png") || cb.getFileType().equals("image/gif")) {
                        dataJson = "[{'url':'" + cb.getFull_path() + "'" +
                                ",'full_path':'" + cb.getFull_path() + "'" +
                                ",'fileName':'" + cb.getFileName() + "'" +
                                ",'id':'" + cb.getId() + "'" +
                                ",'active':'" + cb.getActive() + "'" +
                                ",'thumb':'" + cb.getThumb() + "'" +
                                ",'fileType':'" + cb.getFileType() + "'}]";
                        theType = Message.MSG_TYPE_PHOTO;

                        if (message.messageType == Message.MSG_TYPE_PHOTO) {
                            listMsgObjPhoto.add(message);
                        }
                    } else if (cb.getFileType().equals("video/mp4")
                        //|| cb.getFileType().equals("video/3gpp")
                            ) {
                        dataJson = "[{'url':'" + cb.getFull_path() + "'" +
                                ",'full_path':'" + cb.getFull_path() + "'" +
                                ",'fileName':'" + cb.getFileName() + "'" +
                                ",'id':'" + cb.getId() + "'" +
                                ",'active':'" + cb.getActive() + "'" +
                                ",'thumb':'" + cb.getThumb() + "'" +
                                ",'fileType':'" + cb.getFileType() + "'}]";
                        theType = Message.MSG_TYPE_CLIP;
                    } else {
                        dataJson = "[{'url':'" + cb.getFull_path() + "'" +
                                ",'full_path':'" + cb.getFull_path() + "'" +
                                ",'fileName':'" + cb.getFileName() + "'" +
                                ",'id':'" + cb.getId() + "'" +
                                ",'active':'" + cb.getActive() + "'" +
                                ",'thumb':'" + cb.getThumb() + "'" +
                                ",'fileType':'" + cb.getFileType() + "'}]";
                        theType = Message.MSG_TYPE_VOICE;
                    }

                    if (theType == Message.MSG_TYPE_PHOTO) {
                        message.onRight = true;
                        message.state = Message.MSG_STATE_SUCCESS;

                        List<TheMessageObject.DataEntity> list = new ArrayList<TheMessageObject.DataEntity>();
                        list.add(cb);

                        message.setSenderId(mUserId);
                        TheMessageObject.SenderEntity senderEntity = new TheMessageObject.SenderEntity();
                        senderEntity.setSenderId(mUserId);
                        senderEntity.setId(mUserId);
                        senderEntity.setUsername(mUsername);
                        senderEntity.setName(mName);
                        senderEntity.setAvatar(mAvatarUrl);
                        message.setSender(senderEntity);
                        message.setMessage("");
                        message.setMessageType(theType);
                        message.setData(list);
                        message.setDataString(dataJson);
                        //long unixTime = System.currentTimeMillis() / 1000L;
                        //message.setTimestamp(unixTime);
                        adapter.notifyDataSetChanged();
                        adapter.sendingMsgObj.remove(message);

                        Log.e("SENDING_PENDING", adapter.sendingMsgObj.size() + "");

                        attemptSendMessageToServer(msgType, "", dataJson, "[]");


                    } else {
                        message.onRight = true;
                        message.state = Message.MSG_STATE_SUCCESS;

                        List<TheMessageObject.DataEntity> list = new ArrayList<TheMessageObject.DataEntity>();
                        list.add(cb);

                        message.setSenderId(mUserId);
                        TheMessageObject.SenderEntity senderEntity = new TheMessageObject.SenderEntity();
                        senderEntity.setSenderId(mUserId);
                        senderEntity.setId(mUserId);
                        senderEntity.setUsername(mUsername);
                        senderEntity.setName(mName);
                        senderEntity.setAvatar(mAvatarUrl);
                        message.setSender(senderEntity);
                        message.setMessage("");
                        message.setMessageType(theType);
                        message.setData(list);
                        message.setDataString(dataJson);
                        long unixTime = System.currentTimeMillis() / 1000L;
                        message.setTimestamp(unixTime);

                        listMsgObj.add(message);
                        adapter.notifyDataSetChanged();

                        attemptSendMessageToServer(msgType, "", dataJson, "[]");
                    }


                } else {
                    Toast.makeText(getActivity(), "Upload is not success, try again", Toast.LENGTH_SHORT).show();
                    message.state = Message.MSG_STATE_FAIL;
                }
            }

            @Override
            public void failure(RetrofitError error) {

                Log.e("Upload", "error " + error);
                Log.e("Upload", "error " + error.getLocalizedMessage());
                Log.e("Upload", "error " + error.getLocalizedMessage());

            }
        });
    }

    public static MainApiService buildMainApi() {
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

    public static TheChatApiService buildChatApi() {
        String BASE_URL = "http://candychat.net:1313";

//        Gson gson = new GsonBuilder()
//                .setExclusionStrategies(new ExclusionStrategy() {
//                    @Override
//                    public boolean shouldSkipField(FieldAttributes f) {
//                        return f.getDeclaringClass().equals(RealmObject.class);
//                    }
//
//                    @Override
//                    public boolean shouldSkipClass(Class<?> clazz) {
//                        return false;
//                    }
//                })
//                .create();

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                // .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                    }
                })
                .build()
                .create(TheChatApiService.class);
    }


    public static TheTopicApiService buildTopicApi() {
        String BASE_URL = "http://api.candychat.net";

//        Gson gson = new GsonBuilder()
//                .setExclusionStrategies(new ExclusionStrategy() {
//                    @Override
//                    public boolean shouldSkipField(FieldAttributes f) {
//                        return f.getDeclaringClass().equals(RealmObject.class);
//                    }
//
//                    @Override
//                    public boolean shouldSkipClass(Class<?> clazz) {
//                        return false;
//                    }
//                })
//                .create();

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                // .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                    }
                })
                .build()
                .create(TheTopicApiService.class);
    }


    Bitmap getThumbBitmap(String path) {

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;

        //ivImage.setImageBitmap(bm);
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

//    File imagePathToFile2(Uri selectedImageUri, String path) {
//
//        File sd = Environment.getExternalStorageDirectory();
//        File image = new File(sd+path);
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
//        Bitmap bm = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
//
//
//        bm.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
//        OutputStream fOut = null;
//        File file = new File(path);
//        try {
//            fOut = new FileOutputStream(file);
//            bm.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
//            fOut.flush();
//            fOut.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return file;
//    }

    List<TheMessageObject> theHistoryList;

    private void interpretChatHistory(List<TheMessageObject> msgObjs) {


        //Collections.reverse(msgObjs);
        // = new ArrayList<>();
        for (int i = 0; i < msgObjs.size(); i++) {

            TheMessageObject message = msgObjs.get(i);
            message.onRight = mUserId == message.getSenderId() || mUserId == message.getSender().getId();
            message.htmlStringColor = "";
            message.state = Message.MSG_STATE_SUCCESS;
//            if(message.getMessageType() == 0)
//                message.setType(TheMessageObject.Type.TXT);
//            else if(message.getMessageType() == 1)
//                message.setType(TheMessageObject.Type.STICKER);
//            else if(message.getMessageType() == 2)
//                message.setType(TheMessageObject.Type.IMAGE);
//            else if(message.getMessageType() == 3)
//                message.setType(TheMessageObject.Type.VIDEO);
//            else if(message.getMessageType() == 4)
//                message.setType(TheMessageObject.Type.LOCATION);
//            else if(message.getMessageType() == 5)
//                message.setType(TheMessageObject.Type.CONTACT);
//            else if(message.getMessageType() == 6)
//                message.setType(TheMessageObject.Type.VOICE);
//            else if(message.getMessageType() == 9)
//                message.setType(TheMessageObject.Type.CMD);

            String theStyle = "";
            if (message.getMessageType() == 0) {
                if (message.getMessageColorful() != null && message.getMessageColorful().size() != 0) {
                    for (TheMessageObject.MessageColorfulEntity ent : message.getMessageColorful()) {
                        if (ent.getStyle() != null) {
                            if (ent.getStyle().getStyle().equals(".SFUIText-Regular"))
                                theStyle = "DEFAULT";
                            else
                                theStyle = ent.getStyle().getStyle().replace(".ttf", "").replace(".otf", "");
                            message.htmlStringColor += "<font face='" + theStyle + "' color ='#" + ent.getStyle().getColor() + "' size='24'>" + ent.getMessage() + "</font>";
                        }

                    }
                    //Log.e("htmlString555",message.htmlStringColor);
                    message.spanned = CustomHtml.fromHtml(message.htmlStringColor, fontLoader);
                } else {
                    theStyle = "DEFAULT";
                    message.htmlStringColor = "<font face='" + theStyle + "' color ='#000000' size='24'>" + message.getMessage() + "</font>";
                    message.spanned = CustomHtml.fromHtml(message.htmlStringColor, fontLoader);
                }
            }

            //if (message.getMessageType() == 0) {
            listMsgObj.add(0, message);
            //}

        }

        if (msgObjs.size() != 0) {
            // 刷新ui
            adapter.notifyDataSetChanged();
            chatListView.post(new Runnable() {
                @Override
                public void run() {
                    chatListView.setSelection(listMsgObj.size() - 1);
                }
            });

            if (msgObjs.size() != historyPageSize)
                haveMoreData = false;
        } else {
            haveMoreData = false;
        }
        //loadmorePB.setVisibility(View.GONE);
        isloading = false;

//        // save index and top position
//        final int index = chatListView.getFirstVisiblePosition();
//        View v = chatListView.getChildAt(0);
//        final int bottom = (v == null) ? 0 : (v.getBottom() - chatListView.getListPaddingBottom());

// notify dataset changed or re-assign adapter here
        //adapter.notifyDataSetChanged();
// restore the position of listview
        //chatListView.setSelectionFromTop(index, top);

        //chatListView.setSelection(listMsgObj.size() - 1);
//        chatListView.clearFocus();
//        chatListView.post(new Runnable() {
//            @Override
//            public void run() {
//                //chatListView.setSelection(index);
//                chatListView.setSelectionFromTop(index, bottom);
//               ;
//            }
//        });

        //if(!isScroll)
        //scrollToBottom();

        isScroll = true;

        Log.e("theHistorySize", msgObjs.size() + "");

        RealmManager.getInstance().resetRealm(this);
        Realm realm = RealmManager.getInstance().initRealm(this);
        RealmManager.getInstance().loadDataToRealm(realm, msgObjs);
        theHistoryList = msgObjs;

    }

    boolean isScroll = false;

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

                if (mChatType == 0) {

                    friendAvatarIv = (ImageView) toolbar.findViewById(R.id.avatar);
                    friendNameTv = (TextView) toolbar.findViewById(R.id.title);
                    friendUsernameTv = (TextView) toolbar.findViewById(R.id.sub_title);

                    friendNameTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = null;
                            try {
                                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity.timeline.ChatInfoActivity"));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            i.putExtra("id", mUserId);
                            i.putExtra("fid", mPartnerId);
                            i.putExtra("cid", mCid);
                            i.putExtra("type", "1");
                            if (mChatType == 2) {
                                String theName = (mName.equals("")) ? mUsername : mName;
                                i.putExtra("nameMe", mGroupName);
                                i.putExtra("imageMe", mAvatarUrl);
                                i.putExtra("type", "1");
                            } else {
                                String theName = (mName.equals("")) ? mUsername : mName;
                                i.putExtra("nameMe", theName);
                                i.putExtra("imageMe", mAvatarUrl);
                                i.putExtra("type", "1");
                            }
                            startActivity(i);
                        }
                    });
                    friendAvatarIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            emojiPopup.toggle();
                            // onClickTitleEvent();
                        }
                    });

                    if (!mPartnerName.equals("") && mPartnerName != null) {

                        Picasso.with(getActivity()).load(mPartnerAvatarUrl).resize(200, 200)
                                .transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);
                        friendNameTv.setText(mPartnerName);
                        toolbar.setTitle(mPartnerName);

                        String myString = " \uD83C\uDFC1";
                        friendUsernameTv.setText(mPartnerUsername + "" + AndroidEmoji.ensure(myString, this));
                    } else {

                        Picasso.with(getActivity()).load(mPartnerAvatarUrl).resize(200, 200)
                                .transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);
                        friendNameTv.setText(mPartnerUsername);
                        toolbar.setTitle(mPartnerUsername);

                        friendUsernameTv.setVisibility(View.GONE);

//                        String myString = " \uD83C\uDFC1";
//                        friendUsernameTv.setText(mPartnerUsername + "" + AndroidEmoji.ensure(myString,this));
                    }

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

//            mPref.isLogin().put(true)
//            .userId().put(event.user.id)
//            .username().put(event.user.username)
//            .name().put(event.user.name)
//                    .email().put(event.user.email)
//            .commit();
        }

    }

    private String mGroupName = "";

    @Subscribe
    public void onGetChatInfoSuccess(GetChatInfoSuccess event) {
        List<ChatInfo.ConversationMembersEntity> conversationMembers = event.info.getConversationMembers();
        ArrayList<ChatInfo.ConversationMembersEntity> conversationMembersArrayList = new ArrayList<>(conversationMembers);
        initGroupMembersListView(conversationMembersArrayList);

        if (conversationMembers != null)
            for (int i = 0; i < conversationMembers.size(); i++) {
                getUserInfoService(conversationMembers.get(i).getUserId());
                ChatInfo.ConversationMembersEntity member = conversationMembers.get(i);
                listTopic.add(member);


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


                } else {
                    mName = member.getName();
                    mUsername = member.getUsername();
                    mAvatarUrl = EndpointManager.getAvatarPath(member.getAvatar() + "." + member.getExtension());

                }

                if (toolbar != null && event.info != null) {
                    toolbar.setTitle(event.info.getName());
                    friendAvatarIv = (ImageView) toolbar.findViewById(R.id.avatar);
                    friendNameTv = (TextView) toolbar.findViewById(R.id.title);
                    friendNameTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = null;
                            try {
                                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity.timeline.ChatInfoActivity"));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            i.putExtra("id", mUserId);
                            i.putExtra("fid", mPartnerId);
                            i.putExtra("cid", mCid);
                            if (mChatType == 2) {
                                String theName = (mName.equals("")) ? mUsername : mName;
                                i.putExtra("nameMe", mGroupName);
                                i.putExtra("imageMe", mAvatarUrl);
                            } else {
                                String theName = (mName.equals("")) ? mUsername : mName;
                                i.putExtra("nameMe", theName);
                                i.putExtra("imageMe", mAvatarUrl);
                            }
                            startActivity(i);
                        }
                    });
                    friendAvatarIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickTitleEvent();
                        }
                    });
                    friendUsernameTv = (TextView) toolbar.findViewById(R.id.sub_title);

                    mGroupName = event.info.getName();
                    friendNameTv.setText(event.info.getName());
                    if (mChatType == 2) {
                        //group chat
                        String groupAvatarUrl = "http://chat.candychat.net" + "" + event.info.getAvatar();
                        if (!groupAvatarUrl.equals(""))
                            Picasso.with(getActivity()).load(groupAvatarUrl).resize(200, 200)
                                    .transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);

                        friendUsernameTv.setText(conversationMembers.size() + " members");
                    } else if (mChatType == 1) {
                        //self chat
                        //http://chat.candychat.net/config_uploads/default/no_group_avatar.jpg
                        String yourAvatarUrl = "http://candychat.net" + "/" + event.info.getAvatar();
                        if (!yourAvatarUrl.equals(""))
                            Picasso.with(getActivity()).load(yourAvatarUrl).resize(200, 200)
                                    .transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);
                        friendUsernameTv.setText(mPartnerUsername);
                    }

                }
            }

        // get History Here
        fetchChatHistory(event.info.getConversationId(), currentHistoryPage, historyPageSize);

    }

    ArrayList<RelationsTopic.ContentEntity> list = new ArrayList<>();
    TopicListViewAdapter topicGroupAdapter;

    @Subscribe
    public void onGetTopicSuccess(GetTopicSuccess event) {
        if (event != null) {
           // Toast.makeText(getApplicationContext(),event.info.getContent()+": ทดสอบ",Toast.LENGTH_LONG).show();

            for (int i = 0; i < event.info.getContent().size(); i++) {
                list.add(i, event.info.getContent().get(i));

                Log.e("bbbbbbb", event.info.getContent().get(i).getName());
                topicGroupAdapter = new TopicListViewAdapter(getActivity(), list);
                listView2.setAdapter(topicGroupAdapter);
                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                        i.putExtra("USER_ID_1", mUserId);
                        i.putExtra("USER_ID_2", mPartnerId);
                        i.putExtra("CHAT_TYPE", 2);
                        i.putExtra("CONVERSATION_ID", list.get(position).getId());

                        startActivity(i);
                    }
                });

            }


        }
    }

    private void initGroupMembersListView(final ArrayList<ChatInfo.ConversationMembersEntity> memberList) {
        memberListView = (HListView) findViewById(R.id.hListView2);
        adapterMemberListView = new GroupMemberAdapter(this, memberList);
        myRecyclerListTopicAdapter = new MyRecyclerListTopicAdapter(this, memberList);
        memberListView.setAdapter(adapterMemberListView);

        memberListView.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                Log.e("memberList",memberList.get(position).getName());
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

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
        Log.e("isScrollDown", isScrollDown + "");
    }

    @Override
    public void resetCountUpAnimation() {

    }

    @Override
    public void clickOnItem(String i) {
        Log.e("clickOnItem", i);

        String jsonObjStr = "[{'tattooUri':'" + i + "'}]";
        TheMessageObject message = new TheMessageObject();
        message.onRight = true;
        message.setMessage("");
        message.setMessageType(Message.MSG_TYPE_STICKER);
        message.setDataString(jsonObjStr);
        long unixTime = System.currentTimeMillis() / 1000L;
        message.setTimestamp(unixTime);
        listMsgObj.add(message);
        adapter.notifyDataSetChanged();

        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, i, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }


    @Override
    public void clickOnItem2(String pathset2) {
        String jsonObjStr = "[{'tattooUri':'" + pathset2 + "'}]";
        TheMessageObject message = new TheMessageObject();
        message.onRight = true;
        message.setMessage("");
        message.setMessageType(Message.MSG_TYPE_STICKER);
        message.setDataString(jsonObjStr);
        long unixTime = System.currentTimeMillis() / 1000L;
        message.setTimestamp(unixTime);
        listMsgObj.add(message);
        adapter.notifyDataSetChanged();

        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, pathset2, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }


    @Override
    public void clickOnItem3(String ii) {
        String jsonObjStr = "[{'tattooUri':'" + ii + "'}]";
        TheMessageObject message = new TheMessageObject();
        message.onRight = true;
        message.setMessage("");
        message.setMessageType(Message.MSG_TYPE_STICKER);
        message.setDataString(jsonObjStr);
        long unixTime = System.currentTimeMillis() / 1000L;
        message.setTimestamp(unixTime);
        listMsgObj.add(message);
        adapter.notifyDataSetChanged();

        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, ii, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }

    @Override
    public void clickOnItem4(String pt) {
        String jsonObjStr = "[{'tattooUri':'" + pt + "'}]";
        TheMessageObject message = new TheMessageObject();
        message.onRight = true;
        message.setMessage("");
        message.setMessageType(Message.MSG_TYPE_STICKER);
        message.setDataString(jsonObjStr);
        long unixTime = System.currentTimeMillis() / 1000L;
        message.setTimestamp(unixTime);
        listMsgObj.add(message);
        adapter.notifyDataSetChanged();

        scrollToBottom();
        attemptSendMessageToServer(Message.MSG_TYPE_STICKER, pt, jsonObjStr, "[]");
        holder.setVisibility(View.GONE);
        isLayoutStickerShow = false;
    }


    @Override
    public void clickOnItem5(String pt) {
        chatEditText.setText(pt + " ");
//        TheMessageObject msgObj = new TheMessageObject();
//        msgObj.onRight = true;
//        msgObj.setMessage(pt);
//        msgObj.setMessageType(Message.MSG_TYPE_TEXT);
//        .add(msgObj);
//        scrollToBottom();
//        attemptSendMessageToServer(Message.MSG_TYPE_TEXT, pt, "[]", "[]");
    }

    public static String getText(Element cell) {
        String text = null;
        List<Node> childNodes = cell.childNodes();
        if (childNodes.size() > 0) {
            Node childNode = childNodes.get(0);
            if (childNode instanceof TextNode) {
                text = ((TextNode) childNode).getWholeText();
            }
        }
        if (text == null) {
            text = cell.text();
        }
        return text;
    }

    boolean isNotiOff = false;

    @Override
    public void onClick(View view) {

        int id = view.getId();
        Log.e("clicked", id + "");
        if (id == R.id.enter_chat1) {
            Log.e("clicked", "enter_chat1");

            //TODO: PERFECT MESSAGE OBJECT
            //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            //@Expose private Long id;

            final String theChatMessage = chatEditText.getStringText();

            if (theChatMessage.equals("") || theChatMessage == null)
                return;

            isLayoutColorfulShow = false;
            isLayoutAttachmentShow = false;
            customFontlayout.setVisibility(View.GONE);
            chatAttachmentLayout.setVisibility(View.GONE);

            runOnUiThread(new Runnable() {
                public void run() {
                    toHtml = TheHtml.toHtml(chatEditText.getSpannedTextOrigin());
                }
            });

            TheMessageObject.SenderEntity sender = new TheMessageObject.SenderEntity();
            message = new TheMessageObject();
            message.onRight = true;
            message.setMessage(theChatMessage);
            message.setMessageType(Message.MSG_TYPE_TEXT);
            message.spanned = chatEditText.getSpannedText();
            message.htmlStringColor = toHtml;
            message.setSender(sender);
            long unixTime = System.currentTimeMillis() / 1000L;
            message.setTimestamp(unixTime);

            listMsgObj.add(message);
            adapter.notifyDataSetChanged();

            scrollToBottom();
            chatEditText.setText("");
            chatEditText.setTextSize(currentFontSize);

            // colorful processing
            List<TheMessageObject.MessageColorfulEntity> theColorfulList = new ArrayList<TheMessageObject.MessageColorfulEntity>();
            String theHtml2 = toHtml;

            chatEditText.setText("");

            theHtml2 = theHtml2.replace("><font", "").replace("</font></font color", "</font><font color").replace("</font></font face", "</font><font face");

            //Log.e("theHtml2",theHtml2);

            Document doc = Jsoup.parse(theHtml2);
            Elements links = doc.getElementsByTag("font");

            for (Element link : links) {
                String color = link.attr("color").replace("#", "");

                String font = link.attr("face");
                String size = "16";
                if (font.contains("DEFAULT"))
                    size = "16";
                else
                    size = "24";

                //TheElement theLink = ((TheElement)link);
                String message = getText(link);

                //Log.e("link",link.toString());

                //theChatMessage += message;

                TheMessageObject.MessageColorfulEntity messageColorfulEntity = new TheMessageObject.MessageColorfulEntity();
                TheMessageObject.MessageColorfulEntity.StyleEntity styleEntity = new TheMessageObject.MessageColorfulEntity.StyleEntity();

                messageColorfulEntity.setMessage(message);

                //TODO: mak mak
                styleEntity.setLocale("");
                styleEntity.setStyle(font);
                styleEntity.setSize(size);
                styleEntity.setColor(color);

                messageColorfulEntity.setStyle(styleEntity);
                theColorfulList.add(messageColorfulEntity);

            }

            message.setMessage(theChatMessage);
            message.setMessageColorful(theColorfulList);


            Gson gson = new GsonBuilder().create();
            String msgObjStr = gson.toJson(message.getMessageColorful());// obj is your object

            attemptSendMessageToServer(Message.MSG_TYPE_TEXT, theChatMessage, "[]", msgObjStr.toString());

        } else if (id == R.id.enter_camera) {
            Log.e("clicked", "enter_camera");
            takePhoto();
            hideEverything();

        } else if (id == R.id.txt_take_photo) {
            takePhoto();
            hideEverything();
        } else if (id == R.id.txt_gallery) {
            pickPhoto();
            hideEverything();
        } else if (id == R.id.txt_video) {
            buildVideoDialog();
            hideEverything();
        } else if (id == R.id.txt_location) {
            try {
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                Intent intent = intentBuilder.build(getActivity());
                startActivityForResult(intent, Constants.RESULT_PLACE_PICKER);
            } catch (GooglePlayServicesRepairableException e) {
                GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), getActivity(), 0);
                Toast.makeText(getActivity(), "Google Play Services is not available.", Toast.LENGTH_LONG).show();
            } catch (GooglePlayServicesNotAvailableException e) {
                Toast.makeText(getActivity(), "Google Play Services is not available.", Toast.LENGTH_LONG).show();
            }
            hideEverything();
        } else if (id == R.id.txt_contact) {
            Intent intent = new Intent(ChatActivity.this, ContactPickerActivity.class);
            intent.putExtra("user_id", mUserId);
            startActivityForResult(intent, Constants.REQUEST_CONTACT_PICKER);
            hideEverything();
        } else if (id == R.id.txt_voice) {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            startActivityForResult(intent, Constants.REQUEST_VOICE_RECORDER_NATIVE);
        } else if (id == R.id.drawer_search) {

            Intent i = null;
            try {
                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity.search.SearchActivity"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            startActivity(i);


            //Intent i =  new Intent(ChatActivity.this, RealmSearchActivity.class);
            //i.putExtra("chat", Parcels.wrap(theHistoryList));
            //startActivity(i);

        } else if (id == R.id.drawer_info) {

            //com.module.candychat.net.ChatActivity.startChat(this,cid,userId1,userId2,chatType);

//            mDrawer.getMenuView().findViewById(R.id.drawer_info).setOnClickListener(this);
//            mDrawer.getMenuView().findViewById(R.id.drawer_contact).setOnClickListener(this);
//            mDrawer.getMenuView().findViewById(R.id.drawer_invite).setOnClickListener(this);
//            mDrawer.getMenuView().findViewById(R.id.drawer_media).setOnClickListener(this);
//            mDrawer.getMenuView().findViewById(R.id.drawer_wallpaper).setOnClickListener(this);
//            mDrawer.getMenuView().findViewById(R.id.drawer_notification).setOnClickListener(this);
//            mDrawer.getMenuView().findViewById(R.id.drawer_photo).setOnClickListener(this);
//            mDrawer.getMenuView().findViewById(R.id.drawer_chat_setting).setOnClickListener(this);

            Intent i = null;
            try {
                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity.timeline.ChatInfoActivity"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            i.putExtra("id", mUserId);
            i.putExtra("fid", mPartnerId);
            i.putExtra("cid", mCid);
            i.putExtra("type", "1");
            if (mChatType == 2) {
                String theName = (mName.equals("")) ? mUsername : mName;
                i.putExtra("nameMe", mGroupName);
                i.putExtra("imageMe", mAvatarUrl);
                i.putExtra("type", "1");
            } else {
                String theName = (mName.equals("")) ? mUsername : mName;
                i.putExtra("nameMe", theName);
                i.putExtra("imageMe", mAvatarUrl);
                i.putExtra("type", "1");
            }
            startActivity(i);
            if (mDrawer != null)
                mDrawer.closeMenu();

        } else if (id == R.id.drawer_contact) {
            Intent i = null;
            try {
                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity.profile.ProfileActivityNew"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            i.putExtra("id", mUserId);
            i.putExtra("cid", mCid);
            i.putExtra("fid", mPartnerId);
            i.putExtra("type", "1");
            if (mChatType == 2) {
                String theName = (mName.equals("")) ? mUsername : mName;
                i.putExtra("nameMe", mGroupName);
                i.putExtra("imageMe", mAvatarUrl);
            } else {
                String theName = (mName.equals("")) ? mUsername : mName;
                i.putExtra("nameMe", theName);
                i.putExtra("imageMe", mAvatarUrl);
            }

            startActivity(i);
            if (mDrawer != null)
                mDrawer.closeMenu();
        } else if (id == R.id.drawer_invite) {

            if (mChatType == 2) {
                Intent intent = new Intent(ChatActivity.this, InviteActivity.class);
                intent.putExtra("cid", mCid);
                intent.putExtra("user_id", mUserId);
                startActivityForResult(intent, 701);
            } else {
                Intent i = null;
                try {
                    i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity2.CreateGroupActivity"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                i.putExtra("cid", mCid);
                startActivity(i);
            }
            //Toast.makeText(getActivity(), "drawer_invite", Toast.LENGTH_LONG).show();
            if (mDrawer != null)
                mDrawer.closeMenu();
        } else if (id == R.id.drawer_media) {
            Intent i = null;
            try {
                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity.timeline.MediaActivity"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            i.putExtra("cid", mCid);
            startActivity(i);
            if (mDrawer != null)
                mDrawer.closeMenu();
        } else if (id == R.id.drawer_wallpaper) {
            startActivity(new Intent(ChatActivity.this, ChatWallpaperActivity.class));
            //Toast.makeText(getActivity(), "drawer_wallpaper", Toast.LENGTH_LONG).show();
            if (mDrawer != null)
                mDrawer.closeMenu();
        } else if (id == R.id.drawer_notification) {

            //Toast.makeText(getActivity(), "isNotiOff: " + isNotiOff, Toast.LENGTH_LONG).show();
            ImageView notiIconIv = (ImageView) view;
            if (isNotiOff) {
                notiIconIv.setImageResource(R.drawable.ic_more_noti_off);
                Toast.makeText(getActivity(), "Notification off", Toast.LENGTH_LONG).show();
            } else {
                notiIconIv.setImageResource(R.drawable.ic_more_noti);
                Toast.makeText(getActivity(), "Notification on", Toast.LENGTH_LONG).show();
            }

            //Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();

            isNotiOff = !isNotiOff;
            if (mDrawer != null)
                mDrawer.closeMenu();
        } else if (id == R.id.drawer_photo) {

//            ArrayList<String> arrayList = new ArrayList<>();
//
//            for(TheMessageObject m : listMsgObjPhoto) {
//                if(m.messageType == Message.MSG_TYPE_PHOTO) {
//
//                }
//
//            }

            // TO-DOs:
            Intent i = new Intent(this, SampleGridViewActivity.class);
            i.putStringArrayListExtra("URLS", adapter.getPhotoUrls());
            startActivity(i);


            //Toast.makeText(getActivity(), "Photo Grid", Toast.LENGTH_LONG).show();
            if (mDrawer != null)
                mDrawer.closeMenu();
        } else if (id == R.id.drawer_chat_setting) {
            Intent i = null;
            try {
                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity.ChatSettingsActivity"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            i.putExtra("cid", mCid);
            startActivity(i);
            if (mDrawer != null)
                mDrawer.closeMenu();
        } else if (id == R.id.imag_create_topic) {

            // TO-DOs:
//            Intent i = new Intent(this, CreateTopicActivity.class);
//            i.putExtra("mCid", mCid);
//            i.putExtra("mUserId",mUserId);
//            i.putExtra("mChatType",mChatType);
//            startActivity(i);


            Intent i = null;
            try {
                i = new Intent(ChatActivity.this, Class.forName("com.candychat.net.activity2.TopicGroupActivity"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            i.putExtra("id", mUserId);
            i.putExtra("fid", mPartnerId);
            i.putExtra("cid", mCid);
            startActivity(i);


            //Toast.makeText(getActivity(), "Photo Grid", Toast.LENGTH_LONG).show();
            if (mDrawer != null)
                mDrawer.closeMenu();
        }
    }


    private void hideEverything() {
        customFontlayout.setVisibility(View.GONE);
        chatAttachmentLayout.setVisibility(View.GONE);
        holder.setVisibility(View.GONE);

        isLayoutColorfulShow = false;
        isLayoutAttachmentShow = false;
    }
}

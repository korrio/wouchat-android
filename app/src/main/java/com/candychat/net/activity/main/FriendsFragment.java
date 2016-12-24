package com.candychat.net.activity.main;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.binaryfork.spanny.Spanny;
import com.candychat.net.ActivityResultBus;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.ChatActivity;
import com.candychat.net.activity.MainSettingActivity;
import com.candychat.net.activity.contact.Contact;
import com.candychat.net.activity.main.event.GetRelationDataEvent;
import com.candychat.net.activity.profile.ProfileActivityNew;
import com.candychat.net.activity.search.SearchActivity;
import com.candychat.net.activity.timeline.ChatInfoActivity;
import com.candychat.net.activity2.AddActivity;
import com.candychat.net.activity2.UpdateProfileActivity2;
import com.candychat.net.activity2.UpdateStatusActivity;
import com.candychat.net.adapter.ExpandableListViewAdapter;
import com.candychat.net.alogin.activity.LoginOrSignupActivity;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.event.ActivityResultEvent;
import com.candychat.net.event.AddUserEvent;
import com.candychat.net.event.AddUserEventSuccess;
import com.candychat.net.event.GetRoomChatEvent;
import com.candychat.net.eventfeed.RefreshEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.candychat.net.model.Relations;
import com.module.candychat.net.service.MainApiService;
import com.module.candychat.net.util.ToastUtils;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FriendsFragment extends BaseFragment {
    private AQuery aq;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;

    private int userId;
    private int partnerId;

    private ExpandableListViewAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    ArrayList<Contact> listContacts = new ArrayList<>();

    public static final Integer[] images = {
            R.drawable.candy_logo,
            R.drawable.ic_group_friends,
            R.drawable.ic_favorite_friends,
            R.drawable.ic_friend_friends,
            R.drawable.ic_friend_friends
    };

    int cId;
    boolean checkData = false;
    public PrefManager prefManager;

    LinearLayout layout_search;
    boolean isCheckState;

    public static FriendsFragment getInstance(String message) {
        FriendsFragment mainFragment = new FriendsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    String url;
    //AsyncHttpClient asyncHttpClient;
    public static Relations relations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        setHasOptionsMenu(true);
        StrictMode.setVmPolicy (new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());
        //asyncHttpClient = new AsyncHttpClient();
        aq = new AQuery(getActivity());





        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(getActivity(),
                new String[]{Manifest.permission.CAMERA}, new PermissionsResultAction() {

                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied(String permission) {
                        Log.i("TAG", "onDenied: Write Storage: " + permission);

                    }
                }
        );


        prefManager = WOUApp.get(getActivity()).getPrefManager();
        userId = prefManager.userId().getOr(0);

        if (userId == 0)
            WOUApp.logout(getActivity());

        //fetchContact();
        fetchRelationsData();

    }

    public void fetchContact() {
        url = WOUApp.API_ENDPOINT + "/user/" + userId + "/relations";
        Log.e("aaa",url);
        aq.ajax(url, JSONObject.class, this, "jsonCallback");
    }

    @Subscribe public void onRefreshContact(RefreshEvent event) {
        //fetchContact();
        fetchRelationsData();
    }

    boolean myItemShouldBeEnabled = false;

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_friend_status);

        if(item != null)
        if (myItemShouldBeEnabled) {
            item.setEnabled(true);
            //  item.getIcon().setAlpha(255);
        } else {
            // disabled
            item.setEnabled(false);
//            item.getIcon().setAlpha(130);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (toolbar != null) {
            toolbar.setTitle(Spanny.spanText(getResources().getString(R.string.friends), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
            toolbar.inflateMenu(R.menu.menu_main_friends);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (toolbar != null) {
            toolbar.setTitle(Spanny.spanText(getResources().getString(R.string.friends), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
            toolbar.inflateMenu(R.menu.menu_main_friends);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if(toolbar != null) {
//            getActivity().setTitle(Spanny.spanText(getResources().getString(R.string.friends), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
//            toolbar.inflateMenu(R.menu.menu_main_friends);
//        }

    }

    @Subscribe
    public void onGetRelationsData(GetRelationDataEvent event) {
        fetchRelationsData();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchRelationsData();
//        if(toolbar != null) {
//            getActivity().setTitle(Spanny.spanText(getResources().getString(R.string.friends), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
//            toolbar.inflateMenu(R.menu.menu_main_friends);
//        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);

        if (!checkData) {
            fetchRelationsData();
            checkData = true;
        }
        //ApiBus.getInstance().post(new LoadTimelineEvent(Integer.parseInt("295"), "", 1, 20, false));
        expListView = (ExpandableListView) rootView.findViewById(R.id.expand);

        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                return false;
            }
        });

        layout_search = (LinearLayout) rootView.findViewById(R.id.layout_search);
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id
                .coordinatorLayout);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            Dialog dialog;

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, final int childPosition, long id) {

                if(relations == null)
                    System.exit(0); ;

                dialog = new Dialog(getActivity(), R.style.FullHeightDialog);

                System.out.println("groupPosition:" + groupPosition + " " + "childPosition:" + childPosition);

                System.out.println("relation.getMe:" + relations.getMe() + " " +
                        "relation.getFriends:" + relations.getFriends() + " " +
                        "relation.getGroup:" + relations.getGroup() + " " +
                        "relation.getFavorite:" + relations.getFavorite() + " ");




                if (groupPosition == 0) {
                    String urlAvatra = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getMe().get(childPosition).getAvatar();
                    String titleName;
                    if(!relations.getMe().get(childPosition).getName().equals(""))
                        titleName = relations.getMe().get(childPosition).getName();
                    else
                        titleName = "" + relations.getMe().get(childPosition).getUsername();
                    dialog.setContentView(R.layout.dialog_me);
                    ImageView img_avatra = (ImageView) dialog.findViewById(R.id.img_avatra);
                    TextView name_title = (TextView) dialog.findViewById(R.id.name_title);
                    ImageView img_setting = (ImageView) dialog.findViewById(R.id.img_setting);
                    TextView txt_me = (TextView) dialog.findViewById(R.id.txt_me);

                    TextView txt_profile = (TextView) dialog.findViewById(R.id.txt_profile);
                    txt_profile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), ProfileActivityNew.class);
                            String nameMe = relations.getMe().get(childPosition).getName();
                            String ImageMe = relations.getMe().get(childPosition).getAvatar();
                            int cid = relations.getMe().get(childPosition).getCid();
                            i.putExtra("id", userId);
                            i.putExtra("cid", cid);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", ImageMe);
                            startActivity(i);
                        }
                    });

                    TextView txt_chat = (TextView) dialog.findViewById(R.id.txt_chat);
                    txt_chat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApiBus.getInstance().post(new GetRoomChatEvent(userId, userId));
                            dialog.dismiss();
                            // TODO: add and show loading dialog
                        }
                    });
                    TextView txt_home = (TextView) dialog.findViewById(R.id.txt_home);
                    txt_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                            String nameMe = relations.getMe().get(childPosition).getName();
                            String imageMe = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getMe().get(childPosition).getAvatar();
                            int cid = relations.getMe().get(childPosition).getCid();
                            prefManager.myCid().put(cid).commit();
                            i.putExtra("id", userId);
                            i.putExtra("cid", cid);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", imageMe);
                            i.putExtra("type","0");
                            startActivity(i);
                        }
                    });


                    img_setting.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), UpdateProfileActivity2.class));
                        }
                    });
                    txt_me.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                            startActivity(i);
                        }
                    });
                    name_title.setText(titleName);
                    Picasso.with(getActivity())
                            .load(urlAvatra)
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_avatra);

                    img_avatra.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), UpdateProfileActivity2.class));
                        }
                    });

                    ImageView img_info = (ImageView) dialog.findViewById(R.id.img_info);


                    img_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                            String nameMe = relations.getMe().get(childPosition).getName();
                            String ImageMe = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getMe().get(childPosition).getAvatar();
                            int cid = relations.getMe().get(childPosition).getCid();
                            i.putExtra("id", userId);
                            i.putExtra("cid", cid);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", ImageMe);
                            i.putExtra("type","0");
                            startActivity(i);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

                if (groupPosition == 1) {
                    dialog.setContentView(R.layout.dialog_group);

                    List<ImageView> avatarList = new ArrayList<ImageView>();

                    ImageView img_group = (ImageView) dialog.findViewById(R.id.img_group);
                    ImageView img_friend_1 = (ImageView) dialog.findViewById(R.id.img_friend_1);
                    ImageView img_friend_2 = (ImageView) dialog.findViewById(R.id.img_friend_2);
                    ImageView img_friend_3 = (ImageView) dialog.findViewById(R.id.img_friend_3);
                    ImageView img_friend_4 = (ImageView) dialog.findViewById(R.id.img_friend_4);
                    ImageView info_group = (ImageView) dialog.findViewById(R.id.info_group);
                    ImageView img_next = (ImageView) dialog.findViewById(R.id.img_next);

                    TextView title_group = (TextView) dialog.findViewById(R.id.title_group);

                    info_group.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

                    avatarList.add(img_friend_1);
                    avatarList.add(img_friend_2);
                    avatarList.add(img_friend_3);
                    avatarList.add(img_friend_4);

                    img_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int  position = childPosition;
                            Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                            String nameMe = relations.getGroup().get(childPosition).getName();
                            String ImageMe = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getGroup().get(childPosition).getAvatar();
                            int cid = relations.getGroup().get(childPosition).getId();
                            i.putExtra("id", userId);
                            i.putExtra("position",position);
                            //i.putExtra("fid", relations.getFavorite().get(childPosition).getId());
                            i.putExtra("cid", cid);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", ImageMe);
                            i.putExtra("type","0");
                            startActivity(i);
                            dialog.dismiss();
                        }
                    });


                    if (relations.getGroup() != null) {

                        title_group.setText(relations.getGroup().get(childPosition).getName() + " (" + relations.getGroup().get(childPosition).getConversationMembers().size() + ")");

                        Picasso.with(getActivity())
                                .load(WOUApp.CHAT_ENDPOINT + relations.getGroup().get(childPosition).getAvatar())
                                .centerCrop()
                                .resize(200, 200)
                                .transform(new RoundedTransformation(100, 4))
                                .into(img_group);

                        int index = 0;
                        for (Relations.GroupBean.ConversationMembersBean conversationMembersEntity : relations.getGroup().get(childPosition).getConversationMembers()) {
                            if (index <= 3) {
                                if (conversationMembersEntity != null)
                                    Picasso.with(getActivity())
                                            .load(WOUApp.SOCIAL_ENDPOINT + "/" + conversationMembersEntity.getAvatar() + "." + conversationMembersEntity.getExtension())
                                            .centerCrop()
                                            .resize(200, 200)
                                            .transform(new RoundedTransformation(100, 4))
                                            .into(avatarList.get(index));
                                avatarList.get(index).setVisibility(View.VISIBLE);
                                index++;
                            }

                        }
                    }

                    dialog.show();

                    TextView txt_home = (TextView) dialog.findViewById(R.id.txt_home);
                    txt_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int  position = childPosition;
                            Log.e("position: position:",position+"");
                            Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                            String nameMe = relations.getGroup().get(childPosition).getName();
                            String ImageMe = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getGroup().get(childPosition).getAvatar();
                            int cid = relations.getGroup().get(childPosition).getConversationId();
                            i.putExtra("id", userId);
                            i.putExtra("cid", cid);
                            i.putExtra("position",position);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", ImageMe);
                            i.putExtra("type","0");
                            startActivity(i);
                            dialog.dismiss();
                        }
                    });

                    TextView txt_chat = (TextView) dialog.findViewById(R.id.txt_chat);
                    txt_chat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(getActivity(), ChatActivity.class);
                            i.putExtra("USER_ID_1", WOUApp.mPref.userId().getOr(1));
                            i.putExtra("USER_ID_2", partnerId);
                            i.putExtra("CHAT_TYPE", 2);
                            i.putExtra("CONVERSATION_ID", relations.getGroup().get(childPosition).getId());
                            startActivity(i);
                        }
                    });

                }
                if (groupPosition == 2) {

                    String urlAvatra = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getFavorite().get(childPosition).getAvatar();

                    if(!relations.getFavorite().get(childPosition).isIs_following()) {
                        dialog.setContentView(R.layout.dialog_add_friend);
                        TextView name_add = (TextView) dialog.findViewById(R.id.name_add);
                        TextView btn_add_friend = (TextView) dialog.findViewById(R.id.btn_add_friend);
                        ImageView avatar = (ImageView) dialog.findViewById(R.id.img_friend);
                        final int friendUserId = relations.getFavorite().get(childPosition).getId();

                        btn_add_friend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ApiBus.getInstance().post(new AddUserEvent(userId, friendUserId));
                                dialog.dismiss();
                            }
                        });

                        name_add.setText(relations.getFavorite().get(childPosition).getName());
                        Picasso.with(getContext())
                                .load(urlAvatra)
                                .centerCrop()
                                .resize(200, 200)
                                .transform(new RoundedTransformation(100, 4))
                                .into(avatar);
                    } else {
                        dialog.setContentView(R.layout.dialog_friends);

                        ImageView img_friend = (ImageView) dialog.findViewById(R.id.img_friend);
                        TextView name_title = (TextView) dialog.findViewById(R.id.name_title);
                        int mPartnerId = relations.getFavorite().get(childPosition).getId();
                        partnerId = mPartnerId;

                        Picasso.with(getActivity())
                                .load(urlAvatra)
                                .centerCrop()
                                .resize(200, 200)
                                .transform(new RoundedTransformation(100, 4))
                                .into(img_friend);
                        name_title.setText(relations.getFavorite().get(childPosition).getName());


                        ImageView img_info = (ImageView) dialog.findViewById(R.id.img_info);
                        img_info.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                                String nameMe = relations.getFavorite().get(childPosition).getName();
                                String ImageMe = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getFavorite().get(childPosition).getAvatar();
                                //int cid = relations.getFavorite().get(childPosition).getCid();
                                i.putExtra("id", userId);
                                i.putExtra("fid", relations.getFavorite().get(childPosition).getId());
                                //i.putExtra("cid", cid);
                                i.putExtra("nameMe", nameMe);
                                i.putExtra("imageMe", ImageMe);
                                i.putExtra("type","0");
                                startActivity(i);
                                dialog.dismiss();
                            }
                        });

                        TextView txt_home = (TextView) dialog.findViewById(R.id.txt_home);
                        txt_home.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                                String nameMe = relations.getFavorite().get(childPosition).getName();
                                String ImageMe = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getFavorite().get(childPosition).getAvatar();
                                //int cid = relations.getFavorite().get(childPosition).getCid();
                                i.putExtra("id", userId);
                                i.putExtra("fid", relations.getFavorite().get(childPosition).getId());
                                //i.putExtra("cid", cid);
                                i.putExtra("nameMe", nameMe);
                                i.putExtra("imageMe", ImageMe);
                                i.putExtra("type","1");
                                startActivity(i);
                                dialog.dismiss();
                            }
                        });

                        TextView txt_chat = (TextView) dialog.findViewById(R.id.txt_chat);
                        txt_chat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ApiBus.getInstance().post(new GetRoomChatEvent(userId, partnerId));
                                dialog.dismiss();
                            }
                        });

                    }

                    dialog.show();

                }
                if (groupPosition == 3 && relations.getFriends() != null) {
                    String urlAvatra = WOUApp.SOCIAL_ENDPOINT + "/" + relations.getFriends().get(childPosition).getAvatar();

                    String titleName = relations.getFriends().get(childPosition).getName();
                    dialog.setContentView(R.layout.dialog_friends);

                    ImageView img_friend = (ImageView) dialog.findViewById(R.id.img_friend);
                    TextView name_title = (TextView) dialog.findViewById(R.id.name_title);
                    int mPartnerId = relations.getFriends().get(childPosition).getId();
                    partnerId = mPartnerId;

                    Picasso.with(getActivity())
                            .load(urlAvatra)
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(img_friend);
                    name_title.setText(titleName);

                    TextView txt_profile = (TextView) dialog.findViewById(R.id.txt_profile);
                    txt_profile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), ProfileActivityNew.class);
                            String nameMe = relations.getFriends().get(childPosition).getName();
                            String ImageMe = relations.getFriends().get(childPosition).getAvatar();
                            //int cid = relations.getFavorite().get(childPosition).getCid();
                            i.putExtra("id", userId);
                            i.putExtra("fid", relations.getFriends().get(childPosition).getId());
                            //i.putExtra("cid", cid);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", ImageMe);
                            startActivity(i);
                        }
                    });

                    ImageView img_info = (ImageView) dialog.findViewById(R.id.img_info);
                    img_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                            String nameMe = relations.getFriends().get(childPosition).getName();
                            String ImageMe = relations.getFriends().get(childPosition).getAvatar();
                            //int cid = relations.getFavorite().get(childPosition).getCid();
                            i.putExtra("id", userId);
                            i.putExtra("fid", relations.getFriends().get(childPosition).getId());
                            //i.putExtra("cid", cid);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", ImageMe);
                            i.putExtra("type","1");
                            startActivity(i);
                        }
                    });

                    TextView txt_home = (TextView) dialog.findViewById(R.id.txt_home);
                    txt_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), ChatInfoActivity.class);
                            String nameMe = relations.getFriends().get(childPosition).getName();
                            String ImageMe = relations.getFriends().get(childPosition).getAvatar();
                            //int cid = relations.getFavorite().get(childPosition).getCid();
                            i.putExtra("id", userId);
                            i.putExtra("fid", relations.getFriends().get(childPosition).getId());
                            //i.putExtra("cid", cid);
                            i.putExtra("nameMe", nameMe);
                            i.putExtra("imageMe", ImageMe);
                            i.putExtra("type","0");
                            startActivity(i);
                        }
                    });

                    TextView txt_chat = (TextView) dialog.findViewById(R.id.txt_chat);
                    txt_chat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApiBus.getInstance().post(new GetRoomChatEvent(userId, partnerId));
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }


                return false;
            }
        });


        return rootView;
    }

    @Subscribe
    public void onAddUserEventSuccess(AddUserEventSuccess event) {
        Toast.makeText(getContext(),event.user.getMessage(),Toast.LENGTH_SHORT).show();
        fetchRelationsData();
    }


    private void scrollToBottom() {
        if (expListView != null)
            expListView.setSelection(expListView.getBottom());
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

    public void fetchRelationsData() {
        buildMainApi().getRelations(userId, new Callback<com.module.candychat.net.model.Relations>() {
            @Override
            public void success(com.module.candychat.net.model.Relations relationsResp, Response response) {
                relations = relationsResp;
                if(relations != null && relations.getGroup() != null) {
                    listDataHeader = new ArrayList<String>();
                    listDataHeader.add("Me");
                    listDataHeader.add("Groups " + relations.getGroup().size());
                    listDataHeader.add("Recently add you " + relations.getFavorite().size());
                    listDataHeader.add("Friends " + relations.getFriends().size());

                    Log.e("getFriends",relations.getFriends().size()+"");
                    listAdapter = new ExpandableListViewAdapter(getActivity(), listDataHeader, relations);
                    expListView.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();

                    expListView.expandGroup(0);
                    expListView.expandGroup(1);
                    expListView.expandGroup(2);
                    expListView.expandGroup(3);

                } else {
                    WOUApp.logout(getActivity());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void getRelationsData() {
        String url = WOUApp.API_ENDPOINT + "/user/" + userId + "/relations";

        Map<String, String> params = new HashMap<>();

        AQuery aq = new AQuery(getContext());
        aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                Gson gson = new GsonBuilder().create();
                relations = gson.fromJson(json.toString(), Relations.class);// obj is your object

                if(relations != null && relations.getGroup() != null) {
                    listDataHeader = new ArrayList<String>();
                    listDataHeader.add("Me");
                    listDataHeader.add("Groups " + relations.getGroup().size());
                    listDataHeader.add("Recently add you " + relations.getFavorite().size());
                    listDataHeader.add("Friends " + relations.getFriends().size());


//                    listAdapter = new ExpandableListViewAdapter(getActivity(), listDataHeader, relations);
//                    expListView.setAdapter(listAdapter);
//                    listAdapter.notifyDataSetChanged();
//
//                    expListView.expandGroup(0);
//                    expListView.expandGroup(1);
//                    expListView.expandGroup(2);
//                    expListView.expandGroup(3);

                } else {
                    WOUApp.logout(getActivity());
                }

            }
        });

        scrollToBottom();


    }

    public void jsonCallback(String url, JSONObject json, AjaxStatus status) {
        if (json != null) {
            Gson gson = new GsonBuilder().create();
            relations = gson.fromJson(json.toString(), Relations.class);// obj is your object

            listDataHeader = new ArrayList<String>();
            listDataHeader.add("Me");
            listDataHeader.add("Groups " + relations.getGroup().size());
            listDataHeader.add("Recently add you " + relations.getFavorite().size());
            listDataHeader.add("Friends " + relations.getFriends().size());


//            listAdapter = new ExpandableListViewAdapter(getActivity(), listDataHeader, relations);
//            expListView.setAdapter(listAdapter);
//            listAdapter.notifyDataSetChanged();
//
//            expListView.expandGroup(0);
//            expListView.expandGroup(1);
//            expListView.expandGroup(2);
//            expListView.expandGroup(3);

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(getActivity(), AddActivity.class));
                return true;

            case R.id.action_search:
                //  OnStateSearch();
                Intent i = new Intent(getActivity(), SearchActivity.class);
                startActivity(i);
                return true;
            case R.id.action_refresh:
                //fetchContact();
                fetchRelationsData();
                //ApiBus.getInstance().post(new GetRelationDataEvent());
                //FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.detach(this).attach(FriendsFragment.getInstance("friend")).commit();
                return true;
            case R.id.action_status:
                startActivity(new Intent(getActivity(), UpdateStatusActivity.class));
                return true;
            case R.id.action_friend_status:
                //startActivity(new Intent(getActivity(), ChatBackgroundActivity.class));

                return true;
            case R.id.action_setting:
                startActivity(new Intent(getActivity(), MainSettingActivity.class));

                return true;

            case R.id.action_log_out:
//                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Log out", Snackbar.LENGTH_LONG);
//                snackbar.show();


                new AlertDialog.Builder(getActivity())
                        .setTitle(getString(R.string.app_name))
                        .setMessage("Are you sure you want to log out?\n" +
                                "If you skip all data and message will be gone.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                final Intent intent = new Intent(getActivity(), AccountKitActivity.class);
                                final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                                        = createAccountKitConfiguration(LoginType.EMAIL);
                                intent.putExtra(
                                        AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                                        configurationBuilder.build());
                                startActivityForResult(intent, FRAMEWORK_REQUEST_CODE);
                            }
                        })
                        .setNegativeButton(R.string.skip, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                //dialog.dismiss();

                                new AlertDialog.Builder(getActivity())
                                        .setTitle(getString(R.string.app_name))
                                        .setMessage("Are you sure you want to log out?")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete
                                                WOUApp.logout(getActivity());
                                                Intent login = new Intent(getActivity(), LoginOrSignupActivity.class);
                                                startActivity(login);
                                                getActivity().finish();
                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                                final Intent intent = new Intent(getActivity(), AccountKitActivity.class);
                                                final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                                                        = createAccountKitConfiguration(LoginType.EMAIL);
                                                intent.putExtra(
                                                        AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                                                        configurationBuilder.build());
                                                startActivityForResult(intent, FRAMEWORK_REQUEST_CODE);



                                            }
                                        });


                                WOUApp.logout(getActivity());
                                Intent login = new Intent(getActivity(), LoginOrSignupActivity.class);
                                startActivity(login);
                                getActivity().finish();

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();



                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public static int FRAMEWORK_REQUEST_CODE = 1;

    private AccountKitConfiguration.AccountKitConfigurationBuilder createAccountKitConfiguration(
            final LoginType loginType) {
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                loginType,
                AccountKitActivity.ResponseType.TOKEN);

        configurationBuilder.setTitleType(AccountKitActivity.TitleType.APP_NAME);
        configurationBuilder.setTheme(R.style.AppLoginTheme_Wouchat);
        configurationBuilder.setFacebookNotificationsEnabled(true);
        configurationBuilder.setReadPhoneStateEnabled(true);
        configurationBuilder.setReceiveSMS(true);
        //configurationBuilder.setDefaultCountryCode("66");

        return configurationBuilder;
    }

    public void OnStateSearch() {
        if (!isCheckState) {
            layout_search.setVisibility(View.VISIBLE);
        } else {
            layout_search.setVisibility(View.GONE);
        }

        isCheckState = !isCheckState;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultBus.getInstance().postQueue(
                new ActivityResultEvent(requestCode, resultCode, data));

        if (requestCode == FRAMEWORK_REQUEST_CODE) {
            final AccountKitLoginResult loginResult =
                    data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            final String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                ToastUtils.showCenter(getActivity(), toastMessage);
                //showErrorActivity(loginResult.getError());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                final AccessToken accessToken = loginResult.getAccessToken();
                final String authorizationCode = loginResult.getAuthorizationCode();
                final long tokenRefreshIntervalInSeconds =
                        loginResult.getTokenRefreshIntervalInSeconds();

                Log.e("accessToken", accessToken.getToken() + "");
                Log.e("authorizationCode",authorizationCode+"");
                Log.e("tokenRefresh",tokenRefreshIntervalInSeconds+"");
                Log.e("state",loginResult.getFinalAuthorizationState()+"");


                if (accessToken != null) {
                    toastMessage = "Success:" + accessToken.getAccountId()
                            + tokenRefreshIntervalInSeconds;
                    // ToastUtils.showCenter(getActivity(),toastMessage);
                    //showHelloActivity(loginResult.getFinalAuthorizationState());
                } else if (authorizationCode != null) {
                    toastMessage = String.format(
                            "Success:%s...",
                            authorizationCode.substring(0, 10));
                    //ToastUtils.showCenter(this,toastMessage);
                    //showHelloActivity(authorizationCode, loginResult.getFinalAuthorizationState());
                } else {
                    toastMessage = "Unknown response type";
                }

                WOUApp.AccountKitGet(false);

                // thePhoneNumber = "+66910833820";


            }

        }
    }
}

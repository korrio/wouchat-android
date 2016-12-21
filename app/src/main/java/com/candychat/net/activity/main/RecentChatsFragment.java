package com.candychat.net.activity.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.ChatActivity;
import com.candychat.net.activity.MainActivity;
import com.candychat.net.activity.MainSettingActivity;
import com.candychat.net.activity.event.UpdateBadgeEvent;
import com.candychat.net.activity.search.SearchActivity;
import com.candychat.net.activity2.AddActivity;
import com.candychat.net.activity2.CreateBroadcastActivity;
import com.candychat.net.activity2.CreateGroupActivity;
import com.candychat.net.adapter.RecentChatsAdapter;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.event.GetRecentChatEvent;
import com.candychat.net.event.GetRecentChatSuccess;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.model.Conversation;
import com.candychat.net.model.ListRecentChat;
import com.candychat.net.view.CustomTypefaceSpan;
import com.squareup.otto.Subscribe;
import com.vlonjatg.progressactivity.ProgressActivity;
import com.wouchat.messenger.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RecentChatsFragment extends BaseFragment {
    ListView listView;
    RecentChatsAdapter adapterRecentChats;
    ArrayList<Conversation> list = new ArrayList<>();

    private Bundle bundleState;
    PrefManager prefManager;

    int conversationId;
    LinearLayout layout_search;
    boolean isCheckState;

    public int userId;
    public int partnerId;
    public static RecentChatsFragment getInstance(String message) {
        RecentChatsFragment mainFragment = new RecentChatsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    ProgressActivity progressActivity;
    LinearLayout noDataLayout;

    Button btnAddActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_chats, container, false);

//        ToastUtils.showCenter(getActivity(),"Yow!");
//
//        ToastUtils.showCenter(getActivity(),"Yea!");

        noDataLayout = (LinearLayout) rootView.findViewById(R.id.layout_no_data);
        noDataLayout.setVisibility(View.VISIBLE);

        btnAddActivity = (Button) rootView.findViewById(R.id.btn_add);
        btnAddActivity.setTypeface(WOUApp.CustomFontTypeFace());
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), AddActivity.class));
            }
        });

        progressActivity = (ProgressActivity) rootView.findViewById(R.id.progress);
        layout_search = (LinearLayout) rootView.findViewById(R.id.layout_search);
        listView = (ListView) rootView.findViewById(R.id.listView);
        adapterRecentChats = new RecentChatsAdapter(getActivity(), list);
        listView.setAdapter(adapterRecentChats);
        listView.setEmptyView(noDataLayout);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Conversation c = list.get(position);
                partnerId = c.partnerId;

                Intent i = new Intent(getActivity(), ChatActivity.class);

                i.putExtra("USER_ID_1", WOUApp.mPref.userId().getOr(0));
                i.putExtra("USER_ID_2", partnerId);
                i.putExtra("CHAT_TYPE", c.type);
                i.putExtra("CONVERSATION_ID", c.cid);

                startActivity(i);

                ChatActivity.chatApiService.readRecent(c.recentId, new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        ApiBus.getInstance().post(new GetRecentChatEvent(WOUApp.mPref.userId().getOr(0)));
                        unreadMessageBadgeCount = unreadMessageBadgeCount - c.badge;
                        MainActivity.isUpdateBadge = false;
                        ApiBus.getInstance().postQueue(new UpdateBadgeEvent(unreadMessageBadgeCount,0));
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

                //ApiBus.getInstance().post(new ReadNotiEvent(c.recentId));


            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Conversation c = list.get(i);
                partnerId = c.partnerId;

                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // continue with delete
                                ChatActivity.chatApiService.deleteRecent(c.recentId, new Callback<Response>() {
                                    @Override
                                    public void success(Response response, Response response2) {
                                        ApiBus.getInstance().post(new GetRecentChatEvent(WOUApp.mPref.userId().getOr(0)));
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                    }
                                });

                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                dialog.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher)
                        .show();
                return true;
            }
        });



        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        setHasOptionsMenu(true);

        prefManager = WOUApp.get(getActivity()).getPrefManager();
        userId = prefManager.userId().getOr(0);
    }

    boolean myItemShouldBeEnabled = false;

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_order);

        if(item != null) {
            if (myItemShouldBeEnabled) {
                item.setEnabled(true);
                //  item.getIcon().setAlpha(255);
            } else {
                // disabled
                item.setEnabled(false);
//            item.getIcon().setAlpha(130);
            }
//            MenuItem item2 = menu.findItem(R.id.action_send_multi);
//
//            if (myItemShouldBeEnabled) {
//                item2.setEnabled(true);
//                //    item2.getIcon().setAlpha(255);
//            } else {
//                // disabled
//                item2.setEnabled(false);
//                //    item2.getIcon().setAlpha(130);
//            }
        }



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(toolbar != null) {
            toolbar.setTitle(Spanny.spanText(getResources().getString(R.string.recentchat), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
            toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if(toolbar != null) {
//            getActivity().setTitle(Spanny.spanText(getResources().getString(R.string.recentchat), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
//            toolbar.inflateMenu(R.menu.menu_main_recent_chat);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiBus.getInstance().post(new GetRecentChatEvent(WOUApp.mPref.userId().getOr(0)));
//        if(toolbar != null) {
//            getActivity().setTitle(Spanny.spanText(getResources().getString(R.string.recentchat), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
//            toolbar.inflateMenu(R.menu.menu_main_recent_chat);
//        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bundleState = new Bundle();
        bundleState.putParcelable("posts", Parcels.wrap(list));
    }

    @Override
    public void onDestroyView() {
        bundleState = new Bundle();
        bundleState.putParcelable("posts", Parcels.wrap(list));
        super.onDestroyView();
    }

    private View.OnClickListener errorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Chat button clicked", Toast.LENGTH_LONG).show();
        }
    };

    int unreadMessageBadgeCount = 0;

    @Subscribe
    public void onGetRecentChatSuccess(GetRecentChatSuccess event) {
        List<ListRecentChat.ContentEntity> recentChatList = event.response.getContent();
        //WOUApp.recentChatList = recentChatList;
        list.clear();
        unreadMessageBadgeCount = 0;

        for (int i = 0; i < recentChatList.size(); i++) {
            ListRecentChat.ContentEntity recentChat = recentChatList.get(i);
            List<ListRecentChat.ContentEntity.ConversationMembersEntity> conversationMembers = recentChat.getConversationMembers();

            unreadMessageBadgeCount += recentChat.getBadge();

            String friendName = "";
            String friendUsername = "";
            String friendAvatar = "";
            int friendId = 0;


            for (int j = 0; j < conversationMembers.size(); j++) {
                ListRecentChat.ContentEntity.ConversationMembersEntity member;
                member = conversationMembers.get(j);
                int mUserId = userId;
                if (member.getUserId() != mUserId) {
                    friendId = member.getUserId();
                    friendName = member.getName();
                    friendUsername = member.getUsername();
                    friendAvatar =  WOUApp.SOCIAL_ENDPOINT + "/" + member.getAvatar() + "." + member.getExtension();
                    conversationId = member.getConversationId();
                }
            }

            if (recentChat.getMemberType().equals("INDIVIDUAL") && conversationMembers.size() > 1) {
                int type = 0;
                Conversation item = new Conversation(recentChat.getBadge(),type,recentChat.getConversationRecentId(), recentChat.getConversationId(), friendId, recentChat.getLastMessage(), friendUsername, friendName, friendAvatar, recentChat.getLastHistoryDatetime(),recentChat.getData());
                list.add(item);

            } else {
                String groupName = "";
                int size = conversationMembers.size();
                if(recentChat.getName() != null) {
                    groupName = recentChat.getName() + " (" + size +  ")";
                } else {
                    groupName = "New Group " + " (" + size +  ")";
                 }

                int type = 2;
                String groupAvatarUrl = WOUApp.CHAT_ENDPOINT + recentChat.getAvatar();
                Conversation item = new Conversation(recentChat.getBadge(),type,recentChat.getConversationRecentId(),recentChat.getConversationId(), friendId, recentChat.getLastMessage(),friendUsername, groupName ,groupAvatarUrl , recentChat.getLastHistoryDatetime(),recentChat.getData());
                list.add(item);
            }



        }



        adapterRecentChats.notifyDataSetChanged();

        ApiBus.getInstance().postQueue(new UpdateBadgeEvent(unreadMessageBadgeCount,0));
    }


    Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
               // OnStateSearch();
                Intent i = new Intent(getActivity(), SearchActivity.class);
                startActivity(i);
                return true;
            case R.id.action_new_group:
            case R.id.action_new_group2:
                startActivity(new Intent(getActivity(), CreateGroupActivity.class));
                return true;
            case R.id.action_send_multi:
                startActivity(new Intent(getActivity(), CreateBroadcastActivity.class));
                return true;
            case R.id.action_setting:
                startActivity(new Intent(getActivity(), MainSettingActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    public void OnStateSearch() {
        if (!isCheckState) {
            layout_search.setVisibility(View.VISIBLE);
        } else {
            layout_search.setVisibility(View.GONE);
        }
        isCheckState = !isCheckState;
    }


}

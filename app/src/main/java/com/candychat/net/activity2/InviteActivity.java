package com.candychat.net.activity2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.ChatActivity;
import com.candychat.net.adapter.InviteRecyclerAdapter;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.ConversationEventSuccess;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.model.TheUser;
import com.dpizarro.autolabel.library.AutoLabelUI;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InviteActivity extends BaseActivity {
    private AutoLabelUI mAutoLabel;
    private List<TheUser> mPersonList = new ArrayList<>();
    private InviteRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    public PrefManager prefManager;
    public int userId;
    public boolean checkData = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invite_friends;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAutoLabel = (AutoLabelUI) findViewById(R.id.label_view);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        adapter = new InviteRecyclerAdapter(getApplicationContext(), mPersonList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        mAutoLabel.setOnLabelsCompletedListener(new AutoLabelUI.OnLabelsCompletedListener() {
            @Override
            public void onLabelsCompleted() {

            }
        });

        mAutoLabel.setOnRemoveLabelListener(new AutoLabelUI.OnRemoveLabelListener() {
            @Override
            public void onRemoveLabel(View view, int position) {
                adapter.setItemSelected(position, false);
            }
        });

        mAutoLabel.setOnLabelsEmptyListener(new AutoLabelUI.OnLabelsEmptyListener() {
            @Override
            public void onLabelsEmpty() {

            }
        });

        mAutoLabel.setOnLabelClickListener(new AutoLabelUI.OnLabelClickListener() {
            @Override
            public void onClickLabel(View v) {

            }
        });
        adapter.setOnItemClickListener(new InviteRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemListClicked(position);
            }
        });
    }

    @Override
    protected void initData() {
        prefManager = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = prefManager.userId().getOr(0);

        if (!checkData) {
            prepareListData();
            checkData = true;
        }
    }

    @Subscribe
    public void onCreateGroupChatSuccess(ConversationEventSuccess event) {
        Intent i = new Intent(getApplicationContext(), ChatActivity.class);
        i.putExtra("CHAT_TYPE", 2);
        i.putExtra("CONVERSATION_ID", event.mCid);
        startActivity(i);
    }


    private void itemListClicked(int position) {
        TheUser person = mPersonList.get(position);
        boolean isSelected = person.isSelected();
        boolean success;
        if (isSelected) {
            success = mAutoLabel.removeLabel(position);
            Log.e("person",person+"");
        } else {
            success = mAutoLabel.addLabel(person.getName(), position);
        }
        if (success) {
            adapter.setItemSelected(position, !isSelected);
        }
    }

    public void prepareListData() {

        String url = WOUApp.API_ENDPOINT + "/user/" + userId + "/relations";

        Map<String, String> params = new HashMap<>();

        AQuery aq = new AQuery(this);
        aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {


                JSONArray friendsObj = json.optJSONArray("friends");
                for (int s = 0; s < friendsObj.length(); s++) {
                    JSONObject objFriends = friendsObj.optJSONObject(s);
                    Log.e("objFriends", objFriends + "");
                    String name = objFriends.optString("name");
                    String image = objFriends.optString("avatarIv");
                    int id = objFriends.optInt("id");
                    String imageUrl = WOUApp.API_ENDPOINT +  "/" + image;

                    TheUser listTheUser = new TheUser();
                    listTheUser.setName(name);
                    listTheUser.setImage(imageUrl);
                    listTheUser.setId(id);
                    mPersonList.add(listTheUser);

                }
                adapter.notifyDataSetChanged();

            }
        });
    }

}

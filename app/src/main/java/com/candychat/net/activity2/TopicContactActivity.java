package com.candychat.net.activity2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.ChatActivity;
import com.candychat.net.adapter.TopicAdapter;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.view.CustomTypefaceSpan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.candychat.net.model.Relations;
import com.wouchat.messenger.R;

import org.json.JSONObject;

import java.util.List;


public class TopicContactActivity extends BaseActivity {
    String url;
    private AQuery aq;
    TextView appVersionTv;
    TextView appNameTv;
    Toolbar toolbar;
    private int userId;
    public PrefManager prefManager;
    private List<String> listDataHeader;
    public static Relations relations;
    private int partnerId;

    RecyclerView recyclerView;
    TopicAdapter MoviesAdapter;

    public static final String USER_ID_1 = "USER_ID_1";
    public static final String USER_ID_2 = "USER_ID_2";
    public static final String CONVERSATION_ID = "CONVERSATION_ID";
    public static final String CHAT_TYPE = "CHAT_TYPE";

    public static void startChat(Context context, int cId, int userId, int partnerId, int chatType) {
        Intent i = new Intent(context, TopicContactActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(CONVERSATION_ID, cId);
        i.putExtra(USER_ID_1, userId);
        i.putExtra(USER_ID_2, partnerId);
        i.putExtra(CHAT_TYPE, chatType);
        context.startActivity(i);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_topic;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        appVersionTv = (TextView) findViewById(R.id.app_version);
        appNameTv = (TextView) findViewById(R.id.app_name);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("Topic Chat", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }




    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        aq = new AQuery(getApplicationContext());
        prefManager = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = prefManager.userId().getOr(0);
        recyclerView = findView(R.id.recyclerView);

        fetchContact();

    }

    public void fetchContact() {
        url = WOUApp.API_ENDPOINT + "/user/" + userId + "/relations";
        Log.e("aaa", url);
        aq.ajax(url, JSONObject.class, this, "jsonCallback");
    }


    public void jsonCallback(String url, JSONObject json, AjaxStatus status) {
        if (json != null) {
            Gson gson = new GsonBuilder().create();
            relations = gson.fromJson(json.toString(), Relations.class);// obj is your object



            Log.e("relations", relations.getGroup().get(0).getName());

            for(int i = 0 ; i < relations.getGroup().size();i++){
                MoviesAdapter = new TopicAdapter(getApplicationContext(),relations.getGroup());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(MoviesAdapter);
                MoviesAdapter.SetOnItemClickListener(new TopicAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                        i.putExtra("USER_ID_1", WOUApp.mPref.userId().getOr(1));
                        i.putExtra("USER_ID_2", partnerId);
                        i.putExtra("CHAT_TYPE", 2);
                        i.putExtra("CONVERSATION_ID", relations.getGroup().get(position).getId());
                        startActivity(i);
                    }
                });
            }



//            listAdapter = new ExpandableListViewAdapter(getActivity(), listDataHeader, relations);
//            expListView.setAdapter(listAdapter);
//            listAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
package com.candychat.net.activity.search;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.contact.Contact;
import com.candychat.net.activity.contact.ContactFetcher;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.GetFriendsEvent;
import com.candychat.net.event.GetFriendsEventSuccess;
import com.candychat.net.event.GetRoomChatEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.view.CustomTypefaceSpan;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends BaseActivity {
    public PrefManager mPref;
    EditText ios_style_edit_text;
    Toolbar toolbar;
    ListView listView3;
    List<Contact> listContacts = new ArrayList<>();

    public int userId;
    ListviewAdapterWithFilter listviewAdapterWithFilter;

    @Override
    protected int getLayoutId() {
        return R.layout.search_activity;
    }

    final static String INVITE_SUBJECT = WOUApp.mPref.name().getOr("") + " invite you to join WOUchat";
    final static String INVITE_TEXT = "You are invited to join WOUchat, please download at www.wouchat.com/app?" + WOUApp.mPref.userId().getOr(0);


    @Override
    protected void initViews(Bundle savedInstanceState) {


        ios_style_edit_text = (EditText) findViewById(R.id.ios_style_edit_text);
        listView3 = (ListView) findViewById(R.id.listView3);
        listContacts = new ContactFetcher(getApplicationContext()).fetchAll(true);
        listviewAdapterWithFilter = new ListviewAdapterWithFilter(getApplicationContext(),listContacts);
        listView3.setAdapter(listviewAdapterWithFilter);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            Dialog dialog;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int childPosition, long id) {
                dialog = new Dialog(SearchActivity.this, R.style.FullHeightDialog);
                if(!listContacts.get(childPosition).isFriend) {
                    dialog.setContentView(R.layout.dialog_friends_suggestions);
                    TextView name_sms = (TextView) dialog.findViewById(R.id.name_sms);
                    name_sms.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listContacts.get(childPosition).numbers.size() > 0) {
                                String number = listContacts.get(childPosition).numbers.get(0).number;
                                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                smsIntent.setType("vnd.android-dir/mms-sms");
                                smsIntent.putExtra("address", number);
                                smsIntent.putExtra("sms_body", INVITE_TEXT);
                                startActivity(smsIntent);
                            } else {
                                Toast.makeText(SearchActivity.this, "This contact has no phone number", Toast.LENGTH_SHORT).show();
                            }
                            // The number on which you want to send SMS

                        }
                    });
                    TextView name_email = (TextView) dialog.findViewById(R.id.name_email);
                    name_email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(listContacts.get(childPosition).emails.size() > 0 ){

                                String email = listContacts.get(childPosition).emails.get(0).address;
                                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                                emailIntent.setType("plain/text");
                                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
                                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, INVITE_SUBJECT);
                                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, INVITE_TEXT);
                                startActivity(Intent.createChooser(emailIntent, "Send email via..."));
                                dialog.dismiss();
                            } else {
                                Toast.makeText(SearchActivity.this, "This contact has no email", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                    TextView order = (TextView) dialog.findViewById(R.id.name_other);
                    order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, INVITE_SUBJECT);
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, INVITE_TEXT);
                            startActivity(Intent.createChooser(sharingIntent, "Share invitation via"));
                            dialog.dismiss();
                        }
                    });

                    TextView name_cancle = (TextView) dialog.findViewById(R.id.name_cancle);
                    name_cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();

                        }
                    });

                    dialog.show();
                } else {

                    int partnerId = listContacts.get(childPosition).id;
                    ApiBus.getInstance().post(new GetRoomChatEvent(userId, partnerId));
                }



            }
        });

        Log.e("sss",listContacts.size()+"");
        ios_style_edit_text.addTextChangedListener(filterTextWatcher);
    }

    @Subscribe
    public void onGetFriendEventSuccess(GetFriendsEventSuccess event) {
        if (event.user.getUsers() != null && event.user.getUsers().size() != 0) {

            for(int i = 0 ; i < event.user.getUsers().size();i++){
                com.candychat.net.model.UserModel userModel = event.user.getUsers().get(i);

                Contact newContact = new Contact();
                newContact.setName(userModel.name);
                newContact.setId(userModel.id);
                newContact.avatarUrl = userModel.getAvatarUrl();
                newContact.phoneNo = userModel.phone;
                newContact.isFriend = true;
                listContacts.add(newContact);

            }
            //listviewAdapterWithFilter.reverseList();
            listviewAdapterWithFilter.notifyDataSetChanged();
            Log.e("notified","notify");


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


    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            listviewAdapterWithFilter.getFilter().filter(s);
        }

    };
    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("Search friends", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        mPref = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = mPref.userId().getOr(0);
        ApiBus.getInstance().postQueue(new GetFriendsEvent(userId));
    }


}
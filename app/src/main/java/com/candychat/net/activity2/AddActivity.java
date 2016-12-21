package com.candychat.net.activity2;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.binaryfork.spanny.Spanny;
import com.candychat.net.Constant;
import com.candychat.net.activity.contact.Contact2;
import com.candychat.net.activity.contact.ContactsAdapter2;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainActivity;
import com.candychat.net.activity.contact.Contact;
import com.candychat.net.activity.contact.ContactFetcher;
import com.candychat.net.activity.contact.ContactsAdapter;
import com.candychat.net.activity.search.SearchActivity;
import com.candychat.net.adapter.ExpandableListForFriendsViewAdapter;
import com.candychat.net.adapter.OfficialFriendListViewAdapter;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.AddUserEvent;
import com.candychat.net.event.AddUserEventSuccess;
import com.candychat.net.event.GetFriendSuggestionEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.handler.GetFriendsSuggestionEventSuccess;
import com.candychat.net.model.UserModel;
import com.candychat.net.utils.ContactsUtils;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddActivity extends BaseActivity {

    private List<String> listDataHeader;
    public PrefManager mPref;

    LinearLayout btn_create_group;
    LinearLayout layout_create_topic;
    TextView txtAddPhone, txtInvite, txtQr, txtAddUsername;
    public int userId;

    private ExpandableListView expListView;

    List<UserModel> listFriendSuggestions = new ArrayList<>();
    OfficialFriendListViewAdapter mFriendSuggestionAdapter;
    ArrayList<Contact> listContacts = new ArrayList<>();
    ArrayList<Contact2> listContacts2 = new ArrayList<>();
    ContactsAdapter mContactAdapter;
    ContactsAdapter2 contactsAdapter2;

    ExpandableListForFriendsViewAdapter expandableListViewAdapter;


    Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_add_friends;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        mContext = this;

        txtAddPhone = (TextView) findViewById(R.id.txtAddPhone);
        txtInvite = (TextView) findViewById(R.id.txtInvite);
        txtQr = (TextView) findViewById(R.id.txtQr);
        expListView = (ExpandableListView) findViewById(R.id.expand);

        listDataHeader = new ArrayList<>();
        listDataHeader.add("Official Friend Suggestions");
        listDataHeader.add("Friend from Contacts");

        txtAddUsername = (TextView) findViewById(R.id.txtAddUsername);
        btn_create_group = (LinearLayout) findViewById(R.id.layout_create_group);

        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(AddActivity.this,
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

        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this,
                new String[]{Manifest.permission.READ_CONTACTS}, new PermissionsResultAction() {

                    @Override
                    public void onGranted() {
                        Log.e("TAG", "onGranted: Read Contacts");
                        ContactsUtils.readPhoneContacts(AddActivity.this);

                        ContentResolver contentResolver = getApplicationContext().getContentResolver();
                        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

                        if (null != cursor && cursor.getCount() > 0) {
                            while (cursor.moveToNext()) {
                                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                // String  phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                                    // Query phone here. Covered next
                                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                                    while (phones.moveToNext()) {
                                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                        // Log.i("Number", phoneNumber);
                                        Log.e("TAG", "================= " + contactName + " ==========" + phoneNumber + "");

                                        Contact2 contacts2 = new Contact2();
                                        contacts2.setName(contactName);
                                        contacts2.setPhone(phoneNumber);
                                        listContacts2.add(contacts2);

                                    }
                                    phones.close();
//                                }
//
//                                if (cursor.getCount() > 0) {
//                                    while (cursor.moveToNext()) {
//
//                                        Cursor emailCur = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id},null);
//                                        while (emailCur.moveToNext()) {
//                                            email = emailCur.getString( emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
//                                            //Log.e("Email",name+" "+email);
//                                        }
//                                        emailCur.close();
//                                    }
//
                                }


                            }
                            cursor.close();
                        }

                    }

                    @Override
                    public void onDenied(String permission) {
                        Log.e("TAG", "onDenied: Read Contacts");
                        String message = String.format(Locale.getDefault(), "", permission);
                        Toast.makeText(AddActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
        );


//        listContacts = new ContactFetcher(getApplicationContext()).fetchAll(true);


//        toolbar = getToolbar();
//        toolbar.setTitle(Spanny.spanText("Add friend", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("Add friend", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }


    @Override
    protected void initListeners() {
        Invite();
        AddPhone();
        AddUsername();
        Qrcode();

        btn_create_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, CreateGroupActivity.class));
            }
        });

    }

    @Override
    protected void initData() {
        mPref = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = mPref.userId().getOr(0);

        ApiBus.getInstance().postQueue(new GetFriendSuggestionEvent(userId));
        Log.e("getfriendevent", "fire!");
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


    @Subscribe
    public void GetOfficalFriendSuggestion(GetFriendsSuggestionEventSuccess event) {
        Log.e("suggestion_size", event.friendsDataResponse.getUsers().size() + "");
        if (event.friendsDataResponse.getUsers() != null && event.friendsDataResponse.getUsers().size() != 0) {
            //listFriendData.setUsers(event.friendsDataResponse.getUsers());
            listFriendSuggestions = event.friendsDataResponse.getUsers();
            Log.e("aaa", "bbb");
        }
//        mFriendSuggestionAdapter.notifyDataSetChanged();
//        Log.e("bbb",mFriendSuggestionAdapter.getCount()+"");
        // mContactAdapter.notifyDataSetChanged();
        expandableListViewAdapter = new ExpandableListForFriendsViewAdapter(getApplicationContext(), listDataHeader, listFriendSuggestions, listContacts2);
        expListView.setAdapter(expandableListViewAdapter);
        expListView.expandGroup(0);
        expListView.expandGroup(1);
        expandableListViewAdapter.notifyDataSetChanged();

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            Dialog dialog;

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, final int childPosition, long id) {

                dialog = new Dialog(AddActivity.this, R.style.FullHeightDialog);

                if (groupPosition == 0) {
                    dialog.setContentView(R.layout.dialog_add_friend);
                    TextView name_add = (TextView) dialog.findViewById(R.id.name_add);
                    TextView btn_add_friend = (TextView) dialog.findViewById(R.id.btn_add_friend);
                    ImageView avatar = (ImageView) dialog.findViewById(R.id.img_friend);
                    final int friendUserId = listFriendSuggestions.get(childPosition).id;

                    name_add.setText(listFriendSuggestions.get(childPosition).name);
                    Picasso.with(getApplicationContext())
                            .load(listFriendSuggestions.get(childPosition).getAvatarUrl())
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(avatar);

                    btn_add_friend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApiBus.getInstance().post(new AddUserEvent(userId, friendUserId));
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }

                if (groupPosition == 1) {
                    dialog.setContentView(R.layout.dialog_friends_suggestions);
                    TextView name_sms = (TextView) dialog.findViewById(R.id.name_sms);
                    name_sms.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            String number = listContacts2.get(childPosition).getPhone();
                            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
// Invokes only SMS/MMS clients
                            smsIntent.setType("vnd.android-dir/mms-sms");
// Specify the Phone Number
                            smsIntent.putExtra("address", number);
// Specify the Message
                            smsIntent.putExtra("sms_body", Constant.INVITE_TEXT);

// Shoot!
                            startActivity(smsIntent);


//                            if (listContacts.get(childPosition).numbers.size() > 0) {
//                                String number = listContacts.get(childPosition).numbers.get(0).number;
//                                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//// Invokes only SMS/MMS clients
//                                smsIntent.setType("vnd.android-dir/mms-sms");
//// Specify the Phone Number
//                                smsIntent.putExtra("address", number);
//// Specify the Message
//                                smsIntent.putExtra("sms_body", Constant.INVITE_TEXT);
//
//// Shoot!
//                                startActivity(smsIntent);
//                            } else {
//                                Toast.makeText(AddActivity.this, "This contact has no phone number", Toast.LENGTH_SHORT).show();
//                            }
//                            // The number on which you want to send SMS

                        }
                    });
                    TextView name_email = (TextView) dialog.findViewById(R.id.name_email);
                    name_email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String email = listContacts2.get(childPosition).getName();
                            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                            emailIntent.setType("plain/text");
                            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
                            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Constant.INVITE_SUBJECT);
                            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Constant.INVITE_TEXT);
                            startActivity(Intent.createChooser(emailIntent, "Send email via..."));
//

//                            if(listContacts2.get(childPosition).emails.size() > 0 ){
//
////                                String email = listContacts.get(childPosition).emails.get(0).address;
////                                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
////                                emailIntent.setType("plain/text");
////                                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
////                                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Constant.INVITE_SUBJECT);
////                                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Constant.INVITE_TEXT);
////                                startActivity(Intent.createChooser(emailIntent, "Send email via..."));
//
//
//                                String email = listContacts.get(childPosition).emails.get(0).address;
//                                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//                                emailIntent.setType("plain/text");
//                                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
//                                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Constant.INVITE_SUBJECT);
//                                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Constant.INVITE_TEXT);
//                                startActivity(Intent.createChooser(emailIntent, "Send email via..."));
//
//
//
//                                dialog.dismiss();
//                            } else {
//                                Toast.makeText(AddActivity.this, "This contact has no email", Toast.LENGTH_SHORT).show();
//                            }


                        }
                    });

                    TextView order = (TextView) dialog.findViewById(R.id.name_other);
                    order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Constant.INVITE_SUBJECT);
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Constant.INVITE_TEXT);
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

                }

                return false;
            }
        });

    }

//    public void onClickSuggestionHeader() {
//        if (!isCheckContact) {
//            friendsSuggestionListview.setVisibility(View.VISIBLE);
//        } else {
//            friendsSuggestionListview.setVisibility(View.GONE);
//        }
//        isCheckContact = !isCheckContact;
//    }
//
//    public void onClickOfficialHeader() {
//        if (!isCheckMe) {
//            officialFriendsSuggestionListView.setVisibility(View.VISIBLE);
//        } else {
//            officialFriendsSuggestionListView.setVisibility(View.GONE);
//        }
//        isCheckMe = !isCheckMe;
//    }

    public void Invite() {
        txtInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SearchActivity.class));
            }
        });
    }

    public void AddPhone() {
        txtAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, FindFriendsByPhoneActivity.class));
            }
        });
    }

    public void AddUsername() {
        txtAddUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, FindFriendsByUsernameActivity.class));
            }
        });
    }

    public void Qrcode() {
        txtQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ShowQRCodeActivity.class));
            }
        });


    }

    @Subscribe
    public void onAddUserEventSuccess(AddUserEventSuccess event) {
        Toast.makeText(getApplicationContext(), event.user.getMessage(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
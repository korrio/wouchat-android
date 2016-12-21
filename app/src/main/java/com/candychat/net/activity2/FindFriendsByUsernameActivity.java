package com.candychat.net.activity2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.Utils;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainActivity;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.AddUserEvent;
import com.candychat.net.event.AddUserEventSuccess;
import com.candychat.net.event.SearchUserEvent;
import com.candychat.net.event.SearchUserEventSuccess;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

public class FindFriendsByUsernameActivity extends BaseActivity {
    EditText input_username;

    ImageView avatar;
    TextView searchUsernameTv;
    ImageView img_search;
    int searchUserId;
    String searchTerm;
    int userId;
    TextView txt_name;
    Button btn_qrcode;
    Button btn_add;
    TextView linkTv;

    public PrefManager mPref;

    public Activity mActivity;
    String username;
    int friendUserId;

    Toolbar toolbar;

    public static void startSearch(Context context, String str, int searchType) {
        Intent i = new Intent(context, FindFriendsByUsernameActivity.class);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("USERNAME", str);
        //i.putExtra("SEARCH_TYPE", searchType);
        context.startActivity(i);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_by_username;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        mActivity = this;
        input_username = (EditText) findViewById(R.id.input_username);

        Link instructionLink = new Link("Where to find username ?");
        instructionLink.setTypeface(Typeface.DEFAULT_BOLD)
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {

                    }
                });

        linkTv = (TextView) findViewById(R.id.btn_instruction);
        LinkBuilder.on(linkTv)
                .addLink(instructionLink)
                .build();

        avatar = (ImageView) findViewById(R.id.avatar);
        searchUsernameTv = (TextView) findViewById(R.id.searchUsernameTv);
        btn_add = (Button) findViewById(R.id.btn_add);

        img_search = (ImageView) findViewById(R.id.img_search);
        txt_name = (TextView) findViewById(R.id.txt_name);
        btn_qrcode = (Button) findViewById(R.id.btn_qrcode);

        input_username.setTypeface(WOUApp.CustomFontTypeFace());
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText(getResources().getString(R.string.find_friend_username), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {

        input_username.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchTerm = input_username.getText().toString().trim();
                    performSearch(searchTerm);
                    hideKeyboard(v);
                    return true;
                }
                return false;
            }
        });

        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scan = new Intent(getApplicationContext(), com.candychat.net.activity.DecoderActivity.class);
                startActivity(scan);
            }
        });

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTerm = input_username.getText().toString().trim();
                performSearch(searchTerm);
                hideKeyboard(v);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiBus.getInstance().post(new AddUserEvent(userId, friendUserId));
            }
        });
    }

    @Override
    protected void initData() {
        if(getIntent().getExtras() != null) {
            username = getIntent().getExtras().getString("USERNAME");
            searchTerm = username;
            if(searchTerm != null && !searchTerm.equals("")) {
                performSearch(searchTerm);
                txt_name.setText(searchTerm);
            }

            else
                Utils.showToast("No friend detected");
        } else {
            username = WOUApp.mPref.username().getOr("");
            txt_name.setText(username);
        }

        mPref = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = mPref.userId().getOr(0);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_username,menu);
        return super.onCreateOptionsMenu(menu);
    }

    void performSearch(String searchTerm) {
        ApiBus.getInstance().postQueue(new SearchUserEvent(searchTerm));
    }


    @Subscribe
    public void onSearchUsernameSuccess(SearchUserEventSuccess event) {
        Log.e("succes","searchusernamesuccess");

        if (event.user != null) {
            searchUserId = event.user.userId;
            friendUserId = event.user.userId;
            btn_add.setVisibility(View.VISIBLE);
            searchUsernameTv.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.VISIBLE);
            searchUsernameTv.setText(event.user.username);

            Picasso.with(getApplicationContext())
                    .load(event.user.getAvatarUrl())
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(avatar);
        }
    }

    @Subscribe
    public void onAddUserEventSuccess(AddUserEventSuccess event) {
        Toast.makeText(getApplicationContext(),event.user.getMessage(),Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
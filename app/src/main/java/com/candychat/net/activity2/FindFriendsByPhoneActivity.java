package com.candychat.net.activity2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainActivity;
import com.candychat.net.activity2.countrypicker.CountryPicker;
import com.candychat.net.activity2.countrypicker.CountryPickerListener;
import com.candychat.net.activity2.countrypicker.SearchPhoneEventSuccess;
import com.candychat.net.activity2.countrypicker.SearchUserPhoneEvent;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.AddUserEvent;
import com.candychat.net.event.AddUserEventSuccess;
import com.candychat.net.event.SearchUserEventSuccess;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.module.candychat.net.ChatActivity;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.BetterSpinner;
import com.wouchat.messenger.R;

public class FindFriendsByPhoneActivity extends BaseActivity {
    Toolbar toolbar;
    EditText input_phone;
    String[] codeContry = {"All Code","(+60) Malaysia", "(+61) Australia", "(+62) Indonesia", "(+63) Philippines", "(+64) New Zealand", "(+65) Singapore", "(+66) Thailand"};
    BetterSpinner spinner1;
    ImageView searchBtn;

    String phone;
    String phoneCode = "66";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_by_phone;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        avatar = (ImageView) findViewById(R.id.avatar);
        searchUsernameTv = (TextView) findViewById(R.id.searchUsernameTv);
        btn_add = (Button) findViewById(R.id.btn_add);

        input_phone = (EditText) findViewById(R.id.input_phone);
        spinner1  = (BetterSpinner) findViewById(R.id.spinner1);

        spinner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CountryPicker picker = new CountryPicker();
//                picker.setListener(new CountryPickerListener() {
//                    @Override
//                    public void onSelectCountry(String name, String code, String dialCode) {
//                        Toast.makeText(
//                                FindFriendsByPhoneActivity.this,
//                                "Country Name: " + name + " - Code: " + code
//                                        + " - Currency: "
//                                        + CountryPicker.getCurrencyCode(code) + " - Dial Code: " + dialCode,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });

                final CountryPicker picker = CountryPicker.newInstance("SelectCountry");
                picker.setListener(new CountryPickerListener() {

                    @Override
                    public void onSelectCountry(String name, String code, String dialCode) {
                        Toast.makeText(
                                FindFriendsByPhoneActivity.this,
                                "Country Name: " + name + " - Code: " + code
                                        + " - Currency: "
                                        + CountryPicker.getCurrencyCode(code) + " - Dial Code: " + dialCode,
                                Toast.LENGTH_SHORT).show();


                        String theText = "("+dialCode+") " + name;
                        phoneCode = dialCode;
                        spinner1.setText(theText);

                        picker.dismiss();
                        spinner1.dismissDropDown();
                    }
                });

                picker.show(getSupportFragmentManager(), "COUNTRY_CODE_PICKER");
            }
        });

        searchBtn = (ImageView) findViewById(R.id.imageView12);

        Typeface type = WOUApp.CustomFontTypeFace();
        spinner1.setTypeface(type);
        input_phone.setTypeface(type);

        spinner1.setTextColor(Color.BLACK);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText(getResources().getString(R.string.find_friend_phone), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchTerm = input_phone.getText().toString().trim();
                searchTerm = searchTerm.startsWith("0") ? searchTerm.substring(1) : searchTerm;
                phone = searchTerm;
                performSearch(searchTerm);
                hideKeyboard(view);

                //}
            }
        });

        input_phone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchTerm = input_phone.getText().toString().trim();
                    searchTerm = searchTerm.startsWith("0") ? searchTerm.substring(1) : searchTerm;
                    phone = searchTerm;
                    performSearch(searchTerm);
                    hideKeyboard(v);
                    return true;
                }
                return false;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line, codeContry);
        spinner1.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.menu_add_phone,menu);
        return super.onCreateOptionsMenu(menu);
    }

    void performSearch(String searchTerm) {
        //http://api.candychat.net/phone?phone=945650925&phone_code=66
        ApiBus.getInstance().postQueue(new SearchUserPhoneEvent(phoneCode,phone));
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


    ImageView avatar;
    TextView searchUsernameTv;
    ImageView img_search;
    int searchUserId,friendUserId;
    String searchTerm;
    int userId;
    TextView txt_name;
    Button btn_qrcode;
    Button btn_add;

    @Subscribe public void onSearchPhoneSuccess(SearchPhoneEventSuccess event) {
        if (event.userResponse.user != null) {
            searchUserId = userId;
            friendUserId = event.userResponse.user.id;

            btn_add.setVisibility(View.VISIBLE);
            searchUsernameTv.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.VISIBLE);

            searchUsernameTv.setText(event.userResponse.user.name);
//            txt_name.setText(event.user.username);
            Picasso.with(getApplicationContext())
                    .load(event.userResponse.user.getAvatarUrl())
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(avatar);

            if(event.userResponse.user.isFollowing) {
                btn_add.setText("Chat");
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //ChatActivity.startChat(getApplicationContext(),0,userId,friendUserId,0);
                    }
                });
            }
        }
    }

    @Subscribe
    public void onSearchUsernameSuccess(SearchUserEventSuccess event) {
        if (event.user != null) {
            searchUserId = event.user.userId;
            friendUserId = event.user.userId;

            btn_add.setVisibility(View.VISIBLE);
            searchUsernameTv.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.VISIBLE);

            String friendPhone = "+" + phoneCode + "" + phone.replaceFirst("^0+(?!$)", "");

            if(!event.user.name.equals(""))
                searchUsernameTv.setText(event.user.name);
            else
                searchUsernameTv.setText(friendPhone);
//            txt_name.setText(event.user.username);
            Picasso.with(getApplicationContext())
                    .load(event.user.getAvatarUrl())
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(avatar);

            if(event.user.isFollowing) {
                btn_add.setText("Chat");
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChatActivity.startChat(getApplicationContext(),0,userId,friendUserId,0);
                    }
                });
            }
        }
    }


}
package com.candychat.net.alogin.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.event.CheckUsernameEvent;
import com.candychat.net.event.UsernameNotOkEvent;
import com.candychat.net.event.UsernameOkEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.utils.Utils;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SignByEmailStep1Fragment extends BaseFragment {

    Toolbar toolbar;
    EditText dtEmail;
    EditText dtPassword;
    Button btnNext;
    CheckBox imgShowPassword;
    Typeface type;
    public PrefManager prefManager;


    String email;
    String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        prefManager = WOUApp.get(getActivity()).getPrefManager();

    }

    TextView usernameStatus;
    TextView conditionTv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_by_email_step_1, container, false);
        type = WOUApp.CustomFontTypeFace();
        dtEmail = (EditText) rootView.findViewById(R.id.input_email);
        dtPassword = (EditText) rootView.findViewById(R.id.input_password);
        btnNext = (Button) rootView.findViewById(R.id.btn_next);
        dtEmail.setTypeface(type);
        dtPassword.setTypeface(type);
        btnNext.setTypeface(type);
        imgShowPassword = (CheckBox) rootView.findViewById(R.id.check_show_password);
        usernameStatus = (TextView) rootView.findViewById(R.id.username_status);

        conditionTv = (TextView) rootView.findViewById(R.id.condition);

        Link terms = new Link("terms of use")
                .setTextColor(Color.parseColor("#5db2d6"))    // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                       // optional, defaults to .15f
                .setUnderlined(true)
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        // single clicked
                        String url = "https://www.wouchat.com/terms";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

        Link policy = new Link("privacy policy")
                .setTextColor(Color.parseColor("#5db2d6"))    // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                       // optional, defaults to .15f
                .setUnderlined(true)
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        // single clicked
                        String url = "https://www.wouchat.com/privacy";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });



// create the link builder object add the link rule
        LinkBuilder.on(conditionTv)
                .addLink(terms)
                .addLink(policy)
                .build(); // create the clickable links

        Link termLink = new Link("terms of use");
        termLink.setTypeface(Typeface.DEFAULT_BOLD)
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {

                    }
                });

        Link privacyLink = new Link("privacy policy");
        privacyLink.setTypeface(Typeface.DEFAULT_BOLD)
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {

                    }
                });

        List<Link> links = new ArrayList<Link>();
        links.add(termLink);
        links.add(privacyLink);

        LinkBuilder.on(conditionTv)
                .addLinks(links)
                .build();

        final Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
//        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
//        for (Account account : accounts) {
//            if (emailPattern.matcher(account.name).matches()) {
//                String possibleEmail = account.name;
//                dtEmail.setText(possibleEmail);
//            }
//        }

        imgShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    dtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    dtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

            }
        });

        dtEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                email = dtEmail.getText().toString().trim();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                email = dtEmail.getText().toString().trim();
                if(emailPattern.matcher(email).matches()) {
                    ApiBus.getInstance().post(new CheckUsernameEvent(email));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
//                if(s.length() != 0)
//                    dtUserName.setText("");
//
                email = dtEmail.getText().toString().trim();
                if(emailPattern.matcher(email).matches()) {
                    ApiBus.getInstance().post(new CheckUsernameEvent(email));
                }

            }
        });

        Next();

        return rootView;
    }

    @Subscribe
    public void usernameOk(UsernameOkEvent event) {
        usernameStatus.setVisibility(View.VISIBLE);
        usernameStatus.setTextColor(Color.GREEN);
        usernameStatus.setText("✔");
        isEmailExist = false;
    }

    @Subscribe public void usernameNotOk(UsernameNotOkEvent event) {
        usernameStatus.setVisibility(View.VISIBLE);
        usernameStatus.setTextColor(Color.RED);
        usernameStatus.setText("✘");
        isEmailExist = true;
    }

    boolean isEmailExist = false;

    public void Next() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = dtEmail.getText().toString().trim();
                password = dtPassword.getText().toString().trim();
                prefManager.email().put(email);
                prefManager.password().put(password);
                prefManager.commit();

                if(Utils.isValidEmailAddress(email)) {
                    if(password.length() >= 6) {
                        if(!isEmailExist) {
                            SignByEmailFinalFragment oneFragment = new SignByEmailFinalFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.content, oneFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else {
                            Utils.theToast("The email is already in use");
                        }

                    } else {
                        Utils.theToast("Please input pass word with more than 6 letters");
                    }

                } else {
                    Utils.theToast("Please input valid email address");
                }


            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.setTitle("Sign up");
        // toolbar.inflateMenu(R.menu.menu_main_recent_chat);
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


        }
        return super.onOptionsItemSelected(item);
    }
}
package com.candychat.net.alogin.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainActivity;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.event.LoginEvent;
import com.candychat.net.event.LoginFailedAuthEvent;
import com.candychat.net.event.LoginSuccessEvent;
import com.candychat.net.handler.ApiBus;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;


public class RegisterionCompletedFragment extends BaseFragment {
    Toolbar toolbar;
    Button btnStart;
    Typeface type;
    public PrefManager prefManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registerion_completed, container, false);
        type = WOUApp.CustomFontTypeFace();
        prefManager = WOUApp.get(getActivity()).getPrefManager();
        btnStart = (Button) rootView.findViewById(R.id.btn_start);
        btnStart.setTypeface(type);
        Start();
        return rootView;
    }


    public void Start() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(WOUApp.mPref.signupByPhone().getOr(false)) {
                    Toast.makeText(getActivity(),WOUApp.mPref.phone().getOr(""),Toast.LENGTH_SHORT).show();
                    ApiBus.getInstance().post(new LoginEvent(WOUApp.mPref.phone().getOr(""), WOUApp.mPref.password().getOr("")));
                } else {
                    Toast.makeText(getActivity(),WOUApp.mPref.phone().getOr(""),Toast.LENGTH_SHORT).show();
                    ApiBus.getInstance().post(new LoginEvent(WOUApp.mPref.username().getOr(""), WOUApp.mPref.password().getOr("")));
                }

            }
        });
    }

    @Subscribe public void onLoginSuccess(LoginSuccessEvent event) {
        Log.e("LoginSuccessEvent:token",event.getLoginData().token);
        Log.e("LoginData",event.getLoginData().user.id+"");
        prefManager
                .name().put(event.getLoginData().user.name)
                .username().put(event.getLoginData().user.username)
                .password().put(event.getLoginData().user.password)
                .email().put(event.getLoginData().user.email)
                .userId().put(event.getLoginData().user.id)
                .token().put(event.getLoginData().token)
                .cover().put(event.getLoginData().user.cover)
                .avatar().put(event.getLoginData().user.avatar)
                .phone().put(event.getLoginData().user.phone)
                .phoneCode().put(event.getLoginData().user.phoneCode)
                .phoneNumber().put(event.getLoginData().user.phone)
                .isLogin().put(true)
                .commit();
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @Subscribe public void onLoginFailed(LoginFailedAuthEvent event) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


        }
        return super.onOptionsItemSelected(item);
    }


}
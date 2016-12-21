package com.candychat.net.alogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseActivity;
import com.wouchat.messenger.R;

import butterknife.Bind;
import butterknife.OnClick;


public class LoginOrSignupActivity extends BaseActivity {
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_sign_up)
    Button btnSignUp;

    @OnClick(R.id.btn_login)
    public void loginAction() {
        Intent i = new Intent(getApplicationContext(), MainLoginActivity.class);
        i.putExtra("type", 0);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btn_sign_up)
    public void signupAction() {
        Intent i = new Intent(getApplicationContext(), MainLoginActivity.class);
        i.putExtra("type", 1);
        startActivity(i);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_or_signup;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        btnLogin.setTypeface(WOUApp.CustomFontTypeFace());
        btnSignUp.setTypeface(WOUApp.CustomFontTypeFace());
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        WOUApp.get(getApplicationContext()).getPrefManager().clear().commit();
    }

}

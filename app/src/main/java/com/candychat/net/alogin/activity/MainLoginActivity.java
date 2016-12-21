package com.candychat.net.alogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.alogin.fragment.MainLoginFragment;
import com.candychat.net.alogin.fragment.SignupFragment2;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.view.CustomTypefaceSpan;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.module.candychat.net.util.ToastUtils;
import com.wouchat.messenger.R;


public class MainLoginActivity extends BaseToolbarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_login;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        int type = getIntent().getIntExtra("type", 0);
        if(type == 0){
            getToolbar().setTitle(Spanny.spanText("Login", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
            MainLoginFragment threeFragment = new MainLoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, threeFragment);
            transaction.commit();
        }else if(type == 1){
            //startActivity(new Intent(MainLoginActivity.this,SignupFragment2.class));
            getToolbar().setTitle(Spanny.spanText("Sign up", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
            SignupFragment2 threeFragment = new SignupFragment2();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, threeFragment);
            transaction.commit();
        }
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static int FRAMEWORK_REQUEST_CODE = 1 ;

    //@Subscribe

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       

        if(resultCode == FRAMEWORK_REQUEST_CODE) {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    //final TextView userId = (TextView) findViewById(R.id.user_id);
                    String accountId = account.getId();

                    PhoneNumber accountNumber = account.getPhoneNumber();

                    String phoneNumber = AccountKit.getCurrentPhoneNumberLogInModel().getPhoneNumber().getPhoneNumber();
                    String phoneCode = AccountKit.getCurrentPhoneNumberLogInModel().getPhoneNumber().getCountryCode();

                    Log.e("phoneNumber",phoneNumber+"");
                    Log.e("phoneCode",phoneCode+"");

                    //String number == null ? null : number.toString();

                    String accountEmail = account.getEmail();

                    String toastMessage = accountId + " " + accountNumber + " " + accountEmail;

                    ToastUtils.showCenter(getApplicationContext(),"MainLoginActivityResult:" + toastMessage);



                }

                @Override
                public void onError(final AccountKitError error) {
                }
            });
        }

    }
}

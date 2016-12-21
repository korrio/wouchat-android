package com.candychat.net.alogin.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainActivity;
import com.wouchat.messenger.BuildConfig;
import com.wouchat.messenger.R;

public class SplashActivity extends Activity {

    private static final int SPLASH_TIME_OUT = 1500;
    public PrefManager prefManager;
    boolean isLogin ;
    int id = 0;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    TextView versionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefManager = WOUApp.get(getApplicationContext()).getPrefManager();
        isLogin =  prefManager.isLogin().getOr(false);

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        versionTxt = (TextView) findViewById(R.id.version);
        versionTxt.setText("Version " + versionName);

        if(isLogin){
            Intent i = new Intent(getApplication(), MainActivity.class);
            startActivity(i);
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    prefManager.clear().commit();

                    Intent i = new Intent(SplashActivity.this, LoginOrSignupActivity.class);
                    startActivity(i);
                    finish();

                }
            }, SPLASH_TIME_OUT);
        }
    }

}

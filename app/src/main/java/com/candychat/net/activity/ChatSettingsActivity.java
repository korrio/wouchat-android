package com.candychat.net.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.RingtonePreference;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.candychat.net.manager.PrefManager;
import com.candychat.net.WOUApp;
import com.module.candychat.net.activity.ChatWallpaperActivity;
import com.wouchat.messenger.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChatSettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    private static String appVersion;
    Context context;

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        AppBarLayout bar;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
            bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);
            root.addView(bar, 0);
        } else {
            ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
            ListView content = (ListView) root.getChildAt(0);
            root.removeAllViews();
            bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);

            int height;
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
                height = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            }else{
                height = bar.getHeight();
            }

            content.setPadding(0, height, 0, 0);

            root.addView(content);
            root.addView(bar);
        }

        Toolbar Tbar = (Toolbar) bar.getChildAt(0);

        Tbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = pInfo.versionName;
        } catch(PackageManager.NameNotFoundException e) {
            appVersion = "unknown";
        }

        setupSimplePreferencesScreen();
    }

    int mCid;

    private void clearChatHistory(int cid, final boolean sendEmail) {

        HashMap<String, String> option = new HashMap<String, String>();
        option.put("id", cid + "");
        option.put("a", "clear");

        WOUApp.buildMainApi().downloadChatHistory(option, new Callback<Response>() {

            @Override
            public void success(Response response, Response response2) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void downloadChatHistory(int cid, final boolean sendEmail) {

        HashMap<String, String> option = new HashMap<String, String>();
        option.put("id", cid + "");

        WOUApp.buildMainApi().downloadChatHistory(option, new Callback<Response>() {

            @Override
            public void success(Response responseBody, Response response2) {
                InputStream inputStream = null;
                String str = "test history\nhaha\nheyhey";
                String fileName = "wouchat_backup_"+System.currentTimeMillis()+".txt";

                if(sendEmail) {
                    String savedFileName = writeToFile(fileName,str);
                    sendEmail(mPref.email().getOr("thanaanon@gmail.com"),savedFileName);
                } else {
                    String savedFileName = writeToFile(fileName,str);
                    Toast.makeText(context,"Saved to " + savedFileName,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public String writeToFile(String fileName, String body)
    {
        FileOutputStream fos = null;

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WOUchat/";
        String targetFilePath = "";

        Log.e("dirPath",dirPath);

        try {
            final File dir = new File(dirPath);

            if (!dir.exists())
            {
                dir.mkdirs();
            }

            final File myFile = new File(dir, fileName);

            if (!myFile.exists())
            {
                myFile.createNewFile();
            }

            fos = new FileOutputStream(myFile);

            fos.write(body.getBytes());
            fos.close();

            targetFilePath = myFile.getAbsolutePath();

            Log.e("targetFilePath",targetFilePath);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return targetFilePath;
    }

    private void sendEmail(String email,String filelocation) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse( "file://"+filelocation));
            intent.putExtra(Intent.EXTRA_TEXT, "Hi, "+mPref.name().getOr("")+" \n This is chat history file, sent from WOUchat Messenger.\nPlease find attachment.");
            intent.setData(Uri.parse("mailto:" + email));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
//            activity.finish();
        } catch(Exception e)  {
            System.out.println("is exception raises during sending mail"+e);
        }
    }

    PrefManager mPref;

    @SuppressWarnings("deprecation")
    private void setupSimplePreferencesScreen() {
        context = this;
        addPreferencesFromResource(R.xml.chat_settings);

        if(getIntent()!= null) {
            mCid = getIntent().getExtras().getInt("cid");
            Log.e("chat_setting_mCid",mCid + "");
        }

        mPref = WOUApp.mPref;


        Preference backupChatPref = (Preference) findPreference("backup_history");
        backupChatPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                downloadChatHistory(mCid,false);
                return false;
            }
        });

        Preference emailChatPref = (Preference) findPreference("email_history");
        emailChatPref.setSummary(mPref.email().getOr(""));
        emailChatPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                downloadChatHistory(mCid,true);
                return false;
            }
        });

//        Preference clearChatPref = (Preference) findPreference("clear_history");
//        clearChatPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                clearChatHistory(mCid,true);
//                return false;
//            }
//        });

        Preference deleteChatPref = (Preference) findPreference("delete_chat");
        deleteChatPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //deleteChat(mCid,true);
                return false;
            }
        });

        Preference changeWallPref = (Preference) findPreference("change_wallpaper");
        changeWallPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(context,ChatWallpaperActivity.class));
                return false;
            }
        });

        Preference notiPref = (Preference) findPreference("toggle_notification");
        notiPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {

                Toast.makeText(context, "Notification: " + o.toString(), Toast.LENGTH_LONG).show();
                //TODO: send mCid to server turn on / off notification
                return true;
            }
        });

        Preference addLanguagePref = (Preference) findPreference("launguage_setting");
        addLanguagePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //startActivity(new Intent(context,LanguageSettingsActivity.class));
                return false;
            }
        });


//        bindPreferenceSummaryToValue(findPreference("notifications_ringtone"));
//        Preference app_version = findPreference("application_version");
//        setPreferenceSummary(app_version, appVersion);
    }



    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    //preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static void setPreferenceSummary(Preference preference, String value) {
        preference.setSummary(value);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        // Allow super to try and create a view first
        final View result = super.onCreateView(name, context, attrs);
        if (result != null) {
            return result;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // If we're running pre-L, we need to 'inject' our tint aware Views in place of the
            // standard framework versions
            switch (name) {
                case "EditText":
                    return new AppCompatEditText(this, attrs);
                case "Spinner":
                    return new AppCompatSpinner(this, attrs);
                case "CheckBox":
                    return new AppCompatCheckBox(this, attrs);
                case "RadioButton":
                    return new AppCompatRadioButton(this, attrs);
                case "CheckedTextView":
                    return new AppCompatCheckedTextView(this, attrs);
            }
        }

        return null;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        super.onPreferenceTreeClick(preferenceScreen, preference);

        if (preference!=null) {
            if (preference instanceof PreferenceScreen) {
                if (((PreferenceScreen) preference).getDialog() != null) {
                    ((PreferenceScreen) preference).getDialog().getWindow().getDecorView().setBackgroundDrawable(this.getWindow().getDecorView().getBackground().getConstantState().newDrawable());
                    setUpNestedScreen((PreferenceScreen) preference);
                }
            }
        }

        return false;
    }

    public void setUpNestedScreen(PreferenceScreen preferenceScreen) {
        final Dialog dialog = preferenceScreen.getDialog();

        AppBarLayout bar;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            LinearLayout root = (LinearLayout) dialog.findViewById(android.R.id.list).getParent();
            bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);
            root.addView(bar, 0);
        } else {
            ViewGroup root = (ViewGroup) dialog.findViewById(android.R.id.content);
            ListView content = (ListView) root.getChildAt(0);
            root.removeAllViews();
            bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root, false);

            int height;
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
                height = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            }else{
                height = bar.getHeight();
            }

            content.setPadding(0, height, 0, 0);

            root.addView(content);
            root.addView(bar);
        }

        Toolbar Tbar = (Toolbar) bar.getChildAt(0);
        Tbar.setTitle(preferenceScreen.getTitle());
        Tbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        if(preference.getKey().equals("backup_history")) {

        } else if(preference.getKey().equals("email_history")) {
            Toast.makeText(context, "email_history", Toast.LENGTH_LONG).show();
            preference.setSummary("heyhey");
        }
        return false;
    }
}

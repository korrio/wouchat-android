package com.module.candychat.net;

import android.content.SharedPreferences;

import com.tale.prettysharedpreferences.BooleanEditor;
import com.tale.prettysharedpreferences.DoubleEditor;
import com.tale.prettysharedpreferences.FloatEditor;
import com.tale.prettysharedpreferences.IntegerEditor;
import com.tale.prettysharedpreferences.LongEditor;
import com.tale.prettysharedpreferences.PrettySharedPreferences;
import com.tale.prettysharedpreferences.StringEditor;

public class ChatPrefManager extends PrettySharedPreferences<ChatPrefManager> {

    public ChatPrefManager(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }


    public BooleanEditor<ChatPrefManager> isCheck() {
        return getBooleanEditor("isCheck");
    }
    public StringEditor<ChatPrefManager> font() {
        return getStringEditor("font");
    }
    public IntegerEditor<ChatPrefManager> intColor() {
        return getIntegerEditor("intColor");
    }

    public StringEditor<ChatPrefManager> name() {
        return getStringEditor("name");
    }

    public StringEditor<ChatPrefManager> username() {
        return getStringEditor("username");
    }

    public StringEditor<ChatPrefManager> password() {
        return getStringEditor("password");
    }

    public StringEditor<ChatPrefManager> email() {
        return getStringEditor("email");
    }

    public StringEditor<ChatPrefManager> about() {
        return getStringEditor("about");
    }

    public IntegerEditor<ChatPrefManager> userId() {
        return getIntegerEditor("userId");
    }

    public StringEditor<ChatPrefManager> token() {
        return getStringEditor("token");
    }

    public StringEditor<ChatPrefManager> cover() {
        return getStringEditor("cover");
    }

    public StringEditor<ChatPrefManager> avatar() {
        return getStringEditor("avatar");
    }



    public StringEditor<ChatPrefManager> fbToken() {
        return getStringEditor("fbToken");
    }

    public StringEditor<ChatPrefManager> fbId() {
        return getStringEditor("fbId");
    }

    public BooleanEditor<ChatPrefManager> isLogin() {
        return getBooleanEditor("isLogin");
    }

    public BooleanEditor<ChatPrefManager> isNoti() {
        return getBooleanEditor("isNoti");
    }

    public LongEditor<ChatPrefManager> resumePosition() {
        return getLongEditor("resume_position");
    }

    public StringEditor<ChatPrefManager> stringValue() {
        return getStringEditor("stringValue");
    }

    public BooleanEditor<ChatPrefManager> booleanValue() {
        return getBooleanEditor("booleanValue");
    }
    public IntegerEditor<ChatPrefManager> integerValue() {
        return getIntegerEditor("integerValue");
    }

    public LongEditor<ChatPrefManager> longValue() {
        return getLongEditor("longValue");
    }

    public FloatEditor<ChatPrefManager> floatValue() {
        return getFloatEditor("floatValue");
    }

    public DoubleEditor<ChatPrefManager> doubleValue() {
        return getDoubleEditor("doubleValue");
    }

    public StringEditor<ChatPrefManager> string(String key) {
        return getStringEditor(key);
    }


    public StringEditor<ChatPrefManager> phoneNumber() {
        return getStringEditor("phoneNumber");
    }

    public StringEditor<ChatPrefManager> phone() {
        return getStringEditor("phone");
    }

    public StringEditor<ChatPrefManager> phoneCode() {
        return getStringEditor("phoneCode");
    }

    public BooleanEditor<ChatPrefManager> signupByPhone() {
        return getBooleanEditor("signupByPhone");
    }

    public IntegerEditor<ChatPrefManager> chatBackground() {
        return getIntegerEditor("chatBackground");
    }



}


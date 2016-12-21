package com.candychat.net.manager;

import android.content.SharedPreferences;

import com.tale.prettysharedpreferences.BooleanEditor;
import com.tale.prettysharedpreferences.DoubleEditor;
import com.tale.prettysharedpreferences.FloatEditor;
import com.tale.prettysharedpreferences.IntegerEditor;
import com.tale.prettysharedpreferences.LongEditor;
import com.tale.prettysharedpreferences.PrettySharedPreferences;
import com.tale.prettysharedpreferences.StringEditor;

public class PrefManager extends PrettySharedPreferences<PrefManager> {

    public PrefManager(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }


    public BooleanEditor<PrefManager> isCheck() {
        return getBooleanEditor("isCheck");
    }
    public StringEditor<PrefManager> font() {
        return getStringEditor("font");
    }
    public IntegerEditor<PrefManager> intColor() {
        return getIntegerEditor("intColor");
    }

    public StringEditor<PrefManager> name() {
        return getStringEditor("name");
    }

    public StringEditor<PrefManager> username() {
        return getStringEditor("username");
    }

    public StringEditor<PrefManager> password() {
        return getStringEditor("password");
    }

    public StringEditor<PrefManager> email() {
        return getStringEditor("email");
    }

    public StringEditor<PrefManager> about() {
        return getStringEditor("about");
    }

    public IntegerEditor<PrefManager> userId() {
        return getIntegerEditor("userId");
    }

    public StringEditor<PrefManager> token() {
        return getStringEditor("token");
    }

    public StringEditor<PrefManager> cover() {
        return getStringEditor("cover");
    }

    public StringEditor<PrefManager> avatar() {
        return getStringEditor("avatarIv");
    }

    public StringEditor<PrefManager> fbToken() {
        return getStringEditor("fbToken");
    }

    public StringEditor<PrefManager> fbId() {
        return getStringEditor("fbId");
    }

    public BooleanEditor<PrefManager> isLogin() {
        return getBooleanEditor("isLogin");
    }

    public BooleanEditor<PrefManager> isNoti() {
        return getBooleanEditor("isNoti");
    }

    public LongEditor<PrefManager> resumePosition() {
        return getLongEditor("resume_position");
    }

    public StringEditor<PrefManager> stringValue() {
        return getStringEditor("stringValue");
    }

    public BooleanEditor<PrefManager> booleanValue() {
        return getBooleanEditor("booleanValue");
    }
    public IntegerEditor<PrefManager> integerValue() {
        return getIntegerEditor("integerValue");
    }

    public LongEditor<PrefManager> longValue() {
        return getLongEditor("longValue");
    }

    public FloatEditor<PrefManager> floatValue() {
        return getFloatEditor("floatValue");
    }

    public DoubleEditor<PrefManager> doubleValue() {
        return getDoubleEditor("doubleValue");
    }

    public StringEditor<PrefManager> string(String key) {
        return getStringEditor(key);
    }

    public StringEditor<PrefManager> gcmToken() {
        return getStringEditor("gcm_token");
    }

    public StringEditor<PrefManager> phoneNumber() {
        return getStringEditor("phoneNumber");
    }

    public StringEditor<PrefManager> phone() {
        return getStringEditor("phone");
    }

    public StringEditor<PrefManager> phoneCode() {
        return getStringEditor("phoneCode");
    }

    public BooleanEditor<PrefManager> signupByPhone() {
        return getBooleanEditor("signupByPhone");
    }

    public IntegerEditor<PrefManager> chatBackground() {
        return getIntegerEditor("chatBackground");
    }

    public IntegerEditor<PrefManager> myCid() {
        return getIntegerEditor("cid");
    }

    public BooleanEditor<PrefManager> notification() {
        return getBooleanEditor("notification");
    }

    public BooleanEditor<PrefManager> allowOther() {
        return getBooleanEditor("allow_other");
    }

    public BooleanEditor<PrefManager> autoAdd() {
        return getBooleanEditor("auto_add");
    }

    public BooleanEditor<PrefManager> showAvatarChange() {
        return getBooleanEditor("show_avatar_change");
    }



}


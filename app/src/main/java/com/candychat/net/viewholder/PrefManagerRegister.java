package com.candychat.net.viewholder;

import android.content.SharedPreferences;

import com.tale.prettysharedpreferences.BooleanEditor;
import com.tale.prettysharedpreferences.DoubleEditor;
import com.tale.prettysharedpreferences.FloatEditor;
import com.tale.prettysharedpreferences.IntegerEditor;
import com.tale.prettysharedpreferences.LongEditor;
import com.tale.prettysharedpreferences.PrettySharedPreferences;
import com.tale.prettysharedpreferences.StringEditor;

public class PrefManagerRegister extends PrettySharedPreferences<PrefManagerRegister> {

    public PrefManagerRegister(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }


    public StringEditor<PrefManagerRegister> name() {
        return getStringEditor("name");
    }

    public StringEditor<PrefManagerRegister> username() {
        return getStringEditor("username");
    }

    public StringEditor<PrefManagerRegister> password() {
        return getStringEditor("password");
    }

    public StringEditor<PrefManagerRegister> email() {
        return getStringEditor("email");
    }

    public StringEditor<PrefManagerRegister> userId() {
        return getStringEditor("userId");
    }



}


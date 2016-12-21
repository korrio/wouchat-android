package com.candychat.net.activity2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wouchat.messenger.R;

public class InputPhoneNumberActivity extends AppCompatActivity {

    String myInternationalNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_phone_number);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        IntlPhoneInput phoneInputView = (IntlPhoneInput) findViewById(R.id.my_phone_input);
//
//        if(phoneInputView.isValid()) {
//            myInternationalNumber = phoneInputView.getNumber();
//            com.module.candychat.net.util.ToastUtils.showCenter(this,myInternationalNumber);
//        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

package com.candychat.net.alogin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.candychat.net.alogin.fragment.RegisterionCompletedFragment;
import com.wouchat.messenger.R;

/**
 * Created by korrio on 2/10/16.
 */
public class RegistrationCompleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        RegisterionCompletedFragment oneFragment = new RegisterionCompletedFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, oneFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

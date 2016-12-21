package com.candychat.net.activity.timeline;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.candychat.net.base.BaseToolbarActivity;

import com.wouchat.messenger.R;

public class MainHomeGroupActivity extends BaseToolbarActivity {
    ImageView wall, media, member;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_group;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        wall = (ImageView) findViewById(R.id.wall);
        media = (ImageView) findViewById(R.id.media);
        member = (ImageView) findViewById(R.id.member);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                WallFragment threeFragment = new WallFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, threeFragment);
//                transaction.commit();
            }
        });

        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InfomationGroupDataFragment threeFragment = new InfomationGroupDataFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, threeFragment);
                transaction.commit();
            }
        });

        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfomationMemberFragment threeFragment = new InfomationMemberFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, threeFragment);
                transaction.commit();
            }
        });
    }

    @Override
    protected void initData() {
//        WallFragment threeFragment = new WallFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, threeFragment);
//        transaction.commit();
    }

}

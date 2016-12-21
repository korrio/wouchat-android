package com.module.candychat.net.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.VideoView;

import com.module.candychat.net.R;
import com.module.candychat.net.fragment.VideoViewNativeFragment;

public class VideoActivity extends AppCompatActivity {
    private String path = "";
    private VideoView mVideoView;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_video);

        path = getIntent().getStringExtra("videoPath");
        Bundle data2 = new Bundle();
        data2.putString("PATH", path);
        VideoViewNativeFragment fragment = new VideoViewNativeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.sub_container, fragment);
        fragment.setArguments(data2);
        transaction.commit();
    }



}

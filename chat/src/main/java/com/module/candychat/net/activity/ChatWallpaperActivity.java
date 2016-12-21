package com.module.candychat.net.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.module.candychat.net.Constants;
import com.module.candychat.net.R;
import com.module.candychat.net.adapter.ChatBackgroupAdapter;

/**
 * Created by korrio on 2/9/16.
 */
public class ChatWallpaperActivity extends ToolbarActivity {

    ChatBackgroupAdapter chatBackgroupAdapter;
    int[] res = {R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,R.drawable.bg5};
    RecyclerView rvContacts;
    RelativeLayout layout_background;
    //public ChatPrefManager mPref;
    //ImageView btn_add,btn_back;
    ImageView imageViewBg;

    int selectedPos;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_chat_background;
    }


    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Select wallpaper");
        //setContentView(R.layout.activity_chat_background);

//        btn_add = (ImageView) findViewById(R.id.btn_add);
//        btn_back = (ImageView) findViewById(R.id.btn_back);
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        //mPref = ChatActivity.mPref;
        layout_background = (RelativeLayout) findViewById(R.id.layout_background);
        chatBackgroupAdapter = new ChatBackgroupAdapter(getApplicationContext(), res);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        setToolbar();
        rvContacts.setAdapter(chatBackgroupAdapter);
        imageViewBg = (ImageView) findViewById(R.id.imageViewBg);

        if(Constants.idBackground != R.drawable.bg4)
            imageViewBg.setImageResource(Constants.idBackground);

        chatBackgroupAdapter.SetOnItemClickListener(new ChatBackgroupAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                chatBackgroupAdapter.notifyItemChanged(position);
//                selectedPosition = chatBackgroupAdapter.getLayoutPosition();
//                chatBackgroupAdapter.notifyItemChanged(selectedPos);

                //int idBackground = res[position];
                selectedPos = position;
                //imageViewBg.setBackgroundResource(res[position]);
                imageViewBg.setImageResource(res[position]);

                Constants.idBackground = res[selectedPos];
                //mPref.chatBackground().put(idBackground);
                //mPref.commit();
            }
        });

//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int idBackground = res[selectedPos];
//                mPref.chatBackground().put(idBackground);
//                mPref.commit();
//                finish();
//            }
//        });
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

    }

    private void setToolbar() {

        setTitle("Chat Background");
        setAppBarAlpha(0.7f);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

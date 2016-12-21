package com.candychat.net.noti;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.WOUApp;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;


public class ShowCallPopUp extends Activity implements OnClickListener {
    Button ok;
    Button cancel;
    boolean click = true;  
    
    TextView msgTv;
    ImageView avatar;
    
    String title;
    String msg;
    
    String session;
    String from_id;
    String type;
    String from_avatar;
    String customdata;

    Activity mActivity;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.popdialog);

        mActivity = this;
         
         title = getIntent().getStringExtra("title");
         msg = getIntent().getStringExtra("msg");

        customdata = getIntent().getStringExtra("customdata");
         from_avatar = getIntent().getStringExtra("from_avatar");
         from_id = getIntent().getStringExtra("from_id");
         session = getIntent().getStringExtra("session");
         type = getIntent().getStringExtra("type");
         setTitle(title);  
        
         msgTv = (TextView) findViewById(R.id.msg_tv);
         avatar = (ImageView) findViewById(R.id.avatar_img);
         Picasso.with(this).load(WOUApp.SOCIAL_ENDPOINT + from_avatar).centerCrop().resize(100,100).transform(new RoundedTransformation(50,4)).into(avatar);
         
         msgTv.setText(msg);
         ok = (Button)findViewById(R.id.buttonOk);
         ok.setOnClickListener(this);  
         cancel = (Button)findViewById(R.id.buttonCancel);
         cancel.setOnClickListener(this);  
    }  
    
    @Override
    public void onClick(View view) {
//          if(view == ok) {
//
//              Utils.showToast(customdata);
//
//        	  if(type.equals("504")) {
//
//                  ChatActivityMy.startChatActivity(ShowCallPopUp.this, WOUApp.mPref.userId().getOr(0), Integer.parseInt(from_id), 0);
//                  ChatUIActivity.connectToRoom(mActivity, customdata, false);
//              } else if(type.equals("505")) {
//
//                  ChatActivityMy.startChatActivity(ShowCallPopUp.this,WOUApp.mPref.userId().getOr(0), Integer.parseInt(from_id), 0);
//                  ChatUIActivity.connectToRoom(mActivity, customdata,true);
//        	   }
//          }
         finish();
    }


}  

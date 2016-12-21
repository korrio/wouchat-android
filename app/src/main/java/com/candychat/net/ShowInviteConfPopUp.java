package com.candychat.net;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wouchat.messenger.R;

public class ShowInviteConfPopUp extends Activity implements OnClickListener {
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
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.popdialog);
         
         title = getIntent().getStringExtra("title");
         msg = getIntent().getStringExtra("msg");
         
         String from_avatar = getIntent().getStringExtra("from_avatar");
         from_id = getIntent().getStringExtra("from_id");
         session = getIntent().getStringExtra("session");
         type = getIntent().getStringExtra("type");
         setTitle(title);  
        
         msgTv = (TextView) findViewById(R.id.msg_tv);
         avatar = (ImageView) findViewById(R.id.avatar_img);
         //Picasso.with(this).load(DataUser.BASE + from_avatar).into(avatarIv);
         
         msgTv.setText(msg);
         ok = (Button)findViewById(R.id.buttonOk);
         ok.setOnClickListener(this);  
         cancel = (Button)findViewById(R.id.buttonCancel);
         cancel.setOnClickListener(this);  
    }  
    
    @Override
    public void onClick(View view) {
          if(view == ok) {

              Toast.makeText(this, type, Toast.LENGTH_LONG).show();
        	  
          }
         finish();  
    }  
}  

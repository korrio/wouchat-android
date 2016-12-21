package com.module.candychat.net.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.module.candychat.net.ChatActivity;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import com.module.candychat.net.R;
import com.module.candychat.net.handler.ApiBus;
import com.module.candychat.net.widgets.CustomTypefaceSpan;


public class MainLoginActivity extends AppCompatActivity {

    Button button;
    private Integer mUserId;
    private String mName;
    private String mUsername;
    private String mAvatarUrl ;

    private Integer mPartnerId;
    private String mPartnerName;
    private String mPartnerUsername;
    private String mPartnerAvatarUrl;

    private Integer mCid;
    private Integer mChatType;
    EditText mUserIdEt;
    EditText mPartnerIdEt;
    EditText mConverstionIdEt;
    ArrayList<String> chatType = new ArrayList<String>();
    int chattype ;
    String[] type = {"0","1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_login);
        ApiBus.getInstance().postQueue(new GetRecentChatEvent(6));
        Typeface font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/SWZ721BR.ttf");
        SpannableStringBuilder SS = new SpannableStringBuilder("WOUchat");
        SS.setSpan(new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(SS);
        button = (Button) findViewById(R.id.button);

        final Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);

        spinner.setAdapter(new MyCustomAdapter(getApplication(), R.layout.item_type, type));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chatTypeId = spinner.getSelectedItem().toString();
                chattype = Integer.parseInt(chatTypeId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mUserIdEt= (EditText) findViewById(R.id.userid);
        mPartnerIdEt = (EditText) findViewById(R.id.parnerid);
        mConverstionIdEt = (EditText) findViewById(R.id.conversation_id);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = mUserIdEt.getText().toString();
                String PartnerID = mPartnerIdEt.getText().toString();
                String ConverstionID = mConverstionIdEt.getText().toString();

                int userid = Integer.parseInt(userID);
                int parnerid = Integer.parseInt(PartnerID);
                int conversationid = Integer.parseInt(ConverstionID);

                Log.e("userid",userid+"");
                Log.e("parnerid",parnerid+"");
                Log.e("conversationid",conversationid+"");

                Intent i = new Intent(getApplicationContext(),ChatActivity.class);
                i.putExtra("USER_ID_1",userid);
                i.putExtra("USER_ID_2",parnerid);
                i.putExtra("CHAT_TYPE",chattype);
                i.putExtra("CONVERSATION_ID",conversationid);
                i.putExtra("image",mPartnerAvatarUrl);
                i.putExtra("mAvatarUrl",mAvatarUrl);
                startActivity(i);
            }
        });

    }

    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.item_type, parent, false);
            TextView label = (TextView) row.findViewById(R.id.weekofday);
            label.setText(type[position]);


            return row;
        }
    }
    @Subscribe
    public void onGetRecentChatSuccess(GetRecentChatSuccess event) {
        Log.e("fffff", event.response.getContent().size() + "");

    }


}

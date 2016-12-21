package com.module.candychat.net;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.module.candychat.net.event.InterpretMessageEvent;
import com.module.candychat.net.handler.ApiBus;
import com.module.candychat.net.wouclass.event.SeeEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import butterknife.ButterKnife;


public abstract class BaseChatActivity extends FragmentActivity {
    public Context mContext;
    public Integer mCid;

    public List<Integer> mCurrentInRoom = new ArrayList<>();

    public int mChatType; // 0 = 1-1 chat, 1 = public group chat, 2 = private group chat
    public int mUserId;
    public int mPartnerId;

    public boolean isConnected = false;
    public static final String CHAT_SERVER = "http://candychat.net:1313";
    public String socketUrl = CHAT_SERVER;
    public Socket mSocket;
    {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, null, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        IO.setDefaultSSLContext(sc);
        IO.Options opts = new IO.Options();
        opts.secure = true;
        opts.sslContext = sc;

        try {
            mSocket = IO.socket(socketUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    int cI = 0;

    public void initConnect(final int mUserId) {

        mSocket.on("ping", sendPong);
        //if (!isConnected) {
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    //mSocket.emit("OnlineUser");
                    JSONObject jObj = new JSONObject();
                    try {
                        jObj.put("userId", mUserId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mSocket.emit("Authenticate", jObj);
                    //addUser(mUsername); //username
                }
            });
        //}

        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                //mSocket.emit("OnlineUser");
                Log.e("55555","Why disconnecting me 555");
                //addUser(mUsername); //username
            }
        });

        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on("Authenticate:Success", onAuthSuccess);
        mSocket.on("Authenticate:Failure", onAuthFailure);
        mSocket.on("JoinRoomSuccess", onUserJoined);
        mSocket.on("JoinRoom:Success", onUserJoined);
        mSocket.on("JoinRoomFailure", onUserJoinedFailed);
        mSocket.on("JoinRoom:Failure", onUserJoinedFailed);

        mSocket.on("SendMessage", onSendMessage);
        mSocket.on("EnterRoom", onUserEnter);
        mSocket.on("LeaveRoom", onUserLeft);
        mSocket.on("Typing", onTyping);
        //mSocket.on("Read",null);
        //mSocket.on("login" , onLogin);
        mSocket.on("OnlineUser", onOnlineUser);
        mSocket.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.off(Socket.EVENT_CONNECT);
        mSocket.off(Socket.EVENT_DISCONNECT);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.off("Authenticate:Success", onAuthSuccess);
        mSocket.off("Authenticate:Failure", onAuthFailure);
        mSocket.off("JoinRoomSuccess", onUserJoined);
        mSocket.off("JoinRoom:Success", onUserJoined);
        mSocket.off("JoinRoomFailure", onUserJoinedFailed);
        mSocket.off("JoinRoom:Failure", onUserJoinedFailed);

        mSocket.off("SendMessage", onSendMessage);
        mSocket.off("LeaveRoom", onUserLeft);
        mSocket.off("Typing", onTyping);
        //mSocket.off("Read",null);
        //mSocket.off("login" , onLogin);
        mSocket.off("OnlineUser", onOnlineUser);
        mSocket.disconnect();

        isConnected = false;
    }

    private Emitter.Listener sendPong = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String ping;
                    try {
                        ping = data.getString("ping");
                    } catch (JSONException e) {
                        return;
                    }
                    if(ping.equals("1")){
                        mSocket.emit("pong", "pong");
                    }
                    Log.e("SOCKETPING", "RECEIVED PING! ");
                }
            });
        }
    };

    boolean mTyping = false;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);


        if (shouldInstallDrawer()) {
            //setupDrawer();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void setupToolbar() {

    }

    protected boolean shouldInstallDrawer() {
        return true;
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    */

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiBus.getInstance().register(this);

        if(isConnected && mSocket != null) {
            JSONObject jObj = new JSONObject();
            try {
                jObj.put("userId", mUserId);
                jObj.put("conversationId", mCid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mSocket.emit("EnterRoom",jObj);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        ApiBus.getInstance().unregister(this);

        if(isConnected && mSocket != null) {
            JSONObject jObj = new JSONObject();
            try {
                jObj.put("userId", mUserId);
                jObj.put("conversationId", mCid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mSocket.emit("LeaveRoom",jObj);
        }
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }


//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        Events.register(this);
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // EMITTER GROUP
    private Emitter.Listener onAuthSuccess = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            JSONObject jObj = new JSONObject();
            isConnected = true;
            try {
                jObj.put("conversationId", mCid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mSocket.emit("JoinRoom", jObj);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("onAuthSuccess#", "" + cI);
                    cI++;
                    //Toast.makeText(getApplicationContext(), "onAuthSuccess:" + mCid, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private Emitter.Listener onAuthFailure = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getApplicationContext(), "onAuthFailure:" + mCid, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "onConnectError: check internet connection", Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    private Emitter.Listener onOnlineUser = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                }
            });
        }
    };
    private Emitter.Listener onUserJoinedFailed = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject obj = (JSONObject) args[0];
//            try {
//                Log.e("onUserJoinedFailed",obj.toString(4));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "userId: " + mUserId + " failed entered room: " + mCid, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject obj = (JSONObject) args[0];
            try {
                Log.e("onUserJoinedFailed",obj.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject jObj = new JSONObject();
                    try {
                        jObj.put("userId", mUserId);
                        jObj.put("conversationId", mCid);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mSocket.emit("EnterRoom",jObj);
                    //Toast.makeText(getApplicationContext(), "userId: " + mUserId + "  enter room: " + mCid, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

        }
    };

    int theUserId;

    private Emitter.Listener onUserEnter = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            final JSONObject data = (JSONObject) args[0];
//            try {
//                //Log.e("onUserEnter",data.toString(4));
//                //Toast.makeText(mContext,"userId:" +data.optString("userId") + " enter cId:" + data.optString("conversationId"),Toast.LENGTH_LONG).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject jObj = new JSONObject();
                    try {
                        jObj.put("userId", mUserId);
                        jObj.put("conversationId", mCid);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                    JSONObject mydata = (JSONObject) args[0];
                    theUserId = mydata.optInt("userId");
                    mCurrentInRoom.add(theUserId);

                    if(mUserId != theUserId) {
                        mSocket.emit("See",jObj);
                        ApiBus.getInstance().post(new SeeEvent());
                    }




                }
            });
        }
    };

    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];

                    addPartnerTyping();
                }
            });
        }
    };

    private void addPartnerTyping() {
        ApiBus.getInstance().postQueue(new TypingEvent());
    };



    private Emitter.Listener onSendMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            Log.e("onSendMessage", ((JSONObject) args[0]).toString());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

                Toast.makeText(getApplicationContext().getApplicationContext(), "Default Signature Fail", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            if (getApplicationContext() == null)
                return;

            ApiBus.getInstance().postQueue(new InterpretMessageEvent((JSONObject) args[0]));
        }
    };

    public Runnable onTypingTimeout = new Runnable() {
        @Override
        public void run() {

            mTyping = false;

            JSONObject jObj = new JSONObject();
            try {
                jObj.put("conversationId", mCid);
                jObj.put("isTyping", mTyping);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mSocket.emit("Typing",jObj);
            if(!mTyping)
                ApiBus.getInstance().post(new NotTypingEvent());
        }
    };

    public Handler mTypingHandler = new Handler();

    public static final int TYPING_TIMER_LENGTH = 600;


}
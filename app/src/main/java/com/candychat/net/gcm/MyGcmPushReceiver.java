package com.candychat.net.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.candychat.net.activity.MainActivity;
import com.google.android.gms.gcm.GcmListenerService;
import com.module.candychat.net.ChatActivity;

/**
 * Created by korrio on 2/12/16.
 */
public class MyGcmPushReceiver extends GcmListenerService {

    private static final String TAG = MyGcmPushReceiver.class.getSimpleName();

    private NotificationUtils notificationUtils;

    /**
     * Called when message is received.
     *
     * @param from   SenderID of the sender.
     * @param bundle Data bundle containing message data as key/value pairs.
     *               For Set of keys use data.keySet().
     */

    @Override
    public void onMessageReceived(String from, Bundle bundle) {

        for (String key : bundle.keySet())
        {
            Log.d("Bundle Debug", key + " = \"" + bundle.get(key) + "\"");
        }

        String notiType = bundle.getString("type");
        String cid = bundle.getString("cid");
        String toId = bundle.getString("to_id");
        String fromId = bundle.getString("from_id");

        String title = bundle.getString("title");
        String message = bundle.getString("message");
        String image = bundle.getString("image");
        String timestamp = bundle.getString("created_at");
        String senderId = bundle.getString("sender_id");

        String chatType = bundle.getString("chat_type");
//        Log.e(TAG, "Conversation ID: " + cid);
//        Log.e(TAG, "Chat Type: " + chatType);
//
//        Log.e(TAG, "From ID: " + fromId);
//        Log.e(TAG, "To ID: " + toId);
//        //Log.e(TAG, "Sender ID: " + senderId);
//        Log.e(TAG, "From: " + from);
//        Log.e(TAG, "Title: " + title);
//        Log.e(TAG, "message: " + message);
//        Log.e(TAG, "image: " + image);
//        Log.e(TAG, "timestamp: " + timestamp);

        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {

            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("cid", cid);
            pushNotification.putExtra("message", message);
            pushNotification.putExtra("type", notiType);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils();
            notificationUtils.playNotificationSound();
        } else {

            //Toast.makeText(App.getInstance(),"" + message,Toast.LENGTH_LONG).show();

            //Intent resultIntent = new Intent(getApplicationContext(), LoginActivity.class);
            //resultIntent.putExtra("message", message);

            Intent resultIntent;
            if(cid != null && toId != null && senderId != null & chatType != null) {
                resultIntent  = ChatActivity.startChatIntent(getApplicationContext(),
                        Integer.parseInt(cid),
                        Integer.parseInt(toId),
                        Integer.parseInt(senderId),
                        Integer.parseInt(chatType));
            } else {
                resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            }



            if (TextUtils.isEmpty(image)) {
                showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
            } else {
                showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, image);
            }
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
}

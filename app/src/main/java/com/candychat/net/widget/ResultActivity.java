package com.candychat.net.widget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.candychat.net.Utils;
import com.candychat.net.WOUApp;
import com.candychat.net.event.upload.UpdateAvatarEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.handler.ApiServiceWOU;
import com.candychat.net.model.media.UploadAvatarCallback;
import com.wouchat.messenger.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;


public class ResultActivity extends Activity {

    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFileRetrofit(((WOUApp) getApplication()).tempFile,WOUApp.mPref.userId().getOr(0),"avatar");
            }
        });

        // apply custom font
        //FontUtils.setFont((ViewGroup)findViewById(R.id.layout_root));
        // get cropped bitmap from Application
        Bitmap cropped = ((WOUApp)getApplication()).cropped;
        // set cropped bitmap to ImageView
        ((ImageView)findViewById(R.id.result_image)).setImageBitmap(cropped);
    }

    ApiServiceWOU buildUploadApi() {

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(WOUApp.API_ENDPOINT)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addQueryParam("p1", "var1");
                        //request.addQueryParam("p2", "");
                    }
                })
                .build()
                .create(ApiServiceWOU.class);
    }

    public void uploadFileRetrofit(File file, int timelineId, String type) {

        TypedFile typedFile = new TypedFile("multipart/form-data", file);

        Map<String, String> options = new HashMap<String, String>();


        if (type.equals("avatar")) {
            buildUploadApi().uploadAvatar(timelineId,typedFile, new Callback<UploadAvatarCallback>() {
                @Override
                public void success(UploadAvatarCallback uploadCb, Response response) {

                    if (uploadCb != null && uploadCb.getStatus().equals("1")) {

                        Utils.showToast("Update avatar success!");

                        WOUApp.mPref.avatar().put(uploadCb.getUser().getAvatar()).commit();
                        ApiBus.getInstance().post(new UpdateAvatarEvent(uploadCb));

                        finish();
                    } else {
                        Log.e("responseUrl", response.getUrl());
                    }


                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("myAvatarUrl", error.getMessage() + "");
                    //Utils.showToast("Update avatar failed!");

                }
            });
        }


    }
}

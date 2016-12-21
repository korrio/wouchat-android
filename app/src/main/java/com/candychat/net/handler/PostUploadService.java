package com.candychat.net.handler;


import com.candychat.net.model.feed.UploadPostCallback;

import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

public interface PostUploadService {

    @Multipart
    @POST("/request.php?t=post&a=new&user_id=3&token=123456&user_pass=039a726ac0aeec3dde33e45387a7d4ac")
    void uploadPostPhoto(
            @Part("text") String text,
            @Part("timeline_id") String timelineId,
            @Part("recipient_id") String recipientId,
            @Part("photos[]") TypedFile file,
            Callback<UploadPostCallback> cb);

    @Multipart
    @POST("/request.php?t=post&a=new&user_id=3&token=123456&user_pass=039a726ac0aeec3dde33e45387a7d4ac")
    void uploadPostClip(
            @Part("text") String text,
            @Part("timeline_id") String timelineId,
            @Part("recipient_id") String recipientId,
            @Part("clips[]") TypedFile file,
            Callback<UploadPostCallback> cb);



}

package com.module.candychat.net.upload;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

public interface MainApiService {


    @Multipart
    @POST("/upload")
    void upload(@Part("file[]") TypedFile file,
                @Part("description") String description,
                Callback<UploadCallback> cb);

    @GET("/user/{id}")
    public void getProfile(@Path("id") int id,
                           Callback<UserProfileDataResponse> responseJson);


}

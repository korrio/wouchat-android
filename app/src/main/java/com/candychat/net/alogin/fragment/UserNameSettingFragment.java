package com.candychat.net.alogin.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.PhotoUtils;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.Utils;
import com.candychat.net.WOUApp;
import com.candychat.net.alogin.activity.RegistrationCompleteActivity;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.event.CheckUsernameEvent;
import com.candychat.net.event.LoginSuccessEvent;
import com.candychat.net.event.RegisterEvent;
import com.candychat.net.event.RegisterSuccessEvent;
import com.candychat.net.event.UsernameNotOkEvent;
import com.candychat.net.event.UsernameOkEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.handler.ApiServiceWOU;
import com.candychat.net.model.media.UploadAvatarCallback;
import com.candychat.net.model.UserProfile;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import java.io.File;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class UserNameSettingFragment extends BaseFragment {
    private CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    EditText dtUserName;
    ImageView imgAvatar;
    CheckBox imgAllowAdd;
    CheckBox imgAutoAddFriend;
    Button btnDone;
    Typeface type;
    PrefManager mPref;
    String name;
    String password;
    String email;
    String username;
    Dialog dialog;

    TextView usernameStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.username_setting, container, false);
        if(toolbar != null)
            toolbar.setTitle("Set avatarIv and username");

        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id
                .coordinatorLayout);
        type = WOUApp.CustomFontTypeFace();

        mPref = WOUApp.get(getActivity()).getPrefManager();
        name = mPref.name().getOr("").trim();
        password = mPref.password().getOr("").trim();
        email = mPref.email().getOr("").trim();

        usernameStatus = (TextView) rootView.findViewById(R.id.username_status);

        dtUserName = (EditText) rootView.findViewById(R.id.input_username_setting);
        imgAvatar = (ImageView) rootView.findViewById(R.id.img_avatar);
        imgAllowAdd = (CheckBox) rootView.findViewById(R.id.img_allow_add);
        imgAutoAddFriend = (CheckBox) rootView.findViewById(R.id.img_auto_add_friend);


        if(WOUApp.mPref.signupByPhone().getOr(false)) {
            dtUserName.setText("0" + WOUApp.mPref.phone().getOr("000000"));
        } else {
            String email = mPref.email().getOr("");
            if(email.toLowerCase().contains("@")){
                String[] parts = email.split("@");
                dtUserName.setText(parts[0]);
            }

        }

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAvatar();
            }
        });

        btnDone = (Button) rootView.findViewById(R.id.btn_done);
        btnDone.setTypeface(type);
        dtUserName.setTypeface(type);

        dtUserName.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                username = dtUserName.getText().toString().trim();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
//                if(s.length() != 0)
//                    dtUserName.setText("");
//

                if(s.length() >= 6) {
                    ApiBus.getInstance().post(new CheckUsernameEvent(s.toString()));
                }

                }
        });
        dialog = new Dialog(getActivity(), R.style.FullHeightDialog);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = dtUserName.getEditableText().toString().trim();

                dialog.setContentView(R.layout.dialog_login);
                dialog.show();

                WOUApp.mPref.username().put(username).commit();

//                RegisterEvent event = new RegisterEvent(
//                        name, username, password, email, "male");
//                mPref.username().put(username);
//                mPref.commit();
//                ApiBus.getInstance().post(event);

                if(WOUApp.mPref.signupByPhone().getOr(false)) {
                    String phone =  WOUApp.mPref.phone().getOr("");;
                    String phoneCode = WOUApp.mPref.phoneCode().getOr("");

                    if(name.equals(""))
                        name = "+" + phoneCode + phone;

                    RegisterEvent event = new RegisterEvent(name,username, password, email, "male",phone,phoneCode);
                    ApiBus.getInstance().post(event);
                } else {

                    if(name.equals(""))
                        name = username;

                    RegisterEvent event = new RegisterEvent(name, username, password, email, "male");
                    Log.e("POST!",event.toString());
                    ApiBus.getInstance().post(event);
                }


            }

        });
        return rootView;
    }

    @Subscribe public void usernameOk(UsernameOkEvent event) {
        usernameStatus.setVisibility(View.VISIBLE);
        usernameStatus.setTextColor(Color.GREEN);
        usernameStatus.setText("✔");
    }

    @Subscribe public void usernameNotOk(UsernameNotOkEvent event) {
        usernameStatus.setVisibility(View.VISIBLE);
        usernameStatus.setTextColor(Color.RED);
        usernameStatus.setText("✘");
    }

    @Subscribe
    public void onRegisterSuccess(RegisterSuccessEvent event) {
//        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Register Success", Snackbar.LENGTH_LONG);
//        snackbar.show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
               getActivity().startActivity(new Intent(getActivity(), RegistrationCompleteActivity.class));

                //getActivity().finish();


            }
        }, 1500);

    }

    @Subscribe
    public void onLoginSuccess(LoginSuccessEvent event) {
        mPref
                .name().put(event.getLoginData().user.name)
                .username().put(event.getLoginData().user.username)
                .password().put(event.getLoginData().user.password)
                .email().put(event.getLoginData().user.email)
                .userId().put(event.getLoginData().user.id)
                .token().put(event.getLoginData().token)
                .cover().put(event.getLoginData().user.cover)
                .avatar().put(event.getLoginData().user.avatar)
                .isLogin().put(true)
                .commit();


        Log.e("VM_PROFILE", event.getLoginData().user.toString());

        //UserProfile registeredUserProfile = event.getLoginData().user;


        UserProfile user = event.getLoginData().user;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode
            , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", requestCode + " + " + resultCode);
        if (requestCode == REQUEST_GALLERY && resultCode == getActivity().RESULT_OK) {

            String selectedImagePath = PhotoUtils.getPath(getActivity(),data.getData());

            //Bitmap bmp = PhotoUtils.createScaledBitmap(selectedImagePath,200,200)
            Picasso.with(getActivity())
                    .load(selectedImagePath)
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 2))
                    .into(imgAvatar);

            File file = new File(selectedImagePath);
            //if (isAvatar) {
                uploadFileRetrofit(file, userId,"avatarIv");
            //}


        } else if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK) {
            //Uri selectedImageUri = data.getData();
            String selectedImagePath = PhotoUtils.getPath(getActivity(),data.getData());

            Picasso.with(getActivity())
                    .load(selectedImagePath)
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 2))
                    .into(imgAvatar);

            File file = new File(selectedImagePath);
            //if (isAvatar) {
                uploadFileRetrofit(file, userId,"avatarIv");
            //}
        }

    }

    ApiServiceWOU buildUploadApi() {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(WOUApp.API_ENDPOINT)

                .setRequestInterceptor(new RequestInterceptor() {
                    @Override public void intercept(RequestFacade request) {
                        //request.addQueryParam("p1", "var1");
                        //request.addQueryParam("p2", "");
                    }
                })
                .build()
                .create(ApiServiceWOU.class);
    }

    private void uploadFileRetrofit(File file, int timelineId,String type) {
        TypedFile typedFile = new TypedFile("multipart/form-data", file);

        if(type.equals("avatarIv")) {
            buildUploadApi().uploadAvatar(WOUApp.mPref.userId().getOr(0),typedFile, new Callback<UploadAvatarCallback>() {
                @Override
                public void success(UploadAvatarCallback uploadCb, Response response) {

                    Log.e("response",response.getBody().toString());

                    if(uploadCb != null && uploadCb.getStatus().equals("1") )  {
                        Picasso.with(getActivity())
                                .load(uploadCb.getAvatar().getThumb())
                                .centerCrop()
                                .resize(200, 200)
                                .transform(new RoundedTransformation(100, 2))
                                .into(imgAvatar);

                        Utils.showToast("Update avatarIv success!");
                        WOUApp.mPref.avatar().put(uploadCb.getUser().getAvatar()).commit();

                    } else {
                        Log.e("responseUrl",response.getUrl());
                    }



                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("myAvatarUrl", error.getMessage() + "");
                    Utils.showToast("Update avatarIv failed!");

                }
            });
        }


    }

    public PrefManager prefManager;
    public static final int REQUEST_GALLERY = 1;
    public static final int REQUEST_CAMERA = 2;
    public String url = "";
    public int userId ;

    public void selectAvatar() {
        final CharSequence[] items = {"Choose from Gallery", "Take from Camera",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Update avatarIv");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Choose from Gallery")) {
                    pickImage();
                } else if (items[item].equals("Take from Camera")) {
                    pickCamera();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void pickImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_GALLERY);
    }

    public void pickCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

}
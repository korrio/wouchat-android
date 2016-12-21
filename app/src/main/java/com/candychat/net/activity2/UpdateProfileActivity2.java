package com.candychat.net.activity2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.binaryfork.spanny.Spanny;
import com.candychat.net.Utils;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.timeline.ChatInfoActivity;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.event.CheckUsernameEvent;
import com.candychat.net.event.UsernameNotOkEvent;
import com.candychat.net.event.UsernameOkEvent;
import com.candychat.net.event.upload.UpdateAvatarEvent;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.handler.ApiServiceWOU;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.model.media.UploadAvatarCallback;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.MyCustomTextView;
import com.candychat.net.view.RoundedTransformation;
import com.candychat.net.widget.CropActivity;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.module.candychat.net.util.ToastUtils;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wouchat.messenger.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class UpdateProfileActivity2 extends BaseActivity  implements EditNameDialogFragment.EditNameDialogListener {

    ImageView txt_qr;
    public PrefManager mPref;
    public static final int REQUEST_GALLERY = 1;
    public static final int REQUEST_CAMERA = 2;
    public String url = "";
    public int userId;
    public TextView input_name;
    public TextView input_status;
    public TextView input_username;
    public TextView input_phone;

    public ImageView avatarIv;
    boolean isAvatar = true;
    ImageView cameraOverlay;
    ImageView cameraOverlay2;
    Button btn_home;
    ImageView img_qr_code;
    ImageView image_hone_timeline;
    String name;
    String status;
    String userName;

    MyCustomTextView txt_save;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                    | ActionBar.DISPLAY_SHOW_TITLE
                    | ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText(getResources().getString(R.string.edit_profile), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile_setting;
    }

    String thePhoneNumber;
    String phone;
    String code;

    //DigitsAuthButton verifyPhoneBtn;
    Button verifyPhoneBtn;
    Button showQrBtn;

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    LinearLayout status_box;
    LinearLayout name_box;

    @Override
    protected void onResume() {
        super.onResume();
        if(input_status != null)
        input_status.setText(mPref.about().getOr(""));
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {



        input_name = (TextView) findViewById(R.id.input_name);
        input_username = (TextView) findViewById(R.id.input_username);
        input_status = (TextView) findViewById(R.id.input_status);
        input_phone = (TextView) findViewById(R.id.input_phone);

        btn_home = (Button) findViewById(R.id.btn_home);
        image_hone_timeline = (ImageView) findViewById(R.id.image_hone_timeline);
        //txt_save = (MyCustomTextView) findViewById(R.id.txt_save);
        //  nameTv = (TextView) findViewById(R.id.nameTv);
        avatarIv = (ImageView) findViewById(R.id.avatar);
        avatarCamera = (ImageView) findViewById(R.id.avatar_camera);

        img_qr_code = (ImageView) findViewById(R.id.img_qr_code);
        txt_qr = (ImageView) findViewById(R.id.img_qr_code);

        input_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateProfileActivity2.this,InputPhoneNumberActivity.class));
            }
        });

        input_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        input_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        input_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        input_status.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        verifyPhoneBtn = (Button) findViewById(R.id.verify_phone_button);
        verifyPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginPhone(view);
            }
        });

//        if(!mPref.phone().getOr("").equals("")) {
//            verifyPhoneBtn.setVisibility(View.VISIBLE);
//            //input_phone.setVisibility(View.GONE);
//        } else {
//            verifyPhoneBtn.setVisibility(View.GONE);
//            //input_phone.setVisibility(View.VISIBLE);
//        }

        showQrBtn = (Button) findViewById(R.id.show_qr_code);
        showQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateProfileActivity2.this,ShowQRCodeActivity.class));
            }
        });

        status_box = (LinearLayout) findViewById(R.id.status_box);
        status_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateProfileActivity2.this,UpdateStatusActivity.class));
            }
        });
        input_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateProfileActivity2.this,UpdateStatusActivity.class));
            }
        });

        name_box = (LinearLayout) findViewById(R.id.name_box);
        name_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                EditNameDialogFragment editNameDialogFragment = EditNameDialogFragment.newInstance(input_name.getText().toString());
                editNameDialogFragment.show(fm, "fragment_edit_name");
            }
        });
        input_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                EditNameDialogFragment editNameDialogFragment = EditNameDialogFragment.newInstance(input_name.getText().toString());
                editNameDialogFragment.show(fm, "fragment_edit_name");
            }
        });


//        Digits.getSessionManager().clearActiveSession();
//
//        verifyPhoneBtn = (DigitsAuthButton) findViewById(R.id.verify_phone_button);
//        verifyPhoneBtn.setText("Verify phone number");
//        verifyPhoneBtn.setBackgroundColor(getResources().getColor(R.color.primary));
//        verifyPhoneBtn.setAuthTheme(R.style.CustomDigitsTheme);
//        verifyPhoneBtn.setCallback(new AuthCallback() {
//            @Override
//            public void success(DigitsSession session, String phoneNumber) {
//                // TODO: associate the session userID with your user model
//                Log.d("digit_session", session + "Authentication successful for "
//                        + phoneNumber);
//
//                if(phoneNumber == null) {
//                    thePhoneNumber = Digits.getSessionManager().getActiveSession().getPhoneNumber();
//                } else {
//                    thePhoneNumber = phoneNumber;
//                }
//
//                // thePhoneNumber = "+66910833820";
//                code = thePhoneNumber.substring(1,3);
//                phone = thePhoneNumber.substring(3,thePhoneNumber.length());
//
//                Log.d("code",code);
//                Log.d("phone",phone);
//                Log.d("thePhoneNo",thePhoneNumber);
//
//
//
//                ApiBus.getInstance().post(new CheckUsernameEvent("0" + phone));
//
//            }
//
//            @Override
//            public void failure(DigitsException exception) {
//                Log.d("Digits", "Sign in with Digits failure", exception);
//            }
//        });
//
//        if(WOUApp.mPref.phone().getOr("").equals("")) {
//            verifyPhoneBtn.setVisibility(View.VISIBLE);
//            input_phone.setVisibility(View.GONE);
//        }

//        txt_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadProfile(userId);
//            }
//        });
    }

    private static final int FRAMEWORK_REQUEST_CODE = 123;

    public void onLoginEmail(final View view) {
        onLogin(LoginType.EMAIL);
    }

    public void onLoginPhone(final View view) {
        onLogin(LoginType.PHONE);
    }

    private void onLogin(final LoginType loginType) {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = createAccountKitConfiguration(loginType);
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, FRAMEWORK_REQUEST_CODE);
    }

    private AccountKitConfiguration.AccountKitConfigurationBuilder createAccountKitConfiguration(
            final LoginType loginType) {
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                loginType,
                getResponseType());

        configurationBuilder.setTheme(R.style.AppLoginTheme_Wouchat);
        configurationBuilder.setTitleType(AccountKitActivity.TitleType.APP_NAME);
        configurationBuilder.setFacebookNotificationsEnabled(true);
        configurationBuilder.setReadPhoneStateEnabled(true);
        configurationBuilder.setReceiveSMS(true);

        return configurationBuilder;
    }

    private AccountKitActivity.ResponseType getResponseType() {
        //final Switch responseTypeSwitch = (Switch) findViewById(R.id.response_type_switch);
        //if (responseTypeSwitch != null && responseTypeSwitch.isChecked()) {
        if (true) {
            return AccountKitActivity.ResponseType.TOKEN;
        } else {
            return AccountKitActivity.ResponseType.CODE;
        }
    }


    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FRAMEWORK_REQUEST_CODE) {
            final AccountKitLoginResult loginResult =
                    data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            final String toastMessage;
            if(loginResult != null) {
                if (loginResult.getError() != null) {
                    toastMessage = loginResult.getError().getErrorType().getMessage();
                    ToastUtils.showCenter(this,toastMessage);
                    //showErrorActivity(loginResult.getError());
                } else if (loginResult.wasCancelled()) {
                    toastMessage = "Login Cancelled";
                } else {
                    final AccessToken accessToken = loginResult.getAccessToken();
                    final String authorizationCode = loginResult.getAuthorizationCode();
                    final long tokenRefreshIntervalInSeconds =
                            loginResult.getTokenRefreshIntervalInSeconds();

                    Log.e("accessToken",accessToken.getToken() +"");
                    Log.e("authorizationCode",authorizationCode+"");
                    Log.e("tokenRefresh",tokenRefreshIntervalInSeconds+"");
                    Log.e("state",loginResult.getFinalAuthorizationState()+"");


                    if (accessToken != null) {
                        toastMessage = "Success:" + accessToken.getAccountId()
                                + tokenRefreshIntervalInSeconds;
                        ToastUtils.showCenter(this,toastMessage);
                        //showHelloActivity(loginResult.getFinalAuthorizationState());
                    } else if (authorizationCode != null) {
                        toastMessage = String.format(
                                "Success:%s...",
                                authorizationCode.substring(0, 10));
                        ToastUtils.showCenter(this,toastMessage);
                        //showHelloActivity(authorizationCode, loginResult.getFinalAuthorizationState());
                    } else {
                        toastMessage = "Unknown response type";
                    }

                    String thePhoneNumber = WOUApp.AccountKitGet(false);

                    // thePhoneNumber = "+66910833820";
                    code = thePhoneNumber.substring(1,3);
                    phone = thePhoneNumber.substring(3,thePhoneNumber.length());

                    Log.d("code",code);
                    Log.d("phone",phone);
                    Log.d("thePhoneNo",thePhoneNumber);

                    WOUApp.mPref.phoneCode().put(code).phone().put(phone).phoneNumber().put(thePhoneNumber).signupByPhone().put(true).commit();
                    ApiBus.getInstance().post(new CheckUsernameEvent("0" + phone));

                }
            }


        } else {
            Log.e("onActivityResult", requestCode + " + " + resultCode);
            //Uri selectedImageUri = data.getData();
            //File uploadAvatarFile = new File(selectedImageUri.getPath());



            if (resultCode == Activity.RESULT_OK) {
                File file = null;
                if (requestCode == REQUEST_GALLERY) {
                    file = onSelectFromGalleryResult(data);
                    uploadFileRetrofit(file, userId, "avatar");
                }
                else if (requestCode == REQUEST_CAMERA) {
                    file = onCaptureImageResult(data);
                    uploadFileRetrofit(file, userId, "avatar");
                }

                if(file != null) {
                    ((WOUApp) getApplication()).tempFile = file;
                    Picasso.with(UpdateProfileActivity2.this)
                        .load(file)
                        .centerCrop()
                        .resize(200, 200)
                        .transform(new RoundedTransformation(100, 2))
                        .into(avatarIv);
                    Intent i = new Intent(UpdateProfileActivity2.this, CropActivity.class);
                    //i.putExtra("URI",file.getAbsolutePath());
                    startActivity(i);
                }


            }

//                if (isAvatar) {
//                    uploadFileRetrofit(uploadAvatarFile, userId, "avatarIv");
//                }

        }
    }

    private File onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        Log.e("getAbsolutePath",destination.getAbsolutePath());
        Log.e("getPath",destination.getPath());

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        avatarIv.setImageBitmap(thumbnail);

        return destination;
    }

    @SuppressWarnings("deprecation")
    private File onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Log.e("selectedImagePath",selectedImagePath);

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        avatarIv.setImageBitmap(bm);


        return new File(selectedImagePath);
    }

//    private void showHelloActivity(final String finalState) {
//        final Intent intent = new Intent(this, TokenActivity.class);
//        intent.putExtra(
//                TokenActivity.HELLO_TOKEN_ACTIVITY_INITIAL_STATE_EXTRA,
//                initialStateParam);
//        intent.putExtra(TokenActivity.HELLO_TOKEN_ACTIVITY_FINAL_STATE_EXTRA, finalState);
//        startActivity(intent);
//    }
//
//    private void showHelloActivity(final String code, final String finalState) {
//        final Intent intent = new Intent(this, CodeActivity.class);
//        intent.putExtra(CodeActivity.HELLO_CODE_ACTIVITY_CODE_EXTRA, code);
//        intent.putExtra(
//                CodeActivity.HELLO_CODE_ACTIVITY_INITIAL_STATE_EXTRA,
//                initialStateParam);
//        intent.putExtra(CodeActivity.HELLO_CODE_ACTIVITY_FINAL_STATE_EXTRA, finalState);
//
//        startActivity(intent);
//    }
//
//    private void showErrorActivity(final AccountKitError error) {
//        final Intent intent = new Intent(this, ErrorActivity.class);
//        intent.putExtra(ErrorActivity.HELLO_TOKEN_ACTIVITY_ERROR_EXTRA, error);
//
//        startActivity(intent);
//    }

    @Subscribe
    public void onCheckUsernameOk(UsernameOkEvent event) {
        Toast.makeText(getApplicationContext(),"Phone number verified",Toast.LENGTH_LONG).show();

        WOUApp.mPref.phoneCode().put(code).phone().put(phone).phoneNumber().put(thePhoneNumber).signupByPhone().put(true).commit();

        input_phone.setVisibility(View.VISIBLE);
        input_phone.setText(thePhoneNumber);
        verifyPhoneBtn.setVisibility(View.GONE);
//        Bundle i = new Bundle();
//        SignByPhoneStep2Fragment oneFragment = new SignByPhoneStep2Fragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.content, oneFragment);
//        i.putString("numberPhone", thePhoneNumber);
//        i.putString("code", code);
//        i.putString("phone", phone);
//        oneFragment.setArguments(i);
//        transaction.addToBackStack(null);
//        transaction.commitAllowingStateLoss();
    }

    @Subscribe
    public void onCheckUsernameNotOk(UsernameNotOkEvent event) {

        Toast.makeText(getApplicationContext(),"Phone number is not available.",Toast.LENGTH_LONG).show();

        input_phone.setVisibility(View.GONE);
        verifyPhoneBtn.setVisibility(View.VISIBLE);

//        WOUApp.mPref
//                .name().put(event.checkUsernameData.user.name)
//                .username().put(event.checkUsernameData.user.username)
//                .password().put(event.checkUsernameData.user.password)
//                .email().put(event.checkUsernameData.user.email)
//                .userId().put(event.checkUsernameData.user.id)
//                //.token().put(event.checkUsernameData.token)
//                .cover().put(event.checkUsernameData.user.cover)
//                .avatarIv().put(event.checkUsernameData.user.avatarIv)
//                .isLogin().put(true)
//                .commit();
//
//        // create alert dialog to login with phone no.
//        new android.support.v7.app.AlertDialog.Builder(UpdateProfileActivity.this)
//
//                .setTitle(thePhoneNumber)
//                .setMessage("Already registered, \n do you want to sign in now ?")
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                        ApiBus.getInstance().post(new LoginEvent(WOUApp.mPref.phone().getOr(""), WOUApp.mPref.password().getOr("")));
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                        dialog.dismiss();
//                    }
//                })
//                .setIcon(android.R.drawable.ic_dialog_info)
//                .show();
    }

    ImageView avatarCamera;

    @Override
    protected void initListeners() {
        txt_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowQRCodeActivity.class));
            }
        });

        avatarCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAvatar = true;
                selectAvatar();
            }
        });

        avatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAvatar = true;
                selectAvatar();
            }
        });

        image_hone_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChatInfoActivity.class);
                startActivity(i);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // uploadProfile(userId);

                Intent i = new Intent(getApplicationContext(), ChatInfoActivity.class);

                String imageMe = WOUApp.SOCIAL_ENDPOINT + "/" + mPref.avatar();
                i.putExtra("id", userId);
                i.putExtra("cid", mPref.myCid().getOr(0));
                i.putExtra("nameMe", mPref.name().getOr(""));
                i.putExtra("imageMe", imageMe);


                startActivity(i);
            }
        });
    }

    @Override
    protected void initData() {
        //mToolbar.inflateMenu(R.menu.menu_save);
        //getToolbar().inflateMenu(R.menu.menu_save);

        mPref = WOUApp.get(getApplicationContext()).getPrefManager();
        userId = mPref.userId().getOr(0);
        url = WOUApp.API_ENDPOINT + "/user/update/" + userId + "";
        //ApiBus.getInstance().post(new GetUserProfileEvent(userId));

        img_qr_code.setImageBitmap(encodeToQrCode(WOUApp.mPref.username().getOr(""), 250, 250));

        Picasso.with(getApplicationContext())
                .load(WOUApp.SOCIAL_ENDPOINT + "/" + mPref.avatar().getOr(""))
                .centerCrop()
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 2))
                .into(avatarIv);

        input_name.setText(mPref.name().getOr(""));
        input_username.setText(mPref.username().getOr(""));
        input_phone.setText(WOUApp.mPref.phone().getOr(""));
        input_status.setText(WOUApp.mPref.about().getOr(""));





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void selectAvatar() {
        final CharSequence[] items = {"Choose from Gallery", "Take from Camera",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (isAvatar)
            builder.setTitle("Update avatar");
        else
            builder.setTitle("Update cover");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Choose from Gallery")) {
                    pickImage();
                } else if (items[item].equals("Take from Camera")) {
                    takePhoto();
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

    public void takePhoto() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    File imagePathToFile(Uri selectedImageUri, String path) {
        Bitmap bm;
        BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

        bm = BitmapFactory.decodeFile(path, btmapOptions);
        OutputStream fOut = null;
        File file = new File(path);
        try {
            fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
            fOut.flush();
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private void uploadProfile(int userId,String userName,String name,String status,String phone) {
        String url = WOUApp.API_ENDPOINT + "/user/update/" + userId;
        Log.e("userName", userName);
        Log.e("name", name);
        Log.e("status", status);

        mPref.name().put(name)
                .username().put(userName)
                .phone().put(phone)
                .about().put(status)
        .commit();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", userName);
        params.put("name", name);
        params.put("about", status);
        params.put("phone", phone);
//        params.put("birthday[]", "");
//        params.put("birthday[]", "");
//        params.put("birthday[]", "");

        params.put("gender", "male");
        params.put("current_city", "Bangkok");
        params.put("hometown", "Bangkok");
        params.put("timezone", "Bangkok");

        AQuery aq = new AQuery(getApplicationContext());
        aq.ajax(url, params, JSONObject.class, this, "updateProfile");
    }

    public void updateProfile(String url, JSONObject jo, AjaxStatus status)
            throws JSONException {
        Log.e("hahaha", jo.toString(4));
        Utils.showToast("Update complete!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                userName = input_username.getText().toString().trim();
                name = input_name.getText().toString().trim();
                status = input_status.getText().toString().trim();
                phone = input_phone.getText().toString().trim();
                uploadProfile(userId,userName,name,status,phone);
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        userName = input_username.getText().toString().trim();
        name = input_name.getText().toString().trim();
        status = input_status.getText().toString().trim();
        phone = input_phone.getText().toString().trim();
        uploadProfile(userId,userName,name,status,phone);
    }

    public static Bitmap encodeToQrCode(String text, int width, int height) {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = null;
        try {
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, width);
        } catch (WriterException ex) {
            ex.printStackTrace();
        }
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
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
                        Picasso.with(UpdateProfileActivity2.this)
                                .load(uploadCb.getAvatar().getThumb())
                                .centerCrop()
                                .resize(200, 200)
                                .transform(new RoundedTransformation(100, 2))
                                .into(avatarIv);

                        //Utils.showToast("Update avatarIv success!");


                        WOUApp.mPref.avatar().put(uploadCb.getUser().getAvatar()).commit();
                        ApiBus.getInstance().post(new UpdateAvatarEvent(uploadCb));

                        if (cameraOverlay != null && cameraOverlay2 != null) {
                            cameraOverlay.setVisibility(View.GONE);
                            cameraOverlay2.setVisibility(View.GONE);
                        }
                    } else {
                        Log.e("responseUrl", response.getUrl());
                    }


                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("myAvatarUrl", error.getMessage() + "");
                    Utils.showToast("Update avatar failed!");

                }
            });
        }


    }

    @Override
    public void onFinishEditNameDialog(String inputText) {
        input_name.setText(inputText);
    }
}
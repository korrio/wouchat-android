package com.candychat.net.activity2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseActivity;
import com.candychat.net.utils.ToastUtils;
import com.candychat.net.view.CustomTypefaceSpan;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wouchat.messenger.R;

public class ShowQRCodeActivity extends BaseActivity {

    ImageView img_qr_code;
    Button btn_qr;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_qrcode;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        img_qr_code = (ImageView) findViewById(R.id.img_qr_code);
        btn_qr = (Button) findViewById(R.id.btn_qr);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Spanny.spanText("My QR code", new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
        }
    }

    @Override
    protected void initListeners() {
        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scan = new Intent(ShowQRCodeActivity.this, DecoderAdvancedActivity.class);
                startActivity(scan);
                finish();
            }
        });
    }

    Bitmap qrBitmap;

    @Override
    protected void initData() {
        setTitle(Spanny.spanText(getResources().getString(R.string.find_friend_qr), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));

        qrBitmap = encodeToQrCode(WOUApp.mPref.username().getOr(""), 320, 320);
        if(!WOUApp.mPref.username().getOr("").equals("")) {
            qrBitmap = encodeToQrCode(WOUApp.mPref.username().getOr(""), 320, 320);
            img_qr_code.setImageBitmap(qrBitmap);
        }
        else if(!WOUApp.mPref.phone().getOr("").equals("")) {
            qrBitmap = encodeToQrCode(WOUApp.mPref.phone().getOr(""), 320, 320);
            img_qr_code.setImageBitmap(qrBitmap);
        }

       // pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), qrBitmap, "WOUchat_QRcode_" + WOUApp.mPref.username().getOr(""), null);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), qrBitmap, "WOUchat_QRcode_" + WOUApp.mPref.username().getOr(""), null);


                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        111);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


    }


    String pathofBmp = "";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), qrBitmap, "WOUchat_QRcode_" + WOUApp.mPref.username().getOr(""), null);

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.Gallery:
                ToastUtils.show(this,"Saved to your gallery",Toast.LENGTH_SHORT);
                return true;
            case R.id.Email:
                Uri bmpUri = Uri.parse(pathofBmp);
                final Intent emailIntent1 = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
                emailIntent1.setType("image/png");
                startActivity(emailIntent1);
                return true;
            case R.id.Chat:
                return true;
            case R.id.Other:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                shareIntent.setType("image/*");
// For a file in shared storage.  For data in private storage, use a ContentProvider.
                Uri uri = Uri.fromFile(getFileStreamPath(pathofBmp));
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(shareIntent);
                return true;
//            case R.id.Generate_qr:
//                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 111: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), qrBitmap, "WOUchat_QRcode_" + WOUApp.mPref.username().getOr(""), null);


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_qr_code,menu);
        return super.onCreateOptionsMenu(menu);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
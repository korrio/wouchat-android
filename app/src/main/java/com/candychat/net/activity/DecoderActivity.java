package com.candychat.net.activity;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.TextView;

import com.candychat.net.activity2.FindFriendsByUsernameActivity;
import com.candychat.net.base.BaseActivity;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.wouchat.messenger.R;

public class DecoderActivity extends BaseActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private TextView myTextView;
    private QRCodeReaderView mydecoderview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_decoder;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

        myTextView = (TextView) findViewById(R.id.username);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }


    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        myTextView.setText(text);

        FindFriendsByUsernameActivity.startSearch(getApplicationContext(),text.toLowerCase().trim(),0);

    }


    // Called when your device have no camera
    @Override
    public void cameraNotFound() {

    }

    // Called when there's no QR codes in the camera preview image
    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }
}

package com.candychat.net.activity2;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.candychat.net.base.BaseActivity;
import com.candychat.net.utils.Utils;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;
import com.wouchat.messenger.R;


public class DecoderAdvancedActivity extends BaseActivity implements OnQRCodeReadListener {

    private TextView myTextView;
    private QRCodeReaderView mydecoderview;
    private ImageView redLine;

    Button myQrBtn;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        myTextView.setText(text);
        Utils.theToast(text);
        FindFriendsByUsernameActivity.startSearch(DecoderAdvancedActivity.this,text,0);

//        Uri uri = Uri.parse(text);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
    }

    @Override
    public void cameraNotFound() {
        Toast.makeText(this,"No camera for this phone",Toast.LENGTH_LONG).show();
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {
        //Toast.makeText(this,"No QR code detected",Toast.LENGTH_LONG).show();
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_decoder_scan;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

//        Camera.Parameters params = mydecoderview.getCameraManager().getCamera().getParameters();
//        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//        mydecoderview.getCameraManager().getCamera().setParameters(params);

        myTextView = (TextView) findViewById(R.id.exampleTextView);
        myQrBtn = (Button) findViewById(R.id.btn_qr_mine);
        myQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DecoderAdvancedActivity.this,ShowQRCodeActivity.class));
                finish();
            }
        });

        redLine = (ImageView) findViewById(R.id.red_line_image);
        initRedlineAnim();
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

    public void initRedlineAnim() {
        TranslateAnimation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f);
        mAnimation.setDuration(1000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());

        redLine.setAnimation(mAnimation);
    }

//    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
//        final AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
//        downloadDialog.setTitle(title);
//        downloadDialog.setMessage(message);
//        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Uri uri = Uri.parse("http://www.folkrice.com/");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                try {
//                    act.startActivity(intent);
//
//                } catch (ActivityNotFoundException anfe) {
//
//                }
//            }
//        });
//        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//
//        return downloadDialog.show();
//    }
}

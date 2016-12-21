package com.candychat.net.widget;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.candychat.net.WOUApp;
import com.isseiaoki.simplecropview.CropImageView;
import com.wouchat.messenger.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CropActivity extends Activity {

    // Views ///////////////////////////////////////////////////////////////////////////////////////
    private CropImageView mCropView;
    private RelativeLayout mRootLayout;

    // Image file index(1 ~ 5)
    private int mImageIndex = 5;

    // Bundle key for Save/Restore state ///////////////////////////////////////////////////////////
    private static final String KEY_IMG_INDEX = "img_index";

    // Lifecycle Method ////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        // bind Views
        findViews();
        // apply custom font
        //FontUtils.setFont(mRootLayout);
        // set bitmap to CropImageView

        File imgFile = ((WOUApp) getApplication()).tempFile;
        //Picasso.with(this).load(f).into(mCropView);

        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


        mCropView.setImageBitmap(myBitmap);



       // if(getIntent().getData() != null || getIntent().getExtras() != null) {

//            Uri imageUri = (Uri) getIntent().getExtras().getString("URI");
//
//            if(imageUri != null) {
//                Log.e("imageInCropRoom",imageUri.toString());
//                //sendPhoto(imageUri);
//            }
//            // Let's read picked image path using content resolver
//            if(imageUri != null) {
//                String[] filePath = { MediaStore.Images.Media.DATA };
//                Cursor cursor = getContentResolver().query(imageUri, filePath, null, null, null);
//                cursor.moveToFirst();
//                String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
//
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
//                mCropView.setImageBitmap(bitmap);
//            } else {
//                mCropView.setImageBitmap(getImageForIndex(mImageIndex));
//            }

//        } else {
//            mCropView.setImageBitmap(getImageForIndex(mImageIndex));
//        }

        mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_IMG_INDEX, mImageIndex);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mImageIndex = savedInstanceState.getInt(KEY_IMG_INDEX);
    }

    // Handle button event /////////////////////////////////////////////////////////////////////////

    private File storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return null;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();

        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
        return pictureFile;
    }

    public static String TAG = "555";

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    private final View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.buttonDone:
                    // Get cropped bitmap and pass it to Application
                    ((WOUApp) getApplication()).cropped = mCropView.getCroppedBitmap();
                    ((WOUApp) getApplication()).tempFile = storeImage(((WOUApp) getApplication()).cropped);
                    // Start ResultActivity
                    Intent intent = new Intent(CropActivity.this, ResultActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.buttonFitImage:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_FIT_IMAGE);
                    break;
                case R.id.button1_1:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);
                    break;
                case R.id.button3_4:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_3_4);
                    break;
                case R.id.button4_3:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_4_3);
                    break;
                case R.id.button9_16:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_9_16);
                    break;
                case R.id.button16_9:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_16_9);
                    break;
                case R.id.buttonCustom:
                    mCropView.setCustomRatio(7, 5);
                    break;
                case R.id.buttonFree:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_FREE);
                    break;
                case R.id.buttonCircle:
                    mCropView.setCropMode(CropImageView.CropMode.CIRCLE);
                    break;
                case R.id.buttonChangeImage:
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, REQUEST_GALLERY);
                    //incrementImageIndex();
                    //mCropView.setImageBitmap(getImageForIndex(mImageIndex));
                    break;
                case R.id.buttonRotateImage:
                    mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                    break;
            }
        }
    };

    public static int REQUEST_GALLERY = 111;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {

            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            // Do something with the bitmap


            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();

            mCropView.setImageBitmap(bitmap);

        }

    }

    // Bind views //////////////////////////////////////////////////////////////////////////////////

    private void findViews() {
        mCropView = (CropImageView) findViewById(R.id.cropImageView);
        findViewById(R.id.buttonDone).setOnClickListener(btnListener);
        findViewById(R.id.buttonFitImage).setOnClickListener(btnListener);
        findViewById(R.id.button1_1).setOnClickListener(btnListener);
        findViewById(R.id.button3_4).setOnClickListener(btnListener);
        findViewById(R.id.button4_3).setOnClickListener(btnListener);
        findViewById(R.id.button9_16).setOnClickListener(btnListener);
        findViewById(R.id.button16_9).setOnClickListener(btnListener);
        findViewById(R.id.buttonFree).setOnClickListener(btnListener);
        findViewById(R.id.buttonChangeImage).setOnClickListener(btnListener);
        findViewById(R.id.buttonRotateImage).setOnClickListener(btnListener);
        findViewById(R.id.buttonCustom).setOnClickListener(btnListener);
        findViewById(R.id.buttonCircle).setOnClickListener(btnListener);
        mRootLayout = (RelativeLayout) findViewById(R.id.layout_root);
    }

    // Switch image files //////////////////////////////////////////////////////////////////////////

    private void incrementImageIndex() {
        mImageIndex++;
        if (mImageIndex > 5) mImageIndex -= 5;
    }

    public Bitmap getImageForIndex(int index) {
        String fileName = "sample" + index;
        int resId = getResources().getIdentifier(fileName, "mipmap", getPackageName());
        return BitmapFactory.decodeResource(getResources(), resId);
    }
}

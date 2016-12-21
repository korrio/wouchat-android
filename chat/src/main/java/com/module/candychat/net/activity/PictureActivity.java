/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.module.candychat.net.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.module.candychat.net.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class PictureActivity extends ToolbarActivity {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";

    PhotoView mImageView;

    PhotoViewAttacher mPhotoViewAttacher;
    String mImageUrl, mImageTitle;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_picture;
    }


    @Override
    public boolean canBack() {
        return true;
    }

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, desc);
        return intent;
    }


    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife.bind(this);
        mImageView = (PhotoView) findViewById(R.id.picture);
        mImageView.setDrawingCacheEnabled(true);
        parseIntent();

        // init image view
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
        Picasso.with(this).load(mImageUrl).into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                mPhotoViewAttacher.update();
            }

            @Override
            public void onError() {

            }
        });


        // set up app bar
        setAppBarAlpha(0.7f);
        //setTitle(mImageTitle);
        setupPhotoAttacher();
    }


    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        mPhotoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                hideOrShowToolbar();
            }
        });

        mPhotoViewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(PictureActivity.this)
                        .setMessage("Save this picture ?")
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //dialog.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                saveImageToGallery();
                                //dialog.dismiss();
                            }
                        })
                        .show();

                return true;
            }
        });
    }


    private void saveImageToGallery() {
        Uri uri = getShareImageUri(mImageTitle,mImageUrl);
                String msg = String.format("Picture has been saved to " + uri);
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_share) {

            Handler uiHandler = new Handler(Looper.getMainLooper());
            uiHandler.post(new Runnable(){
                @Override
                public void run() {

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, getShareImageUri(mImageTitle,mImageUrl));
                    shareIntent.setType("image/jpg");
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(Intent.createChooser(shareIntent, "Share..."));

                    //ShareUtils.shareImage(getApplicationContext(), getShareImageUri(mImageTitle,mImageUrl), "Share...");
                }
            });


            //return true;
        } else if(id == R.id.action_save) {

            Handler uiHandler = new Handler(Looper.getMainLooper());
            uiHandler.post(new Runnable(){
                @Override
                public void run() {
                    saveImageToGallery();
                }
            });


            //return true;
        } else if(id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    Bitmap theBitmap = null;

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            theBitmap = bitmap;
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };



    private Uri getShareImageUri(String title, String url) {

        Picasso.with(this).load(url).into(target);


//        Handler uiHandler = new Handler(Looper.getMainLooper());
//        uiHandler.post(new Runnable(){
//            @Override
//            public void run() {
//                try {
//                    bitmap[0] = .into(mImageView).get();
//                } catch (IOException e) {
//                    //subscriber.onError(e);
//                }
//            }
//        });



        File appDir = new File(Environment.getExternalStorageDirectory(), "WOUchat");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = title.replace('/', '-') + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            assert theBitmap != null;
            theBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Toast.makeText(PictureActivity.this, "Saved to " + fileName, Toast.LENGTH_SHORT).show();

        return Uri.fromFile(file);

    }


    @Override
    public void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        //MobclickAgent.onPause(this);
    }


    @Override
    protected void onDestroy() {

        //mPhotoViewAttacher.cleanup();
        ButterKnife.unbind(this);
        Picasso.with(this).cancelRequest(target);
        super.onDestroy();
    }

}

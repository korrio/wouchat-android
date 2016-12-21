package com.candychat.net.fragment.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.candychat.net.WOUApp;
import com.candychat.net.base.BaseToolbarActivity;
import com.wouchat.messenger.R;

public class CameraFragment extends Fragment {
    Toolbar toolbar;

    Button button5;

    public static CameraFragment getInstance(String message) {
        CameraFragment mainFragment = new CameraFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    private final static int CAMERA_RQ = 6969;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
//        new MaterialCamera(getActivity())
//                .start(CAMERA_RQ);


        button5 = (Button) rootView.findViewById(R.id.btnTakePhoto);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ยังไม่ใช้ แต่งรูป
//                Intent i = new Intent(getActivity(), TakePhotoActivity2.class);
//                startActivity(i);
//                int[] startingLocation = new int[2];
//                v.getLocationOnScreen(startingLocation);
//                startingLocation[0] += v.getWidth() / 2;
//                TakePhotoActivity2.startCameraFromLocation(startingLocation, getActivity(), false);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        SpannableString title = new SpannableString(getResources().getString(R.string.camera));
        title.setSpan(WOUApp.CustomFontTypeFace(), 0, title.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setTitle(title);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

}
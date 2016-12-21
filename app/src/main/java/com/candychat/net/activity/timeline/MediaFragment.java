package com.candychat.net.activity.timeline;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.candychat.net.manager.MediaManager;
import com.candychat.net.activity.VideoCacheActivity;
import com.candychat.net.adapter.RecyclerviewHorizontalMediaAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalVideosAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalVoicesAdapter;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.event.ContentInfoEvent;
import com.candychat.net.event.ContentInfoSuccess;
import com.candychat.net.handler.ApiBus;
import com.candychat.net.model.media.MediaUrl;
import com.module.candychat.net.activity.PictureActivity;
import com.squareup.otto.Subscribe;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;


public class MediaFragment extends BaseFragment {
    TextView menu_pictures;

    RecyclerView rvContacts;
    RecyclerView rvContacts2;
    RecyclerView rvContacts3;

    RecyclerviewHorizontalMediaAdapter adapterRecyclerviewHorizontal;
    RecyclerviewHorizontalVoicesAdapter adapterRecyclerviewHorizontalVoices;
    RecyclerviewHorizontalVideosAdapter adapterRecyclerviewHorizontalVideo;

    List<MediaUrl> listPhoto = new ArrayList<>();
    List<MediaUrl> listPhotoVideo = new ArrayList<>();
    List<MediaUrl> listPhotoVoice = new ArrayList<>();

    LinearLayout sectionPicture;
    LinearLayout sectionVoice;
    LinearLayout sectionVideo;

    //private HttpProxyCache proxyCache;

    public static MediaFragment newInstance(int cid) {
        Bundle args = new Bundle();
        MediaFragment fragment = new MediaFragment();
        args.putInt("CID",cid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().getInt("CID",0) != 0) {
            ApiBus.getInstance().post(new ContentInfoEvent(getArguments().getInt("CID",0),1,100));
        }
        //setHasOptionsMenu(true);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info_home_data, container, false);

        sectionPicture = (LinearLayout) rootView.findViewById(R.id.picture_section);
        sectionVoice = (LinearLayout) rootView.findViewById(R.id.voice_section);
        sectionVideo = (LinearLayout) rootView.findViewById(R.id.video_section);

        menu_pictures = (TextView) rootView.findViewById(R.id.menu_more_picture);

        rvContacts = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        rvContacts2 = (RecyclerView) rootView.findViewById(R.id.rvContacts2);
        rvContacts3 = (RecyclerView) rootView.findViewById(R.id.rvContacts3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvContacts.setLayoutManager(layoutManager);
        rvContacts2.setLayoutManager(layoutManager2);
        rvContacts3.setLayoutManager(layoutManager3);

        menu_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoViewPager(v,0);
            }
        });
        return rootView;
    }

    @Subscribe
    public void getListPhoto(ContentInfoSuccess event) {
        if (event != null) {
            Log.e("qqqq", event.response.getContent().size() + "");
            for (int i = 0; i < event.response.getContent().size(); i++) {
                Log.e("vvvvvvvv", event.response.getContent().get(i).getMessageType() + "");

                if (event.response.getContent().get(i).getMessageType() == 2) {
                    Log.e("btttt", event.response.getContent().get(i).getMessageType() + "");
                    for (int j = 0; j < event.response.getContent().get(i).getData().size(); j++) {
                        Log.e("rrr", event.response.getContent().get(i).getData().get(j).getThumb() + "");
                        String url = event.response.getContent().get(i).getData().get(j).getThumb();
                        MediaUrl list = new MediaUrl();
                        list.setUrlPhoto(url);
                        listPhoto.add(list);
                    }

                    if(listPhoto.size() == 0) {
                        sectionPicture.setVisibility(View.GONE);
                    }

                }
                if (event.response.getContent().get(i).getMessageType() == 3) {
                    Log.e("btttt", event.response.getContent().get(i).getMessageType() + "");
                    for (int j = 0; j < event.response.getContent().get(i).getData().size(); j++) {
                        Log.e("rrr", event.response.getContent().get(i).getData().get(j).getThumb() + "");
                        String url = event.response.getContent().get(i).getData().get(j).getThumb();
                        String pathVideo = event.response.getContent().get(i).getData().get(j).getFull_path();
                        String fileName = event.response.getContent().get(i).getData().get(j).getFileName();
                        MediaUrl list = new MediaUrl();
                        list.setUrlPhoto(url);
                        list.setUrlVideo(pathVideo);
                        list.setFileName(fileName);
                        listPhotoVideo.add(list);
                    }

                    if(listPhotoVideo.size() == 0) {
                        sectionVideo.setVisibility(View.GONE);
                    }
                }

                if (event.response.getContent().get(i).getMessageType() == 8) {
                    Log.e("btttt", event.response.getContent().get(i).getMessageType() + "");
                    for (int j = 0; j < event.response.getContent().get(i).getData().size(); j++) {
                        String urlVoice = event.response.getContent().get(i).getData().get(j).getFull_path();
                        MediaUrl list = new MediaUrl();
                        list.setUrlVoice(urlVoice);
                        Log.e("rrr", urlVoice + "");
                        listPhotoVoice.add(list);
                    }

                    if(listPhotoVoice.size() == 0) {
                        sectionVoice.setVisibility(View.GONE);
                    }
                }
            }
            adapterRecyclerviewHorizontal = new RecyclerviewHorizontalMediaAdapter(getActivity(), listPhoto);
            adapterRecyclerviewHorizontalVideo = new RecyclerviewHorizontalVideosAdapter(getActivity(), listPhotoVideo);
            adapterRecyclerviewHorizontalVoices = new RecyclerviewHorizontalVoicesAdapter(getActivity(), listPhotoVoice);

            rvContacts.setAdapter(adapterRecyclerviewHorizontal);
            rvContacts3.setAdapter(adapterRecyclerviewHorizontalVideo);
            rvContacts2.setAdapter(adapterRecyclerviewHorizontalVoices);

            adapterRecyclerviewHorizontal.SetOnItemClickListener(new RecyclerviewHorizontalMediaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    openPhotoViewPager(view,position);
                }
            });


            adapterRecyclerviewHorizontalVideo.SetOnItemClickListener(new RecyclerviewHorizontalVideosAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String urlVideo = listPhotoVideo.get(position).getUrlVideo();
                    String fileName = listPhotoVideo.get(position).getFileName();
                    Log.e("urlVideo", urlVideo);

                    Intent i = new Intent(getActivity(), VideoCacheActivity.class);
                    i.putExtra("urlVideo", urlVideo);
                    i.putExtra("fileName", fileName);
                    startActivity(i);
                }
            });

            adapterRecyclerviewHorizontalVoices.SetOnItemClickListener(new RecyclerviewHorizontalVoicesAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String urlVoice = listPhotoVoice.get(position).getUrlVoice();
                    Log.e("urlVoice", urlVoice);

                    MediaManager.playSound(urlVoice, new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {

                        }
                    });
                }
            });

        }
    }

    private void openPhotoViewPager(View view,int position) {

        Intent intent = PictureActivity.newIntent(getActivity(), listPhoto.get(position).getUrlPhoto().replace("_100x100","").replace("_thumb",""),
                "");
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(), view, PictureActivity.TRANSIT_PIC);

        try {
            ActivityCompat.startActivity(getActivity(), intent,
                    optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            getActivity().startActivity(intent);
        }

        //Intent intent = new Intent(getActivity(), PhotoPagerActivity.class);


//                    String imageUrl = urlPhoto;
//                    urls.add(0, imageUrl);
//        intent.putExtra("current_item", position);
//        intent.putStringArrayListExtra("photos", listUrls);
//        startActivity(intent);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
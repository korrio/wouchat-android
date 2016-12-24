package com.candychat.net.activity.timeline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.candychat.net.adapter.ListViewChatInfomationAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalMediaAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalVideosAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalVoicesAdapter;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.fragment.CameraFragment;
import com.wouchat.messenger.R;


public class InfomationGroupDataFragment extends Fragment{
    Toolbar toolbar;
    RecyclerView rvContacts;
    RecyclerView rvContacts2;
    RecyclerView rvContacts3;
    RecyclerviewHorizontalMediaAdapter adapterRecyclerviewHorizontal;
    RecyclerviewHorizontalVoicesAdapter adapterRecyclerviewHorizontalVoices;
    RecyclerviewHorizontalVideosAdapter adapterRecyclerviewHorizontalVideos;
    TextView menu_more_picture;

    ListViewChatInfomationAdapter mAdapterListView;
    LinearLayout childScroll;

    public static InfomationGroupDataFragment getInstance(String message) {
        InfomationGroupDataFragment mainFragment = new InfomationGroupDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info_group_data, container, false);

        menu_more_picture = (TextView) rootView.findViewById(R.id.menu_more_picture);
        rvContacts = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        rvContacts2 = (RecyclerView) rootView.findViewById(R.id.rvContacts2);
        rvContacts3 = (RecyclerView) rootView.findViewById(R.id.rvContacts3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        rvContacts2.setLayoutManager(layoutManager2);
        rvContacts3.setLayoutManager(layoutManager3);


        menu_more_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rootView;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //toolbar.inflateMenu(R.menu.menu_main_tatoo);
        toolbar.setTitle("Media");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

}
package com.candychat.net.fragment;

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
import android.widget.ListAdapter;
import android.widget.ListView;

import com.candychat.net.adapter.ListViewChatInfomationAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalMediaAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalVideosAdapter;
import com.candychat.net.adapter.RecyclerviewHorizontalVoicesAdapter;
import com.candychat.net.base.BaseToolbarActivity;
import com.wouchat.messenger.R;

public class ChatInfoFragment extends Fragment  {

    ListView listGroup;
    Toolbar toolbar;
    ListViewChatInfomationAdapter mAdapterListView;
    LinearLayout childScroll;

    RecyclerView rvContacts;
    RecyclerView rvContacts2;
    RecyclerView rvContacts3;
    RecyclerviewHorizontalMediaAdapter adapterRecyclerviewHorizontal;
    RecyclerviewHorizontalVoicesAdapter adapterRecyclerviewHorizontalVoices;
    RecyclerviewHorizontalVideosAdapter adapterRecyclerviewHorizontalVideos;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat_infomation, container, false);


        rvContacts = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        rvContacts2 = (RecyclerView) rootView.findViewById(R.id.rvContacts2);
        rvContacts3 = (RecyclerView) rootView.findViewById(R.id.rvContacts3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvContacts.setLayoutManager(layoutManager);
        rvContacts2.setLayoutManager(layoutManager2);
        rvContacts3.setLayoutManager(layoutManager3);

        listGroup = (ListView) rootView.findViewById(R.id.list_group);


        setListViewHeightBasedOnItems(listGroup);


        return rootView;
    }



    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        toolbar.inflateMenu(R.menu.menu_main_tatoo);
        toolbar.setTitle("Chat information");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }
}
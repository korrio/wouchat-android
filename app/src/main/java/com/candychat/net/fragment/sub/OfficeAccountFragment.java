package com.candychat.net.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.candychat.net.adapter.OfficeAccountAdapter;
import com.candychat.net.base.BaseToolbarActivity;
import com.wouchat.messenger.R;


public class OfficeAccountFragment extends Fragment {

    OfficeAccountAdapter adapter;

    Toolbar toolbar;
    private ListView mListView;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.office_account, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listView2);



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

        toolbar.inflateMenu(R.menu.menu_main_office);
        toolbar.setTitle("Official account");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }
}
package com.candychat.net.activity.timeline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.candychat.net.adapter.GroupMemberAdapter;
import com.candychat.net.base.BaseToolbarActivity;
import com.wouchat.messenger.R;


public class InfomationMemberFragment extends Fragment {
    Toolbar toolbar;
    GridView gridView;
    GroupMemberAdapter adapterGroupMember;



    public static InfomationMemberFragment getInstance(String message) {
        InfomationMemberFragment mainFragment = new InfomationMemberFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_member, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView2);


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
        toolbar.setTitle("Members");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

}
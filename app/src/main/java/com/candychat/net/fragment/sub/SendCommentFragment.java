package com.candychat.net.fragment.sub;

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

import com.candychat.net.base.BaseToolbarActivity;
import com.wouchat.messenger.R;

public class SendCommentFragment extends Fragment {
    Toolbar toolbar;
    RecyclerView rvComments;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_comment, container, false);
        rvComments = (RecyclerView) rootView.findViewById(R.id.rvComments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvComments.setLayoutManager(linearLayoutManager);
        rvComments.setHasFixedSize(true);
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

        // toolbar.inflateMenu(R.menu.menu_add_friends);
        toolbar.setTitle("Comment");
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main_noti,menu);
    }

}
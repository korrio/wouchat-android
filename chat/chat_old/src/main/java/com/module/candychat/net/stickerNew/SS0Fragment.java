package com.module.candychat.net.stickerNew;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.module.candychat.net.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SS0Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private GridView gridView;
    private EmoAdapter sitckerGridAdapter;
    private int myLastVisiblePos;
    private boolean isGridViewScrolling;
    List<String> imageList;
    private OnChildInteractionListener mChildListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PagerItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SS0Fragment newInstance() {
        SS0Fragment fragment = new SS0Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static SS0Fragment getInstance(String message) {
        SS0Fragment mainFragment = new SS0Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sticker_pager_item, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        gridView = (GridView) view.findViewById(R.id.gridView1);

        try {
            imageList = getImage();

            imageList = getImage();
            sitckerGridAdapter = new EmoAdapter(getActivity(),imageList);
            gridView.setAdapter(sitckerGridAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }


        myLastVisiblePos = gridView.getFirstVisiblePosition();
        isGridViewScrolling = false;
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isGridViewScrolling = true;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int currentFirstVisPos = view.getFirstVisiblePosition();
                if (!isGridViewScrolling) {
                    myLastVisiblePos = currentFirstVisPos;
                    return;
                }

                myLastVisiblePos = currentFirstVisPos;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String path = imageList.get(i);
                mChildListener.clickOnItem(path);

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private List<String> getImage() throws IOException {
        List<String> it = new ArrayList<String>();
        AssetManager assetManager = getActivity().getAssets();
        String[] filelist = assetManager.list("sticker_candy_set1");

        for (String filename : filelist) {
            it.add("sticker_candy_set1/" + filename);
        }
        return it;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mChildListener = (OnChildInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnChildInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnChildInteractionListener {
        public void onScrollDown(boolean isScrollDown);
        public void resetCountUpAnimation();
        public void clickOnItem(String i);
    }
}


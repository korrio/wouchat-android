package com.module.candychat.net.stickerNew;

/**
 * Created by Phuc on 7/18/15.
 */
import android.app.Activity;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnStickerPagerFragmentInteractionListener4} interface
 * to handle interaction events.
 * Use the {@link SS3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SS3Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private GridView gridView;
    private EmoAdapter sitckerGridAdapter;
    private int myLastVisiblePos;
    private boolean isGridViewScrolling;
    List<String> imageList;

    private OnChildInteractionListener4 mChildListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PagerItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SS3Fragment newInstance() {
        SS3Fragment fragment = new SS3Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static SS3Fragment getInstance(String message) {
        SS3Fragment mainFragment = new SS3Fragment();
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
                mChildListener.clickOnItem4(imageList.get(i));
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private List<String> getImage() throws IOException {
        List<String> it = new ArrayList<String>();
        AssetManager assetManager = getActivity().getAssets();
        String[] filelist = assetManager.list("sticker_candy_set0");

        for (String filename : filelist) {
            Log.d("check", filename);
            it.add("sticker_candy_set0/" + filename);
        }
        return it;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mChildListener = (OnChildInteractionListener4) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnChildInteractionListener4");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnStickerPagerFragmentInteractionListener4 {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public interface OnChildInteractionListener4 {

        public void clickOnItem4(String pt);
    }
}


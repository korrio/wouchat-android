package com.module.candychat.net.stickerNew;

/**
 * Created by Phuc on 7/18/15.
 */

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.module.candychat.net.R;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnStickerPagerFragmentInteractionListener4} interface
 * to handle interaction events.
 * Use the {@link SS4EmojiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SS4EmojiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private GridView gridView;
    private TextFaceAdapter sitckerGridAdapter;
    private int myLastVisiblePos;
    private boolean isGridViewScrolling;
    //List<String> imageList;
    List<String> textFaceList;

    private OnChildInteractionListener5 mChildListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PagerItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SS4EmojiFragment newInstance() {
        SS4EmojiFragment fragment = new SS4EmojiFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static SS4EmojiFragment getInstance(String message) {
        SS4EmojiFragment mainFragment = new SS4EmojiFragment();
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

        textFaceList = getTextFace();
        sitckerGridAdapter = new TextFaceAdapter(getActivity(),textFaceList);
        gridView.setAdapter(sitckerGridAdapter);

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
                mChildListener.clickOnItem5(textFaceList.get(i));
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private List<String> getTextFace() {
        String[] mTestArray = getResources().getStringArray(R.array.textface);
        List<String> wordList = Arrays.asList(mTestArray);
        return wordList;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mChildListener = (OnChildInteractionListener5) getActivity();
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
    public interface OnStickerPagerFragmentInteractionListener5 {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public interface OnChildInteractionListener5 {

        public void clickOnItem5(String pt);
    }
}


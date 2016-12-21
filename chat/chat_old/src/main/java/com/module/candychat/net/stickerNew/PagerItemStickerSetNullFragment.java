package com.module.candychat.net.stickerNew;

/**
 * Created by Phuc on 7/18/15.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.module.candychat.net.R;


public class PagerItemStickerSetNullFragment extends Fragment {

    public static PagerItemStickerSetNullFragment newInstance() {
        PagerItemStickerSetNullFragment fragment = new PagerItemStickerSetNullFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static PagerItemStickerSetNullFragment getInstance(String message) {
        PagerItemStickerSetNullFragment mainFragment = new PagerItemStickerSetNullFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }
    TextView emoji;
    String myString;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_emoji, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        emoji = (TextView) view.findViewById(R.id.emoji);
        emoji.setText(AndroidEmoji.ensure(myString,getActivity()));

        super.onViewCreated(view, savedInstanceState);
    }


}


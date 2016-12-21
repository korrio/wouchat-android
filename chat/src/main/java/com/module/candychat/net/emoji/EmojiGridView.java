package com.module.candychat.net.emoji;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.module.candychat.net.R;
import com.module.candychat.net.emoji.emoji.Emoji;
import com.module.candychat.net.emoji.listeners.OnEmojiClickedListener;


final class EmojiGridView extends FrameLayout {
    EmojiGridView(final Context context) {
        super(context);

        View.inflate(context, R.layout.emoji_grid, this);
    }

    public EmojiGridView init(final Emoji[] emojis, @Nullable final OnEmojiClickedListener onEmojiClickedListener) {
        final GridView gridView = (GridView) findViewById(R.id.emoji_grid_view);

        final EmojiArrayAdapter emojiArrayAdapter = new EmojiArrayAdapter(getContext(), emojis);
        emojiArrayAdapter.setOnEmojiClickedListener(onEmojiClickedListener);
        gridView.setAdapter(emojiArrayAdapter);
        return this;
    }
}

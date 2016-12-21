package com.candychat.net.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wouchat.messenger.R;


public class ViewHolder2 extends RecyclerView.ViewHolder {

    private ImageView ivExample,thumb;
    private LinearLayout ln_comment;
    private ImageView profile_avatar;
    public ViewHolder2(View v) {
        super(v);
        ivExample = (ImageView) v.findViewById(R.id.ic_type);
        thumb = (ImageView) v.findViewById(R.id.thumb);
        profile_avatar = (ImageView) v.findViewById(R.id.profile_avatar);
        ln_comment = (LinearLayout) v.findViewById(R.id.ln_comment);
    }

    public ImageView getImageView() {
        return ivExample;
    }

    public void setImageView(ImageView ivExample) {
        this.ivExample = ivExample;
    }

    public ImageView getIvExample() {
        return ivExample;
    }

    public void setIvExample(ImageView ivExample) {
        this.ivExample = ivExample;
    }

    public ImageView getThumb() {
        return thumb;
    }

    public void setThumb(ImageView thumb) {
        this.thumb = thumb;
    }

    public LinearLayout getLn_comment() {
        return ln_comment;
    }

    public void setLn_comment(LinearLayout ln_comment) {
        this.ln_comment = ln_comment;
    }

    public ImageView getProfile_avatar() {
        return profile_avatar;
    }

    public void setProfile_avatar(ImageView profile_avatar) {
        this.profile_avatar = profile_avatar;
    }
}
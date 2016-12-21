package com.candychat.net.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wouchat.messenger.R;

public class ViewHolder1 extends RecyclerView.ViewHolder {

    private TextView label1, label2,tvName;
    private ImageView profile_avatar;
    private View comment_view_1;
    private View comment_view_2;
    private Button btn_comment;
    private ImageView ivUserAvatar1;
    public ViewHolder1(View v) {
        super(v);
        label1 = (TextView) v.findViewById(R.id.text);
        label2 = (TextView) v.findViewById(R.id.profile_name);
        profile_avatar = (ImageView) v.findViewById(R.id.profile_avatar);
        comment_view_1 =  v.findViewById(R.id.comment_view_1);
        comment_view_2 =  v.findViewById(R.id.comment_view_2);
        btn_comment = (Button) v.findViewById(R.id.btn_comment);
        ivUserAvatar1 = (ImageView) comment_view_1.findViewById(R.id.ivUserAvatar);
        tvName = (TextView) comment_view_1.findViewById(R.id.tvName);
    }

    public TextView getLabel1() {
        return label1;
    }

    public void setLabel1(TextView label1) {
        this.label1 = label1;
    }

    public TextView getLabel2() {
        return label2;
    }

    public void setLabel2(TextView label2) {
        this.label2 = label2;
    }

    public ImageView getProfile_avatar() {
        return profile_avatar;
    }

    public void setProfile_avatar(ImageView profile_avatar) {
        this.profile_avatar = profile_avatar;
    }

    public View getComment_view_1() {
        return comment_view_1;
    }

    public void setComment_view_1(View comment_view_1) {
        this.comment_view_1 = comment_view_1;
    }

    public View getComment_view_2() {
        return comment_view_2;
    }

    public void setComment_view_2(View comment_view_2) {
        this.comment_view_2 = comment_view_2;
    }

    public ImageView getIvUserAvatar1() {
        return ivUserAvatar1;
    }

    public void setIvUserAvatar1(ImageView ivUserAvatar1) {
        this.ivUserAvatar1 = ivUserAvatar1;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public Button getBtn_comment() {
        return btn_comment;
    }

    public void setBtn_comment(Button btn_comment) {
        this.btn_comment = btn_comment;
    }
}

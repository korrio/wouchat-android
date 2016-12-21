package com.candychat.net.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.module.candychat.net.R;
import com.module.candychat.net.event.ChatInfo;
import com.module.candychat.net.widgets.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GroupChatMemberAdapter extends BaseAdapter
{
	private Context mContext;
	private ArrayList<ChatInfo.ConversationMembersEntity> listMembers = new ArrayList<>();



	public GroupChatMemberAdapter(Context context, ArrayList<ChatInfo.ConversationMembersEntity> listMembers){
		this.mContext = context;
		this.listMembers = listMembers;
		Log.e("listMembers", listMembers.size()+"");

	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return listMembers.size();
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return listMembers.get(p1);
	}

	public ArrayList<ChatInfo.ConversationMembersEntity> getData()
	{
		// TODO: Implement this method
		return listMembers;
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public View getView(int i, View view, ViewGroup parent)
	{
		ChatInfo.ConversationMembersEntity m = listMembers.get(i);

		LayoutInflater mInflater =
				(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if(view == null)
			view = mInflater.inflate(R.layout.item_member_avatar, parent, false);

		String thumbUrl = "http://www.candychat.net" + "/" + m.getAvatar() + "." + m.getExtension();

		ImageView avatar = (ImageView)view.findViewById(R.id.avatar);
		//Picasso.with(getActivity()).load(yourAvatarUrl).resize(200, 200)
		//.transform(new RoundedTransformation(100, 0)).into(friendAvatarIv);
		Picasso.with(mContext).load(thumbUrl).resize(200, 200).transform(new RoundedTransformation(100, 0)).centerCrop().into(avatar);

		Log.e("thumbUrl",thumbUrl);

		return view;
	}

}

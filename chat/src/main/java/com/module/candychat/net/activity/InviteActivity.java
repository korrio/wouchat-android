package com.module.candychat.net.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.module.candychat.net.ChatActivity;
import com.module.candychat.net.R;
import com.module.candychat.net.adapter.InviteAdapter;
import com.module.candychat.net.event.FriendsDataResponse;
import com.module.candychat.net.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InviteActivity extends ToolbarActivity {
    //private AutoLabelUI mAutoLabel;
    private List<UserModel> mPersonList = new ArrayList<>();
    private List<UserModel> selectedList = new ArrayList<>();
    private UserModel selectedFriend;
    private InviteAdapter adapter;
    private RecyclerView recyclerView;
    private Button ok;
    String name;

    int userId;
    int cId;

    Context context;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_invite);
        setTitle("Invite friends to group");

        context = this;

        if(getIntent().getExtras() != null) {
            cId = getIntent().getExtras().getInt("cid");
            userId = getIntent().getExtras().getInt("user_id");
        } else {

            finish();
        }

       // mAutoLabel = (AutoLabelUI) findViewById(R.id.label_view);
        recyclerView = (RecyclerView) findViewById(R.id.invite_fragment);

        ok = (Button) findViewById(R.id.ok);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);

        mPersonList = new ArrayList<>();

        fetchFriendList();

        //Populate list
//        List<String> names = Arrays.asList(getResources().getStringArray(R.array.names));
//        List<String> ages = Arrays.asList(getResources().getStringArray(R.array.ages));
        //TypedArray photos = getResources().obtainTypedArray(R.array.photos);

        adapter = new InviteAdapter(this,mPersonList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new InviteAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                itemListClicked(v,position);
                selectedFriend = mPersonList.get(position);
                selectedList.add(selectedFriend);


//                if(!mPersonList.get(position).name.equals(""))
//                    name = mPersonList.get(position).name;
//                else
//                    name = mPersonList.get(position).username;
//
//                Log.e("mPersonList",name+"");
//                Intent intent = getIntent();
//                //intent.putExtra("friend", Parcels.wrap(selectedFriend));
//                intent.putExtra("USER_ID",""+mPersonList.get(position).id);
//                intent.putExtra("NAME",mPersonList.get(position).name);
//                intent.putExtra("ABOUT",mPersonList.get(position).about);
//                intent.putExtra("USERNAME",mPersonList.get(position).username);
//                intent.putExtra("AVATAR_FULL_PATH",mPersonList.get(position).getAvatarUrl());
//                intent.putExtra("AVATAR",mPersonList.get(position).avatar);
//                setResult(RESULT_OK, intent);
//                finish();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer responseText = new StringBuffer();

                ArrayList<Integer> userIds = new ArrayList<Integer>();

                for (int i = 0; i < selectedList.size(); i++) {
                    UserModel friend = selectedList.get(i);
                    if (friend.getSelected()) {
                        responseText.append("\n" + friend.id);
                        userIds.add(friend.id);
                    }
                }

                if(selectedList.size() == 0)
                    Toast.makeText(context,"Please select at least one friend.",Toast.LENGTH_SHORT).show();
                else {

                    inviteUserIdStr = "[";

                    for (int i = 0; i < userIds.size(); i++) {
                        inviteUserIdStr = inviteUserIdStr.concat(userIds.get(i) + ",");
                    }

                    if (inviteUserIdStr.endsWith(",")) {
                        inviteUserIdStr = inviteUserIdStr.substring(0, inviteUserIdStr.length() - 1);
                    }

                    inviteUserIdStr = inviteUserIdStr.concat("]");
                    if (inviteUserIdStr.startsWith("[") && inviteUserIdStr.endsWith("]")) {
                        Log.e("mystring", inviteUserIdStr);
                        ChatActivity.buildChatApi().inviteFriendsToGroupChat(cId,inviteUserIdStr, new Callback<Response>() {

                            @Override
                            public void success(Response response, Response response2) {
                                Toast.makeText(context,selectedList.size() +" friends are invited",Toast.LENGTH_SHORT).show();
                                Log.e("mPersonList",inviteUserIdStr+"");
                                Intent intent = getIntent();
                                intent.putExtra("data",inviteUserIdStr);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    } else {
                        Log.e("mystring", inviteUserIdStr);
                    }

                }



            }
        });

    }

    String inviteUserIdStr;

    private void fetchFriendList() {
        ChatActivity.buildMainApi().getFriends(userId, new Callback<FriendsDataResponse>() {
            @Override
            public void success(FriendsDataResponse friendsDataResponse, Response response) {
                adapter.setPeople(friendsDataResponse.getUsers());
               // mPersonList = friendsDataResponse.getUsers();
                mPersonList = friendsDataResponse.getUsers();
                Log.d("mPersonList",adapter.getItemCount() + "");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void itemListClicked(View view, int position) {
        UserModel person = mPersonList.get(position);

        boolean isSelected = !person.getSelected();
        //if (success) {
        adapter.setItemSelected(view, position, isSelected);
        //}

//        boolean isSelected = person.isSelected();
//        boolean success;
//        if (isSelected) {
//            success = mAutoLabel.removeLabel(position);
//        } else {
//            success = mAutoLabel.addLabel(person.getName(), position);
//        }
//        if (true) {
//            adapter.setItemSelected(position, !isSelected);
//        }
    }

    private void setListeners() {
//        mAutoLabel.setOnLabelsCompletedListener(new AutoLabelUI.OnLabelsCompletedListener() {
//            @Override
//            public void onLabelsCompleted() {
//                Toast.makeText(getApplication(), "Completed!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mAutoLabel.setOnRemoveLabelListener(new AutoLabelUI.OnRemoveLabelListener() {
//            @Override
//            public void onRemoveLabel(View view, int position) {
//                adapter.setItemSelected(position, false);
//            }
//        });
//
//        mAutoLabel.setOnLabelsEmptyListener(new AutoLabelUI.OnLabelsEmptyListener() {
//            @Override
//            public void onLabelsEmpty() {
//                Toast.makeText(getApplication(), "EMPTY!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mAutoLabel.setOnLabelClickListener(new AutoLabelUI.OnLabelClickListener() {
//            @Override
//            public void onClickLabel(View v) {
//                Toast.makeText(getApplication(), ((Label) v).getText() , Toast.LENGTH_SHORT).show();
//            }
//        });

    }




}

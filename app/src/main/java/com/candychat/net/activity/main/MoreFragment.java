package com.candychat.net.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binaryfork.spanny.Spanny;
import com.candychat.net.Constant;
import com.candychat.net.WOUApp;
import com.candychat.net.activity.MainSettingActivity;
import com.candychat.net.activity2.AboutActivity;
import com.candychat.net.activity2.AddActivity;
import com.candychat.net.activity2.UpdateProfileActivity2;
import com.candychat.net.activity2.UpdateStatusActivity;
import com.candychat.net.adapter.MoreSettingsAdapter;
import com.candychat.net.base.BaseFragment;
import com.candychat.net.base.BaseToolbarActivity;
import com.candychat.net.event.upload.UpdateAvatarEvent;
import com.candychat.net.manager.PrefManager;
import com.candychat.net.view.CustomTypefaceSpan;
import com.candychat.net.view.RoundedTransformation;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.thefinestartist.finestwebview.FinestWebView;
import com.wouchat.messenger.R;


public class MoreFragment extends BaseFragment {
    //ยังไม่ใช้ตอนนี้
//    , "Official accounts", "Notices", "Tell friend", "Tips & Tricks", "Help & Support", "Put doodle"
//    , R.drawable.ic_activities_officialaccount, R.drawable.ic_activities_notice, R.drawable.ic_activities_tell, R.drawable.ic_activities_tips, R.drawable.ic_activities_support, R.drawable.ic_doodle
    Toolbar toolbar;
    MoreSettingsAdapter mAdpater;
    GridView gridView;
    public PrefManager mPref;
    ImageView avatar;
    LinearLayout click;
    int[] res = {R.drawable.ic_chat_pop_contact, R.drawable.ic_activities_add, R.drawable.ic_activities_setting,R.drawable.ic_fb,R.drawable.ic_activities_tell,
            R.drawable.ic_activities_officialaccount,R.drawable.ic_activities_notice, R.drawable.ic_activities_tips,R.drawable.ic_activities_support};
    String[] title = {"About", "Add friends", "Settings","Invite friends","Other app friends","Official account", "Notices", "Tips & Tricks", "Supports"};
    TextView name_txt;

    public static MoreFragment getInstance(String message) {
        MoreFragment mainFragment = new MoreFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    public int userId;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more, container, false);

        avatar = (ImageView) rootView.findViewById(R.id.avatar_profile);
        click = (LinearLayout) rootView.findViewById(R.id.click);
        name_txt = (TextView) rootView.findViewById(R.id.name_txt);

        gridView = (GridView) rootView.findViewById(R.id.gridView);
        mAdpater = new MoreSettingsAdapter(getActivity(), title, res);

        gridView.setAdapter(mAdpater);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                allowRefresh = false;

                if (position == 0) {
                    startActivity(new Intent(getActivity(), AboutActivity.class));
                }
                if (position == 1) {
                    allowRefresh = true;
                    startActivity(new Intent(getActivity(), AddActivity.class));

                }
                if (position == 2) {
                    startActivity(new Intent(getActivity(), MainSettingActivity.class));
                }
                if (position == 3) {

                    String appLinkUrl, previewImageUrl;

                    //appLinkUrl = "https://www.wouchat.com/app?r=1";
                    appLinkUrl = "https://fb.me/1788098944752719";
                    previewImageUrl = "https://wouchat.com/img/ic_launcher.png";

                    if (AppInviteDialog.canShow()) {
                        AppInviteContent content = new AppInviteContent.Builder()
                                .setApplinkUrl(appLinkUrl)
                                .setPreviewImageUrl(previewImageUrl)
                                .build();
                        AppInviteDialog.show(getActivity(), content);
                    }

                }
                if(position == 4) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Constant.INVITE_SUBJECT);
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Constant.INVITE_TEXT);
                    startActivity(Intent.createChooser(sharingIntent, "Share invitation via"));
                }
                if(position == 5) {

                }
                if(position == 6) {
                    new FinestWebView.Builder(getActivity()).disableIconMenu(true).show("http://blog.wouchat.com/?p=18");
                }
                if(position == 7) {
                    new FinestWebView.Builder(getActivity()).show("http://blog.wouchat.com/?p=16");
                }
                if(position == 8) {
                    new FinestWebView.Builder(getActivity()).show("http://blog.wouchat.com/?p=10");
                }
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),UpdateProfileActivity2.class));
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),UpdateProfileActivity2.class));
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPref = WOUApp.get(getActivity()).getPrefManager();
        userId = mPref.userId().getOr(0);
        toolbar = ((BaseToolbarActivity) getActivity()).getToolbar();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name_txt.setText(mPref.name().getOr(""));
        if(!(mPref.avatar().getOr("")).equals(""))
            Picasso.with(getActivity())
                    .load(WOUApp.SOCIAL_ENDPOINT + "/" + mPref.avatar().getOr(""))
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(avatar);

//        if(toolbar != null) {
//            getActivity().setTitle(Spanny.spanText(getResources().getString(R.string.activities), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
//            toolbar.inflateMenu(R.menu.menu_main_more);
//        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Subscribe public void onUploadAvatarSuccess(UpdateAvatarEvent event) {

            Picasso.with(getActivity())
                    .load(event.cb.getAvatar().getThumb())
                    .centerCrop()
                    .resize(200, 200)
                    .transform(new RoundedTransformation(100, 4))
                    .into(avatar);

        name_txt.setText(event.cb.getUser().getName());
    }

    boolean allowRefresh = false;

    @Override
    public void onResume() {
        super.onResume();
        if (allowRefresh) {
            //getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            if(name_txt != null && avatar != null) {
                name_txt.setText(mPref.name().getOr(""));
                if(!(mPref.avatar().getOr("")).equals(""))
                    Picasso.with(getActivity())
                            .load(WOUApp.SOCIAL_ENDPOINT + "/" + mPref.avatar().getOr(""))
                            .centerCrop()
                            .resize(200, 200)
                            .transform(new RoundedTransformation(100, 4))
                            .into(avatar);
            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(toolbar != null) {
            toolbar.setTitle(Spanny.spanText(getResources().getString(R.string.activities), new CustomTypefaceSpan(WOUApp.CustomFontTypeFace())));
            toolbar.inflateMenu(R.menu.menu_main_more);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
//                Intent i = new Intent(getActivity(), ChatSettingsActivity.class);
//                startActivity(i);
                Intent i = new Intent(getActivity(), UpdateStatusActivity.class);
                startActivity(i);
                allowRefresh = true;
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
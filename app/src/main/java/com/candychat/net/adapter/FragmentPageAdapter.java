package com.candychat.net.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.candychat.net.activity.main.FriendsFragment;
import com.candychat.net.activity.main.MoreFragment;
import com.candychat.net.activity.main.RecentChatsFragment;
import com.wouchat.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentPageAdapter extends FragmentStatePagerAdapter  {
    private Context mContext;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();
    private List<Integer> mFragmentIcons = new ArrayList<>();
    public List<Integer> mFragmentCount = new ArrayList<>();

    int count;

    public int getItemPosition(Object item) {
        if(item instanceof FriendsFragment) {
            return 0;
        } else if(item instanceof RecentChatsFragment)  {
            return 1;
        } else if(item instanceof MoreFragment)  {
            return 2;
        }
        return POSITION_NONE;
    }

    public FragmentPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    public void addFragment(Fragment fragment, String title, int drawable,int count) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        mFragmentIcons.add(drawable);
        mFragmentCount.add(count);
        this.count = count;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public View getTabView(int position) {
        View tab = LayoutInflater.from(mContext).inflate(R.layout.tabbar_view, null);
        TextView tabText = (TextView) tab.findViewById(R.id.tabText);
        TextView text_count = (TextView) tab.findViewById(R.id.text_count);
        ImageView tabImage = (ImageView) tab.findViewById(R.id.tabImage);
        tabText.setText(mFragmentTitles.get(position));
        text_count.setText(mFragmentCount.get(position) + "");
        tabImage.setBackgroundResource(mFragmentIcons.get(position));
        if (position == 0) {
            if(mFragmentCount.get(position) != 0)
                text_count.setVisibility(View.VISIBLE);
            else
                text_count.setVisibility(View.GONE);
            tab.setSelected(true);
        }if(position == 1){
            if(mFragmentCount.get(position) != 0)
                text_count.setVisibility(View.VISIBLE);
            else
                text_count.setVisibility(View.GONE);
        }if(position == 2){
            text_count.setVisibility(View.GONE);
        }if(position == 3){
            if(mFragmentCount.get(position) != 0)
                text_count.setVisibility(View.VISIBLE);
            else
                text_count.setVisibility(View.GONE);
        }if(position == 4){
            text_count.setVisibility(View.GONE);
        }
        return tab;
    }

}

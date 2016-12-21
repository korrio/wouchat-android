package com.candychat.net.impls;

import android.support.v4.app.Fragment;

/**
 * Created by korrio on 12/16/15.
 */
public interface OnFragmentAttachedListener {
    public void addFragment(Fragment f);
    public void removeFragment(Fragment f);
}

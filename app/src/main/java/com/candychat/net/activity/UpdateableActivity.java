package com.candychat.net.activity;

import com.candychat.net.activity.event.UpdateBadgeEvent;

/**
 * Created by Mac on 2/24/16.
 */
public interface UpdateableActivity {
    public void update(UpdateBadgeEvent event);
}

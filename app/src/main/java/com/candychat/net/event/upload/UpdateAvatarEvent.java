package com.candychat.net.event.upload;

import com.candychat.net.model.media.UploadAvatarCallback;

/**
 * Created by Mac on 8/7/15.
 */
public class UpdateAvatarEvent {
    public UploadAvatarCallback cb;

    public UpdateAvatarEvent(UploadAvatarCallback cb) {
        this.cb = cb;
    }
}

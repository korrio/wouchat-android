package com.candychat.net.event;

/**
 * Created by Mac on 7/12/15.
 */
public class ContentInfoEvent {
    public int id;
    public int page;
    public int size;

    public ContentInfoEvent(int id, int page, int size) {
        this.id = id;
        this.page = page;
        this.size = size;
    }
}

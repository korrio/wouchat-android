package com.module.candychat.net.wouclass;

import java.util.List;

import co.moonmonkeylabs.realmsearchview.model.TheMessageObject;

/**
 * Created by korrio on 1/6/16.
 */
public class TheHistoryDataResponse {

    public List<TheMessageObject> content;
    public TheHistoryDataResponse(List<TheMessageObject> content) {
        this.content = content;
    }

}

package com.module.candychat.net.event;

import java.util.List;

import com.module.candychat.net.model.ChatMessage;
import com.module.candychat.net.model.Message;


/**
 * Created by matthewlogan on 9/3/14.
 */
public class HistoryDataResponse {

    public List<ChatMessage> content;
    public HistoryDataResponse(List<ChatMessage> content) {
        this.content = content;
    }

}

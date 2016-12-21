package com.module.candychat.net.event;

import java.util.List;

import com.module.candychat.net.model.ChatMessage;
import com.module.candychat.net.model.Message;


/**
 * Created by matthewlogan on 9/3/14.
 */
public class HistoryEventSuccess {

    public List<ChatMessage> content;
    public HistoryEventSuccess(List<ChatMessage> content) {
        this.content = content;
    }

}

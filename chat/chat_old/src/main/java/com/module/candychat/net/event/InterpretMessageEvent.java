package com.module.candychat.net.event;

import org.json.JSONObject;

/**
 * Created by korrio on 12/28/15.
 */
public class InterpretMessageEvent {
    public JSONObject mMessageListObject;
    public InterpretMessageEvent(JSONObject mMessageListObject) {
        this.mMessageListObject = mMessageListObject;
    }

}

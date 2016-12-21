package com.module.candychat.net.event;

import com.module.candychat.net.model.RelationsGroup;
import com.module.candychat.net.model.RelationsTopic;

/**
 * Created by Mac on 8/3/15.
 */
public class GetTopicSuccess {
    public RelationsTopic info;

    public GetTopicSuccess(RelationsTopic info) {
        this.info = info;
    }
}

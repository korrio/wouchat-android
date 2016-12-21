package com.module.candychat.net.wouclass;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 * Created by korrio on 1/17/16.
 */
public class TheElement extends Element {
    public TheElement(Tag tag, String baseUri) {
        super(tag, baseUri);
    }

    public TheElement(Tag tag, String baseUri, Attributes attributes) {
        super(tag, baseUri,attributes);
    }

    @Override
    public String ownText() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}

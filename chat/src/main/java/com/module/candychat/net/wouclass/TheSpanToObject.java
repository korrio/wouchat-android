package com.module.candychat.net.wouclass;

import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ParagraphStyle;

import com.module.candychat.net.view.CalligraphyTypefaceSpanOriginal;

import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.realmsearchview.model.TheMessageObject;

public class TheSpanToObject {

    private CaseInsensitiveAssetFontLoader fontLoader;

    public TheSpanToObject(CaseInsensitiveAssetFontLoader fontLoader) {
        this.fontLoader = fontLoader;
    }

    public static List<TheMessageObject.MessageColorfulEntity> toColorfulList(Spanned text) {
        List<TheMessageObject.MessageColorfulEntity> theColorfulList = new ArrayList<>();
        withinHtml(theColorfulList, text);
        return theColorfulList;
    }

    private static void withinHtml(List<TheMessageObject.MessageColorfulEntity> theColorfulList, Spanned text) {
        int len = text.length();
        int next;
        for (int i = 0; i < text.length(); i = next) {
            next = text.nextSpanTransition(i, len, ParagraphStyle.class);
            withinBlockquote(theColorfulList, text, i, next);
        }
    }

    private static void withinBlockquote(List<TheMessageObject.MessageColorfulEntity> out, Spanned text,
                                         int start, int end) {
        //out.append(getOpenParaTagWithDirection(text, start, end));
        int next;
        for (int i = start; i < end; i = next) {
            next = TextUtils.indexOf(text, '\n', i, end);
            if (next < 0) {
                next = end;
            }
            int nl = 0;
            while (next < end && text.charAt(next) == '\n') {
                nl++;
                next++;
            }
            if (withinParagraph(out, text, i, next - nl, nl, next == end)) {
                /* Paragraph should be closed */
                //out.append("</p>\n");
                //out.append(getOpenParaTagWithDirection(text, next, end));
            }
        }
        //out.append("</p>\n");
    }

    private static String getOpenParaTagWithDirection(Spanned text, int start, int end) {
        return "";
    }

    private static boolean withinParagraph(List<TheMessageObject.MessageColorfulEntity> out, Spanned text,
                                           int start, int end, int nl,
                                           boolean last) {
        int next;
        for (int i = start; i < end; i = next) {
            next = text.nextSpanTransition(i, end, CharacterStyle.class);
            CharacterStyle[] style = text.getSpans(i, next,
                    CharacterStyle.class);
            for (int j = 0; j < style.length; j++) {

                String color = "000000";

                String font = "";
                String size;


                if (style[j] instanceof AbsoluteSizeSpan) {
                    size = ((AbsoluteSizeSpan) style[j]).getSize() + "";
                }
                if (style[j] instanceof ForegroundColorSpan) {

                    color = Integer.toHexString(((ForegroundColorSpan)
                            style[j]).getForegroundColor() + 0x01000000);
                    while (color.length() < 6) {
                        color = "0" + color;
                    }
                }

                if (style[j] instanceof CalligraphyTypefaceSpanOriginal) {
                    String faceName = ((CalligraphyTypefaceSpanOriginal) style[j]).getTypeFaceName();
                    if (faceName == null)
                        faceName = "DEFAULT";
                    font = faceName;

                }

                if (font.contains("DEFAULT"))
                    size = "16";
                else
                    size = "24";


                TheMessageObject.MessageColorfulEntity messageColorfulEntity = new TheMessageObject.MessageColorfulEntity();
                TheMessageObject.MessageColorfulEntity.StyleEntity styleEntity = new TheMessageObject.MessageColorfulEntity.StyleEntity();

                messageColorfulEntity.setMessage(text.toString());

                //TO-DO: mak mak
                styleEntity.setLocale("");
                styleEntity.setStyle(font);
                styleEntity.setSize(size);
                styleEntity.setColor(color);

                messageColorfulEntity.setStyle(styleEntity);
                out.add(messageColorfulEntity);

            }

        }
        return !last;
    }

    private static void withinStyle(StringBuilder out, CharSequence text,
                                    int start, int end) {
        for (int i = start; i < end; i++) {
            char c = text.charAt(i);
            if (c == '<') {
                out.append("&lt;");
            } else if (c == '>') {
                out.append("&gt;");
            } else if (c == '&') {
                out.append("&amp;");
            } else if (c >= 0xD800 && c <= 0xDFFF) {
                if (c < 0xDC00 && i + 1 < end) {
                    char d = text.charAt(i + 1);
                    if (d >= 0xDC00 && d <= 0xDFFF) {
                        i++;
                        int codepoint = 0x010000 | (int) c - 0xD800 << 10 | (int) d - 0xDC00;
                        out.append("&#").append(codepoint).append(";");
                    }
                }
            } else if (c > 0x7E || c < ' ') {
                out.append("&#").append((int) c).append(";");
            } else if (c == ' ') {
                while (i + 1 < end && text.charAt(i + 1) == ' ') {
                    out.append("&nbsp;");
                    i++;
                }
                out.append(' ');
            } else {
                out.append(c);
            }
        }
    }

}
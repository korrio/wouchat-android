package com.module.candychat.net.view;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

public class AgsUnderlineSpan extends CharacterStyle implements UpdateAppearance, ParcelableSpan {
    public AgsUnderlineSpan() {
    }

    public AgsUnderlineSpan(Parcel src) {
    }

    public int getSpanTypeId() {
        return 6;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
    }

    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(true);
    }
}

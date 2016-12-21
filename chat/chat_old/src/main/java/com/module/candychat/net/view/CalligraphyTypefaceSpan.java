package com.module.candychat.net.view;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import com.module.candychat.net.wouclass.WFont;

public class CalligraphyTypefaceSpan extends MetricAffectingSpan {
    private Typeface typeface;
    private String fontName;
    private String color;
    private WFont wFont;

    public CalligraphyTypefaceSpan(final WFont wFont) {
        this.wFont = wFont;
        this.fontName = wFont.mFace;
        this.color = wFont.mColor;
        this.typeface = wFont.typeface;
        if (typeface == null) {
            throw new IllegalArgumentException("typeface is null");
        }
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public WFont getwFont() {
        return wFont;
    }

    public void setwFont(WFont wFont) {
        this.wFont = wFont;
    }

    @Override
    public void updateDrawState(final TextPaint drawState) {
        apply(drawState);
    }

    @Override
    public void updateMeasureState(final TextPaint paint) {
        apply(paint);
    }

    private void apply(final Paint paint) {
        final Typeface oldTypeface = paint.getTypeface();
        final int oldStyle = oldTypeface != null ? oldTypeface.getStyle() : 0;
        final int fakeStyle = oldStyle & ~typeface.getStyle();

        if ((fakeStyle & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fakeStyle & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(typeface);
    }
}

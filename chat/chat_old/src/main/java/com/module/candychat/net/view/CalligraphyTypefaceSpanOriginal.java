package com.module.candychat.net.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import com.module.candychat.net.wouclass.WFont;

import info.degois.damien.android.CustomFontHtml.CaseInsensitiveAssetFontLoader;

/**
 * Created by korrio on 1/16/16.
 */
public class CalligraphyTypefaceSpanOriginal extends MetricAffectingSpan {
    private final Typeface typeface;
    //private final String typefaceName;
    private int color;
    public WFont wFont;

    Context context;

    public CalligraphyTypefaceSpanOriginal(final Typeface typeface, Context context) {
        if (typeface == null) {
            throw new IllegalArgumentException("typeface is null");
        }

        this.typeface = typeface;
        this.context = context;
    }

    public Typeface getTypeFace() {
        return typeface;
    }

    public String getTypeFaceName() {
        CaseInsensitiveAssetFontLoader fontLoader = new CaseInsensitiveAssetFontLoader(context);
        return fontLoader.getTypeFaceName(typeface);
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
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

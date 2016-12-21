package com.module.candychat.net.wouclass;


import android.graphics.Typeface;

public class WFont {

        public String mColor;
        public String mFace;
        public int mSize;
        public Typeface typeface;
        public int start;
    public int end;
       public String mMessage = "";

        public WFont(String color, String face, int size) {
           // mMessage = message;
            mColor = color;
            mFace = face;
            mSize = size;
        }

        public Typeface getTypeface(CaseInsensitiveAssetFontLoader loader) {
            return loader.getTypeFace(mFace);
        }

}

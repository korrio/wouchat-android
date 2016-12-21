package com.candychat.net.utils;

import android.content.Context;
import android.os.Vibrator;

public final class VibratorController {
    private static VibratorController sController;
    private Vibrator mVibrator;

    private VibratorController(Context context) {
        mVibrator = ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE));
    }

    synchronized public static VibratorController getController(Context context) {
        if (sController == null)
            sController = new VibratorController(context);
        return sController;
    }

    public void vibrate() {
        mVibrator.vibrate(35L);
    }

    public void vibratePattern(long[] pattern) {
        //mVibrator.vibrate(35L);
        mVibrator.vibrate(pattern, -1);
    }

}
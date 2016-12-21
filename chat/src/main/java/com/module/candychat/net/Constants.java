package com.module.candychat.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root1 on 11/17/15.
 */
public class Constants {

    public static int idBackground = R.drawable.bg4;

    //EN
    public static final String TAG="chatmodule";

    public static Map<String,String> MTYPEFACE = new HashMap<String,String>();

    public static final String DEFAULT = "DEFAULT";

    public static final int REQUEST_PICK_PHOTO = 2;
    public static final int REQUEST_PICK_VIDEO = 3;
    public static final int REQUEST_CAMERA = 5;
    public static final int REQUEST_RECORD_VIDEO = 4;
    public static final int RESULT_PLACE_PICKER = 6;
    public static final int REQUEST_CONTACT_PICKER = 7;
    public static final int REQUEST_VOICE_RECORDER = 8;
    public static final int REQUEST_VOICE_RECORDER_NATIVE = 81;

    public static final int RESULT_CODE_COPY = 1;
    public static final int RESULT_CODE_DELETE = 2;
    public static final int RESULT_CODE_FORWARD = 3;
    public static final int RESULT_CODE_OPEN = 4;
    public static final int RESULT_CODE_DOWNLOAD = 5;
    public static final int RESULT_CODE_TO_CLOUD = 6;
    public static final int RESULT_CODE_EXIT_GROUP = 7;

    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;


    public static final int PHOTO_SIZE_WIDTH = 100;
    public static final int PHOTO_SIZE_HEIGHT = 100;

    public static final String[] pallete = new String[]{"#000000", "#67bb43", "#41b691",
            "#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a"};

}

package com.keyboard.utils.demo;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.keyboard.bean.EmoticonBean;
import com.keyboard.bean.EmoticonSetBean;
import com.keyboard.db.DBHelper;
import com.keyboard.utils.DefEmoticons;
import com.keyboard.utils.EmoticonsKeyboardBuilder;
import com.keyboard.utils.Utils;
import com.keyboard.utils.imageloader.ImageBase;

import java.io.IOException;
import java.util.ArrayList;

public class EmoticonsUtils {

    /**
     * 初始化表情数据库
     * @param context
     */
    public static void initEmoticonsDB(final Context context) {
        if (!Utils.isInitDb(context)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DBHelper dbHelper = new DBHelper(context);

                    /**
                     * FROM DRAWABLE
                     */
                    ArrayList<EmoticonBean> emojiArray = ParseData(DefEmoticons.emojiArray, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean = new EmoticonSetBean("emoji", 3, 7);
                    emojiEmoticonSetBean.setIconUri("drawable://ic_chat_pop_contact");
                    emojiEmoticonSetBean.setItemPadding(20);
                    emojiEmoticonSetBean.setVerticalSpacing(10);
                    emojiEmoticonSetBean.setShowDelBtn(true);
                    emojiEmoticonSetBean.setEmoticonList(emojiArray);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean);

                    /**
                     * FROM ASSETS
                     */
                    ArrayList<EmoticonBean> xhsfaceArray = ParseData(xhsemojiArray, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.ASSETS);
                    Log.e("xhsfaceArray",xhsfaceArray.size()+"");
                    EmoticonSetBean xhsEmoticonSetBean = new EmoticonSetBean("sticker_candy_set0", 3, 7);
                    xhsEmoticonSetBean.setIconUri("assets://sticker_candy_set0/Sticker_layout_send_01.png");
                    xhsEmoticonSetBean.setItemPadding(20);
                    xhsEmoticonSetBean.setVerticalSpacing(10);
                    xhsEmoticonSetBean.setShowDelBtn(true);
                    xhsEmoticonSetBean.setEmoticonList(xhsfaceArray);
                    dbHelper.insertEmoticonSet(xhsEmoticonSetBean);

                    /**
                     * FROM FILE
                     */
                    String filePath = Environment.getExternalStorageDirectory() + "/sticker_candy_set0";
                    try{
                        FileUtils.unzip(context.getAssets().open("sticker_candy_set0.zip"), filePath);
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                    XmlUtil xmlUtil = new XmlUtil(context);
                    EmoticonSetBean bean =  xmlUtil.ParserXml(xmlUtil.getXmlFromSD(filePath + "/sticker_candy_set0.xml"));
                    bean.setItemPadding(20);
                    bean.setVerticalSpacing(5);
                    bean.setIconUri("assets://sticker_candy_set0/Sticker_layout_send_01.png");
                    dbHelper.insertEmoticonSet(bean);

                    /**
                     * FROM HTTP/HTTPS
                     */


                    /**
                     * FROM CONTENT
                     */


                    /**
                     * FROM USER_DEFINED
                     */

                    dbHelper.cleanup();
                    Utils.setIsInitDb(context, true);
                }
            }).start();
        }
    }

    public static EmoticonsKeyboardBuilder getSimpleBuilder(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        ArrayList<EmoticonSetBean> mEmoticonSetBeanList = dbHelper.queryEmoticonSet("emoji","xhs");
        dbHelper.cleanup();

        ArrayList<AppBean> mAppBeanList = new ArrayList<AppBean>();
        String[] funcArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func);
        String[] funcIconArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func_icon);
        for (int i = 0; i < funcArray.length; i++) {
            AppBean bean = new AppBean();
            bean.setId(i);
            bean.setIcon(funcIconArray[i]);
            bean.setFuncName(funcArray[i]);
            mAppBeanList.add(bean);
        }

        return new EmoticonsKeyboardBuilder.Builder()
                .setEmoticonSetBeanList(mEmoticonSetBeanList)
                .build();
    }

    public static EmoticonsKeyboardBuilder getBuilder(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        ArrayList<EmoticonSetBean> mEmoticonSetBeanList = dbHelper.queryAllEmoticonSet();
        dbHelper.cleanup();

        ArrayList<AppBean> mAppBeanList = new ArrayList<AppBean>();
        String[] funcArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func);
        String[] funcIconArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func_icon);
        for (int i = 0; i < funcArray.length; i++) {
            AppBean bean = new AppBean();
            bean.setId(i);
            bean.setIcon(funcIconArray[i]);
            bean.setFuncName(funcArray[i]);
            mAppBeanList.add(bean);
        }

        return new EmoticonsKeyboardBuilder.Builder()
                .setEmoticonSetBeanList(mEmoticonSetBeanList)
                .build();
    }

    public static ArrayList<EmoticonBean> ParseData(String[] arry, long eventType, ImageBase.Scheme scheme) {
        try {
            ArrayList<EmoticonBean> emojis = new ArrayList<EmoticonBean>();
            for (int i = 0; i < arry.length; i++) {
                if (!TextUtils.isEmpty(arry[i])) {
                    String temp = arry[i].trim().toString();
                    String[] text = temp.split(",");
                    if (text != null && text.length == 2) {
                        String fileName = null;
                        if (scheme == ImageBase.Scheme.DRAWABLE) {
                            if(text[0].contains(".")){
                                fileName = scheme.toUri(text[0].substring(0, text[0].lastIndexOf(".")));
                            }
                            else {
                                fileName = scheme.toUri(text[0]);
                            }
                        } else {
                            fileName = scheme.toUri(text[0]);
                        }
                        String content = text[1];
                        EmoticonBean bean = new EmoticonBean(eventType, fileName, content);
                        emojis.add(bean);
                    }
                }
            }
            return emojis;
        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }

        return null;
    }

    /*
    小红书表情
     */
    public static String[] xhsemojiArray = {
            "Sticker_layout_send_01.png,[Sticker_layout_send_01.png]",
            "Sticker_layout_send_02.png,[Sticker_layout_send_02.png]",
            "Sticker_layout_send_03.png,[Sticker_layout_send_03.png]",
            "sticker_candy_set0/Sticker_layout_send_04.png,[Sticker_layout_send_04.png]",
            "sticker_candy_set0/Sticker_layout_send_05.png,[Sticker_layout_send_05.png]",
            "sticker_candy_set0/Sticker_layout_send_06.png,[Sticker_layout_send_06.png]",
            "sticker_candy_set0/Sticker_layout_send_07.png,[Sticker_layout_send_07.png]",
            "sticker_candy_set0/Sticker_layout_send_08.png,[Sticker_layout_send_08.png]",
            "sticker_candy_set0/Sticker_layout_send_09.png,[Sticker_layout_send_09.png]",
            "sticker_candy_set0/Sticker_layout_send_10.png,[Sticker_layout_send_10.png]",
            "sticker_candy_set0/Sticker_layout_send_11.png,[Sticker_layout_send_11.png]",
            "sticker_candy_set0/Sticker_layout_send_12.png,[Sticker_layout_send_12.png]",
            "sticker_candy_set0/Sticker_layout_send_13.png,[Sticker_layout_send_13.png]",
            "sticker_candy_set0/Sticker_layout_send_14.png,[Sticker_layout_send_14.png]",
            "sticker_candy_set0/Sticker_layout_send_15.png,[Sticker_layout_send_15.png]",
            "sticker_candy_set0/Sticker_layout_send_16.png,[Sticker_layout_send_16.png]",
            "sticker_candy_set0/Sticker_layout_send_17.png,[Sticker_layout_send_17.png]",
            "sticker_candy_set0/Sticker_layout_send_18.png,[Sticker_layout_send_18.png]",
            "sticker_candy_set0/Sticker_layout_send_19.png,[Sticker_layout_send_19.png]",
            "sticker_candy_set0/Sticker_layout_send_20.png,[Sticker_layout_send_20.png]",
            "sticker_candy_set0/Sticker_layout_send_21.png,[Sticker_layout_send_21.png]"};

}

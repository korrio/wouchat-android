package com.module.candychat.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root1 on 11/17/15.
 */
public class Constant {

    //EN

    public static Map<String,String> MTYPEFACE = new HashMap<String,String>();

    public static final String DEFAULT = "DEFAULT";
//    //public static final String TYPEFACE_OPENSANS_FOLDER = "fontsEn/OpenSans-Regular.ttf";
//    public static final String TYPEFACE_ALEXBUSH = "AlexBrush-Regular.ttf";
//    public static final String TYPEFACE_PACIFICO = "Pacifico.ttf";
//    public static final String TYPEFACE_LOBSRAETTWO = "LobsterTwo.otf";
//    public static final String TYPEFACE_KAUSHNSCRIPT = "KaushanScript-Regular.otf";
//    public static final String TYPEFACE_SOFIA = "Sofia.otf";
//    public static final String TYPEFACE_GRANDHOTEL = "GrandHotel-Regular.otf";
//    public static final String TYPEFACE_FFF = "FFFTusj-Bold.ttf";
//    public static final String TYPEFACE_MOUNTAIN_OF_CHRISMAS = "MountainsofChristmas-Regular.ttf";
//    public static final String TYPEFACE_DANCINGSCRIPT = "DancingScriptOT.otf";
//    public static final String TYPEFACE_AMADEUS = "Amadeus.ttf";
//    public static final String TYPEFACE_COMICA = "ComicaBD.ttf";
//    public static final String TYPEFACE_VEGGIBOL = "VeggieBurger.otf";
//    public static final String TYPEFACE_SNIGLET = "Sniglet.otf";
//    public static final String TYPEFACE_BACK_JACK = "BlackJack.ttf";
//
////TH FONTS
//    public static final String TYPEFACE_KWANG_MD = "4803_Kwang_MD.ttf";
//    public static final String TYPEFACE_KWANGMD_INFLUENZA = "4805KwangMD_Influenza.ttf";
//    public static final String TYPEFACE_KWANGMD_GLORY = "4809KwangMD_Glory.ttf";
//
//    public static final String TYPEFACE_5011_THE_LITTLLE_UKI_NOWORY = "5011_thE_Little_Uki_noworry.ttf";
//    public static final String TYPEFACE_5012_THE_TLU_HUATOO0_BOLD = "5012_tLU_huatoo0_BOLD.ttf";
//    public static final String TYPEFACE_5012_THE_TLU_INFECTION = "5012_tLU_infection.ttf";
//    public static final String TYPEFACE_5013_THE_TLU_JIUMJIUM = "5103_tLU_JIUMJIUM.ttf";
//
//    public static final String TYPEFACE_AH_LUGDEKK = "AH_LuGDeK.ttf";
//    public static final String TYPEFACE_ANCHAN = "Anchan.ttf";
//
//    public static final String TYPEFACE_CAN_NONGAOR = "can_NongAor.ttf";
//    public static final String TYPEFACE_CAN_RUKDEAW01 = "can_Rukdeaw01.ttf";
//    public static final String TYPEFACE_CS_CHEANGKHAN = "CS-Cheangkhan.ttf";
//
//    public static final String TYPEFACE_DRJOYFUL = "DRjoyful.ttf";
//    public static final String TYPEFACE_KANYANUT = "Kanyanut uni.ttf";
//    public static final String TYPEFACE_KRR_AENGAEI = "NPNaipolTemplate.ttf";
//    public static final String TYPEFACE_KRR_HUTSHA = "KRR_HutSha.ttf";
//
//    public static final String TYPEFACE_KT_SMARN_PIYATIDA = "kt_smarn_piyatida.ttf";
//    public static final String TYPEFACE_KT_SMARN_SAIPSAN = "kt_smarn_saiparn.ttf";
//    public static final String TYPEFACE_KT_SMARN_SEESAN = "kt_smarn_seesan.ttf";
//
//    public static final String TYPEFACE_LAYIJIMAHANIYOM = "LayijiMahaniyom-Bao-1.2.ttf";
//    public static final String TYPEFACE_LAYIJISARANGEYO = "LayijiSarangheyo.ttf";
//
//    public static final String TYPEFACE_LIPGOFONT_LEKZII = "LIPGOZFONT-Lekzii.ttf";
//    public static final String TYPEFACE_LIPGOZFONT_LITTLE = "LIPGOZFONT-littel(labbit).ttf";
//    public static final String TYPEFACE_LIPGOZFONT_NONGLEK = "LIPGOZFONT-nongLek.ttf";
//
//    public static final String TYPEFACE_LITTLE_START_0406 = "Little_Star_0406.ttf";
//    public static final String TYPEFACE_LITTLE_START_0506 = "LittleStar0506.ttf";
//
//    public static final String TYPEFACE_LZK03_B2CAMPUS = "Lzk03_b2campus.ttf";
//    public static final String TYPEFACE_NANTATOO_111 = "NantatOO_1.1.1.ttf";
//    public static final String TYPEFACE_FONTNONGNAM = "FONTNONGNAM.ttf";
//
//    public static final String TYPEFACE_PEACH = "Peach_TV.ttf";
//    public static final String TYPEFACE_RTEMEHUA135 = "rtemehua1.35.ttf";
//    public static final String TYPEFACE_WAFFLE = "Waffle_Regular.otf";


    public static final int CAMERA_REQUEST = 5;
    public static final int SELECT_PHOTO = 2;
    public static final int RESULT_PICK_VIDEO = 3;
    public static final int RESULT_VIDEO_CAP = 4;


    public static final int PHOTO_SIZE_WIDTH = 100;
    public static final int PHOTO_SIZE_HEIGHT = 100;

    public static final String[] pallete = new String[]{"#000000", "#67bb43", "#41b691",
            "#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a"};


//    public static Typeface getTypeface(Context context, String type) {
//
//        Typeface myTypeface = null;
//
//        if (type != null) {
//            //EN
//            if (type.contains(Constant.TYPEFACE_ALEXBUSH) || Constant.MTYPEFACE.containsKey(type)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_ALEXBUSH);
//                //myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.MTYPEFACE.get(type));
//            }
//            if (type.contains(Constant.TYPEFACE_AMADEUS)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_AMADEUS);
//            }
//
//
//            if (type.contains(Constant.TYPEFACE_DANCINGSCRIPT)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_DANCINGSCRIPT);
//            }
//
//            if (type.contains(Constant.TYPEFACE_FFF)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_FFF);
//            }
//
//
//            if (type.contains(Constant.TYPEFACE_KAUSHNSCRIPT)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_KAUSHNSCRIPT);
//            }
//
//            if (type.contains(Constant.TYPEFACE_MOUNTAIN_OF_CHRISMAS)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_MOUNTAIN_OF_CHRISMAS);
//            }
//
//            if (type.contains(Constant.TYPEFACE_LOBSRAETTWO)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_LOBSRAETTWO);
//            }
//
//            if (type.contains(Constant.TYPEFACE_OPENSANS)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_OPENSANS);
//            }
//
//            if (type.contains(Constant.TYPEFACE_PACIFICO)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_PACIFICO);
//            }
//
//
//            if (type.contains(Constant.TYPEFACE_SNIGLET)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_SNIGLET);
//            }
//
//
//            if (type.contains(Constant.TYPEFACE_SOFIA)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_SOFIA);
//            }
//
//            if (type.contains(Constant.TYPEFACE_GRANDHOTEL)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_GRANDHOTEL);
//            }
//
//            if (type.contains(Constant.TYPEFACE_VEGGIBOL)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_VEGGIBOL);
//            }
//
//
//            if (type.contains(Constant.TYPEFACE_COMICA)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_COMICA);
//            }
//
//            if (type.contains(Constant.TYPEFACE_BACK_JACK)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_BACK_JACK);
//            }
//            if (type.contains(Constant.TYPEFACE_KAUSHNSCRIPT)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_KAUSHNSCRIPT);
//            }
//            //TH
//
//            if (type.contains(Constant.TYPEFACE_KWANG_MD)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KWANG_MD);
//            }
//            if (type.contains(Constant.TYPEFACE_KWANGMD_GLORY)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KWANGMD_GLORY);
//            }
//
//            if (type.contains(Constant.TYPEFACE_FONTNONGNAM)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_FONTNONGNAM);
//            }
//
//            if (type.contains(Constant.TYPEFACE_AH_LUGDEKK)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_AH_LUGDEKK);
//            }
//            if (type.contains(Constant.TYPEFACE_ANCHAN)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_ANCHAN);
//            }
//            if (type.contains(Constant.TYPEFACE_CAN_RUKDEAW01)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_CAN_RUKDEAW01);
//            }
//            if (type.contains(Constant.TYPEFACE_CS_CHEANGKHAN)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_CS_CHEANGKHAN);
//            }
//            if (type.contains(Constant.TYPEFACE_KANYANUT)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KANYANUT);
//            }
//            if (type.contains(Constant.TYPEFACE_KRR_AENGAEI)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KRR_AENGAEI);
//            }
//            if (type.contains(Constant.TYPEFACE_KRR_HUTSHA)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KRR_HUTSHA);
//            }
//            if (type.contains(Constant.TYPEFACE_KT_SMARN_PIYATIDA)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KT_SMARN_PIYATIDA);
//            }
//            if (type.contains(Constant.TYPEFACE_KT_SMARN_SAIPSAN)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KT_SMARN_SAIPSAN);
//            }
//            if (type.contains(Constant.TYPEFACE_KT_SMARN_SEESAN)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_KT_SMARN_SEESAN);
//            }
//            if (type.contains(Constant.TYPEFACE_LAYIJIMAHANIYOM)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LAYIJIMAHANIYOM);
//            }
//            if (type.contains(Constant.TYPEFACE_LAYIJISARANGEYO)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LAYIJISARANGEYO);
//            }
//            if (type.contains(Constant.TYPEFACE_LIPGOFONT_LEKZII)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LIPGOFONT_LEKZII);
//            }
//            if (type.contains(Constant.TYPEFACE_LIPGOZFONT_LITTLE)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LIPGOZFONT_LITTLE);
//            }
//            if (type.contains(Constant.TYPEFACE_LIPGOZFONT_NONGLEK)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LIPGOZFONT_NONGLEK);
//            }
//            if (type.contains(Constant.TYPEFACE_LITTLE_START_0406)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LITTLE_START_0406);
//            }
//            if (type.contains(Constant.TYPEFACE_LITTLE_START_0506)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LITTLE_START_0506);
//            }
//            if (type.contains(Constant.TYPEFACE_LZK03_B2CAMPUS)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_LZK03_B2CAMPUS);
//            }
//
//            if (type.contains(Constant.TYPEFACE_PEACH)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_PEACH);
//            }
//
//            if (type.contains(Constant.TYPEFACE_WAFFLE)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_WAFFLE);
//            }
//            if (type.contains(Constant.TYPEFACE_5011_THE_LITTLLE_UKI_NOWORY)) {
//                myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsTh/" + Constant.TYPEFACE_5011_THE_LITTLLE_UKI_NOWORY);
//            }
//
//        } else {
//            myTypeface = Typeface.createFromAsset(context.getAssets(), "fontsEn/" + Constant.TYPEFACE_OPENSANS);
//        }
//        return myTypeface;
//    }
}

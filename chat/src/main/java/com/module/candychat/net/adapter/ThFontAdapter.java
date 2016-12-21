package com.module.candychat.net.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.module.candychat.net.Constants;
import com.module.candychat.net.wouclass.CaseInsensitiveAssetFontLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ThFontAdapter extends BaseAdapter
{
	private Context context;
	private String[] listFontDiAsset;
	private ArrayList<String> listFont = new ArrayList<>();
	private ArrayList<String> aListFont = new ArrayList<>();
	String[] cacheArr = new String[25];
	Set<String> listHistoryFont;;
	private CaseInsensitiveAssetFontLoader fontLoader;


	public ThFontAdapter(Context context, String[] fontArr){
		this.context = context;
		this.fontLoader = new CaseInsensitiveAssetFontLoader(context,"fonts");
		this.listFontDiAsset = fontArr;
		this.aListFont = new ArrayList<>(Arrays.asList(listFontDiAsset));
		for(String fontName : aListFont) {
			Log.e("fontName",fontName);
			switch (fontName) {
				case "DEFAULT":
					cacheArr[0] = "Roboto-Regular";
					break;
				case "4803_Kwang_MD":
					cacheArr[1] = "4803_Kwang_MD";
					break;
				case "4809KwangMD_Glory":
					cacheArr[2] = "4809KwangMD_Glory";
					break;
				case "5011_thE_Littel_Uki_noworry":
					cacheArr[3] = "5011_thE_Littel_Uki_noworry";
					break;
				case "Kanyanut-uni":
					cacheArr[4] = "Kanyanut-uni";
					break;
				case "LayijiMaHaNiYomBAO1.2":
					cacheArr[5] = "LayijiMaHaNiYomBAO1.2";
					break;
				case "AH_LuGDeK":
					cacheArr[6] = "AH_LuGDeK";
					break;
				case "Little_Star":
					cacheArr[7] = "Little_Star";
					break;
				case "LittleStar0506":
					cacheArr[8] = "LittleStar0506";
					break;
				case "NPNaipolTemplate":
					cacheArr[9] = "NPNaipolTemplate";
					break;
				case "PeachTV":
					cacheArr[10] = "PeachTV";
					break;
				case "TheLazyKillerBack2Campus":
					cacheArr[11] = "TheLazyKillerBack2Campus";
					break;
				case "FFFTusj-Bold":
					cacheArr[12] = "Can_Rukdeaw";
					break;
				case "Anchan":
					cacheArr[13] = "Anchan";
					break;
				case "DRjoyful":
					cacheArr[14] = "DRjoyful";
					break;
				case "CSCheangkhan":
					cacheArr[15] = "CSCheangkhan";
					break;
				case "FontNongnam-Bold":
					cacheArr[16] = "FontNongnam-Bold";
					break;
				case "ktsmarnseesan":
					cacheArr[17] = "ktsmarnseesan";
					break;
				case "LayijiSarangheyo":
					cacheArr[18] = "LayijiSarangheyo";
					break;
				case "LIPGOZFONT-Lekzii":
					cacheArr[19] = "LIPGOZFONT-Lekzii";
					break;
				case "LIPGOZFONTLITTEL":
					cacheArr[20] = "LIPGOZFONTLITTEL";
					break;
				case "LIPGOZFONT-nonglek":
					cacheArr[21] = "LIPGOZFONT-nonglek";
					break;
				case "WaffleRegular":
					cacheArr[22] = "WaffleRegular";
					break;
				case "kt_smarn_piyatida":
					cacheArr[23] = "kt_smarn_piyatida";
					break;
				case "kt_smarn_saiparn":
					cacheArr[24] = "kt_smarn_saiparn";
					break;

			}

		}

		Log.e("listFontSize",listFont.size()+"");
		Log.e("cacheArrSize",cacheArr.length+"");

		this.listFont = new ArrayList<>(Arrays.asList(cacheArr));;
		listHistoryFont = new LinkedHashSet<>(listFont);
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return listHistoryFont.size();
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return fontLoader.getTypeFace(listFont.get(p1));
	}

	public ArrayList<String> getData()
	{
		// TODO: Implement this method
		return listFont;
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public View getView(int i, View view, ViewGroup p3)
	{
		TextView textView=new TextView(context);
		// TODO: Implement this method
		if(listFont.get(i) != null) {

			textView.setPadding(20,20,20,10);
			textView.setTextSize(18);
			textView.setTextColor(Color.BLACK);
			if(listFont.get(i).equals(Constants.DEFAULT) || listFont.get(i).equals("Roboto-Regular")) {
				textView.setText("default");
				textView.setTextSize(16);
				textView.setTypeface(Typeface.DEFAULT);
			} else {
                textView.setText(listFont.get(i));
                //textView.setText("มีตัวอย่าง");
				textView.setTypeface(fontLoader.getTypeFace(listFont.get(i)));
			}

			String theStyle = listFont.get(i);

			if(theStyle.equals("DEFAULT")
					|| theStyle.equals("Roboto-Regular")
					|| theStyle.equals("Amadeus")
					|| theStyle.equals("AlexBrush-Regular")
					|| theStyle.equals("Pacifico")
					|| theStyle.equals("LobsterTwo")
					|| theStyle.equals("KaushanScript-Regular")
					|| theStyle.equals("Sofia")
					|| theStyle.equals("GrandHotel-Regular")
					|| theStyle.equals("FFFTusj-Bold")
					|| theStyle.equals("MountainsofChristmas-Regular")
					|| theStyle.equals("DancingScriptOT")
					|| theStyle.equals("Amandeus")
					|| theStyle.equals("ComicaBD")
					|| theStyle.equals("VeggieBurger")
					|| theStyle.equals("Sniglet")
					|| theStyle.equals("BlackJack")
					)
				textView.setTextSize(16);
			else if(theStyle.toLowerCase().contains("lipgo") || theStyle.equals("LayijiSarangheyo"))
				textView.setTextSize(18);
			else if(theStyle.equals("kt_smarn_piyatida") || theStyle.equals("kt_smarn_saiparn"))
				textView.setTextSize(32);
			else
				textView.setTextSize(28);
		}


		return textView;
	}

}

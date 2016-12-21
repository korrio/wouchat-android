package com.module.candychat.net.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.module.candychat.net.wouclass.CaseInsensitiveAssetFontLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class EnFontAdapter extends BaseAdapter
{
	private Context context;
	private String[] listFontDiAsset;
	private ArrayList<String> listFont = new ArrayList<>();
	private ArrayList<String> aListFont = new ArrayList<>();
	String[] cacheArr = new String[16];
	Set<String> listHistoryFont ;
	private CaseInsensitiveAssetFontLoader fontLoader;


	public EnFontAdapter(Context context, String[] fontArr){
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
				case "Amadeus":
					cacheArr[1] = "Amadeus";
					break;
				case "KaushanScript-Regular":
					cacheArr[2] = "KaushanScript-Regular";
					break;
				case "Pacifico":
					cacheArr[3] = "Pacifico";
					break;
				case "GrandHotel-Regular":
					cacheArr[4] = "GrandHotel-Regular";
					break;
				case "LobsterTwo":
					cacheArr[5] = "LobsterTwo";
					break;
				case "MountainsofChristmas-Regular":
					cacheArr[6] = "MountainsofChristmas-Regular";
					break;
				case "Sofia":
					cacheArr[7] = "Sofia";
					break;
				case "BlackJack":
					cacheArr[8] = "BlackJack";
					break;
				case "VeggieBurger":
					cacheArr[9] = "VeggieBurger";
					break;
				case "ComicaBD":
					cacheArr[10] = "ComicaBD";
					break;
				case "FFFTusj-Bold":
					cacheArr[11] = "FFFTusj-Bold";
					break;
				case "Sniglet":
					cacheArr[12] = "Sniglet";
					break;
				case "DancingScriptOT":
					cacheArr[13] = "DancingScriptOT";
					break;
				case "AlexBrush":
					cacheArr[14] = "AlexBrush";
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
		return listFont.size();
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
			if(listFont.get(i).equals("Roboto-Regular")) {
				textView.setText("default");
				textView.setTextSize(16);
				textView.setTypeface(Typeface.DEFAULT);
			} else {
				textView.setText(listFont.get(i));
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
		}


		return textView;
	}

}

package com.module.candychat.net.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.module.candychat.net.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import info.degois.damien.android.CustomFontHtml.CaseInsensitiveAssetFontLoader;

public class ListViewDefaultFontAdapter extends BaseAdapter
{
	private Context context;
	private String[] listFontDiAsset;
	private ArrayList<String> listFont = new ArrayList<>();
	Set<String> listHistoryFont;;
	private CaseInsensitiveAssetFontLoader fontLoader;


	public ListViewDefaultFontAdapter(Context context,String[] fontArr){
		this.context = context;
		this.fontLoader = new CaseInsensitiveAssetFontLoader(context,"fonts");
		this.listFontDiAsset = fontArr;
		this.listFont = new ArrayList<>(Arrays.asList(listFontDiAsset));
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
		// TODO: Implement this method
		TextView textView=new TextView(context);
		textView.setPadding(20,20,20,10);
		textView.setTextSize(18);
		textView.setTextColor(Color.BLACK);
		if(listFont.get(i).equals(Constant.DEFAULT)) {
			textView.setText("default");
			textView.setTextSize(16);
			textView.setTypeface(Typeface.DEFAULT);
		} else {
			textView.setText(listFont.get(i));
			textView.setTypeface(fontLoader.getTypeFace(listFont.get(i)));
		}

		if(listFont.get(i).toLowerCase().contains("kt"))
			textView.setTextSize(32);

		return textView;
	}

}

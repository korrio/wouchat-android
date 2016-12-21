package com.module.candychat.net.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.IOException;

public class ListViewThAdapter extends BaseAdapter
{
	private Context context;
	private String[] listFontDiAsset;
	private AssetManager assetManager;
	
	
	public ListViewThAdapter(Context context){
		this.context=context;
		assetManager=context.getAssets();
		try
		{
			listFontDiAsset = assetManager.list("fontsTh");
		}
		catch (IOException e)
		{
			listFontDiAsset=new String[0];
		}
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return listFontDiAsset.length;
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return Typeface.createFromAsset(assetManager,"fontsTh/"+listFontDiAsset[p1]);
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
		TextView textView = new TextView(context);
		textView.setPadding(20,20,20,10);
		textView.setTextSize(24);
		textView.setText(listFontDiAsset[i].replace(".ttf",""));
		
		textView.setTypeface(Typeface.createFromAsset(assetManager,"fontsTh/"+listFontDiAsset[i]));
		
		
		return textView;
	}

}

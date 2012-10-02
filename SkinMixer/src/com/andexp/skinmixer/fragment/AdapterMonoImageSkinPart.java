package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import com.andexp.skinmixer.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class AdapterMonoImageSkinPart extends BaseAdapter implements ListAdapter {

	private ArrayList<String> mSkinPathList;
	private Context mContext;
	private SkinPartType mSkinPartType;
	private LayoutInflater mLayoutInflater;

	public AdapterMonoImageSkinPart(Activity activity, ArrayList<String> skinPathList, SkinPartType skinPart) {
		mContext = activity;
		mSkinPathList = skinPathList;
		mSkinPartType = skinPart;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		int size = mSkinPathList.size();
		return size/2 + size%2;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = loadHolder(convertView);
		holder.imageViewLeft.setImageBitmap(new BitmapDrawable(mContext.getResources(), mSkinPathList.get(position*2)).getBitmap());
		if(mSkinPathList.get(position*2+1) != null)
			holder.imageViewRight.setImageBitmap(new BitmapDrawable(mContext.getResources(), mSkinPathList.get(position*2+1)).getBitmap());
		
		return convertView;
	}

	private ViewHolder loadHolder(View convertView) {
		ViewHolder holder;
		if(convertView == null){
			convertView = mLayoutInflater.inflate(R.layout.list_oneimage_skinpart, null); 
			holder = new ViewHolder();
			holder.imageViewLeft = (ImageView) convertView.findViewById(R.id.iv_imageLeft);
			holder.imageViewRight = (ImageView) convertView.findViewById(R.id.iv_imageRight);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return holder;
	}
	
	static class ViewHolder{
		ImageView imageViewLeft;
		ImageView imageViewRight;
	}
}

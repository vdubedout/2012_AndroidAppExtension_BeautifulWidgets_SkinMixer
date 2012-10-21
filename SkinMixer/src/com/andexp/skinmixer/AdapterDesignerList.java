package com.andexp.skinmixer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexp.skinmixer.skindata.SkinData;

public class AdapterDesignerList extends BaseAdapter {
	private final SkinData[] mData;
	private LayoutInflater mLayoutInflater;
	private final Context mContext;

	public AdapterDesignerList(Context ctx, SkinData[] data) {
		this.mContext = ctx;
		this.mData = data;
		this.mLayoutInflater = LayoutInflater.from(ctx);
	}
	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		return mData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder mHolder;
		if(convertView == null){
			convertView = mLayoutInflater.inflate(R.layout.fragment_designerlist, null);
			mHolder = new Holder();
			mHolder.tv_name = (TextView) convertView.findViewById(R.id.sm_designerlist_tv_designer1);
			mHolder.tv_donate = (TextView) convertView.findViewById(R.id.sm_designerlist_tv_designer_donate);
			mHolder.linear = (LinearLayout) convertView.findViewById(R.id.sm_designerlist_linear);
			convertView.setTag(mHolder);
		} else {
			mHolder = (Holder) convertView.getTag();
		}
		
		
		setAuthor(position, mHolder);
		setDonateLink(position, mHolder);
		
		return convertView;
	}
	private void setDonateLink(final int position, Holder mHolder) {
		String url = mData[position].url;
		if(TextUtils.isEmpty(url)){
			mHolder.tv_donate.setVisibility(View.GONE);
		} else {
			mHolder.tv_donate.setVisibility(View.VISIBLE);
			mHolder.tv_donate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(mData[position].url));
					mContext.startActivity(i);
				}
			});
		}
	}
	
	private void setAuthor(final int position, Holder mHolder) {
		String author = mData[position].author;
		if(TextUtils.isEmpty(author)){
			mHolder.linear.setVisibility(View.GONE);
		} else {
			mHolder.linear.setVisibility(View.VISIBLE);
			mHolder.tv_name.setText(author);
		}
			
	}
	
	static class Holder{
		LinearLayout linear;
		TextView tv_name;
		TextView tv_donate;
	}

}

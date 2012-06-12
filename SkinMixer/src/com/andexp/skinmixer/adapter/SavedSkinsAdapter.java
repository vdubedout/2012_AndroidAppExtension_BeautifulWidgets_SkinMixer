package com.andexp.skinmixer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.bw.Skin;

public class SavedSkinsAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<Skin> mSkinList;
	
	public SavedSkinsAdapter(Context ctx, ArrayList<Skin> skinList){
		this.mContext = ctx;
		this.mInflater = LayoutInflater.from(mContext);
		this.mSkinList = skinList;
	}
	
	@Override
	public int getCount() {
		return mSkinList.size();
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
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			inflateHolder(holder, convertView);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		return convertView;
	}
	
	private void inflateHolder(ViewHolder holder, View convertView){
		convertView = mInflater.inflate(R.layout.list_savedskin, null);
		holder.tv_repertoryName = (TextView) convertView.findViewById(R.id.list_savedskin_skinname);
		holder.tv_creationDate = (TextView) convertView.findViewById(R.id.list_savedskin_skincreationdate);
		holder.iv_skinPreview = (ImageView) convertView.findViewById(R.id.list_savedskin_iv_preview);
		holder.btn_modify = (Button) convertView.findViewById(R.id.list_savedskin_modify);
		holder.btn_delete = (Button) convertView.findViewById(R.id.list_savedskin_delete);
	}
	
	static class ViewHolder{
		Button btn_delete;
		Button btn_modify;
		
		TextView tv_creationDate;
		TextView tv_repertoryName;
		
		ImageView iv_skinPreview;
	}

}

package com.andexp.skinmixer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.bw.part.base.SkinPartImpl;

public class OneImageSkinAdapter extends BaseAdapter{
	private Context mContext;
	private ArrayList<SkinPartImpl> mSkinPartList;
	private LayoutInflater mInflater;
	int mSkinPart;
	Handler mHandler;
	
	public OneImageSkinAdapter(Context ctx, ArrayList<SkinPartImpl> mSkinList, int skinPart){
		this.mContext = ctx;
		this.mSkinPartList = mSkinList;
		this.mInflater = LayoutInflater.from(mContext);
		this.mSkinPart = skinPart;
		this.mHandler = new Handler();
	}
	
	@Override
	public int getCount() {
		return mSkinPartList.size();
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
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_onefileskinpart, null);
			holder = new ViewHolder();
			holder.iv_skinPreview = (ImageView) convertView.findViewById(R.id.list_skinpart_iv_skin);
			holder.tv_skinName = (TextView) convertView.findViewById(R.id.list_skinpart_tv_skinname);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		PreviewBinder binder = new PreviewBinder(mSkinPartList.get(position), holder);
		binder.start();
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView tv_skinName;
		ImageView iv_skinPreview;
	}
	
	private class PreviewBinder extends Thread{
		SkinPartImpl tSkin;
		ViewHolder tHolder;
		
		public PreviewBinder(SkinPartImpl mySkin, ViewHolder holder){
			this.tSkin = mySkin;
			this.tHolder = holder;
		}
		
		public void run(){
			Looper.prepare();
			bindImage();
			Looper.loop();
		}
		
		private void bindImage(){
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					tHolder.iv_skinPreview.setImageDrawable(tSkin.getDrawable());
					tHolder.tv_skinName.setText(tSkin.getSkinPartData().directoryName);
				}
			});
		}
	}
}

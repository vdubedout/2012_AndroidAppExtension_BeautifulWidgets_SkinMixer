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

public class MultipleImageSkinAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList<SkinPartImpl[]> mSkinPartList;
	private LayoutInflater mInflater;
	Handler mHandler;

	public MultipleImageSkinAdapter(Context ctx, ArrayList<SkinPartImpl[]> mSkinList){
		this.mContext = ctx;
		this.mSkinPartList = mSkinList;
		this.mInflater = LayoutInflater.from(mContext);
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
			convertView = mInflater.inflate(R.layout.list_multiplefileskinpart, null);
			holder = new ViewHolder();
			holder.tv_skinName = (TextView) convertView.findViewById(R.id.list_skinpart_tv_skinname);
			holder.iv_number = new ImageView[4];
			holder.iv_number[0] = (ImageView) convertView.findViewById(R.id.list_skinpart_iv_number_0);
			holder.iv_number[1] = (ImageView) convertView.findViewById(R.id.list_skinpart_iv_number_1);
			holder.iv_number[2] = (ImageView) convertView.findViewById(R.id.list_skinpart_iv_number_2);
			holder.iv_number[3] = (ImageView) convertView.findViewById(R.id.list_skinpart_iv_number_3);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		PreviewBinder binder = new PreviewBinder( mSkinPartList.get(position), holder);
		binder.start();

		return convertView;
	}

	static class ViewHolder{
		TextView tv_skinName;
		TextView tv_clockType;
		ImageView iv_number[];
	}

	private class PreviewBinder extends Thread{
		SkinPartImpl[] tSkin;
		ViewHolder tHolder;

		public PreviewBinder(SkinPartImpl[] skin, ViewHolder holder){
			this.tSkin = skin;
			this.tHolder = holder;
		}

		public void run(){
			Looper.prepare();
			bindingHandler();
			Looper.loop();
		}

		private void bindingHandler(){
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					bindNumbers();
				}
			});
		}


		private void bindNumbers(){
			//MLog.v("skin:"+tSkinInfo.directoryName+" path:"+Numbers.getDrawable(tSkinInfo.directoryName, 0, tSkinInfo.clockType)); //CLEAN
			tHolder.iv_number[0].setImageDrawable(tSkin[0].getDrawable());
			tHolder.iv_number[1].setImageDrawable(tSkin[1].getDrawable());
			tHolder.iv_number[2].setImageDrawable(tSkin[5].getDrawable());
			tHolder.iv_number[3].setImageDrawable(tSkin[8].getDrawable());
			tHolder.tv_skinName.setText(tSkin[0].getSkinPartData().directoryName);
		}
	}
}

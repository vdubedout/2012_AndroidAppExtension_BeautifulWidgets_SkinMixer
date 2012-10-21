package com.andexp.skinmixer.displaygroup;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.skin.SkinGroupType;

public class AdapterMonoImageSkinPart extends BaseAdapter implements ListAdapter {
	protected ArrayList<String> mSkinPathList;
	private Context mContext;
	private SkinGroupType mSkinGroupType;
	private LayoutInflater mLayoutInflater;
	private OnSkinPartClickListener mOnSkinPartClickListener;
	private static ImageView mlastImageViewClicked;

	public AdapterMonoImageSkinPart(Context context, ArrayList<String> skinPathList,
			SkinGroupType groupType, OnSkinPartClickListener listener) {
		mContext = context;
		mSkinPathList = skinPathList;
		mSkinGroupType = groupType;
		mLayoutInflater = LayoutInflater.from(context);
		mOnSkinPartClickListener = listener;
	}

	@Override
	public int getCount() {
		int size = mSkinPathList.size();
		return size / 2 + size % 2;
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
		convertView = loadConvertView(convertView);
		ViewHolder holder = (ViewHolder) convertView.getTag();
		loadImageViews(position, holder);
		addOnClickListeners(position, holder);

		return convertView;
	}

	private View loadConvertView(View convertView) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.list_oneimage_skinpart, null);
			holder = new ViewHolder();
			holder.imageViewLeft = (ImageView) convertView.findViewById(R.id.iv_imageLeft);
			holder.imageViewRight = (ImageView) convertView.findViewById(R.id.iv_imageRight);

			convertView.setTag(holder);
		}
		return convertView;
	}

	private void loadImageViews(int position, ViewHolder holder) {
		int listPositionLeftPanel = position * 2;
		int listPositionRightPanel = listPositionLeftPanel + 1;
		
		loadPanel(listPositionLeftPanel, holder.imageViewLeft);
			loadPanel(listPositionRightPanel, holder.imageViewRight);
		
		removeAnyBackgroundColors(holder);
	}

	private void loadPanel(int listPosition, ImageView imageView) {
		if(mSkinPathList.size()> listPosition){
			imageView.setVisibility(View.VISIBLE);
			imageView.setImageBitmap(getBitmap(listPosition));
			
		} else {
			imageView.setVisibility(View.INVISIBLE);
		}
	}

	private Bitmap getBitmap(int listPosition) {
		String path = getPathFromPosition(listPosition);
		return new BitmapDrawable(mContext.getResources(), path).getBitmap();
	}

	private String getPathFromPosition(int listPosition) {
		String basePath = mSkinPathList.get(listPosition);
		return basePath + mSkinGroupType.getContainedSkinPartType()[0].getFileName();
	}

	private void removeAnyBackgroundColors(ViewHolder holder) {
		holder.imageViewLeft.setBackgroundColor(Color.parseColor("#00000000"));
		holder.imageViewRight.setBackgroundColor(Color.parseColor("#00000000"));
	}

	private void addOnClickListeners(int position, ViewHolder holder) {
		holder.imageViewLeft.setOnClickListener(new ImageOnClickListener(position, false));
		holder.imageViewRight.setOnClickListener(new ImageOnClickListener(position, true));
	}

	class ImageOnClickListener implements OnClickListener {
		int mPosition;
		boolean isRightImage;

		public ImageOnClickListener(int position, boolean isRightImage) {
			mPosition = position;
			this.isRightImage = isRightImage;
		}

		@Override
		public void onClick(View v) {
			int position = getArrayListPosition();
			mOnSkinPartClickListener.OnSkinPartClick(position, v);
			setSelectedBackgroundColor(v);
		}

		private void setSelectedBackgroundColor(View v) {
			if (mlastImageViewClicked != null) {
				mlastImageViewClicked.setBackgroundColor(Color.parseColor("#00000000"));
			}
			v.setBackgroundColor(mContext.getResources().getColor(R.color.skinpart_selected));
			mlastImageViewClicked = (ImageView) v;
		}

		private int getArrayListPosition() {
			int position = mPosition * 2;
			if (isRightImage)
				position++;
			return position;
		}
	}

	static class ViewHolder {
		ImageView imageViewLeft;
		ImageView imageViewRight;
	}
}

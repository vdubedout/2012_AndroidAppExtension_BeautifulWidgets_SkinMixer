package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import android.content.Context;
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

public class AdapterMonoImageSkinPart extends BaseAdapter implements ListAdapter {
	private static String BACKGROUND_IMAGE = "background.png";
	private static String BACKGROUNDNUMBERS_IMAGE = "background_numbers.png";
	private ArrayList<String> mSkinPathList;
	private Context mContext;
	private SkinPartType mSkinPartType;
	private LayoutInflater mLayoutInflater;
	private OnSkinPartClickListener mOnSkinPartClickListener;
	private static ImageView mlastImageViewClicked;

	public AdapterMonoImageSkinPart(Context context, ArrayList<String> skinPathList,
			SkinPartType skinPart, OnSkinPartClickListener listener) {
		mContext = context;
		mSkinPathList = skinPathList;
		mSkinPartType = skinPart;
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
		loadImages(position, holder);
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

	private void loadImages(int position, ViewHolder holder) {
		loadLeftImage(position, holder);
		loadImageRight(position, holder);
		removeAnyBackgroundColors(holder);
	}

	private void removeAnyBackgroundColors(ViewHolder holder) {
		holder.imageViewLeft.setBackgroundColor(Color.parseColor("#00000000"));
		holder.imageViewRight.setBackgroundColor(Color.parseColor("#00000000"));
	}

	private void loadImageRight(int position, ViewHolder holder) {
		if (mSkinPathList.size() > position * 2 + 1) {
			holder.imageViewRight.setVisibility(View.VISIBLE);
			String rightImagePath = mSkinPathList.get(position * 2 + 1) + "/";
			rightImagePath += getImagePathName(mSkinPartType);
			holder.imageViewRight.setImageBitmap(new BitmapDrawable(mContext.getResources(),
					rightImagePath).getBitmap());
		} else {
			holder.imageViewRight.setVisibility(View.INVISIBLE);
		}
	}

	private void loadLeftImage(int position, ViewHolder holder) {
		String leftImagePath = mSkinPathList.get(position * 2) + "/";
		leftImagePath += getImagePathName(mSkinPartType);
		holder.imageViewLeft.setImageBitmap(new BitmapDrawable(mContext.getResources(),
				leftImagePath).getBitmap());
	}

	private String getImagePathName(SkinPartType skinPartType) {
		return (skinPartType == SkinPartType.BACKGROUND) ? BACKGROUND_IMAGE
				: BACKGROUNDNUMBERS_IMAGE;
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
			v.setBackgroundColor(Color.parseColor("#FFFFCC00"));
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

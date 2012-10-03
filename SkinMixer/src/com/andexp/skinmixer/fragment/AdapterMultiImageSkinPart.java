package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import android.app.Activity;
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

public class AdapterMultiImageSkinPart extends BaseAdapter implements ListAdapter {
	private static String NUMBER_4_IMAGE = "number_4.png";
	private static String NUMBER_7_IMAGE = "number_7.png";
	private ArrayList<String> mSkinPathList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private OnSkinPartClickListener mOnSkinPartClickListener;
	private static View mlastImageViewClicked;

	public AdapterMultiImageSkinPart(Activity activity, ArrayList<String> skinPathList,
			OnSkinPartClickListener listener) {
		mContext = activity;
		mSkinPathList = skinPathList;
		mLayoutInflater = LayoutInflater.from(mContext);
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
			convertView = mLayoutInflater.inflate(R.layout.list_multiimage_skinpart, null);
			holder = new ViewHolder();
			holder.layout_left = convertView.findViewById(R.id.layoutLeft);
			holder.layout_right = convertView.findViewById(R.id.layoutRight);
			holder.left_number4 = (ImageView) convertView.findViewById(R.id.left_number4);
			holder.left_number7 = (ImageView) convertView.findViewById(R.id.left_number7);
			holder.right_number4 = (ImageView) convertView.findViewById(R.id.right_number4);
			holder.right_number7 = (ImageView) convertView.findViewById(R.id.right_number7);
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
		holder.layout_left.setBackgroundColor(Color.parseColor("#00000000"));
		holder.layout_right.setBackgroundColor(Color.parseColor("#00000000"));
	}

	private void loadLeftImage(int position, ViewHolder holder) {
		String leftImagePath = mSkinPathList.get(position * 2) + "/";
		holder.left_number4.setImageBitmap(new BitmapDrawable(mContext.getResources(),
				leftImagePath+NUMBER_4_IMAGE).getBitmap());
		holder.left_number7.setImageBitmap(new BitmapDrawable(mContext.getResources(),
				leftImagePath+NUMBER_7_IMAGE).getBitmap());
	}

	private void loadImageRight(int position, ViewHolder holder) {
		if (mSkinPathList.size() > position * 2 + 1) {
			holder.layout_right.setVisibility(View.VISIBLE);
			String rightImagePath = mSkinPathList.get(position * 2 + 1) + "/";
			holder.right_number4.setImageBitmap(new BitmapDrawable(mContext.getResources(), rightImagePath+NUMBER_4_IMAGE).getBitmap());
			holder.right_number7.setImageBitmap(new BitmapDrawable(mContext.getResources(), rightImagePath+NUMBER_7_IMAGE).getBitmap());
		} else {
			holder.layout_right.setVisibility(View.INVISIBLE);
		}
	}


	private void addOnClickListeners(int position, ViewHolder holder) {
		holder.layout_left.setOnClickListener(new ImageOnClickListener(position, false));
		holder.layout_right.setOnClickListener(new ImageOnClickListener(position, true));
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
			mlastImageViewClicked = v;
		}

		private int getArrayListPosition() {
			int position = mPosition * 2;
			if (isRightImage)
				position++;
			return position;
		}
	}

	static class ViewHolder {
		View layout_left;
		View layout_right;
		ImageView left_number4;
		ImageView left_number7;
		ImageView right_number4;
		ImageView right_number7;
	}
}

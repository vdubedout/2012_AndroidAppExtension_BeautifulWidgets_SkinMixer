package com.andexp.skinmixer.fragment;

import java.io.File;
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
import com.andexp.skinmixer.path.SkinImagePath;

public class AdapterMultiImageSkinPart extends BaseAdapter implements ListAdapter {
	private ArrayList<String> mSkinPathList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private OnSkinPartClickListener mOnSkinPartClickListener;
	private SkinPartType mSkinPart;
	private static View mlastImageViewClicked;

	public AdapterMultiImageSkinPart(Context context, ArrayList<String> skinPathList,
			SkinPartType skinPart, OnSkinPartClickListener listener) {
		mSkinPart = skinPart;
		mContext = context;
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
		loadLeftPanel(position, holder);
		loadRightPanel(position, holder);
		removeAnyBackgroundColors(holder);
	}

	private void removeAnyBackgroundColors(ViewHolder holder) {
		holder.layout_left.setBackgroundColor(Color.parseColor("#00000000"));
		holder.layout_right.setBackgroundColor(Color.parseColor("#00000000"));
	}

	private void loadLeftPanel(int position, ViewHolder holder) {
		int listPosition = position * 2;
		holder.left_number4.setImageBitmap(new BitmapDrawable(mContext.getResources(),
				getLeftImagePath(listPosition)).getBitmap());
		holder.left_number7.setImageBitmap(new BitmapDrawable(mContext.getResources(),
				getRightImagePath(listPosition)).getBitmap());
	}

	private String getLeftImagePath(int listPosition) {
		String path = mSkinPathList.get(listPosition) + File.separator;
		String image;
		switch (mSkinPart) {
		case NUMBER_0:
			image = SkinImagePath.NUMBER[4];
			break;
		case AM:
		default:
			image = SkinImagePath.AM;
			break;
		}

		return path + image;
	}

	private String getRightImagePath(int listPosition) {
		String path = mSkinPathList.get(listPosition) + File.separator;
		String image;
		switch (mSkinPart) {
		case NUMBER_0:
			image = SkinImagePath.NUMBER[7];
			break;
		case AM:
		default:
			image = SkinImagePath.PM;
			break;
		}

		return path + image;
	}

	private void loadRightPanel(int position, ViewHolder holder) {
		int listPosition = position * 2 + 1;
		if (mSkinPathList.size() > listPosition) {
			holder.layout_right.setVisibility(View.VISIBLE);
			holder.right_number4.setImageBitmap(new BitmapDrawable(mContext.getResources(),
					getLeftImagePath(listPosition)).getBitmap());
			holder.right_number7.setImageBitmap(new BitmapDrawable(mContext.getResources(),
					getRightImagePath(listPosition)).getBitmap());
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
			v.setBackgroundColor(mContext.getResources().getColor(R.color.skinpart_selected));
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

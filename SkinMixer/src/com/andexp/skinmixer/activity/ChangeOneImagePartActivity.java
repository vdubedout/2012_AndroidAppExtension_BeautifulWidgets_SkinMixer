package com.andexp.skinmixer.activity;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.adapter.OneImageSkinAdapter;
import com.andexp.skinmixer.bw.part.base.SkinPartImpl;
import com.andexp.skinmixer.utils.Extra;
import com.andexp.skinmixer.utils.SDCard;
import com.andexp.skinmixer.utils.SDCardSkinLoader;

public class ChangeOneImagePartActivity extends ListActivity{
	ListView listview_SkinParts;
	private int skinPartType;

	private ArrayList<SkinPartImpl> mSkinList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skinpartchange);

		if(!SDCard.isMounted()){
			Toast.makeText(this, R.string.SDCard_not_mount, Toast.LENGTH_LONG).show();
			finish();
		} else {
			initializeVariable();
			//TODO bindAdapter, afficher une roue qui charge, async pour charger les données, puis notify dataset changed
			loadSkinList();
			bindAdapterToListView();
		}
	}

	private void initializeVariable(){
		skinPartType = getIntent().getExtras().getInt(Extra.SKIN_PART);
		mSkinList = new ArrayList<SkinPartImpl>();
	}

	private void loadSkinList(){
		mSkinList = SDCardSkinLoader.getSuperClockSkinIdsList(skinPartType);
	}  		


	private void bindAdapterToListView(){
		OneImageSkinAdapter myAdapter = new OneImageSkinAdapter(this, mSkinList, skinPartType);
		setListAdapter(myAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(Intent.ACTION_CHOOSER);
		intent.putExtra(Extra.SKIN_PART, skinPartType);
		intent.putExtra(Extra.SKIN_DIRECTORYNAME, mSkinList.get(position).getSkinPartData().directoryName);
		intent.putExtra(Extra.SKIN_CLOCKTYPE, mSkinList.get(position).getSkinPartData().clockType);

		setResult(RESULT_OK, intent);
		finish();
	}


}

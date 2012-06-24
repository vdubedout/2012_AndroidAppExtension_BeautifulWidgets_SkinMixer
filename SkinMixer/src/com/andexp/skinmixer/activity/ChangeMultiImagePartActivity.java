package com.andexp.skinmixer.activity;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.adapter.MultipleImageSkinAdapter;
import com.andexp.skinmixer.bw.part.base.SkinPartImpl;
import com.andexp.skinmixer.utils.Extra;
import com.andexp.skinmixer.utils.MLog;
import com.andexp.skinmixer.utils.SDCard;
import com.andexp.skinmixer.utils.SDCardSkinLoader;

public class ChangeMultiImagePartActivity extends ListActivity{
	ListView listview_SkinParts;

	private ArrayList<SkinPartImpl[]> mSkinList;

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
//			loadSkinList();
			bindAdapterToListView();
		}
	}

	private void initializeVariable(){
		mSkinList = SDCardSkinLoader.getNumbersSkinPart();
	}



//	private void loadSkinList(){
//		mSkinList = get
//		mSkinList.addAll(SDCardSkinLoader.getNormalClockSkinIdsList(ESkinPart.NUMBER_0)); 
//	}  		


	private void bindAdapterToListView(){
		MultipleImageSkinAdapter myAdapter = new MultipleImageSkinAdapter(this, mSkinList);
		setListAdapter(myAdapter);
		//test
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(Intent.ACTION_CHOOSER);
		String directoryName = mSkinList.get(position)[0].getSkinPartData().directoryName;
		int clockType = mSkinList.get(position)[0].getSkinPartData().clockType;
		
		MLog.d("Directory Name:"+directoryName+" | clockType:"+clockType);
		intent.putExtra(Extra.SKIN_DIRECTORYNAME, directoryName);
		intent.putExtra(Extra.SKIN_CLOCKTYPE, clockType);

		setResult(RESULT_OK, intent);
		finish();
	}


}

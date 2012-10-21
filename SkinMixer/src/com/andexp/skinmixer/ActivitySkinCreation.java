package com.andexp.skinmixer;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;
import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.skin.SkinGroupType;

public class ActivitySkinCreation extends SherlockActivity {
	private String[] mSkinGroupPaths;

	EditText et_skinName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skincreation);
		
		mSkinGroupPaths = getIntent().getExtras().getStringArray(SkinGroupType.class.toString());
		et_skinName = (EditText) findViewById(R.id.skincreation_skinname);
		new Thread(new SkinNameSearcherRunner(this, et_skinName)).run();
		
	}

	class SkinNameSearcherRunner implements Runnable {
		private final Context mContext;
		private final EditText mEditText;
		public SkinNameSearcherRunner(Context ctx, EditText editText) {
			this.mContext = ctx;
			this.mEditText = editText;
		}
		public void run() {
			SharedPreferences prefs = mContext.getSharedPreferences("SkinMixer", Context.MODE_PRIVATE);
			int skinNumber = prefs.getInt("lastGeneratedNumber", 0)+1;
			String baseSkinName = mContext.getString(R.string.sm_base_skinname);
			ArrayList<String> superClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
			
			while(isSkinNameInList(baseSkinName+skinNumber, superClockSkinPathList)){
				skinNumber++;
			}
			mEditText.setHint(baseSkinName+skinNumber);
		}
	}

	protected boolean isSkinNameInList(String skinName, ArrayList<String> superClockSkinPathList) {
		boolean isInList = false;
		for (String skinPath : superClockSkinPathList) {
			if(skinPath.toLowerCase().contains(skinName.toLowerCase())){
				isInList = true;
			}
		}
		return isInList;
	}
	

}

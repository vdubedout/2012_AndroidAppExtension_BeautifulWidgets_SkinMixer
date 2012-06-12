package com.andexp.skinmixer;

import java.util.List;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import roboguice.application.RoboApplication;
import android.content.Context;

import com.google.inject.Module;

@ReportsCrashes(formKey = "dFQ2ZXhrYlYtOXBzMGdQd2lHTGJ2V2c6MQ")
public class MyApplication extends RoboApplication {
	static Context mContext;
	
	@Override
    public void onCreate() {
        ACRA.init(this);
        mContext = this.getApplicationContext();
        super.onCreate();
    }
	
	public static Context getCustomApplicationContext(){ //TODO temporaire
		return mContext;
	}
	
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new MyModules());
	}
}

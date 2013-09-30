package com.android.morehealth;

import android.app.Application;

public class MyGlobalApp extends Application {

	public boolean loginFlag = false;

	public boolean isLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		loginFlag = false;
	}
}

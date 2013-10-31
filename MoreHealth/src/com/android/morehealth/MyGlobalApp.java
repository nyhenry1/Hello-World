package com.android.morehealth;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class MyGlobalApp extends Application {


	public String baseUrl = "http://more-holdings-engine.herokuapp.com/";
//	public String baseUrl= "http://192.168.56.1/";
	public String signIn = "more-health/mobile-sign-in";
	public String signUp = "more-health/mobile-sign-up";
	public boolean loginSuccess = false;
	public String sessionToken = "";
	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public String getSignUp() {
		return signUp;
	}

	public void setSignUp(String signUp) {
		this.signUp = signUp;
	}

	public String getSignIn() {
		return signIn;
	}

	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	public static List<String> getDataSource(){
		List<String> list = new ArrayList<String>();
		list.add("name xiaohan");
		list.add("address: 37 john street");
		list.add("medical 1: not stayup too late");
		list.add("medical 2: vomit if very tired");
		list.add("medical 3: may have high blood pressure because of faternal side");
		list.add("medical 4: have 1 month super long cough after cold, "
				+ "if not pay attention and use strong pills");

		return list;
	}
	
	
}

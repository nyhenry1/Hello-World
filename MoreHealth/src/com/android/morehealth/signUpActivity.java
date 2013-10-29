package com.android.morehealth;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class signUpActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
	}
	
	
	class signupTask extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("hello", "running inside the task");
			MyGlobalApp myApp = (MyGlobalApp) getApplication();
			String newPath = myApp.baseUrl;
			newPath += myApp.signIn;

			Map<String, String> params = new HashMap<String, String>();
			params.put("email", "test2@gmail.com");
			params.put("password", "123456");

			String result = HttpUtils.sendHttpClientPost(newPath, params,
					"utf-8");

			if (JSONParser.successRes(JSONParser.strToJSON(result)))
				myApp.setLoginSuccess(true);
			System.out.println("result-->" + result);
			Log.d("hello", "--result-->" + result);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			super.onPostExecute(result);
			MyGlobalApp myApp = (MyGlobalApp) getApplication();
			if (myApp.isLoginSuccess()) {
				Intent intent = new Intent();
				intent.setClass(signUpActivity.this, MainActivity.class);
				myApp = (MyGlobalApp) getApplication();
				startActivity(intent);
			}

		}
	};
}

package com.android.morehealth;

import java.util.HashMap;
import java.util.Map;

import com.android.morehealth.loginActivity.loginTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class signUpActivity extends Activity {
	private Button registerButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        registerButton = (Button)findViewById(R.id.signUpButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new signupTask().execute();
			}
		});
	}
	
	
	class signupTask extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("hello", "running inside the task");
			MyGlobalApp myApp = (MyGlobalApp) getApplication();
			String newPath = myApp.baseUrl;
			newPath += myApp.signUp;

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

package com.android.morehealth;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends Activity {
	private Button loginButton;
	private Button signUpButton;
	private MyGlobalApp myApp;
	private EditText nameEText;
	private EditText passwordEText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginButton = (Button) this.findViewById(R.id.loginButton);
		signUpButton = (Button)this.findViewById(R.id.signUpButton);
		nameEText = (EditText)this.findViewById(R.id.editText1);
		passwordEText = (EditText)this.findViewById(R.id.editText2);


		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new loginTask().execute();
			}
		});
		signUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(loginActivity.this, signUpActivity.class);
				myApp = (MyGlobalApp) getApplication();
				startActivity(intent);
				
			}
		});
	}

	class loginTask extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("hello", "running inside the task");
			MyGlobalApp myApp = (MyGlobalApp) getApplication();
			String newPath = myApp.baseUrl;
			newPath += myApp.signIn;

			Map<String, String> params = new HashMap<String, String>();
			params.put("email", nameEText.getText().toString());
			params.put("password", passwordEText.getText().toString());

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
				intent.setClass(loginActivity.this, MainActivity.class);
				myApp = (MyGlobalApp) getApplication();
				startActivity(intent);
			}

		}
	};
	
}

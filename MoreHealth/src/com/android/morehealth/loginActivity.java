package com.android.morehealth;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

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
				MyGlobalApp myApp = (MyGlobalApp) getApplication();
				String newPath = myApp.baseUrl;
				newPath += myApp.signIn;
//				newPath = "http://192.168.56.1/PHPAndroidDB/get_all_products.php";
				new loginTask().execute(newPath, "email", nameEText.getText().toString(), "password", passwordEText.getText().toString());
//				new loginTask().execute(newPath);
			}
		});
		signUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(loginActivity.this, signUpActivity.class);
				startActivity(intent);
				
			}
		});
	}

	class loginTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String ...urls) {
			// TODO Auto-generated method stub
			Log.d("hello", "running inside the task");
			String newPath = urls[0];
			Map<String, String> params = new HashMap<String, String>();
			for(int i = 1; i + 1 < urls.length; i += 2)
				params.put(urls[i], urls[i+1]);
			String result = HttpUtils.sendHttpClientPost(newPath, params,
					"utf-8");
			System.out.println("result-->" + result);
			Log.d("hello", "--result-->" + result);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject obj = JSONParser.strToJSON(result);
			if(JSONParser.successRes(obj))
			{
				MyGlobalApp myApp = (MyGlobalApp) getApplication();
				myApp.setLoginSuccess(true);
				Intent intent = new Intent();
				intent.setClass(loginActivity.this, MainActivity.class);
				startActivity(intent);
			}
		}	
	};
	
}

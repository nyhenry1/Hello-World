package com.android.morehealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class loginActivity extends Activity {
	private Button loginButton;
	private MyGlobalApp myApp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginButton= (Button)this.findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(loginActivity.this, MainActivity.class);
				myApp = (MyGlobalApp)getApplication();
				myApp.setLoginFlag(true);
				startActivity(intent);
			}
		});
	}
}

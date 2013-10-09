package com.android.morehealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	MyGlobalApp myApp;
	private Button profileBtn;
	private Button MapBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApp = (MyGlobalApp)getApplication();
        if(!myApp.isLoginFlag())
        {
        	Intent intent = new Intent();
        	intent.setClass(MainActivity.this, loginActivity.class);
        	startActivity(intent);
        }
        profileBtn = (Button)findViewById(R.id.profile);
        MapBtn = (Button)findViewById(R.id.Map);
        profileBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ProfileActivity.class);
				startActivity(intent);
			}
		});
        
        MapBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    }
}

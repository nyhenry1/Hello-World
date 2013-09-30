package com.android.morehealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
	MyGlobalApp myApp;
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
    }
}

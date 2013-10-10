package com.android.morehealth;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProfileActivity extends Activity {

	private ListView listView;
	private ArrayAdapter<String> adapter;
	private List<String> data = null;
	private MyGlobalApp myApp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		myApp = (MyGlobalApp)getApplication();
		data = myApp.getDataSource();
		adapter = new ArrayAdapter<String>(ProfileActivity.this,
				android.R.layout.simple_list_item_1, data);
		listView = (ListView)findViewById(R.id.listview);
		listView.setAdapter(adapter);
	}
}

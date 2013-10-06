package com.google.jsonandroid;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class NewProductActivity extends Activity {
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_product);
		button = (Button)findViewById(R.id.btnNew);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("hello", "button pressed");
				new ThreadTask().execute();
			}
		});
	}
	
class ThreadTask extends AsyncTask<String, String, String>{
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("hello", "running inside the task");
			String path = "http://192.168.56.1/postForm.php";
			Map<String, String> params = new HashMap<String, String>();
			params.put("name",  "nyhenry");
			params.put("name2", "nyhenry2");
			params.put("name4", "nyhenry4");
			params.put("name5", "nyhenry5");
			String result = HttpUtils.sendHttpClientPost(path, params, "utf-8");
			System.out.println("result-->"+ result);
			Log.d("hello", "--result-->"+result);
			return null;
		}
	};
}

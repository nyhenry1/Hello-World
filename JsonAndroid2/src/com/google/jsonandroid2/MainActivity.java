package com.google.jsonandroid2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.all_product_btn);
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
			String path = "http://192.168.56.1/PHPAndroidDB/get_all_products.php";
			Map<String, String> params = new HashMap<String, String>();
			String result = HttpUtils.sendHttpClientPost(path, params, "utf-8");
			try {
				JSONObject jsonObject = new JSONObject(result);
				Iterator keys = jsonObject.keys();
				Map<String, String> map = new HashMap<String, String>();
				Queue<JSONObject> objectQ = new LinkedList<JSONObject>();
				objectQ.offer(jsonObject);
				// actually should not use a map to store them.
				while(!objectQ.isEmpty()){
					JSONObject curObj = objectQ.poll();
					Iterator curKeys = curObj.keys();
					while(curKeys.hasNext()){
						String key = (String)curKeys.next();
						String val = curObj.getString(key);
						char iniCh = val.charAt(0);
						if(iniCh == '{'){
							JSONObject newJsonobj = curObj.getJSONObject(key);
							objectQ.offer(newJsonobj);
						}
						else if(iniCh == '['){
							JSONArray newJsonarr = curObj.getJSONArray(key);
							for(int JsonI = 0; JsonI < newJsonarr.length(); ++ JsonI)
							{
								JSONObject newJsonObj = newJsonarr.getJSONObject(JsonI);
								objectQ.offer(newJsonObj);
							}
						}
						else
							map.put(key,  curObj.getString(key));
					}
				}
				int mapLen= map.size();
				Log.d("map size", ""+mapLen);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("result-->"+ result);
			Log.d("hello", "--result-->"+result);
			return null;
		}
	};
}

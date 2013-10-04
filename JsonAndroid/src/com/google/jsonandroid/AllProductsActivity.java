package com.google.jsonandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;



import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AllProductsActivity extends Activity {
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> productsList;
	private static String url_all_products = 
			"http://127.0.0.1/postForm.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_PID = "pid";
	private static final String TAG_NAME = "name";
	
	JSONArray products = null;
	ListView lv;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_product);
		productsList = new ArrayList<HashMap<String, String>>();
		Log.d("All products", "onCreate");
		new LoadAllProducts().execute();
		lv = (ListView)findViewById(R.id.list);
		lv.setOnItemClickListener(new ListView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				String pid = ((TextView) view.findViewById(R.id.pid)).getText().toString();
			}
		});
	}
	class LoadAllProducts extends AsyncTask<String, String, String>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			Log.d("All products", "onPreExecute");
			super.onPreExecute();
			pDialog = new ProgressDialog(AllProductsActivity.this);
			pDialog.setMessage("Loading products. Please wait");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			
		}
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			Log.d("All Prodcts:", "doInBackgroud");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			JSONObject json = jParser.makeHttpRequest(url_all_products, "POST", params);
			Log.d("All Prodcts:", json.toString());
			try{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1){ 
					products = json.getJSONArray(TAG_PRODUCTS);
					for(int i = 0; i < products.length(); ++i){
						JSONObject c = products.getJSONObject(i);
						String id = c.getString(TAG_PID);
						String name = c.getString(TAG_NAME);
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(TAG_PID, id);
						map.put(TAG_NAME, name);
						productsList.add(map);
						productsList.add(map);
					}
				}
				else{
					Intent i = new Intent(AllProductsActivity.this, NewProductActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			}
			catch(JSONException e){
				e.printStackTrace();
			}

			return null;
		}
		
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
/*				ListAdapter adapter = new SimpleAdapter(
					AllProductsActivity.this, productsList, R.layout.list_item, new String[]
							{TAG_PID, TAG_NAME}, new int[]{ R.id.pid, R.id.name});
					lv.setAdapter(adapter);
*/
				}
			}));
		}
	};
}


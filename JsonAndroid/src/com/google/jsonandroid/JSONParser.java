package com.google.jsonandroid;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser { 

	static InputStream is = null; 
	static JSONObject jObj = null; 
	static String json = ""; 
	// constructor 
	public JSONParser() {} 
	// function get json from url 
	// by making HTTP
	
	
	public  static JSONObject sendHttpClientPost(String path, Map<String, String> map, String encode){
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if(map != null && !map.isEmpty()){
			for(Map.Entry<String, String> entry : map.entrySet()){
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encode);
			HttpPost httpPost = new HttpPost(path);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				return changeInputStream(httpResponse.getEntity().getContent(), encode);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static JSONObject changeInputStream(InputStream inputStream, String encode) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		JSONObject ret = new JSONObject();
		if(inputStream != null)
			try {
				while((len = inputStream.read(data))!= -1){
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ret;
	} 
	
/*	
	public JSONObject makeHttpRequest(String url, String method, 
	List<NameValuePair> params) { 
		// Making HTTP request 
		try { 
			// request method is POST 
			// defaultHttpClient 
			DefaultHttpClient httpClient = new DefaultHttpClient(); 
			HttpResponse httpResponse;
			if(method == "POST")
			{
				HttpPost httpPost = new HttpPost(url); 
				httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8")); 
				httpResponse = httpClient.execute(httpPost); 
			}
			else
			{
				HttpGet httpGet = new HttpGet(url);
				httpResponse = httpClient.execute(httpGet); 
			}
			HttpEntity httpEntity = httpResponse.getEntity(); 
			is = httpEntity.getContent(); 
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
		} catch (ClientProtocolException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		try { 
			BufferedReader reader = new BufferedReader(new InputStreamReader( 
			is, "UTF-8")); 
			StringBuilder sb = new StringBuilder(); 
			String line = null; 
			while ((line = reader.readLine()) != null) { 
				sb.append(line + "\n"); 
			} 
			is.close(); 
			json = sb.toString(); 
		} catch (Exception e) { 
			Log.e("Buffer Error", "Error converting result " + e.toString()); 
			Log.d("json", json.toString()); 
		} 
		// try parse the string to a JSON object 
		try { 
			jObj = new JSONObject(json); 
		} catch (JSONException e) { 
			Log.e("JSON Parser", "Error parsing data " + e.toString()); 
		} 
		return jObj; 
	} 
*/
}; 
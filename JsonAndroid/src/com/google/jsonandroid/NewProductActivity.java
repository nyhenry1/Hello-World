package com.google.jsonandroid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
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
				String path = "http://localhost/postForm.php";
				Map<String, String> params = new HashMap<String, String>();
				params.put("name",  "nyhenry");
				params.put("name2", "nyhenry2");
				params.put("name4", "nyhenry4");
				params.put("name5", "nyhenry5");
				String result = NewProductActivity.sendHttpClientPost(path, params, "utf-8");
				System.out.println(result);
				
			}
		});
	}
	
	public static String sendHttpClientPost(String path, Map<String, String> map, String encode){
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
			DefaultHttpClient client = new DefaultHttpClient();
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
		return "";
	}
	
	
	private static String changeInputStream(InputStream inputStream, String encode) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
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
		return result;
	}
}

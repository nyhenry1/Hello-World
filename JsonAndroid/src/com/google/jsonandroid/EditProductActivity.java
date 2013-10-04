package com.google.jsonandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditProductActivity extends Activity {
	EditText txtName;
	EditText txtPrice;
	EditText txtDesc;
	EditText txtCreatedAt;
	Button btnSave;
	Button btnDelete;
	String pid;
	
	private ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();
	private static final String url_product_details = "";
	private static final String url_update_product ="";
	private static final String url_delete_product = "";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODCUT = "product";
	private static final String TAG_PID = "pid";
	private static final String TAG_NAME = "name";
	private static final String TAG_PRICE = "price";
	private static final String TAG_DESCRIPTION = "description";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_product);
	}
	
}

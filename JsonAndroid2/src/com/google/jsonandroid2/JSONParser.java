package com.google.jsonandroid2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	public JSONParser() {
		// TODO Auto-generated constructor stub
	}
	
	public JSONObject strToJSON(String str){
		try {
			return new JSONObject(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// 0 val 1 obj 2 array -1 error
	public int keyRetType(JSONObject obj, String key) {
		
		String val;
		try {
			val = obj.getString(key);
			char iniCh = val.charAt(0);
			if(iniCh == '{')
				return 1;
			else if(iniCh == '[')
				return 2;
			else
				return 0;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean success(JSONObject obj){
		Iterator keys = obj.keys();
		Queue<JSONObject> objectQ = new LinkedList<JSONObject>();
		objectQ.offer(obj);
		try {
			if(!objectQ.isEmpty()){
				JSONObject curObj = objectQ.poll();
				Iterator curKeys = curObj.keys();
				while(curKeys.hasNext()){
					if((String)curKeys.next() == "success")
						return obj.getString("success") == "1";
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	String jsonStr;
}

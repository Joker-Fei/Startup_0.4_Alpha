package com.syf.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

//解析JSON为字符串
public class JSONTools {
	@SuppressWarnings("static-access")
	public static String getJsonValue(String key,String jsonString){
		String outString = null;
	try {
		JSONObject jsonObject = new JSONObject();
		jsonObject=jsonObject.fromObject(jsonString);
		
		outString=jsonObject.getString(key);
	} catch (JSONException e) {
		e.printStackTrace();
	}
	return outString;
}

}

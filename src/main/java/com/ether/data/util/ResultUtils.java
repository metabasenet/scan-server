package com.ether.data.util;

import com.alibaba.fastjson.JSONObject;


public class ResultUtils {


	public static JSONObject successResult() {
		return successResult(new JSONObject());
	}

	public static JSONObject successResult(Object returnData) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", ErrorEnum.E_200.getErrorCode());
		resultJson.put("msg", ErrorEnum.E_200.getErrorMsg());
		resultJson.put("data", returnData);
		return resultJson;
	}

	public static JSONObject successResult(Object returnData, String msg)
	{
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", ErrorEnum.E_200.getErrorCode());
		resultJson.put("msg", msg);
		resultJson.put("data", returnData);
		return resultJson;
	}

	public static JSONObject errorResult(Object returnData) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", 500);
		resultJson.put("msg", returnData);
		return resultJson;
	}

	public static JSONObject errorResult(ErrorEnum errorEnum) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", errorEnum.getErrorCode());
		resultJson.put("msg", errorEnum.getErrorMsg());
		resultJson.put("data", new JSONObject());
		return resultJson;
	}

}

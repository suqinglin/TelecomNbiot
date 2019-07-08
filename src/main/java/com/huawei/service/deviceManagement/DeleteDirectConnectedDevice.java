package com.huawei.service.deviceManagement;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Delete Direct Connected Device :
 * This interface is used to delete a dierectly connected device.
 */
public class DeleteDirectConnectedDevice {


	public static void main(String args[]) throws Exception {

		// Two-Way Authentication
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();

		// Authentication，get token
		String accessToken = login(httpsUtil);

		//Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

		//please replace the deviceId, when you use the demo.
        String deviceId = "eb33bc43-ee78-40b8-9351-1d5c8a885872";
        String urlDeleteDirectConnectedDevice = Constant.DELETE_DIRECT_CONNECTED_DEVICE + "/" +deviceId;
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseDeleteDirectConnectedDevice = httpsUtil.doDeleteWithParasGetStatusLine(urlDeleteDirectConnectedDevice, null, header);

		System.out.println("DeleteDirectConnectedDevice, response content:");
		System.out.println(responseDeleteDirectConnectedDevice.getStatusLine());
		System.out.println(responseDeleteDirectConnectedDevice.getContent());
		System.out.println();
    }


	/**
	 * Authentication，get token
	 */
	@SuppressWarnings("unchecked")
	public static String login(HttpsUtil httpsUtil) throws Exception {

		String appId = Constant.APPID;
		String secret = Constant.SECRET;
		String urlLogin = Constant.APP_AUTH;

		Map<String, String> paramLogin = new HashMap<>();
		paramLogin.put("appId", appId);
		paramLogin.put("secret", secret);

		StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

		System.out.println("app auth success,return accessToken:");
		System.out.println(responseLogin.getStatusLine());
		System.out.println(responseLogin.getContent());
		System.out.println();

		Map<String, String> data = new HashMap<>();
		data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
		return data.get("accessToken");
	}

}
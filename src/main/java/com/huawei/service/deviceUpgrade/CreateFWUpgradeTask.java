package com.huawei.service.deviceUpgrade;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Create Firmware Upgrade Task :
 * This interface is used to create a firmware upgrade task for devices.
 */
public class CreateFWUpgradeTask {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;        
        String urlCreateFWUpgradeTask = Constant.CREATE_FW_UPGRADE_TASK;
        
        //please replace the fileId, when you use the demo.
        String fileId = "6a5b8658abf5adbae6de914b";
        
        //please replace the deviceId, when you use the demo.
        ArrayList<String> devices = new ArrayList<String>();
        devices.add("9e620731-9a8b-42b7-b685-263546b74afc");
        
        Map<String, Object> operateDevices = new HashMap<>();
        operateDevices.put("devices", devices);
        
        //executeType: now|device_online|custom
        String executeType = "now";
        
        Map<String, Object> operatePolicy = new HashMap<>();
        operatePolicy.put("executeType", executeType);
        
        Map<String, Object> paramCreateFWUpgradeTask = new HashMap<>();
        paramCreateFWUpgradeTask.put("fileId", fileId);
        paramCreateFWUpgradeTask.put("targets", operateDevices);
        paramCreateFWUpgradeTask.put("policy", operatePolicy);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramCreateFWUpgradeTask);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseCreateFWUpgradeTask = httpsUtil.doPostJsonGetStatusLine(urlCreateFWUpgradeTask, header, jsonRequest);

        System.out.println("CreateFWUpgradeTask, response content:");
        System.out.println(responseCreateFWUpgradeTask.getStatusLine());
        System.out.println(responseCreateFWUpgradeTask.getContent());
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

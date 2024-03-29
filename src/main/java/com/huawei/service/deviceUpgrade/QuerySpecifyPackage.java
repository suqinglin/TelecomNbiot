package com.huawei.service.deviceUpgrade;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Query Specify Package :
 * This interface is used to query a version package by fileId, which has been uploaded to IoT platform.
 */
public class QuerySpecifyPackage {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //please replace the fileId, when you use the demo.
        String fileId = "6a5b8658abf5adbae6de914b";
        
        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
        String urlQuerySpecifyPackage = Constant.QUERY_SPECIFY_PACKAGE + "/" + fileId;

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQuerySpecifyPackage = httpsUtil.doGetWithParasGetStatusLine(urlQuerySpecifyPackage, null, header);

        System.out.println("QuerySpecifyPackage, response content:");
        System.out.println(responseQuerySpecifyPackage.getStatusLine());
        System.out.println(responseQuerySpecifyPackage.getContent());
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

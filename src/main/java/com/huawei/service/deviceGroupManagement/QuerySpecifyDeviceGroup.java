package com.huawei.service.deviceGroupManagement;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Query Specify Device Group :
 * This interface is used to query specify device group.
 */
public class QuerySpecifyDeviceGroup {

    public static void main(String[] args) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);
        
        //please replace the devGroupId, when you use the demo.
        String devGroupId = "b9b4f961-eef8-4cd6-91fe-dd4de1b344da";

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlQuerySpecifyDeviceGroup = Constant.QUERY_SPECIFY_DEVICE_GROUP + "/" + devGroupId;
        String appId = Constant.APPID;
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQuerySpecifyDeviceGroup = httpsUtil.doGetWithParasGetStatusLine(urlQuerySpecifyDeviceGroup, null, header);

        System.out.println("QuerySpecifyDeviceGroup, response content:");
        System.out.println(responseQuerySpecifyDeviceGroup.getStatusLine());
        System.out.println(responseQuerySpecifyDeviceGroup.getContent());
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

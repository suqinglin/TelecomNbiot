package com.huawei.service.dataCollection;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Query Batch Devices:
 * This interface is used to query information about devices in batches.
 */
public class QueryBatchDevices {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryBatchDevices = Constant.QUERY_BATCH_DEVICES;

        //please replace the pageNo and pageSize, when you use the demo.
        Integer pageNo = 0;
        Integer pageSize = 10;

        Map<String, String> paramQueryBatchDevices = new HashMap<>();
        paramQueryBatchDevices.put("appId", appId);
        paramQueryBatchDevices.put("pageNo", pageNo.toString());
        paramQueryBatchDevices.put("pageSize", pageSize.toString());

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQueryBatchDevices = httpsUtil.doGetWithParasGetStatusLine(urlQueryBatchDevices,
        		paramQueryBatchDevices, header);

        System.out.println("QueryBatchDevices, response content:");
        System.out.println(responseQueryBatchDevices.getStatusLine());
        System.out.println(responseQueryBatchDevices.getContent());
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

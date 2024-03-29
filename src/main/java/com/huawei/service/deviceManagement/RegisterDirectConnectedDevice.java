package com.huawei.service.deviceManagement;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Register Directly Connected Device :
 * This interface is used to register devices on the IoT platform.
 * After the registration is successful,
 * the IoT platform allocates a device ID for the device,which is used as the unique identifier of the device.
 * Unregistered devices are not allowed to access the IoT platform.
 * In NB-IoT scenarios, the Set device info interface needs to be invoked to set device information after the registration is successful.
 */
public class RegisterDirectConnectedDevice {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
		String urlRegisterDirectConnectedDevice = Constant.REGISTER_DIRECT_CONNECTED_DEVICE;

        //please replace the verifyCode and nodeId and timeout, when you use the demo.
        String verifyCode = "869029030217680";
		String nodeId = verifyCode;
        Integer timeout = 0;

        Map<String, Object> paramReg = new HashMap<>();
        paramReg.put("appId", appId);
        paramReg.put("verifyCode", verifyCode.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("timeout", timeout);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseRegisterDirectConnectedDevice = httpsUtil.doPostJsonGetStatusLine(urlRegisterDirectConnectedDevice, header, jsonRequest);

        System.out.println("RegisterDirectConnectedDevice, response content:");
        System.out.println(responseRegisterDirectConnectedDevice.getStatusLine());
        System.out.println(responseRegisterDirectConnectedDevice.getContent());
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

package com.nbiot.telecom.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;
import com.nbiot.telecom.response.ResponseCode;
import com.nbiot.telecom.response.ResponseData;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class HttpServiceImpl implements HttpService {

    @Resource
    RegisterDeviceService registerDeviceService;

    @Override
    public String getToken(HttpsUtil httpsUtil) throws Exception {
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

        Map data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("accessToken").toString();
    }

    @Override
    public void modifyDevice(HttpsUtil httpsUtil, String token, String deviceId, String name) {
        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        //please replace the deviceId, when you use the demo.
        String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + deviceId;

        //please replace the following parameter values, when you use the demo.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        String manufacturerId= "TERMINUS";
        String manufacturerName = "TERMINUS";
        String deviceType = "SmartDataDevice";
        String model = "DCD001";
        String protocolType = "CoAP";

        Map<String, Object> paramModifyDeviceInfo = new HashMap<>();
        paramModifyDeviceInfo.put("name", name);
        paramModifyDeviceInfo.put("manufacturerId", manufacturerId);
        paramModifyDeviceInfo.put("manufacturerName", manufacturerName);
        paramModifyDeviceInfo.put("deviceType", deviceType);
        paramModifyDeviceInfo.put("model", model);
        paramModifyDeviceInfo.put("protocolType", protocolType);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDeviceInfo);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + token);

        StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
                header, jsonRequest);

        registerDeviceService.updateDevice(deviceId, name, manufacturerId, manufacturerName, deviceType, model, protocolType);
        System.out.println("ModifyDeviceInfo, response content:");
        System.out.println(responseModifyDeviceInfo.getStatusLine());
        System.out.println(responseModifyDeviceInfo.getContent());
        System.out.println();
    }

    @Override
    public String registDevice(HttpsUtil httpsUtil, String token, String imei) throws Exception {
        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlRegisterDirectConnectedDevice = Constant.REGISTER_DIRECT_CONNECTED_DEVICE;

        //please replace the verifyCode and nodeId and timeout, when you use the demo.
        String verifyCode = imei;
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
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + token);

        StreamClosedHttpResponse responseRegisterDirectConnectedDevice = httpsUtil.doPostJsonGetStatusLine(urlRegisterDirectConnectedDevice, header, jsonRequest);

        System.out.println("RegisterDirectConnectedDevice, response content:");
        System.out.println(responseRegisterDirectConnectedDevice.getStatusLine());
        System.out.println(responseRegisterDirectConnectedDevice.getContent());
        System.out.println();
        Map data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseRegisterDirectConnectedDevice.getContent(), data.getClass());
        String deviceId = data.get("deviceId").toString();
        registerDeviceService.registerDevice(appId, imei, deviceId);
        return deviceId;
    }

    @Override
    public HttpResponse sendCommand(HttpsUtil httpsUtil, String deviceId, String token, String data) throws Exception {
        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlCreateDeviceCommand = Constant.CREATE_DEVICE_CMD;
        String appId = Constant.APPID;

        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
        Integer maxRetransmit = 3;

        //please replace the following parameter values, when you use the demo.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        String serviceId = "SystemCmdRawData";
        String method = "COMMAND";
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"value\":\"" + data + "\"}");

        Map<String, Object> paramCommand = new HashMap<>();
        paramCommand.put("serviceId", serviceId);
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);

        Map<String, Object> paramCreateDeviceCommand = new HashMap<>();
        paramCreateDeviceCommand.put("deviceId", deviceId);
        paramCreateDeviceCommand.put("command", paramCommand);
        paramCreateDeviceCommand.put("callbackUrl", callbackUrl);
        paramCreateDeviceCommand.put("maxRetransmit", maxRetransmit);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramCreateDeviceCommand);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + token);

        HttpResponse responseCreateDeviceCommand = httpsUtil.doPostJson(urlCreateDeviceCommand, header, jsonRequest);
        String responseBody = httpsUtil.getHttpResponseBody(responseCreateDeviceCommand);

        System.out.println("CreateDeviceCommand, response content:");
        System.out.println(responseCreateDeviceCommand.getStatusLine());
        System.out.println(responseBody);
        System.out.println();

//        Map responseData = new HashMap<>();
//        responseData = JsonUtil.jsonString2SimpleObj(responseBody, responseData.getClass());
//
//        int statusCode = responseCreateDeviceCommand.getStatusLine().getStatusCode();
        return responseCreateDeviceCommand;
//        if (statusCode == 201) {
//            return new ResponseData(ResponseCode.SUCCESS);
//        } else {
//            String errorCode = responseData.get("error_code").toString();
//            return ResponseData.nbiotError(errorCode);
//        }
    }
}

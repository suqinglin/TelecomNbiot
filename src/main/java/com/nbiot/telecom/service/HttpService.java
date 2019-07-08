package com.nbiot.telecom.service;

import com.huawei.utils.HttpsUtil;
import com.nbiot.telecom.response.ResponseData;
import org.apache.http.HttpResponse;

public interface HttpService {

    String getToken(HttpsUtil httpsUtil) throws Exception;

    void modifyDevice(HttpsUtil httpsUtil, String token, String deviceId, String name);

    String registDevice(HttpsUtil httpsUtil, String token, String imei) throws Exception;

    HttpResponse sendCommand(HttpsUtil httpsUtil, String deviceId, String token, String data) throws Exception;
}

package com.huawei.service.subscribtionManagement;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * SubscribeServiceNotification :
 * This interface is used to subscribe service data of IoT platform.
 */
public class SubscribeServiceNotification {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlSubscribeServiceNotification = Constant.SUBSCRIBE_SERVICE_NOTIFYCATION;
        

        /*
         * subscribe deviceAdded notification
         */
        String callbackurl_deviceAdded = Constant.DEVICE_ADDED_CALLBACK_URL;
        String notifyType_deviceAdded = Constant.DEVICE_ADDED;

        Map<String, Object> paramSubscribe_deviceAdded = new HashMap<>();
        paramSubscribe_deviceAdded.put("notifyType", notifyType_deviceAdded);
        paramSubscribe_deviceAdded.put("callbackUrl", callbackurl_deviceAdded);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_deviceAdded = JsonUtil.jsonObj2Sting(paramSubscribe_deviceAdded);

        Map<String, String> header_deviceAdded = new HashMap<>();
        header_deviceAdded.put(Constant.HEADER_APP_KEY, appId);
        header_deviceAdded.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceAdded = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_deviceAdded, jsonRequest_deviceAdded);

        String bodySubscribe_deviceAdded = httpsUtil.getHttpResponseBody(httpResponse_deviceAdded);

        System.out.println("SubscribeServiceNotification: " + notifyType_deviceAdded + ", response content:");
        System.out.println(httpResponse_deviceAdded.getStatusLine());
        System.out.println(bodySubscribe_deviceAdded);
        System.out.println();
        
        
        /*
         * subscribe deviceInfoChanged notification
         */
        String callbackurl_deviceInfoChanged = Constant.DEVICE_INFO_CHANGED_CALLBACK_URL;
        String notifyType_deviceInfoChanged = Constant.DEVICE_INFO_CHANGED;

        Map<String, Object> paramSubscribe_deviceInfoChanged = new HashMap<>();
        paramSubscribe_deviceInfoChanged.put("notifyType", notifyType_deviceInfoChanged);
        paramSubscribe_deviceInfoChanged.put("callbackUrl", callbackurl_deviceInfoChanged);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_deviceInfoChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceInfoChanged);

        Map<String, String> header_deviceInfoChanged = new HashMap<>();
        header_deviceInfoChanged.put(Constant.HEADER_APP_KEY, appId);
        header_deviceInfoChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceInfoChanged = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_deviceInfoChanged, jsonRequest_deviceInfoChanged);

        String bodySubscribe_deviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceInfoChanged);

        System.out.println("SubscribeServiceNotification: " + notifyType_deviceInfoChanged + ", response content:");
        System.out.println(httpResponse_deviceInfoChanged.getStatusLine());
        System.out.println(bodySubscribe_deviceInfoChanged);
        System.out.println();
        
        
        /*
         * subscribe deviceDataChanged notification
         */
        String callbackurl_deviceDataChanged = Constant.DEVICE_DATA_CHANGED_CALLBACK_URL;
        String notifyType_deviceDataChanged = Constant.DEVICE_DATA_CHANGED;

        Map<String, Object> paramSubscribe_deviceDataChanged = new HashMap<>();
        paramSubscribe_deviceDataChanged.put("notifyType", notifyType_deviceDataChanged);
        paramSubscribe_deviceDataChanged.put("callbackUrl", callbackurl_deviceDataChanged);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_deviceDataChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDataChanged);

        Map<String, String> header_deviceDataChanged = new HashMap<>();
        header_deviceDataChanged.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDataChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDataChanged = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_deviceDataChanged, jsonRequest_deviceDataChanged);

        String bodySubscribe_deviceDataChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDataChanged);

        System.out.println("SubscribeServiceNotification: " + notifyType_deviceDataChanged + ", response content:");
        System.out.println(httpResponse_deviceDataChanged.getStatusLine());
        System.out.println(bodySubscribe_deviceDataChanged);
        System.out.println();
        
        
        /*
         * subscribe deviceDeleted notification
         */
        String callbackurl_deviceDeleted = Constant.DEVICE_DELETED_CALLBACK_URL;
        String notifyType_deviceDeleted = Constant.DEVICE_DELETED;

        Map<String, Object> paramSubscribe_deviceDeleted = new HashMap<>();
        paramSubscribe_deviceDeleted.put("notifyType", notifyType_deviceDeleted);
        paramSubscribe_deviceDeleted.put("callbackUrl", callbackurl_deviceDeleted);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_deviceDeleted = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDeleted);

        Map<String, String> header_deviceDeleted = new HashMap<>();
        header_deviceDeleted.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDeleted.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDeleted = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_deviceDeleted, jsonRequest_deviceDeleted);

        String bodySubscribe_deviceDeleted = httpsUtil.getHttpResponseBody(httpResponse_deviceDeleted);

        System.out.println("SubscribeServiceNotification: " + notifyType_deviceDeleted + ", response content:");
        System.out.println(httpResponse_deviceDeleted.getStatusLine());
        System.out.println(bodySubscribe_deviceDeleted);
        System.out.println();
        
        
        /*
         * subscribe messageConfirm notification
         */
        String callbackurl_messageConfirm = Constant.MESSAGE_CONFIRM_CALLBACK_URL;
        String notifyType_messageConfirm = Constant.MESSAGE_CONFIRM;

        Map<String, Object> paramSubscribe_messageConfirm = new HashMap<>();
        paramSubscribe_messageConfirm.put("notifyType", notifyType_messageConfirm);
        paramSubscribe_messageConfirm.put("callbackUrl", callbackurl_messageConfirm);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_messageConfirm = JsonUtil.jsonObj2Sting(paramSubscribe_messageConfirm);

        Map<String, String> header_messageConfirm = new HashMap<>();
        header_messageConfirm.put(Constant.HEADER_APP_KEY, appId);
        header_messageConfirm.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_messageConfirm = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_messageConfirm, jsonRequest_messageConfirm);

        String bodySubscribe_messageConfirm = httpsUtil.getHttpResponseBody(httpResponse_messageConfirm);

        System.out.println("SubscribeServiceNotification: " + notifyType_messageConfirm + ", response content:");
        System.out.println(httpResponse_messageConfirm.getStatusLine());
        System.out.println(bodySubscribe_messageConfirm);
        System.out.println();
        
        
        /*
         * subscribe serviceInfoChanged notification
         */
        String callbackurl_serviceInfoChanged = Constant.SERVICE_INFO_CHANGED_CALLBACK_URL;
        String notifyType_serviceInfoChanged = Constant.SERVICE_INFO_CHANGED;

        Map<String, Object> paramSubscribeserviceInfoChanged = new HashMap<>();
        paramSubscribeserviceInfoChanged.put("notifyType", notifyType_serviceInfoChanged);
        paramSubscribeserviceInfoChanged.put("callbackUrl", callbackurl_serviceInfoChanged);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequestserviceInfoChanged = JsonUtil.jsonObj2Sting(paramSubscribeserviceInfoChanged);

        Map<String, String> headerserviceInfoChanged = new HashMap<>();
        headerserviceInfoChanged.put(Constant.HEADER_APP_KEY, appId);
        headerserviceInfoChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponseserviceInfoChanged = httpsUtil.doPostJson(urlSubscribeServiceNotification, headerserviceInfoChanged, jsonRequestserviceInfoChanged);

        String bodySubscribeserviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponseserviceInfoChanged);

        System.out.println("SubscribeServiceNotification: " + notifyType_serviceInfoChanged + ", response content:");
        System.out.println(httpResponseserviceInfoChanged.getStatusLine());
        System.out.println(bodySubscribeserviceInfoChanged);
        System.out.println();
        
        
        /*
         * subscribe commandRsp notification
         */
        String callbackurl_commandRsp = Constant.COMMAND_RSP_CALLBACK_URL;
        String notifyType_commandRsp = Constant.COMMAND_RSP;

        Map<String, Object> paramSubscribecommandRsp = new HashMap<>();
        paramSubscribecommandRsp.put("notifyType", notifyType_commandRsp);
        paramSubscribecommandRsp.put("callbackUrl", callbackurl_commandRsp);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequestcommandRsp = JsonUtil.jsonObj2Sting(paramSubscribecommandRsp);

        Map<String, String> headercommandRsp = new HashMap<>();
        headercommandRsp.put(Constant.HEADER_APP_KEY, appId);
        headercommandRsp.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponsecommandRsp = httpsUtil.doPostJson(urlSubscribeServiceNotification, headercommandRsp, jsonRequestcommandRsp);

        String bodySubscribecommandRsp = httpsUtil.getHttpResponseBody(httpResponsecommandRsp);

        System.out.println("SubscribeServiceNotification: " + notifyType_commandRsp + ", response content:");
        System.out.println(httpResponsecommandRsp.getStatusLine());
        System.out.println(bodySubscribecommandRsp);
        System.out.println();
        
        
        /*
         * subscribe deviceEvent notification
         */
        String callbackurl_deviceEvent = Constant.DEVICE_EVENT_CALLBACK_URL;
        String notifyType_deviceEvent = Constant.DEVICE_EVENT;

        Map<String, Object> paramSubscribe_deviceEvent = new HashMap<>();
        paramSubscribe_deviceEvent.put("notifyType", notifyType_deviceEvent);
        paramSubscribe_deviceEvent.put("callbackUrl", callbackurl_deviceEvent);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_deviceEvent = JsonUtil.jsonObj2Sting(paramSubscribe_deviceEvent);

        Map<String, String> header_deviceEvent = new HashMap<>();
        header_deviceEvent.put(Constant.HEADER_APP_KEY, appId);
        header_deviceEvent.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceEvent = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_deviceEvent, jsonRequest_deviceEvent);

        String bodySubscribe_deviceEvent = httpsUtil.getHttpResponseBody(httpResponse_deviceEvent);

        System.out.println("SubscribeServiceNotification: " + notifyType_deviceEvent + ", response content:");
        System.out.println(httpResponse_deviceEvent.getStatusLine());
        System.out.println(bodySubscribe_deviceEvent);
        System.out.println();
        
        
        
        /*
         * subscribe ruleEvent notification
         */
        String callbackurl_ruleEvent = Constant.RULE_EVENT_CALLBACK_URL;
        String notifyType_ruleEvent = Constant.RULE_EVENT;

        Map<String, Object> paramSubscribe_ruleEvent = new HashMap<>();
        paramSubscribe_ruleEvent.put("notifyType", notifyType_ruleEvent);
        paramSubscribe_ruleEvent.put("callbackUrl", callbackurl_ruleEvent);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_ruleEvent = JsonUtil.jsonObj2Sting(paramSubscribe_ruleEvent);

        Map<String, String> header_ruleEvent = new HashMap<>();
        header_ruleEvent.put(Constant.HEADER_APP_KEY, appId);
        header_ruleEvent.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_ruleEvent = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_ruleEvent, jsonRequest_ruleEvent);

        String bodySubscribe_ruleEvent = httpsUtil.getHttpResponseBody(httpResponse_ruleEvent);

        System.out.println("SubscribeServiceNotification: " + notifyType_ruleEvent + ", response content:");
        System.out.println(httpResponse_ruleEvent.getStatusLine());
        System.out.println(bodySubscribe_ruleEvent);
        System.out.println();
        
        
        /*
         * subscribe deviceDatasChanged notification
         */
        String callbackurl_deviceDatasChanged = Constant.DEVICE_DATAS_CHANGED_CALLBACK_URL;
        String notifyType_deviceDatasChanged = Constant.DEVICE_DATAS_CHANGED;

        Map<String, Object> paramSubscribe_deviceDatasChanged = new HashMap<>();
        paramSubscribe_deviceDatasChanged.put("notifyType", notifyType_deviceDatasChanged);
        paramSubscribe_deviceDatasChanged.put("callbackUrl", callbackurl_deviceDatasChanged);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_deviceDatasChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDatasChanged);

        Map<String, String> header_deviceDatasChanged = new HashMap<>();
        header_deviceDatasChanged.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDatasChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDatasChanged = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_deviceDatasChanged, jsonRequest_deviceDatasChanged);

        String bodySubscribe_deviceDatasChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDatasChanged);

        System.out.println("SubscribeServiceNotification: " + notifyType_deviceDatasChanged + ", response content:");
        System.out.println(httpResponse_deviceDatasChanged.getStatusLine());
        System.out.println(bodySubscribe_deviceDatasChanged);
        System.out.println();
        
        
        /*
         * subscribe deviceDesiredPropertiesModifyStatusChanged notification
         */
        String callbackurl_deviceDesiredModify = Constant.DEVICE_SHADOW_MODIFIED_CALLBACK_URL;
        String notifyType_deviceDesiredModify = Constant.DEVICE_SHADOW_MODIFIED;

        Map<String, Object> paramSubscribe_deviceDesiredModify = new HashMap<>();
        paramSubscribe_deviceDesiredModify.put("notifyType", notifyType_deviceDesiredModify);
        paramSubscribe_deviceDesiredModify.put("callbackUrl", callbackurl_deviceDesiredModify);
        paramSubscribe_deviceAdded.put("appId", appId);

        String jsonRequest_deviceDesiredModify = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDesiredModify);

        Map<String, String> header_deviceDesiredModify = new HashMap<>();
        header_deviceDesiredModify.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDesiredModify.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDesiredModify = httpsUtil.doPostJson(urlSubscribeServiceNotification, header_deviceDesiredModify, jsonRequest_deviceDesiredModify);

        String bodySubscribe_deviceDesiredModify = httpsUtil.getHttpResponseBody(httpResponse_deviceDesiredModify);

        System.out.println("SubscribeServiceNotification: " + notifyType_deviceDesiredModify + ", response content:");
        System.out.println(httpResponse_deviceDesiredModify.getStatusLine());
        System.out.println(bodySubscribe_deviceDesiredModify);
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

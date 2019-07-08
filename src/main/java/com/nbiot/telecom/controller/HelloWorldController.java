package com.nbiot.telecom.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;
import com.nbiot.telecom.bean.AuthBean;
import com.nbiot.telecom.bean.CommandBean;
import com.nbiot.telecom.mapper.CommandMapper;
import com.nbiot.telecom.model.CommandInfo;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloWorldController {

    @GetMapping("/index")
    public String index(Model model) {

//        CommandInfo commandInfo = new CommandInfo();
//        commandInfo.setUuid(123456);
//        commandInfo.setReqCmd(0x82);
//        commandInfo.setAckCmd(0x02);
//        commandInfo.setDeviceId("aaaaaaa");
//        commandInfo.setTime(System.currentTimeMillis() / 1000);
//        commandInfo.setStatus(3);
//        commandMapper.saveCommand(commandInfo);

        AuthBean auth = new AuthBean();
//        auth.setAppId(Constant.APPID);
//        auth.setSecret(Constant.SECRET);
        model.addAttribute("auth", new AuthBean());
        model.addAttribute("command", new CommandBean());
        return "index";
    }

    @PostMapping("/authentication")
    public String authentication(@ModelAttribute AuthBean authBean, Model model) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

//        String appId = Constant.APPID;
//        String secret = Constant.SECRET;
        String appId = authBean.getAppId();
        String secret = authBean.getSecret();
        String urlLogin = Constant.APP_AUTH;

        Map<String, String> param = new HashMap<>();
        param.put("appId", appId);
        param.put("secret", secret);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, param);
//        return responseLogin.getContent();

        model.addAttribute("auth", authBean);
        model.addAttribute("command", new CommandBean());
        model.addAttribute("result", responseLogin.getContent());
        return "index";
    }

    @PostMapping("/createDeviceCommand")
    public String createDeviceCommand(@ModelAttribute CommandBean commandBean, Model model) throws Exception {

        model.addAttribute("auth", new AuthBean());
        model.addAttribute("command", commandBean);

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = commandBean.getAuthToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlCreateDeviceCommand = Constant.CREATE_DEVICE_CMD;
        String appId = Constant.APPID;

        //please replace the deviceId, when you use the demo.
        String deviceId = commandBean.getDeviceId();
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
        Integer maxRetransmit = 3;

        //please replace the following parameter values, when you use the demo.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        String serviceId = commandBean.getServiceId();
        String method = commandBean.getMethod();
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"value\":\"" + commandBean.getData() + "\"}");

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
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse responseCreateDeviceCommand = httpsUtil.doPostJson(urlCreateDeviceCommand, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(responseCreateDeviceCommand);

        model.addAttribute("commandResult", responseBody);
        return "index";
    }
}

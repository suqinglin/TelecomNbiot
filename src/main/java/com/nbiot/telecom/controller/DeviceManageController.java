package com.nbiot.telecom.controller;

import com.huawei.utils.HttpsUtil;
import com.nbiot.telecom.model.DeviceInfo;
import com.nbiot.telecom.request.RxRegistDevice;
import com.nbiot.telecom.response.ResponseCode;
import com.nbiot.telecom.response.ResponseData;
import com.nbiot.telecom.service.HttpService;
import com.nbiot.telecom.service.RegisterDeviceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DeviceManageController {

    @Resource
    HttpService httpService;

    @Resource
    RegisterDeviceService registerDeviceService;

    @PostMapping("/device/register")
    public ResponseData registDevice(@Valid @RequestBody RxRegistDevice rxRegistDevice) {
        String deviceId = null;
        try {
            HttpsUtil httpsUtil = HttpsUtil.getInstance();
            String token = httpService.getToken(httpsUtil);
            deviceId = httpService.registDevice(httpsUtil, token, rxRegistDevice.getImei());
            httpService.modifyDevice(httpsUtil, token, deviceId, rxRegistDevice.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
        ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
        responseData.addData("deviceId", deviceId);
        return responseData;
    }

    @PostMapping("/device/list")
    public ResponseData getDevices() {
        List<DeviceInfo> devices;
        try {
            devices = registerDeviceService.getDevices();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
        ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
        responseData.addData("devices", devices);
        return responseData;
    }
}

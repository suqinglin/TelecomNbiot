package com.nbiot.telecom.service;

import com.nbiot.telecom.model.DeviceInfo;

import java.util.List;

public interface RegisterDeviceService {

    void registerDevice(String appId, String imei, String deviceId);

    List<DeviceInfo> getDevices();

    void updateDevice(String deviceId, String name, String manufacturerId, String manufacturerName, String deviceType, String model, String protocolType);
}

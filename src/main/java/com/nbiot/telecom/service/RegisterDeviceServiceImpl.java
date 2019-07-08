package com.nbiot.telecom.service;

import com.nbiot.telecom.mapper.RegisterDeviceMapper;
import com.nbiot.telecom.model.DeviceInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegisterDeviceServiceImpl implements RegisterDeviceService {

    @Resource
    RegisterDeviceMapper registerDeviceMapper;

    @Override
    public void registerDevice(String appId, String imei, String deviceId) {
        DeviceInfo deviceInfo = new DeviceInfo();
        long id = 0;
        if (registerDeviceMapper.getMaxId() != null) {
            id = Long.valueOf(registerDeviceMapper.getMaxId()) + 1;
        }
        deviceInfo.setId(id);
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setAppId(appId);
        deviceInfo.setImei(imei);
        registerDeviceMapper.saveDevice(deviceInfo);
    }

    @Override
    public void updateDevice(String deviceId, String name, String manufacturerId, String manufacturerName, String deviceType, String model, String protocolType) {

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setName(name);
        deviceInfo.setManufId(manufacturerId);
        deviceInfo.setManufName(manufacturerName);
        deviceInfo.setDeviceType(deviceType);
        deviceInfo.setModel(model);
        deviceInfo.setProtocalType(protocolType);
        registerDeviceMapper.updateDevice(deviceInfo);
    }

    @Override
    public List<DeviceInfo> getDevices() {
        return registerDeviceMapper.getRegisterDevices();
    }
}

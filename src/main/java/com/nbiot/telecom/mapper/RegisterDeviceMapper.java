package com.nbiot.telecom.mapper;

import com.nbiot.telecom.model.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterDeviceMapper {

    void saveDevice(DeviceInfo registerDevice);

    void updateDevice(DeviceInfo registerDevice);

    String getMaxId();

    List<DeviceInfo> getRegisterDevices();
}

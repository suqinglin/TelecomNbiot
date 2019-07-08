package com.nbiot.telecom.model;

import lombok.Getter;
import lombok.Setter;

public class DeviceInfo {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String deviceId;

    @Getter
    @Setter
    private String appId;

    @Getter
    @Setter
    private String imei;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String manufId;

    @Getter
    @Setter
    private String manufName;

    @Getter
    @Setter
    private String deviceType;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private String protocalType;
}

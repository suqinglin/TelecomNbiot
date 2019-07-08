package com.nbiot.telecom.bean;

public class DeviceDataChangedNotify {
    private String notifyType;
    private String requestId;
    private String timestamp;
    private String deviceId;
    private String gatewayId;
    private DeviceServiceDataBean service;

    public DeviceDataChangedNotify() {
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public DeviceServiceDataBean getService() {
        return service;
    }

    @Override
    public String toString() {
        return "DeviceDataChangedNotify{" +
                "notifyType='" + notifyType + '\'' +
                ", requestId='" + requestId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", gatewayId='" + gatewayId + '\'' +
                ", service=" + service.toString() +
                '}';
    }
}

package com.nbiot.telecom.bean;

public class DeviceInfoChangedNotify {

    /**
     * notifyType  : deviceInfoChanged
     * deviceId : 7b3979fc-b072-433b-b3f6-673072e1bc04
     * gatewayId : *************
     * deviceInfo : {}
     */

    private String notifyType;
    private String deviceId;
    private String gatewayId;
    private DeviceInfoBean deviceInfo;

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
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

    public DeviceInfoBean getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfoBean deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public String toString() {
        return "DeviceInfoChangedNotify{" +
                "notifyType='" + notifyType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", gatewayId='" + gatewayId + '\'' +
                ", deviceInfo=" + deviceInfo.toString() +
                '}';
    }
}

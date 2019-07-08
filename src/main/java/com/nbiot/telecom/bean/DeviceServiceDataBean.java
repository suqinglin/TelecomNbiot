package com.nbiot.telecom.bean;

import java.util.Map;

public class DeviceServiceDataBean {
    private String serviceId;
    private String serviceType;
    private Map<String, Object> data;
    private String eventTime;

    public DeviceServiceDataBean() {
    }

    public DeviceServiceDataBean(String serviceId, String serviceType, Map<String, Object> data, String eventTime) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.data = data;
        this.eventTime = eventTime;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "DeviceServiceDataBean{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", data=" + data +
                ", eventTime='" + eventTime + '\'' +
                '}';
    }
}

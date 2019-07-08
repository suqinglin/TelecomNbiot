package com.nbiot.telecom.model;

import lombok.Getter;
import lombok.Setter;

public class CommandInfo {

    @Getter
    @Setter
    private long uuid;

    @Getter
    @Setter
    private String cmdId;

    @Getter
    @Setter
    private int reqCmd;

    @Getter
    @Setter
    private int ackCmd;

    @Getter
    @Setter
    private String deviceId;

    @Getter
    @Setter
    private String dataId;

    @Getter
    @Setter
    private long time;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private int result;

    public CommandInfo() {
    }

    public CommandInfo(long uuid, int reqCmd, String deviceId, String dataId, long time, String status) {
        this.uuid = uuid;
        this.reqCmd = reqCmd;
        this.deviceId = deviceId;
        this.dataId = dataId;
        this.time = time;
        this.status = status;
    }
}

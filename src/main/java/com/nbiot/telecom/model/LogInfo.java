package com.nbiot.telecom.model;

import lombok.Getter;
import lombok.Setter;

public class LogInfo {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private int volt;

    @Getter
    @Setter
    private int rssi;

    @Getter
    @Setter
    private int type;

    @Getter
    @Setter
    private int faceCnt;

    @Getter
    @Setter
    private int cardCnt;

    @Getter
    @Setter
    private long time;
}

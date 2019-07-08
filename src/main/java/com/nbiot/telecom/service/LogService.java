package com.nbiot.telecom.service;

import com.nbiot.telecom.model.LogInfo;

import java.util.List;

public interface LogService {

    void saveLog(int volt, int rssi, int type, int faceCnt, int cardCnt, long time);

    List<LogInfo> getLogs(long startTime, long endTime);
}

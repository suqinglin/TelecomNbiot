package com.nbiot.telecom.service;

import com.nbiot.telecom.mapper.LogMapper;
import com.nbiot.telecom.model.LogInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    LogMapper logMapper;

    @Override
    public void saveLog(int volt, int rssi, int type, int faceCnt, int cardCnt, long time) {
        long id = 0;
        if (logMapper.getMaxId() != null) {
            id = Long.valueOf(logMapper.getMaxId()) + 1;
        }
        LogInfo logInfo = new LogInfo();
        logInfo.setId(id);
        logInfo.setVolt(volt);
        logInfo.setRssi(rssi);
        logInfo.setType(type);
        logInfo.setFaceCnt(faceCnt);
        logInfo.setCardCnt(cardCnt);
        logInfo.setTime(time);
        logMapper.saveLog(logInfo);
    }

    @Override
    public List<LogInfo> getLogs(long startTime, long endTime) {
        return logMapper.getLogs(startTime, endTime);
    }
}

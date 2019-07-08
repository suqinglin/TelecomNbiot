package com.nbiot.telecom.mapper;

import com.nbiot.telecom.model.LogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper {

    void saveLog(LogInfo logInfo);

    List<LogInfo> getLogs(@Param("startTime") long startTime, @Param("endTime") long endTime);

    String getMaxId();
}

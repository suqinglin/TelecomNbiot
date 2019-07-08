package com.nbiot.telecom.controller;

import com.nbiot.telecom.model.CommandInfo;
import com.nbiot.telecom.model.CommandResult;
import com.nbiot.telecom.model.LogInfo;
import com.nbiot.telecom.request.RxGetCmdResult;
import com.nbiot.telecom.request.RxGetLogs;
import com.nbiot.telecom.response.ResponseCode;
import com.nbiot.telecom.response.ResponseData;
import com.nbiot.telecom.service.CommandService;
import com.nbiot.telecom.service.LogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LogController {

    @Resource
    LogService logService;

    @Resource
    CommandService commandService;

    @PostMapping("/log/oenLockList")
    public ResponseData getLogs(@Valid @RequestBody RxGetLogs rxGetLogs) {
        List<LogInfo> logs;
        try {
            logs = logService.getLogs(rxGetLogs.getStartTime(), rxGetLogs.getEndTime());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
        ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
        responseData.addData("logs", logs);
        return responseData;
    }

    @PostMapping("/log/optResult")
    public ResponseData getCmdResult(@Valid @RequestBody RxGetCmdResult getCmdResult) {

        CommandResult commandResult;
        try {
            commandResult = commandService.getCommandByUuid(Long.valueOf(getCmdResult.getUuid()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
        ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
        responseData.addData("cmdResult", commandResult);
        return responseData;
    }
}

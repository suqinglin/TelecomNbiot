package com.nbiot.telecom.controller;

import com.huawei.utils.HttpsUtil;
import com.nbiot.telecom.bean.DeviceDataChangedNotify;
import com.nbiot.telecom.bean.DeviceInfoChangedNotify;
import com.nbiot.telecom.bean.ReportCmdExecResultBean;
import com.nbiot.telecom.service.CommandEncryptService;
import com.nbiot.telecom.service.CommandService;
import com.nbiot.telecom.service.HttpService;
import com.nbiot.telecom.service.LogService;
import com.nbiot.telecom.utils.CommUtils;
import com.nbiot.telecom.utils.CommandConstants;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


@RestController
public class NotifyController {

    final Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @Resource
    CommandService commandService;

    @Resource
    LogService logService;

    @Resource
    HttpService httpService;

    @Resource
    CommandEncryptService commandEncryptService;

    @PostMapping("/deviceInfoChanged")
    public String deviceInfoChanged(@RequestBody DeviceInfoChangedNotify deviceInfoChanged) {
        logger.info("deviceInfoChangedNotify -----> {}", deviceInfoChanged.toString());
        return "deviceInfoChangedNotify -----> " + deviceInfoChanged.toString();
    }

    @PostMapping("/deviceDataChanged")
    public String deviceDataChanged(@RequestBody DeviceDataChangedNotify deviceDataChanged) throws DecoderException {
//        String data = "03000000000005003780";
        Map<String, Object> dataMap = deviceDataChanged.getService().getData();
        logger.info("deviceDataChangedNotify -----> {}", dataMap);
        if (dataMap.keySet().contains("SystemCmdRawData")) {
            String data = dataMap.get("SystemCmdRawData").toString();
            String frameData = data.substring(0, data.length() - 4);
            String crcData = data.substring(data.length() - 4);
            int crc = CommUtils.crc16(0, Hex.decodeHex(frameData));
            if (crc == Integer.valueOf(crcData, 16)) { // crc校验成功
                handleDeviceData(deviceDataChanged.getDeviceId(), data);
            } else {
                logger.info("deviceDataChangedNotify -----> {}", "CRC校验失败");
            }
        }
        return "deviceDataChangedNotify ---------> " + deviceDataChanged.getService().getData();
    }

    @PostMapping("/reportCmdExecResult")
    public String reportCmdExecResult(@RequestBody ReportCmdExecResultBean reportCmdExecResult) {
        logger.info("reportCmdExecResult -----> {}", reportCmdExecResult.toString());
        logger.info("reportCmdExecResult status -----> {}", reportCmdExecResult.getResult().getResultCode());
        commandService.updateCommandStatus(
                reportCmdExecResult.getCommandId(),
                reportCmdExecResult.getResult().getResultCode());
        return "reportCmdExecResult status -----> {}" + reportCmdExecResult.getResult().getResultCode();
    }

    private void handleDeviceData(String deviceId, String data) {
        int cmd = Integer.valueOf(data.substring(0, 2), 16);
        switch (cmd) {
            case CommandConstants.ACK_ADD_TEMPLETE:
            case CommandConstants.ACK_DEL_TEMPLETE:
            case CommandConstants.ACK_ADD_CARD:
            case CommandConstants.ACK_DEL_CARD:
                long uuid = Long.valueOf(data.substring(2, 10), 16);
                int result = Integer.valueOf(data.substring(14, 16), 16);
                commandService.updateCommandByUUID(uuid, cmd, result + 1);
                break;
            case CommandConstants.REQ_CONNECT:
                break;
            case CommandConstants.REQ_LOG_REPORT:
                long logUuid = Long.valueOf(data.substring(2, 10), 16);
                long time = Long.valueOf(data.substring(14, 22), 16);
                int volt = Integer.valueOf(data.substring(22, 26), 16);
                int rssi = Integer.valueOf(data.substring(26, 28), 16);
                int type = Integer.valueOf(data.substring(30, 32), 16);
                int faceCnt = Integer.valueOf(data.substring(32, 34), 16);
                int cardCnt = Integer.valueOf(data.substring(34, 36), 16);
                logService.saveLog(volt, rssi, type, faceCnt, cardCnt, time);
                responseLog(deviceId, logUuid);
                break;
        }
    }

    /**
     * 响应锁具
     * @param deviceId
     * @param uuid
     */
    private void responseLog(String deviceId, long uuid) {
        try {
            HttpsUtil httpsUtil = HttpsUtil.getInstance();
            String token = httpService.getToken(httpsUtil);
            // 待发送的数据
            String respLogData = commandEncryptService.encryptResponseLog((int) uuid);
            // 发送
            httpService.sendCommand(
                    httpsUtil,
                    deviceId,
                    token,
                    respLogData
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

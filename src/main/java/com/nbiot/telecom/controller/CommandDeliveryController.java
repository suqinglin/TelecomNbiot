package com.nbiot.telecom.controller;

import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.nbiot.telecom.model.CommandInfo;
import com.nbiot.telecom.request.*;
import com.nbiot.telecom.response.ResponseCode;
import com.nbiot.telecom.response.ResponseData;
import com.nbiot.telecom.service.CommandEncryptService;
import com.nbiot.telecom.service.CommandService;
import com.nbiot.telecom.service.HttpService;
import com.nbiot.telecom.utils.CommUtils;
import com.nbiot.telecom.utils.CommandConstants;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class CommandDeliveryController {

    @Resource
    HttpService httpService;

    @Resource
    CommandEncryptService commandEncryptService;

    @Resource
    CommandService commandService;

    @PostMapping("/face/add")
    public ResponseData addFace(@Valid @RequestBody RxAddFace rxAddFace) {

        try {
            HttpsUtil httpsUtil = HttpsUtil.getInstance();
            String token = httpService.getToken(httpsUtil);
            // 随机生成的4 Bytes包序号
            long uuid = CommUtils.getRandomUUID();
            // 待发送的数据
            String[] addFaceDatas = commandEncryptService.encryptAddFace(
                    (int) uuid,
                    (int) rxAddFace.getEffectTime(),
                    (int) rxAddFace.getFailureTime(),
                    rxAddFace.getFaceId(),
                    rxAddFace.getTempleteData()
            );
            // 发送
            ResponseData responseData = null;
            HttpResponse httpResponse = null;
            for (String addFaceData : addFaceDatas) {
                httpResponse = httpService.sendCommand(
                        httpsUtil,
                        rxAddFace.getDeviceId(),
                        token,
                        addFaceData
                );
            }
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map dataMap = JsonUtil.jsonString2SimpleObj(httpsUtil.getHttpResponseBody(httpResponse), Map.class);
            if (statusCode == 201) {
                // 保存命令到数据库
                String status = dataMap.get("status").toString();
                String cmdId = dataMap.get("commandId").toString();
                commandService.saveCommand(
                        uuid,
                        cmdId,
                        CommandConstants.REQ_ADD_TEMPLETE,
                        rxAddFace.getDeviceId(),
                        rxAddFace.getFaceId(),
                        System.currentTimeMillis() / 1000,
                        status);
                // 响应客户端
                responseData = new ResponseData(ResponseCode.SUCCESS);
                responseData.addData("uuid", String.valueOf(uuid));
                return responseData;
            } else {
                String errorCode = dataMap.get("error_code").toString();
                return ResponseData.nbiotError(errorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
    }

    @PostMapping("/face/delete")
    public ResponseData deleteFace(@Valid @RequestBody RxDeleteFace rxDeleteFace) {
        try {
            HttpsUtil httpsUtil = HttpsUtil.getInstance();
            String token = httpService.getToken(httpsUtil);
            // 随机生成的4 Bytes包序号
            long uuid = CommUtils.getRandomUUID();
            // 待发送的数据
            String deleteFaceData = commandEncryptService.encryptDelFace((int) uuid, rxDeleteFace.getFaceId());
            // 发送
            HttpResponse httpResponse = httpService.sendCommand(
                    httpsUtil,
                    rxDeleteFace.getDeviceId(),
                    token,
                    deleteFaceData
            );
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map dataMap = JsonUtil.jsonString2SimpleObj(httpsUtil.getHttpResponseBody(httpResponse), Map.class);
            if (statusCode == 201) {
                // 保存命令到数据库
                String status = dataMap.get("status").toString();
                String cmdId = dataMap.get("commandId").toString();
                commandService.saveCommand(
                        uuid,
                        cmdId,
                        CommandConstants.REQ_DEL_TEMPLETE,
                        rxDeleteFace.getDeviceId(),
                        rxDeleteFace.getFaceId(),
                        System.currentTimeMillis() / 1000,
                        status);
                // 响应客户端
                ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
                responseData.addData("uuid", String.valueOf(uuid));
                return responseData;
            } else {
                String errorCode = dataMap.get("error_code").toString();
                return ResponseData.nbiotError(errorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
    }

    @PostMapping("/card/add")
    public ResponseData addCard(@Valid @RequestBody RxAddCard rxAddCard) {

        try {
            HttpsUtil httpsUtil = HttpsUtil.getInstance();
            String token = httpService.getToken(httpsUtil);
            // 随机生成的4 Bytes包序号
            long uuid = CommUtils.getRandomUUID();
            // 待发送的数据
            String addCardData = commandEncryptService.encryptAddCard(
                    (int) uuid,
                    (int) rxAddCard.getEffectTime(),
                    (int) rxAddCard.getFailureTime(),
                    rxAddCard.getCardId(),
                    rxAddCard.getCardData()
            );
            // 发送
            HttpResponse httpResponse = httpService.sendCommand(
                    httpsUtil,
                    rxAddCard.getDeviceId(),
                    token,
                    addCardData
            );
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map dataMap = JsonUtil.jsonString2SimpleObj(httpsUtil.getHttpResponseBody(httpResponse), Map.class);
            if (statusCode == 201) {
                // 保存命令到数据库
                String status = dataMap.get("status").toString();
                String cmdId = dataMap.get("commandId").toString();
                commandService.saveCommand(
                        uuid,
                        cmdId,
                        CommandConstants.REQ_ADD_CARD,
                        rxAddCard.getDeviceId(),
                        rxAddCard.getCardId(),
                        System.currentTimeMillis() / 1000,
                        status);
                // 响应客户端
                ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
                responseData.addData("uuid", String.valueOf(uuid));
                return responseData;
            } else {
                String errorCode = dataMap.get("error_code").toString();
                return ResponseData.nbiotError(errorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
    }

    @PostMapping("/card/delete")
    public ResponseData deleteCard(@Valid @RequestBody RxDeleteCard rxDeleteCard) {
        try {
            HttpsUtil httpsUtil = HttpsUtil.getInstance();
            String token = httpService.getToken(httpsUtil);
            // 随机生成的4 Bytes包序号
            long uuid = CommUtils.getRandomUUID();
            // 待发送的数据
            String deleteCardData = commandEncryptService.encryptDelCard(
                    (int) uuid,
                    rxDeleteCard.getCardId(),
                    rxDeleteCard.getCardData());
            // 发送
            HttpResponse httpResponse = httpService.sendCommand(
                    httpsUtil,
                    rxDeleteCard.getDeviceId(),
                    token,
                    deleteCardData
            );
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map dataMap = JsonUtil.jsonString2SimpleObj(httpsUtil.getHttpResponseBody(httpResponse), Map.class);
            if (statusCode == 201) {
                // 保存命令到数据库
                String status = dataMap.get("status").toString();
                String cmdId = dataMap.get("commandId").toString();
                commandService.saveCommand(
                        uuid,
                        cmdId,
                        CommandConstants.REQ_DEL_CARD,
                        rxDeleteCard.getDeviceId(),
                        rxDeleteCard.getCardId(),
                        System.currentTimeMillis() / 1000,
                        status);
                // 响应客户端
                ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
                responseData.addData("uuid", String.valueOf(uuid));
                return responseData;
            } else {
                String errorCode = dataMap.get("error_code").toString();
                return ResponseData.nbiotError(errorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
    }

    @PostMapping("/openLock")
    public ResponseData openLock(@Valid @RequestBody RxOpenLock rxOpenLock) {
        try {
            HttpsUtil httpsUtil = HttpsUtil.getInstance();
            String token = httpService.getToken(httpsUtil);
            // 随机生成的4 Bytes包序号
            long uuid = CommUtils.getRandomUUID();
            long timestamp = System.currentTimeMillis();
            // 待发送的数据
            String openData = commandEncryptService.encryptRemoteOpen(
                    (int) uuid,
                    rxOpenLock.getOperator(),
                    (int) timestamp);
            // 发送
            HttpResponse httpResponse =  httpService.sendCommand(
                    httpsUtil,
                    rxOpenLock.getDeviceId(),
                    token,
                    openData
            );
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map dataMap = JsonUtil.jsonString2SimpleObj(httpsUtil.getHttpResponseBody(httpResponse), Map.class);
            if (statusCode == 201) {
                // 保存命令到数据库
                String status = dataMap.get("status").toString();
                String cmdId = dataMap.get("commandId").toString();
                commandService.saveCommand(
                        uuid,
                        cmdId,
                        CommandConstants.REQ_REMOTE_OPEN,
                        rxOpenLock.getDeviceId(),
                        null,
                        System.currentTimeMillis() / 1000,
                        status);
                // 响应客户端
                ResponseData responseData = new ResponseData(ResponseCode.SUCCESS);
                responseData.addData("uuid", String.valueOf(uuid));
                return responseData;
            } else {
                String errorCode = dataMap.get("error_code").toString();
                return ResponseData.nbiotError(errorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR);
        }
    }

}

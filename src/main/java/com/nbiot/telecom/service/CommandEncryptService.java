package com.nbiot.telecom.service;

import org.apache.commons.codec.DecoderException;

public interface CommandEncryptService {

    /**
     * 发送添加人脸模板
     * @param uuid 包序号
     * @param effectTime 生效时间
     * @param failureTime 失效时间
     * @param faceId 人脸ID
     * @param temData 人脸模板数据
     * @return 生成的发送字符串
     * @throws DecoderException
     */
    String[] encryptAddFace(Integer uuid, Integer effectTime, Integer failureTime, String faceId, String temData) throws DecoderException;

    /**
     * 发送删除人脸模板
     * @param uuid 包序号
     * @param faceId 人脸ID
     * @return 生成的发送字符串
     * @throws DecoderException
     */
    String encryptDelFace(Integer uuid, String faceId) throws DecoderException;

    /**
     * 发送添加卡片
     * @param uuid 包序号
     * @param effectTime 生效时间
     * @param failureTime 失效时间
     * @param cardId 卡片ID
     * @param cardData 卡片数据
     * @return 生成的发送字符串
     * @throws DecoderException
     */
    String encryptAddCard(Integer uuid, Integer effectTime, Integer failureTime, String cardId, String cardData) throws DecoderException;

    /**
     * 发送删除卡片
     * @param uuid 包序号
     * @param cardId 卡片ID
     * @param cardData 卡片数据
     * @return 生成的发送字符串
     * @throws DecoderException
     */
    String encryptDelCard(Integer uuid, String cardId, String cardData) throws DecoderException;

    /**
     * 发送远程开门
     * @param uuid 包序号
     * @param operator 操作人编号
     * @param time 开门时间戳
     * @return 生成的发送字符串
     */
    String encryptRemoteOpen(Integer uuid, String operator, Integer time) throws DecoderException;

    String encryptResponseLog(Integer uuid);
}

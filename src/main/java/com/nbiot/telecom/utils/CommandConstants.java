package com.nbiot.telecom.utils;

/**
 * 帧类型常亮
 */
public class CommandConstants {

    public static final int REQ_CONNECT = 0x01;             // 锁具向后台发出通讯请求
    public static final int ACK_ADD_TEMPLETE = 0x02;        // 锁具应答人脸模板数据帧
    public static final int ACK_DEL_TEMPLETE = 0x03;        // 锁具应答人脸模板删除帧
    public static final int ACK_ADD_CARD = 0x04;            // 锁具应答卡片模板数据帧
    public static final int ACK_DEL_CARD = 0x05;            // 锁具应答卡片模板删除帧
    public static final int ACK_REMOTE_OPEN = 0x06;         // 锁具远程开门应答帧
    public static final int REQ_LOG_REPORT = 0x07;          // 锁具后台传送开门日志和锁具状态

    public static final int ACK_CONNECT = 0x81;             // 后台响应锁具应答帧
    public static final int REQ_ADD_TEMPLETE = 0x82;        // 后台响应人脸模板数据帧
    public static final int REQ_DEL_TEMPLETE = 0x83;        // 后台响应人脸模板删除帧
    public static final int REQ_ADD_CARD = 0x84;            // 后台响应卡片模板数据帧
    public static final int REQ_DEL_CARD = 0x85;            // 后台响应卡片模板删除帧
    public static final int REQ_REMOTE_OPEN = 0x86;         // 后台响应远程开门应答帧
    public static final int ACK_LOG_REPORT = 0x87;          // 后台响应传送开门日志和锁具状态
}

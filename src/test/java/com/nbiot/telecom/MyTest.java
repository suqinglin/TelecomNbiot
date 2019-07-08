package com.nbiot.telecom;

import com.nbiot.telecom.utils.CommUtils;
import com.nbiot.telecom.utils.CommandConstants;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.nio.ByteBuffer;

public class MyTest {

    @Test
    public void test() throws DecoderException {

        String data = "03000000010005023780";
        String frameData = data.substring(0, data.length() - 4);
        String crcData = data.substring(data.length() - 4);
        int crc = CommUtils.crc16(0, Hex.decodeHex(frameData));

        System.out.println("frameData:" + frameData);
        System.out.println("crcData:" + crcData);
        System.out.println("crc:" + Hex.encodeHexString(ByteBuffer.allocate(2).putShort((short) crc).array()));
//        String isCrcOK = crc ==  Integer.valueOf(crcData, 16) ? "true": "false";
//        System.out.println("isCrcOK:" + isCrcOK);
        long uuid = Long.valueOf(data.substring(2, 10), 16);
        int result = Integer.valueOf(data.substring(14, 16), 16);
        System.out.println("uuid:" + uuid);
        System.out.println("result:" + result);
    }

    @Test
    public void ackData() {

        System.out.println("data:" + createAckData(0x04, 3415601776L));
    }

    @Test
    public void logData() {
        System.out.println("data:" + createLogData(20, 10, 1, 5, 6));
    }

    public String createAckData(int cmd, long uuid) {

        // 需要计算crc的部分
        byte[] frameBuf = ByteBuffer.allocate(8)
                .put((byte) cmd)
                .putInt((int) uuid)
                .putShort((short) 5)
                .put((byte) 0)
                .array();
        // crc
        byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

        return Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());
    }

    public String createLogData(int volt, int rssi, int openType, int faceCnt, int cardCnt) {

        long uuid = CommUtils.getRandomUUID();
        // 需要计算crc的部分
        byte[] frameBuf = ByteBuffer.allocate(18)
                .put((byte) 0x07)
                .putInt((int) uuid)
                .putShort((short) 15) // 帧长度
                .putInt((int) (System.currentTimeMillis() / 1000)) // 时间
                .putShort((short) volt)
                .put((byte) rssi)
                .put((byte) 0)
                .put((byte) openType)
                .put((byte) faceCnt)
                .put((byte) cardCnt)
                .array();
        // crc
        byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

        return Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());
    }
}

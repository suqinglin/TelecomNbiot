package com.nbiot.telecom.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.ByteBuffer;
import java.util.Random;

public class CommUtils {

    /**
     * crc16 多项式校验方法
     *
     * @param buffer
     * @return
     */
    public static int crc16(int base, final byte[] buffer) {
        int crc = base;
        for (byte buf : buffer) {
            int i = buf;
            i &= 0xFF;
            crc = (((crc >> 8) & 0xFF) | (crc << 8));
            crc ^= i;
            crc ^= ((crc & 0xFF) >> 4) & 0xFF;
            crc ^= (crc << 8) << 4;
            crc ^= ((crc & 0xFF) << 4) << 1;
            crc &= 0xffff;
        }
        crc &= 0xffff;
        return crc;
    }

    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[4];
        for (int ix = 4; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix - 4] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    /**
     * 随机生成2^32以内的正整数
     * @return
     */
    public static long getRandomUUID() {
        long n = 1L<<32; // 2^31
        Random rng = new Random();
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }

    public static void main(String[] args) {
        long uuid = 2249053356L;
        int i = (int) uuid;
        System.out.println(Hex.encodeHexString(ByteBuffer.allocate(4).putInt(i)));
//        System.out.println(new CommUtils().getRandomUUID());
    }
}

package com.nbiot.telecom.service;

import com.nbiot.telecom.utils.CommUtils;
import com.nbiot.telecom.utils.CommandConstants;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Service
public class CommandEncryptServiceImpl implements CommandEncryptService {


    @Override
    public String[] encryptAddFace(Integer uuid, Integer effectTime, Integer failureTime, String faceId, String temData) throws DecoderException {

        if (faceId.length() != 8) {
            throw new IllegalArgumentException("faceId's length must be 8 chars.");
        }
        byte[] faceIdBuf = Hex.decodeHex(faceId); // 人脸ID
        byte[] tempDataBuf = Hex.decodeHex(temData); // 人脸数据
        byte[] userId = new byte[24]; // 用户ID，目前全为0
        for (int i = 0; i < userId.length; i++) {
            userId[i] = 0x00;
        }
        // 总数据
        byte[] dataBuf = ByteBuffer.allocate(36 + tempDataBuf.length)
                .putInt(effectTime)
                .putInt(failureTime)
                .put(userId)
                .put(faceIdBuf)
                .put(tempDataBuf)
                .array();
        System.out.println("dataBuf:" + Hex.encodeHexString(dataBuf));
        // 包总数(总包数=总的要发送的数据量/每包能发送的数据量，取上线
        // 每包能发送数据量=512-帧类型-uuid-帧长度-包序号-包总数-crc)
        int pkgCount = (int) Math.ceil(dataBuf.length / 501f);
        // 最终数据集
        String[] dataStrs = new String[pkgCount];
        for (int i = 0; i < pkgCount; i++) {
            byte[] pkgData;
            // 如果长度大于501，取出前501个字节，并将剩余字节重新赋值给temDataBuf
            // 如果长度不大于501，直接将temDataBuf全部取出，并清空temDataBuf
            if (dataBuf.length > 501) {
                pkgData = new byte[501];
                System.arraycopy(dataBuf, 0, pkgData, 0, 501);
                byte[] restData = new byte[dataBuf.length - pkgData.length];
                System.arraycopy(dataBuf, pkgData.length, restData, 0, restData.length);
                dataBuf = restData;
            } else {
                pkgData = dataBuf;
                dataBuf = new byte[0];
            }

            // 有效数据载荷长度
            int dataLength = pkgData.length + 4 + 2; // 加上uuid、包序号和包总数的长度
            // 需要计算crc的部分
            byte[] frameBuf = ByteBuffer.allocate(dataLength + 1 + 2)
                    .put((byte) CommandConstants.REQ_ADD_TEMPLETE)
                    .putInt(uuid)
                    .putShort((short) dataLength)
                    .put((byte) i)
                    .put((byte) pkgCount)
                    .put(pkgData)
                    .array();
            // crc
            byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

            dataStrs[i] = Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());

            System.out.println("dataStrs[" + i + "]:" +  dataStrs[i]);
        }
        return dataStrs;
    }

    @Override
    public String encryptDelFace(Integer uuid, String faceId) throws DecoderException {
        if (faceId.length() != 8) {
            throw new IllegalArgumentException("faceId's length must be 8 chars.");
        }
        byte[] faceIdBuf = Hex.decodeHex(faceId); // 人脸ID
        byte[] userId = new byte[24]; // 用户ID，目前全为0
        for (int i = 0; i < userId.length; i++) {
            userId[i] = 0x00;
        }

        // 有效数据载荷长度
        int dataLength = 32;

        // 需要计算crc的部分
        byte[] frameBuf = ByteBuffer.allocate(dataLength + 1 + 2)
                .put((byte) CommandConstants.REQ_DEL_TEMPLETE)
                .putInt(uuid)
                .putShort((short) dataLength)
                .put(userId)
                .put(faceIdBuf)
                .array();

        // crc
        byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

        return Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());
    }

    @Override
    public String encryptAddCard(Integer uuid, Integer effectTime, Integer failureTime, String cardId, String cardData) throws DecoderException {
        if (cardId.length() != 8) {
            throw new IllegalArgumentException("cardId's length must be 8 chars.");
        }
        if (cardData.length() != 32) {
            throw new IllegalArgumentException("cardData's length must be 32 chars.");
        }
        byte[] cardIdBuf = Hex.decodeHex(cardId); // 卡片ID
        byte[] cardDataBuf = Hex.decodeHex(cardData); // 卡数据
        byte[] userId = new byte[24]; // 用户ID，目前全为0
        for (int i = 0; i < userId.length; i++) {
            userId[i] = 0x00;
        }

        // 有效数据载荷长度
        int dataLength = 56;

        // 需要计算crc的部分
        byte[] frameBuf = ByteBuffer.allocate(dataLength + 1 + 2)
                .put((byte) CommandConstants.REQ_ADD_CARD)
                .putInt(uuid)
                .putShort((short) dataLength)
                .putInt(effectTime)
                .putInt(failureTime)
                .put(userId)
                .put(cardIdBuf)
                .put(cardDataBuf)
                .array();
        // crc
        byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

        return Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());
    }

    @Override
    public String encryptDelCard(Integer uuid, String cardId, String cardData) throws DecoderException {
        if (cardId.length() != 8) {
            throw new IllegalArgumentException("cardId's length must be 8 chars.");
        }
        if (cardData.length() != 32) {
            throw new IllegalArgumentException("cardData's length must be 32 chars.");
        }
        byte[] cardIdBuf = Hex.decodeHex(cardId); // 卡片ID
        byte[] cardDataBuf = Hex.decodeHex(cardData); // 卡数据

        // 有效数据载荷长度
        int dataLength = 24;

        // 需要计算crc的部分
        byte[] frameBuf = ByteBuffer.allocate(dataLength + 1 + 2)
                .put((byte) CommandConstants.REQ_DEL_CARD)
                .putInt(uuid)
                .putShort((short) dataLength)
                .put(cardIdBuf)
                .put(cardDataBuf)
                .array();
        // crc
        byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

        return Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());
    }

    @Override
    public String encryptRemoteOpen(Integer uuid, String operator, Integer time) throws DecoderException {
        if (operator.length() != 8) {
            throw new IllegalArgumentException("operator's length must be 8 chars.");
        }
        byte[] operatorBuf = Hex.decodeHex(operator); // 卡片ID

        // 有效数据载荷长度
        int dataLength = 12;

        // 需要计算crc的部分
        byte[] frameBuf = ByteBuffer.allocate(dataLength + 1 + 2)
                .put((byte) CommandConstants.REQ_REMOTE_OPEN)
                .putInt(uuid)
                .putShort((short) dataLength)
                .put(operatorBuf)
                .putInt(time)
                .array();
        // crc
        byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

        return Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());
    }

    @Override
    public String encryptResponseLog(Integer uuid) {
        // 需要计算crc的部分
        byte[] frameBuf = ByteBuffer.allocate(8)
                .put((byte) CommandConstants.ACK_LOG_REPORT)
                .putInt(uuid)
                .putShort((short) 5)
                .put((byte) 1)
                .array();
        // crc
        byte[] crc = ByteBuffer.allocate(2).putShort((short) CommUtils.crc16(0, frameBuf)).array();

        return Hex.encodeHexString(ByteBuffer.allocate(frameBuf.length + 2)
                .put(frameBuf)
                .put(crc)
                .array());
    }

    public static void main(String[] args) {
        try {
//            System.out.println(new CommandEncryptServiceImpl().encryptAddCard((int) CommUtils.getRandomUUID(), 1234567890, 1234567890, "00000001", "AABBCCDDEEFFAABBCCDDEEFFAABBCCDD"));
//            System.out.println(new CommandEncryptServiceImpl().encryptDelFace((int) CommUtils.getRandomUUID(), "12345678"));
//            System.out.println(new CommandEncryptServiceImpl().encryptDelCard((int) CommUtils.getRandomUUID(), "12345678", "AABBCCDDEEFFAABBCCDDEEFFAABBCCDD"));
//            System.out.println(new CommandEncryptServiceImpl().encryptRemoteOpen((int) CommUtils.getRandomUUID(), "12345678", 1234567890));

//            int pkgCount = (int) Math.ceil((36 + 1500) / 501f);
//            System.out.println(pkgCount);
            int uuid = (int) CommUtils.getRandomUUID();
            int effectTime = 1561960884;
            int failueTime = 1564639284;
            String faceId = "12345678";
            String data = "34D8D33DBE5EEEBCDBD9753D74C5593D5069503D741D0EBE2E582FBD828B00BD34D8D33D68C590BC3858F83C5401973D31CF81BBB87D6C3D5634DD3D20F7883D18DF9DBD74C5593DE2BE9A3C936C4BBDA0971F3A00CEC53D9FBB1F3C721042BDA14FC3BD50B438BD65AF9B3DEB54343DDD71BC3DF135B6BC9FBB1F3C43B0CCBBE89F1CBD3A3BA7BDEE1FC13DE2BE9A3C61E40E3DFB8067BD6E45B5BDEE1FC13D43B0CCBB2BC0E8BD31A58D3D65AF9B3D99B9A93DC98325BEF68294BD4353923D7AB8DBBA7AB8DBBA0F49843D3858F83C68C590BC3B6EEDBDE2BE9A3C0BFC25BDF49FE53C2E0D473DCCC3B73D461A7CBCB0E7D23C6E45B5BDC64313BD073199BDE2BE9A3CBE5EEEBC7790E63D0DA83D3BAA67AE3DDD71BC3DDBD9753D3F88053D9621633DBD61163EF68294BDD3268BBD40397ABD6B2FC03CE5D48FBD78A9033E2CCD343E18DF9DBDBE5EEEBCBDA956BCC8F82A3DC64313BD7AB8DBBA6F78FBBDBDA956BCB2FDC7BDFD357F3D1B1F303E90A1BEBD461A7CBC6F78FBBDD9245EBD343931BC2BC0E8BD0F49843D7AB8DBBA2677AD3C3858F83CABC80BBCC8F82A3D40397ABD0BFC25BDA0971F3AB87D6C3D073199BD107CCA3DCCC3B73DE2BE9A3C5401973D2677AD3C7790E63DB1CA81BD755054BEA59C213D6F78FBBD43B0CCBB00CEC53D2677AD3C2BC0E8BDB19C6A3C2476323E0A712B3E343931BC74C5593D3858F83C0DA83D3BFD357F3DC64313BD74C5593D31A58D3D68C590BC0DB13D3D19B5A93BE89F1CBD0DA83D3B721042BD343931BC0DB13D3D0DA83D3BDD71BC3DBD48F93D7AB8DBBA9FBB1F3CC3ABCCBDF7B5DABD74C5593DF68294BDAC7DA3BCA4E709BDC64313BD6B2FC03CE89F1CBD222ACF3D8000063E721042BDB0E7D23C18DF9DBDDBD9753DAC7DA3BC18DF9DBDB19C6A3CDBD9753D36EEC8BCBB15B33DA14FC3BD5401973D90A1BEBDE507D6BD880BA53D4A9D48BE2BC0E8BDF49FE53C222ACF3D0DB13D3DAC7DA3BC31CF81BB43B0CCBB0BFC25BD43B0CCBB19B5A93B936C4BBDF49FE53C61E40E3D721042BD7AA6DBBC2E582FBD36EEC8BCB2FDC7BD343931BCEE1FC13DC64313BD828B00BD";
            System.out.println("uuid:" + Hex.encodeHexString(ByteBuffer.allocate(4).putInt(uuid)));
            new CommandEncryptServiceImpl().encryptAddFace(uuid, effectTime, failueTime, faceId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

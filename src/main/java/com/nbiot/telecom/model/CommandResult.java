package com.nbiot.telecom.model;

import lombok.Getter;
import lombok.Setter;

public class CommandResult {

    /**
     * 操作类型：1、下发人脸模板；2、删除人脸模板；3、下发卡片；4、删除卡片；5、远程开锁
     */
    @Getter
    @Setter
    private int optType;

    /**
     * 人脸或卡片ID
     */
    @Getter
    @Setter
    private String dataId;

    /**
     * 结果：0、未应答；1、成功；2、失败
     */
    @Getter
    @Setter
    private int result;
}

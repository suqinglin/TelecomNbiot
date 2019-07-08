package com.nbiot.telecom.request;

import lombok.Getter;
import lombok.Setter;

public class RxAddFace {

    @Getter
    @Setter
    private String deviceId;

    @Getter
    @Setter
    private String faceId;

    @Getter
    @Setter
    private String templeteData;

    @Getter
    @Setter
    private long effectTime;

    @Getter
    @Setter
    private long failureTime;
}

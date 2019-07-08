package com.nbiot.telecom.request;

import lombok.Getter;
import lombok.Setter;

public class RxAddCard {

    @Getter
    @Setter
    private String deviceId;

    @Getter
    @Setter
    private String cardId;

    @Getter
    @Setter
    private String cardData;

    @Getter
    @Setter
    private long effectTime;

    @Getter
    @Setter
    private long failureTime;
}

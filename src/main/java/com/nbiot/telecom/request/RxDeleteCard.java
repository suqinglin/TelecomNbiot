package com.nbiot.telecom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class RxDeleteCard {

    @NotBlank
    @Getter
    @Setter
    private String deviceId;

    @NotBlank
    @Getter
    @Setter
    private String cardId;

    @NotBlank
    @Getter
    @Setter
    private String cardData;
}

package com.nbiot.telecom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class RxDeleteFace {

    @NotBlank
    @Getter
    @Setter
    private String deviceId;

    @NotBlank
    @Getter
    @Setter
    private String faceId;
}

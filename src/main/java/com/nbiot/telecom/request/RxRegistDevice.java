package com.nbiot.telecom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class RxRegistDevice {

    @NotBlank
    @Getter
    @Setter
    private String imei;

    @NotBlank
    @Getter
    @Setter
    private String name;

}

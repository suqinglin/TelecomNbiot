package com.nbiot.telecom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class RxGetCmdResult {

    @NotBlank
    @Getter
    @Setter
    private String uuid;
}

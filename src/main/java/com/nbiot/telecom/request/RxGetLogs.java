package com.nbiot.telecom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RxGetLogs {

    @NotNull
    @Getter
    @Setter
    private long startTime;

    @NotNull
    @Getter
    @Setter
    private long endTime;
}

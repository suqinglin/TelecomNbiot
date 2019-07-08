package com.nbiot.telecom.service;

import com.nbiot.telecom.model.CommandResult;

public interface CommandService {

    void saveCommand(long id, String cmdId, int reqCmd, String deviceId, String dataId, long time, String status);

    void updateCommandStatus(String cmdId, String status);

    void updateCommandByUUID(long uuid, int ackCmd, int result);

    CommandResult getCommandByUuid(long uuid);
}

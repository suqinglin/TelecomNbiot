package com.nbiot.telecom.service;

import com.nbiot.telecom.mapper.CommandMapper;
import com.nbiot.telecom.model.CommandInfo;
import com.nbiot.telecom.model.CommandResult;
import com.nbiot.telecom.utils.CommandConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommandServiceImpl implements CommandService {

    @Resource
    CommandMapper commandMapper;

    @Override
    public void saveCommand(long id, String cmdId, int reqCmd, String deviceId, String dataId, long time, String status) {
        CommandInfo commandInfo = new CommandInfo(
                id,
                reqCmd,
                deviceId,
                dataId,
                time,
                status);
        commandInfo.setCmdId(cmdId);
        commandMapper.saveCommand(commandInfo);
    }

    @Override
    public void updateCommandStatus(String cmdId, String status) {
        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setCmdId(cmdId);
        commandInfo.setStatus(status);
        if (status != null) {
            commandMapper.updateCommandStatus(commandInfo);
        }
    }

    @Override
    public void updateCommandByUUID(long uuid, int ackCmd, int result) {
        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setUuid(uuid);
        commandInfo.setAckCmd(ackCmd);
        commandInfo.setResult(result);
        commandMapper.updateCommandByUUID(commandInfo);
    }

    @Override
    public CommandResult getCommandByUuid(long uuid) {
        CommandInfo commandInfo = commandMapper.getCommandByUuid(uuid);
        CommandResult commandResult = new CommandResult();
        commandResult.setDataId(commandInfo.getDataId());
        switch (commandInfo.getReqCmd()) {
            case CommandConstants.REQ_ADD_TEMPLETE:
                commandResult.setOptType(1);
                break;
            case CommandConstants.REQ_DEL_TEMPLETE:
                commandResult.setOptType(2);
                break;
            case CommandConstants.REQ_ADD_CARD:
                commandResult.setOptType(3);
                break;
            case CommandConstants.REQ_DEL_CARD:
                commandResult.setOptType(4);
                break;
            case CommandConstants.REQ_REMOTE_OPEN:
                commandResult.setOptType(5);
                break;
        }
        commandResult.setResult(commandInfo.getResult());
        return commandResult;
    }
}

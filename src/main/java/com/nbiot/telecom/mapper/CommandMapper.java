package com.nbiot.telecom.mapper;


import com.nbiot.telecom.model.CommandInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommandMapper {

    void saveCommand(CommandInfo commandInfo);

    void updateCommandByUUID(CommandInfo commandInfo);

    void updateCommandStatus(CommandInfo commandInfo);

    CommandInfo getCommandByUuid(long uuid);
}

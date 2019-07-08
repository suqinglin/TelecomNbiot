package com.nbiot.telecom;

import com.nbiot.telecom.mapper.CommandMapper;
import com.nbiot.telecom.model.CommandInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DbTest {

    final static Logger logger = LoggerFactory.getLogger(DbTest.class);

    @Resource
    private CommandMapper commandMapper;


    @Test
    public void test1() {
        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setUuid(123456);
        commandInfo.setReqCmd(0x82);
        commandInfo.setAckCmd(0x02);
        commandInfo.setDeviceId("aaaaaaa");
        commandInfo.setTime(System.currentTimeMillis() / 1000);
        commandInfo.setStatus("sent");
        commandMapper.saveCommand(commandInfo);
//        DeviceDescribeInfo deviceDescribeInfo = deviceDescribeMapper.getDeviceDescribeInfoByDescribe("NL_GWF01A");
//        logger.info("deviceDescribeInfo {}", deviceDescribeInfo.toString());
    }

}

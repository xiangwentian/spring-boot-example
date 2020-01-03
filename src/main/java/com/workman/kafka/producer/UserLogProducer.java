package com.workman.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.workman.kafka.bean.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserLogProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(String userId) {
        UserLog userLog = new UserLog();
        userLog.setUsername("jhp").setUserid(userId).setState("0");
        log.info("发送用户日志数据：" + userId);
        kafkaTemplate.send("user-log", JSON.toJSONString(userLog));
    }

    public void sendLog2(String userId) {
        UserLog userLog = new UserLog();
        userLog.setUsername("workman").setUserid(userId).setState("1");
        log.info("sendLog2 发送用户日志数据：" + userId);
        kafkaTemplate.send("user-log", JSON.toJSONString(userLog));
    }
}

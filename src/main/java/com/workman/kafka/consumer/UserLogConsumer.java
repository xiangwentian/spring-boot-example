package com.workman.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.workman.kafka.bean.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserLogConsumer {

    @KafkaListener(topics = "user-log", groupId = "user-log-group2")
    public void onMessage(ConsumerRecord<?, ?> consumerRecord, Acknowledgment acknowledgment) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("onMessage消费消息:{}", JSON.toJSONString(message));
        }
        acknowledgment.acknowledge();

    }

    //    @KafkaListener(topics = {"user-log"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        //log.info("=====>record={}",kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            log.info("consumer 消费消息:{}", JSON.toJSONString(message));
            /** 当注释下面方法时，再初始化时会再次消费 */
            //, Acknowledgment ack
//            ack.acknowledge();
        }
    }

    //    @KafkaListener(topics = {"user-log"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consumer1(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        //log.info("=====>record={}",kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            log.info("consumer1 消费消息:{}", JSON.toJSONString(message));
            //            ack.acknowledge();
        }
    }
}

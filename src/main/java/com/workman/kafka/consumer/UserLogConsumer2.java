package com.workman.kafka.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserLogConsumer2 {
//    @KafkaListener(topics = {"user-log"}, groupId = "user-log-group2")
//    @KafkaListener(topics = {"user-log"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consumer2(ConsumerRecord<?, ?> consumerRecord,Acknowledgment ack) {
        //判断是否为null
        Optional kafkaMessage = Optional.ofNullable(consumerRecord.value());
        //log.info("=====>consumer2 record={}",kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到option中的实例
            Object message = kafkaMessage.get();
            log.info("consumer2,消费消息:{}", JSON.toJSONString(message));

            ack.acknowledge();
        }
    }

//    @KafkaListener(topics = {"user-log"}, groupId = "user-log-group1")
    //@KafkaListener(topics = {"user-log"}, groupId = "user-log-group")
//    @KafkaListener(topics = {"user-log"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consumer3(ConsumerRecord<?, ?> consumerRecord,Acknowledgment ack) {
        //判断是否为null
        Optional kafkaMessage = Optional.ofNullable(consumerRecord.value());
        //log.info("=====>consumer2 record={}",kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到option中的实例
            Object message = kafkaMessage.get();
            log.info("consumer3,消费消息:{}", JSON.toJSONString(message));
            //            ack.acknowledge();
            ack.acknowledge();
        }
    }
}

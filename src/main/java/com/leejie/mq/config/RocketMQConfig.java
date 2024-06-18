package com.leejie.mq.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author leejie
 * @Date 2024/6/5 15:32
 * @Description TODO
 **/
@Configuration
public class RocketMQConfig {

    @Bean(name = "rocketMQTemplate")
    public RocketMQTemplate rocketMQTemplate(){
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup("test-producer01");
        producer.setNamesrvAddr("localhost:9876");
        rocketMQTemplate.setProducer(producer);
        return rocketMQTemplate;
    }
}

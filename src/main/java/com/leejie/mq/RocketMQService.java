package com.leejie.mq;

import com.leejie.mq.info.AddMessageReq;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author leejie
 * @Date 2024/6/5 15:09
 * @Description TODO
 **/
public interface RocketMQService {

    void sendMessage(AddMessageReq msg);


}

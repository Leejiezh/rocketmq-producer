package com.leejie.mq;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.leejie.mq.info.AddMessageReq;
import com.leejie.mq.transaction.RocketTransactionListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @Author leejie
 * @Date 2024/6/5 15:09
 * @Description TODO
 **/
@Slf4j
@Service
public class ProducerV2 implements RocketMQService {

    @Resource(name = "rocketMQTemplate")
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMessage(AddMessageReq req) {

        switch (req.getSendType()) {
            case COMMON: {
                SendResult sendResult = rocketMQTemplate.syncSend(req.getTopic(), JSONUtil.toJsonStr(req.getMessage()));
                log.info("status: {}, msgId: {}", sendResult.getSendStatus(), sendResult.getMsgId());
                break;
            }
            case ORDER: {
                SendResult sendResult = rocketMQTemplate.syncSendOrderly(req.getTopic(), JSONUtil.toJsonStr(req.getMessage()), "123");
                log.info("status: {}, msgId: {}, queueId: {}", sendResult.getSendStatus(), sendResult.getMsgId(), sendResult.getMessageQueue().getQueueId());
                break;
            }
            case DELAY: {
                Message msg = new Message();
                msg.setTopic(req.getTopic());
                msg.setBody(JSONUtil.toJsonStr(req.getMessage()).getBytes());
                msg.setDelayTimeLevel(2);
                try {
                    SendResult send = rocketMQTemplate.getProducer().send(msg);
                    log.info("status: {}, msgId: {}", send.getSendStatus(), send.getMsgId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case BATCH:
                break;
            case TRANSACTION:
                Message msg = new Message();
                msg.setTopic(req.getTopic());
                msg.setBody(JSONUtil.toJsonStr(req.getMessage()).getBytes());
                msg.setKeys(IdUtil.fastSimpleUUID());
                TransactionMQProducer producer = new TransactionMQProducer("test-producer01");
                producer.setNamesrvAddr("localhost:9876");
                producer.setTransactionListener(new RocketTransactionListener());
                try {
                    producer.start();
                    TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(msg, null);
                    log.info("status: {}, msgId: {}", transactionSendResult.getSendStatus(), transactionSendResult.getMsgId());
                } catch (MQClientException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                break;
        }

    }
}

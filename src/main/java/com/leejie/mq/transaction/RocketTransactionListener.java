package com.leejie.mq.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;

/**
 * @Author leejie
 * @Date 2024/6/12 16:22
 * @Description TODO
 **/
@Slf4j
@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")
public class RocketTransactionListener implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            log.info("事务消息header：{}, 消息体：{}", msg.getKeys(), new String(msg.getBody()));
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            log.info("本地事务执行失败");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return LocalTransactionState.ROLLBACK_MESSAGE;
    }
}

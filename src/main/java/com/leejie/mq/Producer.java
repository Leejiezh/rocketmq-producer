package com.leejie.mq;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @Author leejie
 * @Date 2024/5/25 17:03
 * @Description TODO
 **/
//@Component
@Slf4j
public class Producer {

    private static final TransactionListener transactionListener = new TransactionListener() {
        /**
         * 在发送消息成功时执行本地事务
         * @param msg Half(prepare) message
         * @param arg Custom business parameter
         * @return 返回事务状态
         * LocalTransactionState.COMMIT_MESSAGE 事务提交,提交之后broker才允许消费者消费
         * LocalTransactionState.RollbackTransaction 事务回滚，回滚后消息将被删除，并且不允许被消费
         * LocalTransactionState.Unknown 中间状态，表示MQ需要核对，以确定状态
         */
        @Override
        public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        /**
         * broker端对未确定状态的消息发起回查，将消息发送到对应的Producer端（同一个Group的Producer）
         * 由Producer根据消息来检查本地事务的状态，进而执行Commit或者Rollback
         * @param msg Check message
         * @return
         */
        @Override
        public LocalTransactionState checkLocalTransaction(MessageExt msg) {
            return null;
        }
    };


        //如果生产者需要发送消息，必须链接一个nameserver服务器并且有一个生产者对象
//    @PostConstruct
    public void send() throws Exception {
        log.info("-----------------生产者生产消息：---------------");
        //创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer();
        //配置nameserver访问的地址信息
        producer.setNamesrvAddr("localhost:9876");
        //给生产者对象进行分组，因为后期都是集群结构所以为了方便区分进行分组
        producer.setProducerGroup("test-producer01");
        //启动生产者
        producer.start();

        //创建消息对象并指定消息内容
        Message message = new Message();
        //设置消息的主题名称(该主题可以理解为消息需要保存的位置，即消息会保存在名为test-01-topic的主题里)
        message.setTopic("test-01-topic");

        for (int i = 0; i < 1; i++) {
            String msgTxt = "msgInfo["+ i + "]";
            //将消息填充到消息对象中，需要将字符串数据转换成字节数据
            message.setBody(msgTxt.getBytes(StandardCharsets.UTF_8));
            //发送信息
            SendResult send = producer.send(message);
            System.out.println(send.getSendStatus());

        }
    }

}

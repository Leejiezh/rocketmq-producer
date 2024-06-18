package com.leejie;

import com.leejie.mq.RocketMQService;
import com.leejie.mq.info.AddMessageReq;
import com.leejie.mq.info.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringsApplicationTests {

	@Autowired
	private RocketMQService rocketMQService;

//	@Test
//	void contextLoads() {
//		//发送同步消息
//		Message<String> message = new Message<>();
//		message.setId("123");
//		message.setContent("测试一下");
//
//		AddMessageReq addMessageReq = new AddMessageReq();
//		addMessageReq.setTopic("topic1");
//		addMessageReq.setTag("tag1");
//		addMessageReq.setMsg(message);
//		rocketMQService.sendMessage(addMessageReq);
//	}

}

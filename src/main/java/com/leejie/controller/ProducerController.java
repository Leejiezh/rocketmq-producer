package com.leejie.controller;

import com.leejie.mq.RocketMQService;
import com.leejie.mq.info.AddMessageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author leejie
 * @Date 2024/6/5 15:56
 * @Description TODO
 **/
@RestController
public class ProducerController {

    @Autowired
    private RocketMQService rocketMQService;

    @PostMapping("/sendMsg")
    public void sendMessage(@RequestBody AddMessageReq req){
        rocketMQService.sendMessage(req);
    }
}

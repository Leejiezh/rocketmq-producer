package com.leejie.mq.info;

import com.leejie.mq.enums.SendTypeEnum;
import lombok.Data;

/**
 * @Author leejie
 * @Date 2024/6/5 15:11
 * @Description TODO
 **/
@Data
public class AddMessageReq {

    private String topic;

    private SendTypeEnum sendType;

    private Message<String> message;
}

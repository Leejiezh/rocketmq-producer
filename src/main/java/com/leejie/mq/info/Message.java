package com.leejie.mq.info;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author leejie
 * @Date 2024/6/5 15:11
 * @Description TODO
 **/
@Data
public class Message<T> implements Serializable {

    private String id;

    private T content;

    private String createTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
}

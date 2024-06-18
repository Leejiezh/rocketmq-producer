package com.leejie.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author leejie
 * @Date 2024/6/7 16:39
 * @Description TODO
 **/
@Getter
@AllArgsConstructor
public enum SendTypeEnum {

    COMMON,
    ORDER,
    DELAY,
    BATCH,
    TRANSACTION,

    ;

}

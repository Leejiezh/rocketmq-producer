package com.leejie.springBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @Author leejie
 * @Date 2024/5/23 11:55
 * @Description TODO
 **/
@Slf4j
@Component
public class DestroyService implements DisposableBean {

    @Override
    public void destroy() {
        log.info("11.实现DisposableBean接口执行销毁方法");
    }
}

package com.leejie.springBean;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @Author leejie
 * @Date 2024/5/21 9:18
 * @Description TODO
 **/
@Slf4j
//@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("4.实例：{} 初始化前", beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("8.实例：{} 初始化后", beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}

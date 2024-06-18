package com.leejie.springBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author leejie
 * @Date 2024/5/21 16:12
 * @Description TODO
 **/
@Slf4j
@Component
public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {

    private String beanName;

    public MyBean() {
        log.info("1.调用构造方法实例化");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        log.info("2.实现BeanNameAware接口：{}", name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("3.实现ApplicationContextAware接口：{}", applicationContext);
    }


    @Override
    public void afterPropertiesSet() {
        log.info("6.使用继承InitializingBean初始化：{}", beanName);
    }


    public void init(){
        log.info("7.使用init-method初始化：{}", beanName);
    }



    @PostConstruct
    public void init1(){
        log.info("5.使用@PostConstruct初始化：{}", beanName);
    }


    @PreDestroy
    public void destroyAnno(){
        log.info("9.@PreDestroy销毁bean：{}", beanName);
    }

    public void destroy(){
        log.info("10.使用destroy-method配置销毁bean：{}", beanName);
    }
}

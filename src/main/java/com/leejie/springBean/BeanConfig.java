package com.leejie.springBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author leejie
 * @Date 2024/5/22 18:07
 * @Description TODO
 **/
@Configuration
public class BeanConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MyBean myBean() {
        return new MyBean();
    }

}

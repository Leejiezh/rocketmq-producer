package com.leejie.springBean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leejie
 * @Date 2024/5/21 9:25
 * @Description TODO
 **/
@Data
public class User implements Serializable {

    private String name;

    private Integer age;

    private String address;

    private String city;
}

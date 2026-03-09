package com.example.zxds.dr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 让当前子系统自动注册到Nacos上面
@EnableDiscoveryClient
// 支持Feign远程调用
@EnableFeignClients
// 解决不能正常调用Web方法，出现404的问题
@ServletComponentScan
// 扫描DAO接口
@MapperScan("com.example.zxds.dr.db.dao")
// 扫描当前子系统中各种配置类的注解
@ComponentScan("com.example.*")
public class ZxdsDrApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZxdsDrApplication.class, args);
    }

}
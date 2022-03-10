package cn.iocoder.springboot.eventdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * description: DemoApplication
 * date: 2021/4/9 3:56 下午
 *
 * @author: guizhenyu
 */
@SpringBootApplication
@EnableAsync // 开启 Spring 异步的功能
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

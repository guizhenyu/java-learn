package io.guizhenyu.transaction.test.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * description: TcTestApp date: 2022/2/12 12:24 下午
 *
 * @author: guizhenyu
 */
@MapperScan("io.guizhenyu.transaction.test.service.repository.mapper")
@SpringBootApplication(scanBasePackages = {"io.guizhenyu"})
public class TcTestApp {

  public static void main(String[] args) {
    SpringApplication.run(TcTestApp.class);
  }
}

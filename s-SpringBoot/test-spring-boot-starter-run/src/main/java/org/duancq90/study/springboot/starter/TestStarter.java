package org.duancq90.study.springboot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/6/18 下午12:14
 */
@EnableLogAutoConfigure
@SpringBootApplication
public class TestStarter {

    public static void main(String[] args) {
        SpringApplication.run(TestStarter.class);
    }
}

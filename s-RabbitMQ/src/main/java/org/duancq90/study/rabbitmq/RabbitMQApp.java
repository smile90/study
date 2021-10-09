package org.duancq90.study.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/7/9 下午2:25
 */
@EnableRabbit
@SpringBootApplication
public class RabbitMQApp {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApp.class, args);
    }

}

package org.duancq90.study.rabbitmq.publishers;

import org.duancq90.study.rabbitmq.RabbitMQApp;
import org.duancq90.study.rabbitmq.RabbitMQConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
/**
* @author dcq
* @date 2021/7/9 下午2:56
* @version V1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitMQApp.class})
public class RabbitPublisherTest {

    @Autowired
    private RabbitPublisher rabbitPublisher;

    @Test
    public void sentMessage() throws InterruptedException {
        rabbitPublisher.sendMessage(
                RabbitMQConstants.EXCHANGE_DIRECT,
                RabbitMQConstants.ROUTING_KEY_RISK,
                "{test:\"test\"}"
        );

        sleep();
    }

    @Test
    public void sleep() throws InterruptedException {
        Thread.sleep(99999999);
    }
}

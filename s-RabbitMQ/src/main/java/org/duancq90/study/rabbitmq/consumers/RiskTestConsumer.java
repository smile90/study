package org.duancq90.study.rabbitmq.consumers;

import lombok.extern.slf4j.Slf4j;
import org.duancq90.study.rabbitmq.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/7/9 下午2:21
 */
@Slf4j
@Configuration
@RabbitListener(queues = RabbitMQConstants.QUEUE_TEST001)
public class RiskTestConsumer {

    @RabbitHandler
    public void processMessage(String content) {
        log.info("processMessage:{}", content);
    }
}

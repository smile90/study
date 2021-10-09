package org.duancq90.study.rabbitmq.config;

import org.duancq90.study.rabbitmq.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明队列
 * @author dcq
 * @version V1.0
 * @date 2021/7/9 下午2:41
 */
@Configuration
public class QueueConfig {

    @Bean("test001Queue")
    public Queue test001Queue(){
        return QueueBuilder.durable(RabbitMQConstants.QUEUE_TEST001).build();
    }

    @Bean
    public Binding test001Binding(
            @Qualifier("test001Queue") Queue queue,
            @Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMQConstants.ROUTING_KEY_RISK).noargs();
    }
}

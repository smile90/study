package org.duancq90.study.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.duancq90.study.rabbitmq.RabbitMQConstants;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * MQ的配置
 * 1、声明交换机
 * @author dcq
 * @version V1.0
 * @date 2021/7/9 下午2:38
 */
@Slf4j
@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * json 序列化
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @PostConstruct
    public void initRabbitTemplate() {
        // 只要消息抵达服务器，就会触发该回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, failCause) ->
                log.info("已成功发送-》correlationData=[{}],ack=[{}],failCause=[{}]", correlationData, ack, failCause)
        );

        // 设置消息抵达队列的确认回调，如果消息没有成功投递到指定队列，就会触发该回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("发送失败-》message=[{}],replyCode=[{}],replyText=[{}],exchange=[{}],routingKey=[{}]",
                        message, replyCode, replyText, exchange, routingKey)
        );
    }

    @Bean("topicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(RabbitMQConstants.EXCHANGE_TOPIC).durable(true).build();
    }

    @Bean("directExchange")
    public Exchange directExchange(){
        return ExchangeBuilder.topicExchange(RabbitMQConstants.EXCHANGE_DIRECT).durable(true).build();
    }

    @Bean("fanoutExchange")
    public Exchange fanoutExchange(){
        return ExchangeBuilder.topicExchange(RabbitMQConstants.EXCHANGE_FANOUT).durable(true).build();
    }
}

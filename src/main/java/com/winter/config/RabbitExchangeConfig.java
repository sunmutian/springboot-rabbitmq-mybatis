package com.winter.config;

import com.winter.utils.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianweichang
 * @date 2018-05-08 11:49
 **/
@Configuration
public class RabbitExchangeConfig {
    //直连交换机
    @Bean
    public DirectExchange exchangeTest() {
        return new DirectExchange(RabbitConstant.EXCHANGE_NAME);
    }

    //队列
    @Bean
    public Queue queueTest() {
        return QueueBuilder.durable(RabbitConstant.QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding bindingTest() {
        return BindingBuilder.bind(this.queueTest()).to(this.exchangeTest()).with(RabbitConstant.ROUTING_KEY);
    }

    //直连交换机
    @Bean
    public DirectExchange exchangeTest1() {
        return new DirectExchange(RabbitConstant.EXCHANGE_NAME);
    }

    //队列
    @Bean
    public Queue queueTest1() {
        return QueueBuilder.durable(RabbitConstant.QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding bindingTest1() {
        return BindingBuilder.bind(this.queueTest1()).to(this.exchangeTest1()).with(RabbitConstant.ROUTING_KEY);
    }

}

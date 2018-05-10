package com.tian.queue;

import com.tian.utils.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianweichang
 * @date 2018-05-08 11:49
 **/
@Configuration
public class RoleQueue {
    //直连交换机
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitConstant.EXCHANGE_NAME);
    }

    //队列
    @Bean
    public Queue queue() {
        return QueueBuilder.durable(RabbitConstant.QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding bindingTest() {
        return BindingBuilder.bind(this.queue()).to(this.exchange()).with(RabbitConstant.ROUTING_KEY_ROLE);
    }
}

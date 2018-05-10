package com.winter.config;


import com.winter.utils.RabbitConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqQueueDefineConfig {


  @Bean
  public Queue defineTradeAfterHandleQueue() {
    return new Queue(RabbitConstant.QUEUE_NAME);
  }
}

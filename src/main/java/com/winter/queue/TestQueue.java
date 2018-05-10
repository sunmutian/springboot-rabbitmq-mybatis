package com.winter.queue;

import com.rabbitmq.client.Channel;
import com.winter.utils.RabbitConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author tianweichang
 * @date 2018-05-09 9:08
 **/
@Component
@RabbitListener(queues = "spring-boot-queue")
public class TestQueue implements ChannelAwareMessageListener {
    private Logger logger = LoggerFactory.getLogger(TestQueue.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        logger.info("test queue===============");
    }
}

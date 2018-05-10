package com.winter.receiver;

import com.rabbitmq.client.Channel;
import com.winter.config.RabbitConfig;
import com.winter.config.RabbitExchangeConfig;
import com.winter.model.User;
import com.winter.queue.TestQueue;
import com.winter.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author tianweichang
 * @date 2018-05-08 11:52
 **/
@Configuration
public class RabbitReceive
{
    private Logger logger = LoggerFactory.getLogger(RabbitReceive.class);
    @Resource
    private RabbitConfig rabbitConfig;
    @Resource
    private UserService userService;

    @Autowired
    private RabbitExchangeConfig rabbitExchangeConfig;

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitConfig.connectionFactory());
        container.setQueues(rabbitExchangeConfig.queueTest()); //设置要监听的队列
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                logger.info("receive msg : " + new String(body));
                User user=new User();
                user.setUserName(new String(body));
                user.setPassword("123456");
                user.setPhone("18257160372");
                userService.addUser(user);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }
}

package com.tian.receiver;

import com.rabbitmq.client.Channel;
import com.tian.config.RabbitConfig;
import com.tian.queue.RoleQueue;
import com.tian.model.User;
import com.tian.service.user.UserService;
import com.tian.utils.JacksonUtil;
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
public class RoleReceive
{
    private Logger logger = LoggerFactory.getLogger(RoleReceive.class);
    @Resource
    private RabbitConfig rabbitConfig;
    @Resource
    private UserService userService;

    @Autowired
    private RoleQueue roleQueue;

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitConfig.connectionFactory());
        container.setQueues(roleQueue.queue()); //设置要监听的队列
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                logger.info("receive msg : " + new String(message.getBody()));
                User user= JacksonUtil.mapper.readValue(message.getBody(),User.class);
                userService.addUser(user);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }
}

package com.tian.service.user.impl;

import com.tian.message.RabbitMessages;
import com.tian.service.SenderService;
import com.tian.utils.JacksonUtil;
import com.tian.utils.RabbitConstant;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianweichang
 * @date 2018-05-09 9:16
 **/
@Service("senderService")
public class SenderServiceImpl implements SenderService {
    private Logger logger = LoggerFactory.getLogger(SenderServiceImpl.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public void send() {
        RabbitMessages rabbitMessages = new RabbitMessages();
        rabbitMessages.setUserName("USER_NAME_"+System.currentTimeMillis());
        rabbitMessages.setPassword("PWD"+System.currentTimeMillis());// 来源通道
        rabbitMessages.setPhone("19909097654");
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_NAME,
                RabbitConstant.ROUTING_KEY_USER,
                JacksonUtil.objWriteStr(rabbitMessages, JsonSerialize.Inclusion.NON_EMPTY));
    }

    @Override
    public void send1() {
        RabbitMessages rabbitMessages = new RabbitMessages();
        rabbitMessages.setUserName("USER_NAME_"+System.currentTimeMillis());
        rabbitMessages.setPassword("PWD"+System.currentTimeMillis());// 来源通道
        rabbitMessages.setPhone("19909097654");
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_NAME,
                RabbitConstant.ROUTING_KEY_ROLE,
                JacksonUtil.objWriteStr(rabbitMessages, JsonSerialize.Inclusion.NON_EMPTY));
    }
}

package com.winter.Controller;

import com.winter.service.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tianweichang
 * @date 2018-05-09 9:25
 **/
@RestController
public class TestSendController {
    private Logger logger = LoggerFactory.getLogger(TestSendController.class);
    @Resource
    private SenderService senderService;
    @GetMapping("/send")
    public String send(){
        logger.info("=====send===");
        senderService.send();
        return "ok";
    }
    @GetMapping("/send1")
    public String send1(){
        logger.info("=====send111===");
        senderService.send1();
        return "ok";
    }
}

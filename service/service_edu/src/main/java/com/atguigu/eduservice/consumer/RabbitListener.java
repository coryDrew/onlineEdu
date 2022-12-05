package com.atguigu.eduservice.consumer;

import com.atguigu.eduservice.controller.EduCourseController;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitListener {
    @Autowired
    private EduCourseController eduCourseController;

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "order.queue")
    public void listenorder(String msg){
        log.info(msg);
        log.info(msg);
        log.info(msg);
        log.info(msg);
        log.info(msg);
    }
}

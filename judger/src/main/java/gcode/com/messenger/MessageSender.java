package gcode.com.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname MessageSender
 * @Description TODO
 * @Date 2021/5/19 下午11:55
 * @Created by gandehua
 */
@Component
public class MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;
    public void sendMessage(String msg){
//        System.out.println("submissionid-----> "+mapMessage.get("submissionId"));
//        long submissionId = (Long)mapMessage.get("submissionId");
        jmsTemplate.convertAndSend("fromJudger",msg);
    }

    public void sendMessage(Map msg){
//        System.out.println("submissionid-----> "+mapMessage.get("submissionId"));
//        long submissionId = (Long)mapMessage.get("submissionId");
        jmsTemplate.convertAndSend("fromJudger",msg);
    }
}

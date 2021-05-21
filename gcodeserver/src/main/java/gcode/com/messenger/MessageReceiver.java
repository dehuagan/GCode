package gcode.com.messenger;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @Classname MessageReceiver
 * @Description TODO
 * @Date 2021/5/19 下午10:22
 * @Created by gandehua
 */
@Component
public class MessageReceiver{
    @JmsListener(destination = "fromJudger")
    public void receiveQueue(Message text) throws JMSException {
        text.acknowledge();
        System.out.println("Consumer收到的消息为:" +  text);
//        System.out.println("Consumer收到的消息1为:" + text.getString("event"));

    }
}

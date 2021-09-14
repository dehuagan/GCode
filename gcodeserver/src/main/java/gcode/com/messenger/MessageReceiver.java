package gcode.com.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

/**
 * @see MessageReceiver
 *
 * @date 2021/5/19 下午10:22
 * @author gandehua
 */
@Component
public class MessageReceiver{
    @Autowired
    private ApplicationEventListener eventPublisher;

    @JmsListener(destination = "fromJudger")
    public void receiveQueue(MapMessage msg) throws JMSException {
//        text.acknowledge();
        Map<String,Object> result = (Map)msg.getObjectProperty("message");
        String event = String.valueOf(result.get("event"));
        if(event.equals("ErrorOccurred")){
            System.out.println("ErrorOccurred");
        }else if(event.equals("Wrong Answer")){
            System.out.println("Wrong Answer");
        }else if(event.equals("Accepted")){
            System.out.println("Accepted");
        }
//        for(String key:result.keySet()){
//
//        }
//        System.out.println("Consumer收到的消息为:" +  text);
//        System.out.println("Consumer收到的消息1为:" + text.getString("event"));

    }
}

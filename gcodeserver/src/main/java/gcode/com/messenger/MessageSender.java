package gcode.com.messenger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @Classname MessageSender
 * @Description TODO
 * @Date 2021/2/16 下午11:26
 * @Created by gandehua
 */
@Component
public class MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;
    public void sendMessage(final Map<String,Object> mapMessage){
        long submissionId = (Long)mapMessage.get("submissionId");
        jmsTemplate.convertAndSend(mapMessage);
    }
}

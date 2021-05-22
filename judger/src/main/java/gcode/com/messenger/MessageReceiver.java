package gcode.com.messenger;

import gcode.com.application.ApplicationDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.io.IOException;

/**
 * @Classname MessageReceiver
 * @Description TODO
 * @Date 2021/5/19 下午11:04
 * @Created by gandehua
 */
@Component
public class MessageReceiver{
    @Autowired
    private ApplicationDispatcher dispatcher;
    @JmsListener(destination = "submission")
    public void receiveQueue(MapMessage msg) throws JMSException, InterruptedException, IOException {
//        msg.acknowledge();
        System.out.println("Consumer收到的消息为:" + msg.getObjectProperty("submissionId"));
        System.out.println("Consumer收到的消息1为:" + msg.getObjectProperty("event"));
        String event = String.valueOf(msg.getObjectProperty("event"));
        if(event.equals("SubmissionCreated")){
            newSubmissionHandler(msg);
        }
    }

    private void newSubmissionHandler(MapMessage mapMessage) throws JMSException, InterruptedException, IOException {
        long submissionId = (long)mapMessage.getObjectProperty("submissionId");
        dispatcher.onSubmissionCreated(submissionId);
    }


}

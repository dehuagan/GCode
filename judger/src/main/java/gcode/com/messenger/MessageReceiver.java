package gcode.com.messenger;

import gcode.com.application.ApplicationDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;

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
    public void receiveQueue(MapMessage msg) throws JMSException, InterruptedException {
        msg.acknowledge();
        System.out.println("Consumer收到的消息为:" + msg.getLong("submissionId"));
        System.out.println("Consumer收到的消息1为:" + msg.getString("event"));
        String event = msg.getString("event");
        if(event.equals("SubmissionCreated")){
            newSubmissionHandler(msg);
        }
    }

    private void newSubmissionHandler(MapMessage mapMessage) throws JMSException, InterruptedException {
        long submissionId = mapMessage.getLong("submissionId");
        dispatcher.onSubmissionCreated(submissionId);
    }


}

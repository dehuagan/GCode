package gcode.com.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
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
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;
    public void sendMessage(String msg){
//        System.out.println("submissionid-----> "+mapMessage.get("submissionId"));
//        long submissionId = (Long)mapMessage.get("submissionId");

        //通过事务来确保消息传递的原子性，确保全部消息到达broker
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        Session session = null;
        try{
            Connection connection = connectionFactory.createConnection();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(session.createQueue("fromJudger"));
            TextMessage textMessage = session.createTextMessage(msg);
            producer.send(textMessage);
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
            try{
                session.rollback();
            } catch (JMSException jmsException) {
                jmsException.printStackTrace();
            }
        }
//        jmsTemplate.convertAndSend("fromJudger",msg);
    }

    public void sendMessage(Map msg){
//        System.out.println("submissionid-----> "+mapMessage.get("submissionId"));
//        long submissionId = (Long)mapMessage.get("submissionId");
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        Session session = null;
        try{
            Connection connection = connectionFactory.createConnection();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(session.createQueue("fromJudger"));
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setObjectProperty("message",msg);
            producer.send(mapMessage);
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
            try{
                session.rollback();
            } catch (JMSException jmsException) {
                jmsException.printStackTrace();
            }
        }
    }
}

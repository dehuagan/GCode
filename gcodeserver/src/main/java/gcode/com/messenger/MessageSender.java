package gcode.com.messenger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;


/**
 * @see MessageSender
 * 
 * @date 2021/2/16 下午11:26
 * @author gandehua
 */
@Component
public class MessageSender {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;
    public void sendMessage(final Map<String,Object> mapMessage){
        System.out.println("submissionid-----> "+mapMessage.get("submissionId"));
        long submissionId = (Long)mapMessage.get("submissionId");
//        jmsTemplate.convertAndSend("submission",mapMessage);
        //通过事务来确保消息传递的原子性，确保全部消息到达broker
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        Session session = null;
        try{
            Connection connection = connectionFactory.createConnection();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(session.createQueue("submission"));
//            TextMessage textMessage = session.createMessage(mapMessage);
            MapMessage message = session.createMapMessage();
            for(String key:mapMessage.keySet()){
                message.setObjectProperty(key, mapMessage.get(key));
            }

            producer.send(message);
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

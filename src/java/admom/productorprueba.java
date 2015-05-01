/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author felipelondono
 */
public class productorprueba {
    public void product(String msg, String topic,String user){

        try {

        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = 
                connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(topic);
        
        MessageProducer productor = session.createProducer(destination);

        String mensaje = user +": " + msg;
        TextMessage message = session.createTextMessage(mensaje);
        productor.send(message, DeliveryMode.PERSISTENT, 4,3600000);//1 hour
        session.close();
        connection.close();
        
        
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
        }
			
    }
}

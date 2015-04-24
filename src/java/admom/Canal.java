/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author felipelondono
 */
public class Canal {
    int id;
    String name;
    
    Canal(int id,String name){
        this.id = id;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
   
    public void createCanal(){
        try {
            ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            session.createTopic(name);
            session.close();
            connection.close();
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Canal " + name + " Creado");
    }    
    
    
}

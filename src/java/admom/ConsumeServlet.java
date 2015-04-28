/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.io.IOException;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author felipelondono
 */
@WebServlet(name = "ConsumeServlet", urlPatterns = {"/ConsumeServlet"})
public class ConsumeServlet extends HttpServlet {
    boolean flag = true;
    TopicConnection conn;
    TopicSession sess;
    TopicSubscriber sub;
    String mensajito;
   void suscriptor(String subscribername, String channel){
       try{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                            "tcp://localhost:61616");
        conn = connectionFactory.createTopicConnection();       
            conn.setClientID("elID");
           sess = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = sess.createTopic(channel);
            sub = sess.createDurableSubscriber((Topic) destination, subscribername);
             conn.start();
        }catch(JMSException ex){
            mensajito = ex.getMessage();
        }
   }
   
   MessageListener listener = new MessageListener() {
       // @Override
       
        public void onMessage(Message msg) {
            if (msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) msg;
                String text = null;
                try {
                    text = textMessage.getText();    
                } catch (JMSException e) {
                    text = "No han llegado";
                }
                mensajito = text;    
            }
        }
    };
   
    String getMessages(String subscribername, String channel){
        if(flag){
            suscriptor(subscribername,channel);
            flag = false;
        }
        try {  
            sub.setMessageListener(listener);
        } catch (JMSException ex) {
          mensajito = ex.getMessage();
        }
        return mensajito;
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
        HttpSession session = request.getSession(true);
        String mensajeaenviar = 
                getMessages(session.getAttribute("username").toString(),
                session.getAttribute("channel").toString());
        if(mensajeaenviar != null){
            response.getWriter().write(mensajeaenviar);   
        }else{
            response.getWriter().write("No hay mensajes disponibles");
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   }
         


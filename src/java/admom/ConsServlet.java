/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author felipelondono
 */
@WebServlet(name = "ConsServlet", urlPatterns = {"/ConsServlet"})
public class ConsServlet extends HttpServlet {

        boolean flag = true;
    TopicConnection conn;
    TopicSession sess;
    TopicSubscriber sub;
    String mensajito;

    
   void suscriptor(String subscribername, String channel){
       System.out.println("CONEXION");
               try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                                "tcp://localhost:61616");
            conn = connectionFactory.createTopicConnection();  
                conn.setClientID(subscribername);
                    sess = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
                    Destination destination = sess.createTopic(channel);
                    sub = sess.createDurableSubscriber((Topic) destination, subscribername);

             conn.start();
        }catch(JMSException ex){
            System.out.println(ex.getMessage());
        }
   }
   
   /*
   MessageListener listener = new MessageListener() {
       // @Override
        public void onMessage(Message msg) {
            if(sub != null){
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
        }
    };
    * 
    */
   
    String getMessages(String subscribername, String channel){
        if(flag){
            suscriptor(subscribername,channel);
            flag = false;
        }
        try {  
            //sub.setMessageListener(listener);
            Message mess = sub.receive(1000);
            if(mess != null){
                TextMessage textMessage = (TextMessage) mess;
                mensajito = textMessage.getText();
            }
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
        }
        return mensajito;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            try {
                System.out.println("Entra al coso");
                sub.close();
                sess.close();
                conn.close();
                
                mensajito = null;
                flag = true;
            } catch (JMSException ex) {
                System.out.println(ex.getMessage());
            }
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            response.sendRedirect("User.jsp");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                Cookie[] cookies = request.getCookies();
        Cookie usercookie = null;
        Cookie channelcookie = null;
        for(int i=0;i<cookies.length;i++){            
            if(cookies[i].getName().toString().equals("username")){
                usercookie = cookies[i];
            }else{
                if(cookies[i].getName().toString().equals("channelcookie")){
                    channelcookie = cookies[i];
                }
            }
        }
        String mensajeaenviar = 
                getMessages(usercookie.getValue().toString(),
                channelcookie.getValue().toString());
        System.out.println(mensajeaenviar);
        if(mensajeaenviar != null && !"".equals(mensajeaenviar)){
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
        processRequest(request, response);
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

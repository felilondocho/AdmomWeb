/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author felipelondono
 */
@WebServlet(name = "ConsumerTest", urlPatterns = {"/ConsumerTest"})
public class ConsumerTest extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    ArrayList<String> messages = new ArrayList();
    boolean flag = true;
    TopicConnection conn;
    TopicSubscriber sub;

void getMessage(){
    try {

        if(flag){
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                            "tcp://localhost:61616");
                conn = connectionFactory.createTopicConnection();
                conn.setClientID("myClientID");
            
                flag = false;
        }
                
            TopicSession sess = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = sess.createTopic("Deportes");
            sub = sess.createDurableSubscriber((Topic) destination, "Felipe");
            String mensajito = null;
            
            conn.start();
            
               
            Message mess = sub.receive(1000);
            TextMessage textMessage = (TextMessage) mess;
            mensajito = textMessage.getText();
                messages.add(mensajito);
            conn.close();        
            } catch (JMSException ex) {
                messages.add(ex.getMessage());
            }
}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setIntHeader("Refresh", 2);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
                out.println("<html>");
                out.println("<meta http-equiv='refresh' content='2' />");
                out.println("<head>");
                out.println("<title>Servlet ConsumeServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                
                getMessage();
                for(int i=0;i<messages.size();i++){
                    out.println("<h2>"+messages.get(i) +"</h2>");
                }
               
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
        processRequest(request, response);
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

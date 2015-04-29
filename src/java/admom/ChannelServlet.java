/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author felipelondono
 */
@WebServlet(name = "ChannelServlet", urlPatterns = {"/ChannelServlet"})
public class ChannelServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        DBConnect db = new DBConnect();
        Enumeration enumeration = request.getParameterNames();
        String channel = null;
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();  
            channel = request.getParameter(parameterName);
        }
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for(int i=0;i<cookies.length;i++){            
            if(cookies[i].getName().toString().equals("username")){
                cookie = cookies[i];
            }
        }
        boolean dio = db.subscribe(channel, cookie.getValue().toString());

        //ConsumeServlet con = new ConsumeServlet();
        //con.suscriptor(cookie.getValue().toString(), channel);
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Channel</title>");            
            out.println("</head>");
            out.println("<body>");
            if(dio){
                out.println("<h1>You Have subscribed to: "+channel+"</h1>");
            }else{
                out.println("<h1>Not Subscribed</h1>");
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

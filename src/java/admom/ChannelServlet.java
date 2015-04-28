/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        boolean dio = db.subscribe(channel, session.getAttribute("username").toString());
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
            out.println("<a href='http://localhost:8080/AdmomWeb/User.jsp'>Return</a>");
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

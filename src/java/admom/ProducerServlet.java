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
@WebServlet(name = "ProducerServlet", urlPatterns = {"/ProducerServlet"})
public class ProducerServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Enumeration enumeration = request.getParameterNames();
        String channel = null;
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            if(!(parameterName.equals("InputMessage"))){
                channel = request.getParameter(parameterName);
                session.setAttribute("channel", channel);
            }
        }                
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {    
            DBConnect db = new DBConnect();
            boolean a = db.CreateChannel(channel);
            String msg = request.getParameter("InputMessage");
            productorprueba prueba = new productorprueba();
            prueba.product(msg,channel);      
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Produce</title>");            
            out.println("</head>");
            out.println("<body>");
            if(a){
                out.println("<h1> The message: " + msg + " has been sent to:  " + channel +"</h1>");
            }else{
                out.println("<h1>No dio</h1>");
            } 
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Produce</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>neh</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        finally {            
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

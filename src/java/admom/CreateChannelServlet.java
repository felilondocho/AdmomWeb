/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author felipelondono
 */
@WebServlet(name = "CreateChannelServlet", urlPatterns = {"/CreateChannelServlet"})
public class CreateChannelServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            DBConnect db = new DBConnect();
            String channelname = request.getParameter("channelinput");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create</title>");            
            out.println("</head>");
            out.println("<body>");
            if(db.CreateChannel(channelname)){
                out.println("<h1>Channel " + channelname + " created successfully</h1>");
            }else{
                out.println("<h1>Not created</h1>");
            }  
            out.println("<form action='ChannelMenu.jsp'>");
            out.println("<input type='submit' value='GO BACK'>");
            out.println("</form>");
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

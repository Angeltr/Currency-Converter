/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angel
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelpServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        
        String redirectLink = (String) getServletContext().getAttribute("redirectLink");
        
        rsp.sendRedirect(redirectLink);
    }
    
}

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

public class CodeServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
    
        rsp.setContentType("application/x-zip");
        
        ServletContext ctx = getServletContext();
        InputStream is = ctx.getResourceAsStream("/Code.zip");
        int read = 0;
        byte[] bytes = new byte[1024];
    

        OutputStream os = rsp.getOutputStream();
        while ((read = is.read(bytes)) != -1) {
        os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
   }
    
}

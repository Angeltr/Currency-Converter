/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angel
 */
import javax.servlet.*;

public class CurrencyConverterContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        
        ServletContext sc = event.getServletContext();
        
        String redirectLink = sc.getInitParameter("redirect");
        
        sc.setAttribute("redirectLink", redirectLink);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

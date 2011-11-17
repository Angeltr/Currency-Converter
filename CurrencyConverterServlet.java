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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class CurrencyConverterServlet extends HttpServlet {
    
    public static String sendGetRequest(String from,String to,String amount) throws IOException {
        String result = null;

        String urlStr = "http://www.google.com/ig/calculator?hl=en&q="+amount+from+"=?"+to;

        try {

        URL url = new URL(urlStr);
        URLConnection conn = url.openConnection ();
        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        result = sb.toString();
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


   public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
	rsp.setContentType("text/html");
	PrintWriter out = rsp.getWriter();
        
        //Get parameters
	String from = req.getParameter("from");
        String to = req.getParameter("to");
        String amount = req.getParameter("amount");
        //Get the result
        String result = sendGetRequest(from,to,amount);
        
        //Take the information we need of the result String
        int k = result.indexOf("rhs");
        int l=0,m=0;
        String lhs = null; //this string keeps the lhs from google's response(only what appears inside the "")
        String rhs = null;  //this string keeps the rhs from google's response(only what appears inside the "")
        
        for(int i=7;i<result.length();i++) {
            if(result.charAt(i)!='"') m++;
            if(result.charAt(i)=='"') break;
        }
        lhs = result.substring(7, 7+m);
        lhs = lhs.replace('�', ',');
        
        for(int i=k+6;i<result.length();i++) {
            if(result.charAt(i)!='"') l++;
            if(result.charAt(i)=='"') break;
        }
      
        rhs = result.substring(k+6, k+6+l);
        rhs = rhs.replace('�', ',');
        
        //Generate the HTML
        out.println("<html>");
        out.println("<head><title>Output</title></head>");
        out.println("<body style=\"background-color:DarkCyan;\">");
        out.println("<h1 style=\"text-align:center;color:black;\"><b>Output</b></h1><br />");
        out.println("<p1 style=\"text-align:center;color:black;\">"+lhs+" "+" is "+rhs+"</p1></br>");
        out.println("</body></html>");
    }
   
}
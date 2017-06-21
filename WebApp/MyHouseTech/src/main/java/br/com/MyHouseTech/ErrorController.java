package br.com.MyHouseTech;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
    public void doPost(HttpServletRequest req, HttpServletResponse res)    throws ServletException, IOException{
    	Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
    	RequestDispatcher requestDispatcher;
    	req.setAttribute("errorType", (String) req.getAttribute("javax.servlet.error.message"));
        req.setAttribute("statusCode", statusCode);
 
        if(statusCode == 404)
        	requestDispatcher = req.getRequestDispatcher("/404.jsp");
        else
        	requestDispatcher = req.getRequestDispatcher("/error.jsp");
        requestDispatcher.forward(req, res);
    }
 
    public void doGet(HttpServletRequest req, HttpServletResponse res)    throws ServletException, IOException{
        doPost(req, res);
    } 
}

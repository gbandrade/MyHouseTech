package br.com.MyHouseTech;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.MyHouseTech.componente.Erro;
import br.com.MyHouseTech.negocio.UsuarioNegocio;

public class Logout  extends HttpServlet{
	
	private ServletConfig config;
	private HttpSession session;
	
	public void init(ServletConfig config) throws ServletException{
		this.config=config;
	}
	
	
	//------------------------------------
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getContentType();
	}
	
	//------------------------------------
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");  
		PrintWriter out = resp.getWriter();  
		HttpSession session = req.getSession();  
		session.invalidate();  
		resp.sendRedirect("logar.jsp");
		out.close();
	}

}

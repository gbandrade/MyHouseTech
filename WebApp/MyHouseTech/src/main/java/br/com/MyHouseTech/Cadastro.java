package br.com.MyHouseTech;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.MyHouseTech.model.Usuario;
import br.com.MyHouseTech.negocio.UsuarioNegocio;

public class Cadastro extends HttpServlet{
	
	//============================
	// 	Declarations
	//============================
	
	private final static Logger loggerError = Logger.getLogger("errorLogger");
	private final static Logger loggerInfo  = Logger.getLogger("infoLogger");
	
	private static final String PerfilCliente = "a563d3e1068211e7bb070050568d4390";
	private ServletConfig config;
	private HttpSession session;
	
	//============================
	// 	Methods
	//============================
	
	public void init(ServletConfig config) throws ServletException{
		this.config=config;
	}
	
	//------------------------------------
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
	        Usuario objUsuario = new Usuario(	null, 
	        									request.getParameter("nome") + " " + request.getParameter("sobrenome"), 
	        									request.getParameter("email"),
	        									request.getParameter("login"),
	        									request.getParameter("senha"), 
	        									request.getParameter("cpf").replaceAll("[^a-zZ-Z1-9]", ""),
	        									request.getParameter("telefone").replaceAll("[^a-zZ-Z1-9]", ""),
	        									request.getParameter("endereco"),
	        									PerfilCliente);
	        if(new UsuarioNegocio().insereUsuario(objUsuario)) {
	        	loggerInfo.info("Usuário " + objUsuario.getNome() + " cadastrado com sucesso!");
	        	sendMessage(response, "Cadastro efetuado com sucesso!");
	        }
	        else 
	        	throw new Exception("Ocorreu um erro durante o processo de cadsatro!");
		}
		catch (Exception e) {
			StringWriter sw = new StringWriter();
		    e.printStackTrace(new PrintWriter(sw));
			loggerError.error(sw.toString());
			response.sendError(response.SC_INTERNAL_SERVER_ERROR, sw.toString());
		}
	}
	
	//------------------------------------
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	//------------------------------------
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			processRequest(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------
	
	private void sendMessage(HttpServletResponse response, String strMessage) throws Exception{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<script type=\"text/javascript\">");
		out.println("alert(\"" + strMessage + "\");");
		out.println("window.location='areaRestrita.jsp';");
		out.println("</script>");
	}

}

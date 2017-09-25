package br.com.MyHouseTech;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.MyHouseTech.componente.Erro;
import br.com.MyHouseTech.model.Usuario;
import br.com.MyHouseTech.negocio.UsuarioNegocio;

public class Login extends HttpServlet {
	
	//============================
	// 	Declarations
	//============================
	
	private final static Logger loggerError = Logger.getLogger("errorLogger");
	private final static Logger loggerInfo  = Logger.getLogger("infoLogger");
	
	private ServletConfig config;
	private HttpSession session;
	
	//============================
	// 	Methods
	//============================
	
	public void init(ServletConfig config) throws ServletException{
		this.config=config;
	}
	
	//------------------------------------
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Erro erros = new Erro();
		try {
			String login = request.getParameter("login");
	        String senha = request.getParameter("password");
	        
	        if ((login == null || login.isEmpty()) || senha == null || senha.isEmpty())
	        	sendMessage(response, "É necessário informar login e senha para logar.");
	        
	        if (!erros.isExisteErros()) {
	        	UsuarioNegocio objUsuNegocio = new UsuarioNegocio();
	        	if(objUsuNegocio.autentica(login, senha)) {
	        		session = request.getSession();
	        		session.setAttribute("userId", objUsuNegocio.getUsuario().get(0).getIdUsuario());
	        		configSession(objUsuNegocio.getUsuario().get(0), response);
	        		loggerInfo.info("Usuário " + objUsuNegocio.getUsuario().get(0).getNome() + " logado com sucesso!");
	        		response.sendRedirect("areaRestrita.jsp");
	        	}
	        	else 
	        		sendMessage(response, "Login ou senha invalida!");
	        }
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
	
	private void configSession(Usuario objusuario, HttpServletResponse response) {
		session.setAttribute("objUser", objusuario);
		session.setMaxInactiveInterval(60 * 15);
		Cookie userLogin = new Cookie("userLogin", objusuario.getLogin());
		Cookie userId = new Cookie("userId", objusuario.getIdUsuario());
		userLogin.setMaxAge(60 * 15);
		userId.setMaxAge(60 * 15);
		response.addCookie(userLogin);
		response.addCookie(userId);
	}
	
	//------------------------------------
	
	private void sendMessage(HttpServletResponse response, String strMessage) throws Exception{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<script type=\"text/javascript\">");
		out.println("alert(\"" + strMessage + "\");");
		out.println("window.location='logar.jsp';");
		out.println("</script>");
	}
}
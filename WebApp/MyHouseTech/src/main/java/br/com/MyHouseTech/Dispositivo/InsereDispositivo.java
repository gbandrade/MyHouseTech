package br.com.MyHouseTech.Dispositivo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.MyHouseTech.componente.Erro;
import br.com.MyHouseTech.model.Usuario;
import br.com.MyHouseTech.negocio.DispositivoNegocio;
import br.com.MyHouseTech.negocio.UsuarioNegocio;

public class InsereDispositivo extends HttpServlet {
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
			String key = request.getParameter("key");
	        
	        if (key == null || key.isEmpty())
	        	sendMessage(response, "Informe um valor válido para o campo Key.");
	        
	        
	        	DispositivoNegocio objDispNegocio = new DispositivoNegocio();
	        	if(objDispNegocio.EhKeyValida(key)) {
	        		session = request.getSession();
	        		Usuario objUsuario = (Usuario) session.getAttribute("objUser");
	        		if(objDispNegocio.vinculaDispositivoUsuario(key, objUsuario.getIdUsuario())) {
	        			sendMessage(response, "Dispositivo adicionado com sucesso!");
	        			response.sendRedirect("areaRestrita.jsp");
	        		}
	        		else 
	        			sendMessage(response, "Ocorreu um erro ao inserir o dispositívo, tente novamente!");
	        	}
	        	else 
	        		sendMessage(response, "Key invalida!");
	       
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
		out.println("window.location='areaRestrita.jsp';");
		out.println("</script>");
	}

}

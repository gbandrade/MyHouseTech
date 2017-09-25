package br.com.MyHouseTech.Dispositivo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.MyHouseTech.componente.Erro;
import br.com.MyHouseTech.model.Usuario;
import br.com.MyHouseTech.negocio.DispositivoNegocio;

public class AtualizaTemperatura extends HttpServlet{
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
		DispositivoNegocio objDispNegocio;
		int i = 0;
		try {
			String hidDisp = request.getParameter("hidDisp");
			int tempMin = Integer.valueOf(request.getParameter("tempMin"));
			int tempMax = Integer.valueOf(request.getParameter("tempMax"));
			objDispNegocio = new DispositivoNegocio();
			
			if(objDispNegocio.atualizaTemperatura(hidDisp, tempMin, tempMax))
				sendMessage(response, "Temperatura atualizada com sucesso!");
			else 
				sendMessage(response, "Ocorreu um erro no processo de atualização de temperatura, tente novamente!");
			
			//response.sendRedirect("areaRestrita.jsp");
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

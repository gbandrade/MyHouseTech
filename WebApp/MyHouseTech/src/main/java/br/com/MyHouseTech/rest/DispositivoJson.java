package br.com.MyHouseTech.rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.MyHouseTech.model.Dispositivo;
import br.com.MyHouseTech.negocio.DispositivoNegocio;

public class DispositivoJson extends HttpServlet{
	
	private final static Logger loggerError = Logger.getLogger("errorLogger");
	private static String[] astrMetodoValido = {"setDispositivo", "getDispositivosUsuario"};
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
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
	
	public void Executa(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strMetodo, strIdArduino, strIdUsuario, strIP, strCallback;
		JSONArray aobjJson; JSONObject objStatus, objRetorno;
		
		strMetodo = (request.getParameter("Method") != null ? request.getParameter("Method") : request.getParameter("method"));
		strIdArduino = (request.getParameter("IdArduino") != null ? request.getParameter("IdArduino") : request.getParameter("idarduino"));
		strIdUsuario = (request.getParameter("IdUsuario") != null ? request.getParameter("IdUsuario") : request.getParameter("idusuario"));
		strIP = (request.getParameter("Ip") != null ? request.getParameter("Ip") : request.getParameter("ip"));
		
		aobjJson = new JSONArray();
		objRetorno = new JSONObject(); 
		objStatus = new JSONObject();
		if (strMetodo == null)
		{
			loggerError.error("Parâmetro 'Method' nulo ou indefinido.");
			objStatus.put("message", "Parâmetro 'Method' nulo ou indefinido.");
			objStatus.put("result", "failure");
			objRetorno.put("Status", objStatus);
			objRetorno.writeJSONString(response.getWriter());
		}
		else if (!EhMetodoInvalido(strMetodo))
		{
			loggerError.error("Parâmetro 'Method' " + strMetodo + " inválido.");
			objStatus.put("result", "failure");
			objStatus.put("message", "Parâmetro 'Method' " + strMetodo + " inválido.");
			objRetorno.put("Status", objStatus);
			objRetorno.writeJSONString(response.getWriter());
		}
		else
		{
			if (strMetodo.equalsIgnoreCase("setDispositivo"))
				setDispositivo(response, new Dispositivo(null, strIdArduino, strIdUsuario, strIP));
			else if(strMetodo.equalsIgnoreCase("getDispositivosUsuario"))
				getDispositivosUsuario(response, strIdUsuario);
		}
	}
	
	//------------------------------------------------
	
	@SuppressWarnings("unchecked")
	private void setDispositivo(HttpServletResponse response, Dispositivo objDispositivo) throws SQLException, IOException {
		JSONArray aobjJson; JSONObject objStatus, objRetorno, objResultado;
		objRetorno = new JSONObject();
		objStatus = new JSONObject();
		try
		{
			DispositivoNegocio objDispNegocio = new DispositivoNegocio();
			objDispNegocio.Inicializa();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			if(objDispNegocio.insereDispositivo(objDispositivo))
				objStatus.put("result", "success");
			else {
				loggerError.error("O correu um erro no processo de inserção do dispositivo: " + objDispositivo.getIdArduino() + " .");
				objStatus.put("result", "failure");
				objStatus.put("message", "O correu um erro no processo de inserção do dispositivo: " + objDispositivo.getIdArduino() + " .");
			}
		}
		catch (Exception e){
			loggerError.error(e.getMessage());
			objStatus.put("result", "failure");
			objStatus.put("message", e.getMessage());
		}
		objRetorno.put("Status", objStatus);
		objRetorno.writeJSONString(response.getWriter());

	}
	
	//------------------------------------------------
	
	private void getDispositivosUsuario(HttpServletResponse response, String strIdusuario) throws SQLException, IOException {
		JSONArray aobjJson; JSONObject objStatus, objRetorno, objResultado;
		objRetorno = new JSONObject();
		objStatus = new JSONObject();
		try
		{
			DispositivoNegocio objDispNegocio = new DispositivoNegocio();
			objDispNegocio.Inicializa();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			if(objDispNegocio.retListaDispositivoByIdUsuario(strIdusuario)) {
				aobjJson = new JSONArray();
				objStatus.put("result", "success");
				objStatus.put("rows", objDispNegocio.getDispositivo().size());
				for(Dispositivo objDispositovo : objDispNegocio.getDispositivo()){
					objResultado = new JSONObject();
					objResultado.put("IdDispositivo", objDispositovo.getIdDispositivo());
					objResultado.put("IdArduino", objDispositovo.getIdArduino());
					objResultado.put("IdUsuario", objDispositovo.getIdUsuario());
					objResultado.put("Ip", objDispositovo.getIp());
					aobjJson.add(objResultado);
				}
				objRetorno.put("Dispositivos", aobjJson);
			}
				
			else {
				loggerError.error("O correu um erro na obtenção da lista de dispositivos.");
				objStatus.put("result", "failure");
				objStatus.put("message", "O correu um erro na obtenção da lista de dispositivos.");
			}
		}
		catch (Exception e){
			loggerError.error(e.getMessage());
			objStatus.put("result", "failure");
			objStatus.put("message", e.getMessage());
		}
		objRetorno.put("Status", objStatus);
		objRetorno.writeJSONString(response.getWriter());
	}
	
	//------------------------------------------------
	
	private boolean EhMetodoInvalido(String strMetodo)
	{
		for (int i = 0; i < astrMetodoValido.length; i++){
			if (strMetodo.equalsIgnoreCase(astrMetodoValido[i]))
				return true;
		}
		return false;
	}
}

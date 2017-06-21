package br.com.MyHouseTech.negocio;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GerenciaJson extends HttpServlet{
	
	private final static Logger loggerError = Logger.getLogger("errorLogger");
	private static String[] astrMetodoValido = {"getUsuarios"};
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		UsuarioNegocio objUsuNegocio = new UsuarioNegocio();
		objUsuNegocio.Inicializa();
		objUsuNegocio.retListaUsuarios();
		String str = "";
		for(int i = 0; i <= objUsuNegocio.getUsuario().size(); i++){
			str += objUsuNegocio.getUsuario().get(i).getNome() + "\n";
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
	
	public void Executa(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strMetodo, strCallback;
		JSONArray aobjJson; JSONObject objStatus, objRetorno;
		
		strMetodo = (request.getParameter("Method") != null ? request.getParameter("Method") : request.getParameter("method"));
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
			if (strMetodo.equalsIgnoreCase("getUsuarios"))
				getUsuarios(response);
		}
	}
	
	//------------------------------------------------
	
	@SuppressWarnings("unchecked")
	private void getUsuarios(HttpServletResponse response) throws SQLException, IOException {
		JSONArray aobjJson; JSONObject objStatus, objRetorno, objResultado;
		objRetorno = new JSONObject();
		objStatus = new JSONObject();
		try
		{
			UsuarioNegocio objUsuNegocio = new UsuarioNegocio();
			objUsuNegocio.Inicializa();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			if(objUsuNegocio.retListaUsuarios())
			{	
				aobjJson = new JSONArray();
				objStatus.put("result", "success");
				objStatus.put("rows", objUsuNegocio.getUsuario().size());
				for(int i = 0; i < objUsuNegocio.getUsuario().size(); i++) {
					objResultado = new JSONObject();
					objResultado.put("idUsuario", objUsuNegocio.getUsuario().get(i).getIdUsuario());
					objResultado.put("nome", objUsuNegocio.getUsuario().get(i).getNome());
					objResultado.put("login", objUsuNegocio.getUsuario().get(i).getLogin());
					objResultado.put("email", objUsuNegocio.getUsuario().get(i).getEmail());
					objResultado.put("cpf", objUsuNegocio.getUsuario().get(i).getCpf());
					objResultado.put("endereco", objUsuNegocio.getUsuario().get(i).getEndereco());
					objResultado.put("telefone", objUsuNegocio.getUsuario().get(i).getTelefone());
					objResultado.put("idPerfil", objUsuNegocio.getUsuario().get(i).getIdPerfil());
					aobjJson.add(objResultado);
				}
				objRetorno.put("Resultado", aobjJson);
			}
			else {
				loggerError.error("O correu um erro no processo de obtenção de usuários.");
				objStatus.put("result", "failure");
				objStatus.put("message", "O correu um erro no processo de obtenção de usuários.");
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

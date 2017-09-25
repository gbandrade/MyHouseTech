package br.com.MyHouseTech.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import br.com.MyHouseTech.componente.Database;
import br.com.MyHouseTech.model.Dispositivo;
import br.com.MyHouseTech.model.Usuario;

public class DispositivoNegocio {
	
	//============================
	// 	Declarations
	//============================
	
	private final static Logger loggerError = Logger.getLogger("errorLogger");
	
	private ArrayList<Dispositivo> alobjDispositivo = new ArrayList<Dispositivo>();
	private boolean blnOK = false;
	private String strQuery = "";
	private Dispositivo objDispositivo;
	
	//============================
	// 	Properties
	//============================
	
	public ArrayList<Dispositivo> getDispositivo() {
		return alobjDispositivo;
	}
	
	//============================
	// 	Methods
	//============================

	public boolean insereDispositivo(Dispositivo objDispositivo) throws Exception {
		Connection cnn = null;
		PreparedStatement stmt = null;
		Inicializa();
		try{
			cnn = new Database().RetornaConnectionDoPool();
			stmt = cnn.prepareStatement("INSERT INTO autDispositivo (IdDispositivo, IdArduino, IdUsuario, IP) VALUES (REPLACE(UUID(),'-',''), ?, ?, ?)");
			stmt.setString(1, objDispositivo.getIdArduino());
			stmt.setString(2, objDispositivo.getIdUsuario());
			stmt.setString(3, objDispositivo.getIp());
			stmt.execute();
			blnOK = true;
		}
		catch (Exception e) {
			loggerError.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		finally {
			if(cnn != null)
				cnn.close();
			if(stmt != null)
				stmt.close();
		}
		return blnOK;
	}
	
	//----------------------------
	
	public boolean atualizaTemperatura(String strIdDispositivo, int intTempMin, int intTempMax) throws Exception {
		Connection cnn = null;
		PreparedStatement stmt = null;
		Inicializa();
		try{
			cnn = new Database().RetornaConnectionDoPool();
			strQuery  = "UPDATE autDispositivo ";
			strQuery += "SET	tempMin = ?, ";
			strQuery += "		tempMax = ? ";
			strQuery += "WHERE	IdDispositivo = ?";
			stmt = cnn.prepareStatement(strQuery);
			stmt.setInt(1, intTempMin);
			stmt.setInt(2, intTempMax);
			stmt.setString(3, strIdDispositivo);
			stmt.execute();
			blnOK = true;
		}
		catch (Exception e) {
			loggerError.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		finally {
			if(cnn != null)
				cnn.close();
			if(stmt != null)
				stmt.close();
		}
		return blnOK;
		
	}

	//----------------------------	
	
	public boolean retListaDispositivoByIdUsuario(String strIdUsuario) throws SQLException{
		Connection cnn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Inicializa();
		try{
			cnn = new Database().RetornaConnectionDoPool();
			strQuery  = "SELECT IdDispositivo,	IdArduino, 		IdUsuario, 		IP, tempMin, tempMax ";
			strQuery += "FROM	autDispositivo ";
			strQuery += "WHERE IdUsuario = ?";
			stmt = cnn.prepareStatement(strQuery);
			stmt.setString(1, strIdUsuario);
			rs = stmt.executeQuery();
			while(rs.next()) {
				objDispositivo = new Dispositivo();
				objDispositivo.setIdDispositivo(rs.getString("IdDispositivo"));
				objDispositivo.setIdArduino(rs.getString("IdArduino"));
				objDispositivo.setIdUsuario(rs.getString("IdUsuario"));
				objDispositivo.setIp(rs.getString("IP"));
				int tempMin = rs.getInt("tempMin");
				objDispositivo.setTempMin(rs.wasNull() ? null : tempMin);
				int tempMax = rs.getInt("tempMax");
				objDispositivo.setTempMax(rs.wasNull() ? null : tempMax);
				alobjDispositivo.add(objDispositivo);
			}
			blnOK = true;
		}
		catch (SQLException e) {
			loggerError.error(e.getMessage());
			throw new SQLException(e.getMessage());
		}
		finally {
			if(cnn != null)
				cnn.close();
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return blnOK;
	}
	
	//----------------------------
	
	public boolean EhKeyValida(String strKey) throws Exception{
		Connection cnn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Inicializa();
		try{
			cnn = new Database().RetornaConnectionDoPool();
			strQuery  = "SELECT IdDispositivo ";
			strQuery += "FROM	autDispositivo ";
			strQuery += "WHERE	`Key` = ?";
			stmt = cnn.prepareStatement(strQuery);
			stmt.setString(1, strKey);
			rs = stmt.executeQuery();
			if(rs.next()) 
				blnOK = true;
		}
		catch (SQLException e) {
			loggerError.error(e.getMessage());
			throw new SQLException(e.getMessage());
		}
		finally {
			if(cnn != null)
				cnn.close();
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return blnOK;
	}
	
	//----------------------------
	
	public boolean vinculaDispositivoUsuario(String strKey, String strIdUsuario)  throws Exception {
		Connection cnn = null; 
		PreparedStatement stmt = null; 
		Inicializa();
		try{
			cnn = new Database().RetornaConnectionDoPool();
			strQuery  = "UPDATE autDispositivo ";
			strQuery += "SET	IdUsuario = ? ";
			strQuery += "WHERE	`Key` = ?";
			stmt = cnn.prepareStatement(strQuery);
			stmt.setString(1, strIdUsuario);
			stmt.setString(2, strKey);
			stmt.execute();
			blnOK = true;
		}
		catch (SQLException e) {
			loggerError.error(e.getMessage());
			throw new SQLException(e.getMessage());
		}
		finally {
			if(cnn != null)
				cnn.close();
			if(stmt != null)
				stmt.close();
		}
		return blnOK;
	}

	//----------------------------
	
	public void Inicializa(){
		blnOK = false;
		alobjDispositivo = new ArrayList<Dispositivo>();
		objDispositivo = new Dispositivo();
		strQuery = "";
	}
}

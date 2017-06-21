package br.com.MyHouseTech.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import br.com.MyHouseTech.componente.Database;
import br.com.MyHouseTech.model.Usuario;

public class UsuarioNegocio {
	
	//============================
	// 	Declarations
	//============================
	
	private final static Logger loggerError = Logger.getLogger("errorLogger");
	
	private ArrayList<Usuario> alobjUsuario = new ArrayList<Usuario>();
	private boolean blnOK = false;
	private Usuario objUsuario;
	
	//============================
	// 	Properties
	//============================
	
	public ArrayList<Usuario> getUsuario() {
		return alobjUsuario;
	}
	
	//============================
	// 	Methods
	//============================
	
	/**
	 * @param login
	 * @param senha
	 * @return  boolean
	 * @throws SQLException
	 */
	
	public boolean autentica(String login, String senha) throws SQLException {
		Connection cnn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			cnn = new Database().RetornaConnectionDoPool();
			stmt = cnn.prepareStatement("SELECT * FROM autUsuario WHERE login = ? and senha = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			if(rs.next()) {
				objUsuario = new Usuario();
				objUsuario.setIdUsuario(rs.getString("idUsuario"));
				objUsuario.setNome(rs.getString("nome"));
				objUsuario.setEmail(rs.getString("email"));
				objUsuario.setLogin(rs.getString("login"));
				objUsuario.setCpf(rs.getString("cpf"));
				objUsuario.setTelefone(rs.getString("telefone"));
				objUsuario.setEndereco(rs.getString("endereco"));
				objUsuario.setIdPerfil(rs.getString("idPerfil"));
				alobjUsuario.add(objUsuario);
				blnOK = true;
			}
			else 
				blnOK = false;
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
	
	//--------------------------------------------
	
	public boolean insereUsuario(Usuario objUsuario) throws SQLException{
		Connection cnn = null;
		PreparedStatement stmt = null;
		try{
			cnn = new Database().RetornaConnectionDoPool();
			stmt = cnn.prepareStatement("INSERT INTO autUsuario VALUES (REPLACE(UUID(),'-',''), ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, objUsuario.getNome());
			stmt.setString(2, objUsuario.getEmail());
			stmt.setString(3, objUsuario.getLogin());
			stmt.setString(4, objUsuario.getSenha());
			stmt.setString(5, objUsuario.getCpf());
			stmt.setString(6, objUsuario.getTelefone());
			stmt.setString(7, objUsuario.getEndereco());
			stmt.setString(8, objUsuario.getIdPerfil());
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
	
	//--------------------------------------------
	
	public boolean retListaUsuarios() throws SQLException{
		Connection cnn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String strQuery = "";
		try{
			cnn = new Database().RetornaConnectionDoPool();
			strQuery  = "SELECT idUsuario,	nome, 		email, 		login, 	senha, ";
			strQuery += " 		cpf, 		telefone, 	endereco, 	idPerfil ";
			strQuery += "FROM	autUsuario ";
			stmt = cnn.prepareStatement(strQuery);
			rs = stmt.executeQuery();
			while(rs.next()) {
				objUsuario = new Usuario();
				objUsuario.setIdUsuario(rs.getString("idUsuario"));
				objUsuario.setNome(rs.getString("nome"));
				objUsuario.setEmail(rs.getString("email"));
				objUsuario.setLogin(rs.getString("login"));
				objUsuario.setCpf(rs.getString("cpf"));
				objUsuario.setTelefone(rs.getString("telefone"));
				objUsuario.setEndereco(rs.getString("endereco"));
				objUsuario.setIdPerfil(rs.getString("idPerfil"));
				alobjUsuario.add(objUsuario);
				blnOK = true;
			}
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
	
	//--------------------------------------------
	
	public void Inicializa(){
		blnOK = false;
		alobjUsuario = new ArrayList<Usuario>();
		objUsuario = new Usuario();
	}
}

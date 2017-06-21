package br.com.MyHouseTech.componente;

import java.sql.*;
import javax.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Database
{
	/**
	 * Busca uma conexão no Pool e retorna. 
	 * @return
	 */
	public static Connection RetornaConnectionDoPool()
	{
		String strJNDIName = "jdbc/myhousetech";
		DataSource objDataSource;
		Connection objConn = null;
		
		try 
		{
			objDataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + strJNDIName);
			objConn = objDataSource.getConnection();
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return objConn;
	}
}
	
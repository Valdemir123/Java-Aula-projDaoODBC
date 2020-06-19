package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.util.Properties;

public class DB {
	
	private static Connection _Conn = null;
	
	public static Connection getConnection() {
		if (_Conn == null) {
			try {
			Properties _props = loadProperties();
			
			String _url = _props.getProperty("dburl");
			_Conn = DriverManager.getConnection(_url, _props);
			} 
			catch (SQLException e){
				throw new dbException(e.getMessage());
			}
		}
		return _Conn;
	}
	
	public static void closeConnection() {
		if (_Conn != null) {
			try {
				_Conn.close();
			}
			catch (SQLException e){
				throw new dbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")) {
			Properties _props = new Properties();
			
			_props.load(fs);
			return _props;
		}
		catch (IOException e) {
			throw new dbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement _St) {
		if (_St != null) {
			try {
				_St.close();
			} 
			catch (SQLException e) {
				throw new dbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet _Rs) {
		if (_Rs != null) {
			try {
				_Rs.close();
			} 
			catch (SQLException e) {
				throw new dbException(e.getMessage());
			}
		}
	}
}

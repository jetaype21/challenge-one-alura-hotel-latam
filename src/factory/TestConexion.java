package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
	public static void main(String[] args) throws SQLException {
		ConexionBD con = new ConexionBD();
		Connection connection = con.conectarBD();
		
		System.out.println("Connect succsesfull");
		connection.close();
		
		System.out.println("Close BD");
	}
}

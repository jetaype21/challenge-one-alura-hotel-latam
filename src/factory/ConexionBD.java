package factory;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionBD {
	public DataSource dataSource;
	
	public ConexionBD () {
		ComboPooledDataSource comboPool = new ComboPooledDataSource();
		comboPool.setJdbcUrl("jdbc:mysql://localhost/hotel_alura_pe?useTimezone=true&serverTimezone=UTC");
	    comboPool.setUser("root");
		comboPool.setPassword("");
		
		this.dataSource = comboPool; 
	}
	
	public Connection conectarBD ( ) {
		try {
			return this.dataSource.getConnection();
		} catch (Exception e) {
			System.out.println("Hubo un error");
			throw new RuntimeException(e);
		}
	}
}

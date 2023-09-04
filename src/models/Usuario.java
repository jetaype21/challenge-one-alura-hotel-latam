package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import factory.ConexionBD;

public class Usuario {
	private String nombre;
	private String contrasenia;

	public Usuario(String nombre, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public static boolean validarUsuario(String nombre, String contrasenia) {
		ConexionBD con = new ConexionBD();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = con.conectarBD();
			statement = connection.prepareStatement("SELECT * FROM usuarios WHERE nombre=? AND contrasenia=?");
			statement.setString(1, nombre);
			statement.setString(2, contrasenia);
			
			resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(resultSet !=  null) {
					resultSet.close();
				}
				if(statement !=  null) {
					statement.close();
				}
				if(connection !=  null) {
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
}





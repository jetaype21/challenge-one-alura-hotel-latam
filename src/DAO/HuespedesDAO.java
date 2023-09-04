package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.mysql.cj.xdevapi.Statement;

import models.Huespedes;
import models.Reserva;

public class HuespedesDAO {
	private Connection connection;

	public HuespedesDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	public void guardar(Huespedes huespedes) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)" 
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, huespedes.getNombre());
				preparedStatement.setString(2, huespedes.getApellido());
				preparedStatement.setObject(3, huespedes.getFecha_nacimiento());
				preparedStatement.setString(4, huespedes.getNacionalidad());
				preparedStatement.setString(5, huespedes.getTelefono());
				preparedStatement.setInt(6, huespedes.getId_reserva());
			
				preparedStatement.execute();
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
					while (resultSet.next()) {
						huespedes.setId(resultSet.getInt(1));
						
					}
				}}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}}

	public List<Huespedes> mostrar() {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		
		try {
			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono,  id_reserva FROM huespedes";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.execute();
				
				transformar(huespedes, preparedStatement);
			}
	return huespedes;		
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public List<Huespedes> buscarId(String id) {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM huespedes WHERE id = ?";
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, id);
				preparedStatement.execute();
				
				transformar(huespedes, preparedStatement);
			}
	return huespedes;		
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public void ActuliazarHuesped (String nombre, String apellido, LocalDate fecha_nacimiento, String nacionalidad, String telefono,
			Integer id_reserva, Integer id) {
		try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?")) {
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, apellido);
			preparedStatement.setObject(3, fecha_nacimiento);
			preparedStatement.setString(4,  nacionalidad);
			preparedStatement.setString(5, telefono);
			preparedStatement.setInt(6, id_reserva);
			preparedStatement.setInt(7, id);
			preparedStatement.execute();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public void EliminarHuesped (Integer id) {
		try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM huespedes WHERE id = ?")){
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	private void transformar (List<Huespedes> huespedesAll, PreparedStatement preparedStatement) {
		try(ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				LocalDate fecha_nacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate().plusDays(1);
				String nacionalidad = resultSet.getString("nacionalidad");
				String telefono = resultSet.getString("telefono");
				int idReserva = resultSet.getInt("id_reserva");
				
				Huespedes huespedes = new Huespedes(id,nombre, apellido
						, fecha_nacimiento, nacionalidad, telefono, idReserva);
				huespedesAll.add(huespedes);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}


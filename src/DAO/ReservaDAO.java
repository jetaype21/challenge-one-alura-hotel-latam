package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Reserva;

public class ReservaDAO {
	private Connection connection;
	
	public ReservaDAO (Connection connection) {
		super();
		this.connection = connection;
	}
	
	public void guardar(Reserva reserva) {
		try {
			
			System.out.println("llego a sql");
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago)" + "VALUES (?, ?,?, ?)";
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setObject(1, reserva.getData_entrada());
				preparedStatement.setObject(2, reserva.getData_salida());
				preparedStatement.setString(3, reserva.getValor());
				preparedStatement.setString(4, reserva.getForma_pago());
				preparedStatement.executeUpdate();
				
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					while (resultSet.next()) {
						System.out.println("id: "+resultSet.getInt(1));
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new RuntimeException("Loco" + e.getMessage() );
		}
	}
	
	public List<Reserva> mostrar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.execute();
				
				transformar(reservas, preparedStatement);
			}
	return reservas;		
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public List<Reserva> buscar(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id = ?";
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, id);
				preparedStatement.execute();
				
				transformar(reservas, preparedStatement);
			}
	return reservas;		
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public void Actualizar(LocalDate data_entrada, LocalDate data_salida, String valor, String forma_de_pago, Integer id) {
		try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE reservas SET " +
	"fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_de_pago = ? WHERE id = ?"
				)) {
			preparedStatement.setObject(1, java.sql.Date.valueOf(data_entrada));
			preparedStatement.setObject(2, java.sql.Date.valueOf(data_salida));
			preparedStatement.setString(3, valor);
			preparedStatement.setString(4, forma_de_pago);
			preparedStatement.setInt(5, id);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public void Eliminar (Integer id) {
		try{
			Statement state = connection.createStatement();
			state.execute("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reservas WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			state.execute("SET FOREIGN_KEY_CHECKS=1");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}
	
	private void transformar (List<Reserva> reservas, PreparedStatement preparedStatement) {
		try(ResultSet resultSet = preparedStatement.getResultSet()) {
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				LocalDate fecha_entrada = resultSet.getDate("fecha_entrada").toLocalDate().plusDays(1);
				LocalDate fecha_salida = resultSet.getDate("fecha_salida").toLocalDate().plusDays(1);
				String valor = resultSet.getString("valor");
				String forma_de_pago = resultSet.getString("forma_de_pago");
				
				Reserva producto = new Reserva(id, fecha_entrada, fecha_salida, valor, forma_de_pago);
				reservas.add(producto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}






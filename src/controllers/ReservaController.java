package controllers;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import DAO.ReservaDAO;
import factory.ConexionBD;
import models.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection connection = new ConexionBD().conectarBD();
		this.reservaDAO = new ReservaDAO(connection);
	}
	
	public void guardar (Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}
	
	public List<Reserva> mostrar () {
		return this.reservaDAO.mostrar();
	}
	
	public  List<Reserva>  buscar (String id) {
		return this.reservaDAO.buscar(id);
	}
	
	public void actualizarReserva(LocalDate data_entrada, LocalDate data_salida, String valor, String forma_de_pago, Integer id) {
		this.reservaDAO.Actualizar(data_entrada, data_salida, valor, forma_de_pago, id);
	}
	
	public void Eliminar(Integer id) {
		this.reservaDAO.Eliminar(id);
	}
}

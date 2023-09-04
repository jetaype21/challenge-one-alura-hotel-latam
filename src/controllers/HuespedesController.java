package controllers;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import DAO.HuespedesDAO;
import factory.ConexionBD;
import models.Huespedes;

public class HuespedesController {

	private HuespedesDAO huespedesDAO;
	
	public HuespedesController () {
		Connection connection = new ConexionBD().conectarBD();
		this.huespedesDAO = new HuespedesDAO(connection);
	}
	
	public void guardar (Huespedes huespedes) {
		this.huespedesDAO.guardar(huespedes);
	}
	
	public List<Huespedes> mostrarHuespedes () {
		return this.huespedesDAO.mostrar();
	}
	
	public List<Huespedes> buscarId (String id) {
		return this.huespedesDAO.buscarId(id);
	}
	
	public void Actualizar(String nombre, String apellido, LocalDate fecha_nacimiento, String nacionalidad, String telefono,
			Integer id_reserva, Integer id) {
		this.huespedesDAO.ActuliazarHuesped(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva, id);
	}
	
	public void Eliminar(Integer idReserva) {
		this.huespedesDAO.EliminarHuesped(idReserva);
	}
}

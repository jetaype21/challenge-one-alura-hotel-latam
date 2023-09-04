package models;

import java.time.LocalDate;

public class Reserva {
	
	private Integer id;
	private LocalDate data_entrada;
	private LocalDate data_salida;
	private String valor;
	private String forma_pago;
	
	public Reserva( LocalDate data_entrada, LocalDate data_salida, String valor, String forma_pago) {
		super();
		this.data_entrada = data_entrada;
		this.data_salida = data_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}
	
	public Reserva(Integer id, LocalDate data_entrada, LocalDate data_salida, String valor, String forma_pago) {
		super();
		this.id = id;
		this.data_entrada = data_entrada;
		this.data_salida = data_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(LocalDate data_entrada) {
		this.data_entrada = data_entrada;
	}

	public LocalDate getData_salida() {
		return data_salida;
	}

	public void setData_salida(LocalDate data_salida) {
		this.data_salida = data_salida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	
	

}

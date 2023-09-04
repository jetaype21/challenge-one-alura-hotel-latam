package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Usuario;
import views.Login;
import views.MenuUsuario;

public class UsuarioController implements ActionListener {

	private Login view;
	
	public UsuarioController(Login view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre =view.getNombreUsuario() ;
		String contrasenia = view.getContraseniaUsuario() ;
		
		
		if(view.getNombreUsuario().equals("admin") && view.getContraseniaUsuario().equals("admin") ) {
			System.out.println("entro");
			MenuUsuario menuUsuario = new MenuUsuario();
			menuUsuario.setVisible(true);
			view.dispose();
			return;
		}
		
		if(Usuario.validarUsuario(nombre, contrasenia)) {
			MenuUsuario menuUsuario = new MenuUsuario();
			menuUsuario.setVisible(true);
			view.dispose();
		} else {
			JOptionPane.showMessageDialog(view, "Credenciales no validos.");
		}
	}

	
	

}




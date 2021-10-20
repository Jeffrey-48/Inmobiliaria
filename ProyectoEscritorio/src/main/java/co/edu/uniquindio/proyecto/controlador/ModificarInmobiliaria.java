package co.edu.uniquindio.proyecto.controlador;

import java.io.IOException;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificarInmobiliaria {

	@FXML
	private TextField cajonCodigo;

	@FXML
	private TextField cajonNombre;

	@FXML
	private TextField cajonDireccion;

	@FXML
	private TextField cajonEmail;

	@FXML
	private TextField cajonPassword;

	public void modificarInmobiliaria() {
		boolean salir =false;
		PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;
		try {

			Inmobiliaria miI = delegado.consultarInmobiliaria(Integer.parseInt(cajonCodigo.getText()));
			if (miI != null) {
				if (cajonNombre.getText().equals("") && cajonDireccion.getText().equals("")
						&& cajonEmail.getText().equals("") && cajonPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Porfavor ingrese al menos un campo");
				} else {
					if (cajonNombre.getText().equals("") == false) {
						delegado.modificarInmobiliariaNombre(Integer.parseInt(cajonCodigo.getText()),
								cajonNombre.getText());
						salir=true;
					}
					if (cajonDireccion.getText().equals("") == false) {
						delegado.modificarInmobiliariaDireccion(Integer.parseInt(cajonCodigo.getText()),
								cajonDireccion.getText());
						salir=true;
					}
					if (cajonPassword.getText().equals("") == false) {
						delegado.modificarInmobiliariaPassword(Integer.parseInt(cajonCodigo.getText()),
								cajonPassword.getText());
						salir=true;
					}
					if (cajonEmail.getText().equals("") == false) {
						try {
								delegado.modificarInmobiliariaEmail(Integer.parseInt(cajonCodigo.getText()),
										cajonEmail.getText());
								JOptionPane.showMessageDialog(null, "Los cambios se han realizado correctamente");
							 
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "El email ya se encuentra registrado");
						}
					}
					if(salir==true) {
					JOptionPane.showMessageDialog(null, "Los cambios se han realizado correctamente");
					}
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Ingrese un codigo valido");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El codigo no puede ser vacio");
		}
	}

	public void atras() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/opcionesAdministrador.fxml"));
		Parent parent;
		try {
			parent = loader.load();
			OpcionesAdministrador recuperarAdmin = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage stagex = (Stage) this.cajonCodigo.getScene().getWindow();
			stagex.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

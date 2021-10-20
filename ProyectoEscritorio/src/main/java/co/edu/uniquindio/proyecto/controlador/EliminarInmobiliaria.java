package co.edu.uniquindio.proyecto.controlador;

import java.io.IOException;

import javax.swing.JOptionPane;

import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EliminarInmobiliaria {

	@FXML
	private TextField cajonCodigo;

	@FXML
	public void eliminarInmobiliaria(ActionEvent e) {
		try {
			PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;
			int des = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la inmobiliaria?");
			if (des == 0) {
				delegado.eliminarInmobiliaria(Integer.parseInt(cajonCodigo.getText()));
				JOptionPane.showMessageDialog(null, "Inmobiliaria eliminada con exito");
			}
		} catch (Exception e2) {

			JOptionPane.showMessageDialog(null, "Ingrese un codigo correcto");
		}
	}

	@FXML
	public void atras(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/opcionesAdministrador.fxml"));
		Parent parent = loader.load();
		OpcionesAdministrador recuperarAdmin = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		Stage stagex = (Stage) this.cajonCodigo.getScene().getWindow();
		stagex.close();
	}

}

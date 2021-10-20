package co.edu.uniquindio.proyecto.controlador;

import java.awt.Button;
import java.io.IOException;

import org.eclipse.persistence.annotations.PrimaryKey;

import co.edu.uniquindio.entidadesProyecto.Administrador;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InicioController {

	@FXML
	private TextField cajonEmail;
	@FXML
	private TextField cajonPassword;

	@FXML
	private ImageView imagen;

	PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;

	public PruebaDelegado getDelegado() {
		return delegado;
	}

	public void setDelegado(PruebaDelegado delegado) {
		this.delegado = delegado;
	}

		

	
	@FXML
	public void autenticarUsuario(ActionEvent e) {

		if (!cajonEmail.getText().isEmpty() && !cajonPassword.getText().isEmpty()) {
			try {
				Usuario u = delegado.autenticarUsuario(cajonEmail.getText(), cajonPassword.getText());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/opcionesAdministrador.fxml"));
				Parent parent = loader.load();
				OpcionesAdministrador opcionesAdministrador = loader.getController();
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				Stage stagex = (Stage) this.cajonPassword.getScene().getWindow();
				stagex.close();
			} catch (Exception e1) {

				Alert a = new Alert(AlertType.ERROR);
				a.setHeaderText("Titulo de la alerta");
				a.setContentText(e1.getMessage());
				a.show();
			}
		}
	}

	@FXML
	public void recuperarContrasena(ActionEvent e) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/recuperarContrasenaAdmin.fxml"));
		Parent parent = loader.load();
		RecuperarAdminController recuperarAdmin = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		Stage stagex = (Stage) this.cajonEmail.getScene().getWindow();
		stagex.close();
	}

}

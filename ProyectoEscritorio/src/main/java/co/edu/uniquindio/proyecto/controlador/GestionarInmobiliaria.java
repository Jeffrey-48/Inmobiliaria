package co.edu.uniquindio.proyecto.controlador;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionarInmobiliaria {

	@FXML
	public void crearInmobiliaria(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/crearInmobiliaria.fxml"));
		Parent parent = loader.load();
		CrearInmobiliaria crearInm = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
//		Stage miStage = (Stage) this.field.getScene().getWindow();
//		miStage.close();
	}

	@FXML
	public void eliminarInmobiliaria(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/eliminarInmobiliaria.fxml"));
		Parent parent = loader.load();
		EliminarInmobiliaria crearInm = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		// Stage stagex = (Stage) this.textfil.getScene().getWindow();
		// stagex.close();
	}

	@FXML
	public void buscarInmobiliaria() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/consultarInmobiliaria.fxml"));
		Parent parent = loader.load();
		ConsultarInmobiliaria crearInm = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
	}

	@FXML
	public void modificarInmobiliaria() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/modificarInmobiliaria.fxml"));
		Parent parent = loader.load();
		ModificarInmobiliaria crearInm = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void atras() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/opcionesAdministrador.fxml"));
		Parent parent;
		try {
			parent = loader.load();
			OpcionesAdministrador crearInm = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

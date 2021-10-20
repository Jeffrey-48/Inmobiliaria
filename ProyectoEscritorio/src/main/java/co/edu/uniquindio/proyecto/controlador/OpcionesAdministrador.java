package co.edu.uniquindio.proyecto.controlador;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OpcionesAdministrador {
	
	InicioController inicio = new InicioController();

	

	@FXML
	public void listarInmobiliaria(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/listarInmobiliaria.fxml"));
		Parent parent = loader.load();
		ListarInmobiliaria crearInm = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	public void detallesInmobiliaria(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/detalleInmobiliaria.fxml"));
		Parent parent = loader.load();
		DetalleInmobiliaria Inm = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void gestionarInmobiliaria(ActionEvent e) {
	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionesInmobiliaria.fxml"));
			Parent parent = loader.load();
			GestionarInmobiliaria Inm = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			//Stage stagex = (Stage) this.botonCrear.getScene().getWindow();
			//stagex.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}

	@FXML
	public void salir(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
		Parent parent = loader.load();
		InicioController recuperarAdmin = loader.getController();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}

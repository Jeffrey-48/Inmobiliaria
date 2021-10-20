package co.edu.uniquindio.proyecto.app;

import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javassist.tools.reflect.Loader;

public class Aplicacion extends Application {

	@FXML
	private ImageView imagen;

	@Override
	public void start(Stage primaryStage) throws Exception {


		FXMLLoader loader = new FXMLLoader(Aplicacion.class.getResource("/inicio.fxml"));
		Parent parent = loader.load();

		Scene s = new Scene(parent);
		
		primaryStage.setScene(s);
		primaryStage.setTitle("Ventana Administrador");
		primaryStage.show();
	

	}

	public static void main(String[] args) {

		launch(args);
	}

}

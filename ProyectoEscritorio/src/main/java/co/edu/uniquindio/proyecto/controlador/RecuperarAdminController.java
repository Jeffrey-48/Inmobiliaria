package co.edu.uniquindio.proyecto.controlador;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import co.edu.uniquindio.entidadesProyecto.Administrador;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import co.edu.uniquindio.proyectoInmobiliaria.EnviarConGmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javassist.tools.reflect.Loader;

public class RecuperarAdminController {

	@FXML
	private TextField cajonEmail;
	@FXML
	private TextField cajonCodigo;

	public void enviarCorreoRecuperacion(ActionEvent e) throws Exception {
		try {
			PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;
			List<Usuario> miU = delegado.enviarCorreoRecuperacion(cajonEmail.getText());
			EnviarConGmail correo = new EnviarConGmail();
			correo.enviarConGMail(cajonEmail.getText(), "Recuperacion de contraseña",
					"Su contraseña es: " + miU.get(0).getPassword());
			JOptionPane.showMessageDialog(null, "Contraseña enviada, verifique en su email");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
			Parent parent = loader.load();
			InicioController recuperarAdmin = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
		}
	}

	@FXML
	public void atras() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
		Parent parent;
		try {
			parent = loader.load();
			InicioController crearInm = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage stagex = (Stage) this.cajonEmail.getScene().getWindow();
			stagex.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

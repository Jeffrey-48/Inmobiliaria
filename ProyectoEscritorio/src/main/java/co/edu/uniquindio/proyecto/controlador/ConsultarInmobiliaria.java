package co.edu.uniquindio.proyecto.controlador;

import java.io.IOException;

import javax.swing.JOptionPane;

import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConsultarInmobiliaria {

	@FXML
	private TextField cajonCodigo;

	@FXML
	private TextArea areaInmobiliaria;

	public void consultarInmobiliaria() {
		try {
		PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;
		Inmobiliaria miInm = delegado.consultarInmobiliaria(Integer.parseInt(cajonCodigo.getText()));
		String texto="Codigo: " + miInm.getCodigo() + "  Nombre: "+miInm.getNombre() +" Email: "+miInm.getEmail()
		+" Direccion: "+miInm.getDireccion()+ " Password: " +miInm.getPassword()+ "\n";
		areaInmobiliaria.setText(texto);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
		}
	}
	
	@FXML
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

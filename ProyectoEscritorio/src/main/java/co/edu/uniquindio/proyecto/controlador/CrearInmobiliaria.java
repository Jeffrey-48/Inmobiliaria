package co.edu.uniquindio.proyecto.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearInmobiliaria {

	@FXML
	private TextField codigoInmobiliaria;
	@FXML
	private TextField direccionInmobiliaria;
	@FXML
	private TextField emailInmobiliaria;
	@FXML
	private TextField nombreInmobiliaria;
	@FXML
	private TextField passwordInmobiliaria;

	@FXML
	private TextField codigoProyecto;

	@FXML
	private TextField nombreProyecto;

	@FXML
	private TextArea telefonosArea;

	@FXML
	public void agregarInmobiliaria(ActionEvent e) throws Exception {

		PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;
		try {

			Inmobiliaria i = new Inmobiliaria();
			i.setCodigo(Integer.parseInt(codigoInmobiliaria.getText()));
			i.setDireccion(direccionInmobiliaria.getText());
			i.setEmail(emailInmobiliaria.getText());
			i.setNombre(nombreInmobiliaria.getText());
			i.setPassword(passwordInmobiliaria.getText());
			Map<String, String> misA = new HashMap<String, String>();
			misA.put(codigoProyecto.getText(), nombreProyecto.getText());
			i.setAsesor(misA);
			List<String> telefonos = new ArrayList<String>();
			i.setTelefono(Arrays.asList(telefonosArea.getText().split(",")));
			delegado.crearInmobiliaria(i);
			JOptionPane.showMessageDialog(null, "Inmobiliaria Registrada con exito");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/opcionesAdministrador.fxml"));
			Parent parent = loader.load();
			OpcionesAdministrador recuperarAdmin = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage stagex = (Stage) this.nombreInmobiliaria.getScene().getWindow();
			stagex.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		Stage stagex = (Stage) this.codigoInmobiliaria.getScene().getWindow();
		stagex.close();
	}

}

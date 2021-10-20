package co.edu.uniquindio.proyecto.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetalleInmobiliaria {
	
	@FXML
	private TextField cajonCodigo;
	
	@FXML
	private TextArea areaLista;

	@FXML
	public void consultarInmobiliaria(ActionEvent e) throws Exception {
		try {
		PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;
		Inmobiliaria miInm = delegado.consultarInmobiliaria(Integer.parseInt(cajonCodigo.getText()));
//		List<String> telefonosL = miInm.getTelefono();
//		String telefonos ="";
//		for (int i = 0; i < telefonosL.size(); i++) {
//			telefonos+=telefonosL.get(i)+ ", ";
//		}
//		Map<String, String> asesoresM = miInm.getAsesor();
//		String asesores ="";
//		for(Map.Entry<String, String> entry : asesoresM.entrySet())
//		{
//			asesores+= "Codigo Asesor: " +entry.getKey() + " Nombre del asesor: " + entry.getValue() + "\n";
//			System.out.println(asesores);
//		}
		String texto="Codigo: " + miInm.getCodigo() + "  Nombre: "+miInm.getNombre() +" Email: "+miInm.getEmail()
				+" Direccion: "+miInm.getDireccion()+ " Password: " +miInm.getPassword()+ "\n";
				//" Asesor: " +asesores+" Telefonos: "+telefonos+"\n";
		areaLista.setText(texto);
		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
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
	
	}

}

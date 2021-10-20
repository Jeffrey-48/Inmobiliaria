package co.edu.uniquindio.proyecto.controlador;

import java.util.List;

import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.proyecto.modelo.PruebaDelegado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ListarInmobiliaria {
	
	@FXML
	private TextArea areaLista;

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
	
	@FXML
	public void consultarLista(ActionEvent e) {
		PruebaDelegado delegado = PruebaDelegado.pruebaDelegado;
		List<Inmobiliaria> lista = delegado.listarInmobiliarias();
		String texto="";
		for (int i = 0; i < lista.size(); i++) {
			texto+="Codigo: " + lista.get(i).getCodigo() + "  Nombre: "+lista.get(i).getNombre() +" Email: "+lista.get(i).getEmail()
					+" Direccion: "+lista.get(i).getDireccion()+ " Password: " +lista.get(i).getPassword()+"\n";
		}
		areaLista.setText(texto);
		
	}
}

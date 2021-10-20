package co.edu.uniquindio.proyecto.beans;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("holaMundoBean")
@ApplicationScoped
public class HolaMundoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mensaje = "Hola Mundo Bean";

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
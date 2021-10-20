package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Contacto
 * Entidad dedicada a la informacion de contacto de los clientes
 */
@Entity

public class Contacto implements Serializable {

	private String asunto;
	private String cuerpoMensaje;

	@EmbeddedId
	private ContactoPK clave;
	
	@ManyToOne
	@MapsId("codigoCliente")
	private Cliente clienteContacto;

	@ManyToOne
	@MapsId("codigoProyecto")
	private Proyecto proyectoContacto;
	private static final long serialVersionUID = 1L;

	public Contacto() {
		super();
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpoMensaje() {
		return this.cuerpoMensaje;
	}

	public void setCuerpoMensaje(String cuerpoMensaje) {
		this.cuerpoMensaje = cuerpoMensaje;
	}

	public ContactoPK getClave() {
		return this.clave;
	}

	public void setClave(ContactoPK clave) {
		this.clave = clave;
	}

	public Cliente getClienteContacto() {
		return clienteContacto;
	}

	public void setClienteContacto(Cliente clienteContacto) {
		this.clienteContacto = clienteContacto;
	}

	public Proyecto getProyectoContacto() {
		return proyectoContacto;
	}

	public void setProyectoContacto(Proyecto proyectoContacto) {
		this.proyectoContacto = proyectoContacto;
	}

	@Override
	public String toString() {
		return "Contacto [asunto=" + asunto + ", cuerpoMensaje=" + cuerpoMensaje + ", clave=" + clave
				+ ", clienteContacto=" + clienteContacto + ", proyectoContacto=" + proyectoContacto + "]";
	}

	
}

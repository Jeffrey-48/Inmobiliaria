package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Calificacion
 * Entidad dedicada a la creacion de una calificacion dada por un cliente para el proyecto
 */
@Entity
public class Calificacion implements Serializable {

	@EmbeddedId
	private CalificacionPK clave;

	@ManyToOne
	@MapsId("codigoCliente")
	private Cliente clienteCalificacion;

	@ManyToOne
	@MapsId("codigoProyecto")
	private Proyecto proyectoCalificacion;

	@Column(nullable = false)
	private int calificacion;

	private static final long serialVersionUID = 1L;

	public Calificacion() {
		super();
	}

	public CalificacionPK getClave() {
		return this.clave;
	}

	public void setClave(CalificacionPK clave) {
		this.clave = clave;
	}

	public Cliente getClienteCalificacion() {
		return this.clienteCalificacion;
	}

	public void setClienteCalificacion(Cliente clienteCalificacion) {
		this.clienteCalificacion = clienteCalificacion;
	}

	public Proyecto getProyectoCalificacion() {
		return this.proyectoCalificacion;
	}

	public void setProyectoCalificacion(Proyecto proyectoCalificacion) {
		this.proyectoCalificacion = proyectoCalificacion;
	}

	public int getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public String toString() {
		return "Calificacion [clave=" + clave + ", clienteCalificacion=" + clienteCalificacion
				+ ", proyectoCalificacion=" + proyectoCalificacion + ", calificacion=" + calificacion + "]";
	}
	
}

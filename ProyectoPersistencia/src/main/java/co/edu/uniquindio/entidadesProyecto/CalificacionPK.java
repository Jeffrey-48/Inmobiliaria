package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Embeddable;

/**
 * Entity implementation class for Entity: CalificacionPK
 * Clase auxiliar para la relacion de la Entidad calificacion y proyecto
 */

@Embeddable
public class CalificacionPK implements Serializable {

	private int codigoCliente;
	private int codigoProyecto;
	private static final long serialVersionUID = 1L;

	public CalificacionPK() {
		super();
	}

	public CalificacionPK(int codigoCliente, int codigoProyecto) {
		super();
		this.codigoCliente = codigoCliente;
		this.codigoProyecto = codigoProyecto;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoCliente;
		result = prime * result + codigoProyecto;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalificacionPK other = (CalificacionPK) obj;
		if (codigoCliente != other.codigoCliente)
			return false;
		if (codigoProyecto != other.codigoProyecto)
			return false;
		return true;
	}

	

	
	

}

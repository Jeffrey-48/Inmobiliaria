package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ContactoPK
 * clase auxiliar la cual relaciona contacto con cliente y proyecto
 */

@Embeddable
public class ContactoPK implements Serializable {

	
	private int codigoProyecto;
	private int codigoCliente;
	private static final long serialVersionUID = 1L;

	public ContactoPK() {
		super();
	}   
	
	
	public ContactoPK(int codigoProyecto, int codigoCliente) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.codigoCliente = codigoCliente;
	}


	public int getCodigoProyecto() {
		return this.codigoProyecto;
	}

	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}   
	public int getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
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
		ContactoPK other = (ContactoPK) obj;
		if (codigoCliente != other.codigoCliente)
			return false;
		if (codigoProyecto != other.codigoProyecto)
			return false;
		return true;
	}
   
	
}

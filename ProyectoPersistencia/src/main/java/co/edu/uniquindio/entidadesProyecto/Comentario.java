package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comentario se encarga de gestionar la
 * informacion de los comentarios de los clientes
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Comentario.LISTA_CLIENTES_PROYECTO, query = "select distinct c.comentarioCliente from Comentario c where c.comentarioProyecto.codigo=:codigo"),
		@NamedQuery(name = Comentario.LISTA_COMENTARIOS_PROYECTO, query = "select c from Comentario c where c.comentarioProyecto.codigo=:codigo") })
public class Comentario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String comentario;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne
	private Cliente comentarioCliente;

	@ManyToOne
	private Proyecto comentarioProyecto;

	private static final long serialVersionUID = 1L;
	public static final String LISTA_CLIENTES_PROYECTO = "LISTA_CLIENTES_PROYECTO";
	public static final String LISTA_COMENTARIOS_PROYECTO = "LISTA_COMENTARIOS_PROYECTO";

	public Comentario() {
		super();
	}

	public Comentario(int codigo, String comentario, Date fecha) {
		super();
		this.codigo = codigo;
		this.comentario = comentario;
		this.fecha = fecha;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getComentarioCliente() {
		return comentarioCliente;
	}

	public void setComentarioCliente(Cliente comentarioCliente) {
		this.comentarioCliente = comentarioCliente;
	}

	public Proyecto getComentarioProyecto() {
		return comentarioProyecto;
	}

	public void setComentarioProyecto(Proyecto comentarioProyecto) {
		this.comentarioProyecto = comentarioProyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		Comentario other = (Comentario) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comentario [codigo=" + codigo + ", comentario=" + comentario + ", fecha=" + fecha
				+ ", comentarioCliente=" + comentarioCliente + ", comentarioProyecto=" + comentarioProyecto + "]";
	}

}

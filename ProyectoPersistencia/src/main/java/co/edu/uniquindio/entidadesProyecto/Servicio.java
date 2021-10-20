package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Servicio Entidad que se encarga de
 * las prestaciones que tiene la inmobiliaria con respecto a sus ofertas
 */
@Entity
@NamedQueries({ @NamedQuery(name = Servicio.LISTAR_SERVICIOS, query = "select s from Servicio s"),
		@NamedQuery(name = Servicio.LISTAR_SERVICIOS_CODIGO, query = "select s from Servicio s where s.codigo_servicio=:codigo") })
public class Servicio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_servicio;

	@ManyToMany(mappedBy = "servicios")
	private List<Proyecto> proyectos;

	@Column(nullable = false)
	private String nombre;
	private static final long serialVersionUID = 1L;
	public static final String LISTAR_SERVICIOS = "LISTAR_SERVICIOS";
	public static final String LISTAR_SERVICIOS_CODIGO = "LISTAR_SERVICIOS_CODIGO";

	public Servicio() {
		super();
	}

	public Servicio(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getCodigo_servicio() {
		return codigo_servicio;
	}

	public void setCodigo_servicio(int codigo_servicio) {
		this.codigo_servicio = codigo_servicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo_servicio;
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
		Servicio other = (Servicio) obj;
		if (codigo_servicio != other.codigo_servicio)
			return false;
		return true;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	@Override
	public String toString() {
		return "Servicio [codigo_servicio=" + codigo_servicio + ", proyectos=" + proyectos + ", nombre=" + nombre + "]";
	}

}

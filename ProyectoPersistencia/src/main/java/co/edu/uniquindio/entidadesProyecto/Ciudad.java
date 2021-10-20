package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ciudad Entidad para la creacion y
 * gestion de una ciudad
 */
@Entity
@NamedQueries({
	@NamedQuery(name= Ciudad.LISTA_CIUDADES, query = "select c from Ciudad c")})
public class Ciudad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_ciudad;

	@Column(nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "miCiudad")
	private List<Proyecto> proyectos;

	private static final long serialVersionUID = 1L;
	public static final String LISTA_CIUDADES = "LISTA_CIUDADES";

	public Ciudad() {
		super();
	}

	public Ciudad(String nombre,int codigo) {
		super();
		this.nombre = nombre;
		this.codigo_ciudad = codigo;
	}

	public Ciudad(int codigo_ciudad) {
		super();
		this.codigo_ciudad = codigo_ciudad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo_ciudad() {
		return codigo_ciudad;
	}

	public void setCodigo_ciudad(int codigo_ciudad) {
		this.codigo_ciudad = codigo_ciudad;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	@Override
	public String toString() {
		return "Ciudad [codigo_ciudad=" + codigo_ciudad + ", nombre=" + nombre + ", proyectos=" + proyectos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo_ciudad;
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
		Ciudad other = (Ciudad) obj;
		if (codigo_ciudad != other.codigo_ciudad)
			return false;
		return true;
	}
	
}

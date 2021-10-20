package co.edu.uniquindio.entidadesProyecto;

import co.edu.uniquindio.entidadesProyecto.Usuario;
import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Inmobiliaria Entidad encargada de las
 * gestiones de la inmobiliaria
 */
@Entity
@NamedQueries({ @NamedQuery(name = Inmobiliaria.LISTAR_INMOBILIARIAS, query = "select i from Inmobiliaria i") })
public class Inmobiliaria extends Usuario implements Serializable {

	@Column(nullable = false)
	private String direccion;

	@ElementCollection
	private Map<String, String> asesor;

	@ElementCollection
	private List<String> telefono;

	@OneToMany(mappedBy = "inmobiliaria")
	private List<Proyecto> proyectos;

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_INMOBILIARIAS = "LISTAR_INMOBILIARIAS";

	public Inmobiliaria() {
		super();
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Map<String, String> getAsesor() {
		return asesor;
	}

	public void setAsesor(Map<String, String> asesor) {
		this.asesor = asesor;
	}

	public List<String> getTelefono() {
		return telefono;
	}

	public void setTelefono(List<String> telefono) {
		this.telefono = telefono;
	}

	public Inmobiliaria(String direccion, Map<String, String> asesor, List<String> telefono) {
		super();
		this.direccion = direccion;
		this.asesor = asesor;
		this.telefono = telefono;
	}

	public Inmobiliaria(int codigo,String direccion, String email, String nombre, String password) {
		super(codigo, email,nombre, password);
		this.direccion=direccion;

	}

	@Override
	public String toString() {
		return "Inmobiliaria [direccion=" + direccion + ", asesor=" + asesor + ", telefono=" + telefono + ", proyectos="
				+ proyectos + "]";
	}

}

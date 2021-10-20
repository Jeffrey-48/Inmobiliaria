package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Proyecto Entidad encargada de toda la
 * gestion del proyecto
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Proyecto.BUSCAR_NOMBRE, query = "select p from Proyecto p where p.nombre like :nombre"),
		@NamedQuery(name = Proyecto.LISTAR_PROYECTOS, query = "select p from Proyecto p"),
		@NamedQuery(name = Proyecto.BUSCAR_CODIGO, query = "select p from Proyecto p where p.codigo  =:codigo") })

public class Proyecto implements Serializable {

	public static final String BUSCAR_NOMBRE = "BUSCAR_NOMBRE";
	public static final String LISTAR_PROYECTOS = "LISTAR_PROYECTOS";
	public static final String BUSCAR_CODIGO = "BUSCAR_CODIGO";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private double ubicacion_latitud;

	@Column(nullable = false)
	private double ubicacion_longitud;

	@Lob
	private String descripcion;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Ciudad miCiudad;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Inmobiliaria inmobiliaria;

	@OneToMany(mappedBy = "miProyecto")
	private List<Vivienda> Viviendas;

	@ElementCollection
	private List<String> imagenes;

	@ManyToMany
	private List<Servicio> servicios;

	@OneToMany(mappedBy = "comentarioProyecto")
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "proyectoCalificacion")
	private List<Calificacion> calificaciones;

	@OneToMany(mappedBy = "proyectoContacto")
	private List<Contacto> contactos;

	@ManyToMany(mappedBy = "favoritosCliente")
	private List<Cliente> favoritosProyecto;

	private static final long serialVersionUID = 1L;

	public Proyecto() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getUbicacion_latitud() {
		return ubicacion_latitud;
	}

	public void setUbicacion_latitud(double ubicacion_latitud) {
		this.ubicacion_latitud = ubicacion_latitud;
	}

	public double getUbicacion_longitud() {
		return ubicacion_longitud;
	}

	public void setUbicacion_longitud(double ubicacion_longitud) {
		this.ubicacion_longitud = ubicacion_longitud;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Ciudad getMiCiudad() {
		return miCiudad;
	}

	public void setMiCiudad(Ciudad miCiudad) {
		this.miCiudad = miCiudad;
	}

	public Inmobiliaria getInmobiliaria() {
		return inmobiliaria;
	}

	public void setInmobiliaria(Inmobiliaria inmobiliaria) {
		this.inmobiliaria = inmobiliaria;
	}

	public List<Vivienda> getViviendas() {
		return Viviendas;
	}

	public void setViviendas(List<Vivienda> viviendas) {
		Viviendas = viviendas;
	}

	public List<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public List<Cliente> getFavoritosProyecto() {
		return favoritosProyecto;
	}

	public void setFavoritosProyecto(List<Cliente> favoritosProyecto) {
		this.favoritosProyecto = favoritosProyecto;
	}

	public Proyecto(String descripcion, String nombre, double ubicacion_latitud, double ubicacion_longitud,
			Inmobiliaria inmobiliaria, Ciudad miCiudad) {
		super();
		this.nombre = nombre;
		this.ubicacion_latitud = ubicacion_latitud;
		this.ubicacion_longitud = ubicacion_longitud;
		this.descripcion = descripcion;
		this.miCiudad = miCiudad;
		this.inmobiliaria = inmobiliaria;
	}

	@Override
	public String toString() {
		return "Proyecto [codigo=" + codigo + ", nombre=" + nombre + ", ubicacion_latitud=" + ubicacion_latitud
				+ ", ubicacion_longitud=" + ubicacion_longitud + ", descripcion=" + descripcion + ", miCiudad="
				+ miCiudad + ", inmobiliaria=" + inmobiliaria + ", Viviendas=" + Viviendas + ", imagenes=" + imagenes
				+ ", servicios=" + servicios + ", comentarios=" + comentarios + ", calificaciones=" + calificaciones
				+ ", contactos=" + contactos + ", favoritosProyecto=" + favoritosProyecto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Proyecto other = (Proyecto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}

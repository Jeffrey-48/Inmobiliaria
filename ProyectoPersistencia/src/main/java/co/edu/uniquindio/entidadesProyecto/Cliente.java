package co.edu.uniquindio.entidadesProyecto;

import co.edu.uniquindio.entidadesProyecto.Usuario;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cliente Entidad para el manejo de la
 * informacion de un cliente
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Cliente.LISTAR_CLIENTES, query = "select c from Cliente c") })
public class Cliente extends Usuario implements Serializable {


	@Column(name = "NUM_TELEFONO", length = 20)
	private String telefono;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NACIMIENTO")
	private Date fecha_nacimiento;

	@OneToMany(mappedBy = "comentarioCliente")
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "clienteContacto")
	private List<Contacto> contactos;

	@OneToMany(mappedBy = "clienteCalificacion")
	private List<Calificacion> calificaciones;

	@ManyToMany
	@JoinTable(name = "FAVORITO")
	private List<Proyecto> favoritosCliente;

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_CLIENTES = "LISTAR_CLIENTES";
	
	public Cliente() {
		super();
	}

	public Cliente(String telefono, Date fecha_nacimiento, List<Comentario> comentarios,
			List<Contacto> contactos, List<Calificacion> calificaciones, List<Proyecto> favoritosCliente) {
		super();
		
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.comentarios = comentarios;
		this.contactos = contactos;
		this.calificaciones = calificaciones;
		this.favoritosCliente = favoritosCliente;
	}


	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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

	public List<Proyecto> getFavoritosCliente() {
		return favoritosCliente;
	}

	public void setFavoritosCliente(List<Proyecto> favoritosCliente) {
		this.favoritosCliente = favoritosCliente;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	

}

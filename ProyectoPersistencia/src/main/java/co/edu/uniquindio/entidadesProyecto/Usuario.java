package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 * Clase encargada de la distribucion de datos del usuario
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@NamedQueries({
	@NamedQuery(name= Usuario.VERIFICAR_EMAIL_UNICO, query = "select u from Usuario u where u.email =:email" ),
	@NamedQuery(name= Usuario.AUTENTICAR_USUARIO, query = "select u from Usuario u where u.email =:email and u.password=:password" ),
	@NamedQuery(name= Usuario.AUTENTICAR_USUARIO_CODIGO, query = "select u from Usuario u where u.email=:email" )})

public class Usuario implements Serializable {

	@Id
	@Column(nullable = false, length = 10)
	private Integer codigo;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private static final long serialVersionUID = 1L;
	
	public static final String VERIFICAR_EMAIL_UNICO = "VERIFICAR_EMAIL_UNICO";
	public static final String AUTENTICAR_USUARIO = "AUTENTICAR_USUARIO";
	public static final String AUTENTICAR_USUARIO_CODIGO = "AUTENTICAR_USUARIO_CODIGO";
	public Usuario() {
		super();
	}

	public Usuario(Integer codigo, String nombre, String email, String password) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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
		Usuario other = (Usuario) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", email=" + email + ", password=" + password + "]";
	}

}
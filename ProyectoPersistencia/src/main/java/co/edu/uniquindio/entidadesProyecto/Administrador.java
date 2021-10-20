package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador Entidad relacionada con
 * las funciones del administrador
 */
@Entity
@NamedQueries({ @NamedQuery(name = Administrador.LISTA_ADMINISTRADORES, query = "select a from Administrador a"),
	@NamedQuery(name = Administrador.RECUPERAR_CONTRASENA, query = "select a from Administrador a where a.codigo =:codigo"),})
public class Administrador extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String LISTA_ADMINISTRADORES = "LISTA_ADMINISTRADORES";
	public static final String RECUPERAR_CONTRASENA = "RECUPERAR_CONTRASENA";

	public Administrador() {
		super();
	}

}

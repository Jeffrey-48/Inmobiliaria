package co.edu.uniquindio.proyectoInmobiliaria;

import java.util.List;

import javax.ejb.Remote;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;

import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Comentario;
import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.entidadesProyecto.Vivienda;

@Remote
public interface PruebaEJBRemote {

	public static final String JNDI = "java:global/ProyectoEAR/ProyectoNegocio/PruebaEJB!co.edu.uniquindio.proyectoInmobiliaria.PruebaEJBRemote";

	void registrarUsuario(Usuario u) throws Exception;

	Usuario autenticarUsuario(String email, String password) throws Exception;

	List<Usuario> enviarCorreoRecuperacion(String email) throws Exception;

	void crearInmobiliaria(Inmobiliaria i) throws Exception;

	Inmobiliaria consultarInmobiliaria(int codigo);

	void eliminarInmobiliaria(int codigo);

	List<Inmobiliaria> listarInmobiliarias();

	void modificarInmobiliariaNombre(int codigo, String nombre);

	void modificarInmobiliariaDireccion(int codigo, String nombre);

	void modificarInmobiliariaEmail(int codigo, String nombre);

	void modificarInmobiliariaPassword(int codigo, String nombre);

	List<Proyecto> consultarProyecto(String nombre);

	List<Proyecto> listarProyectos();

	void registrarProyecto(Proyecto miProyecto) throws Exception;

	boolean verificarEmail(String email);

	List<Ciudad> listarCiudades();

	List<Servicio> listarServicios();

	Servicio obtenerServicio(int codigo) throws Exception;

	Proyecto obtenerProyecto(int codigo) throws Exception;

	List<Comentario> obtenerComentariosProyecto(int codigo) throws Exception;

	List<Servicio> obtenerServicios(int codigo);

	Cliente consultarCliente(int codigo);

	void modificarCliente(Cliente cliente);

	List<Cliente> listarClientes();

	void registrarVivienda(Vivienda vivienda) throws Exception;

	Proyecto consultarProyectoId(Integer nombre);

}

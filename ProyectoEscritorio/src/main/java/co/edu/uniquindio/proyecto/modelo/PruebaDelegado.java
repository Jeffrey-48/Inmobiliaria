package co.edu.uniquindio.proyecto.modelo;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Comentario;
import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.entidadesProyecto.Vivienda;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJBRemote;

public class PruebaDelegado implements PruebaEJBRemote {

	private PruebaEJBRemote pruebaEJB;
	public static PruebaDelegado pruebaDelegado = instancia();

	private PruebaDelegado() {

		try {
			pruebaEJB = (PruebaEJBRemote) new InitialContext().lookup(PruebaEJBRemote.JNDI);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registrarUsuario(Usuario u) throws Exception {
		pruebaEJB.registrarUsuario(u);
	}

	public Usuario autenticarUsuario(String email, String password) throws Exception {
		return pruebaEJB.autenticarUsuario(email, password);
	}

	private static PruebaDelegado instancia() {
		if (pruebaDelegado == null) {
			pruebaDelegado = new PruebaDelegado();
			return pruebaDelegado;
		}
		return pruebaDelegado;
	}

	public List<Usuario> enviarCorreoRecuperacion(String email) throws Exception {
		return pruebaEJB.enviarCorreoRecuperacion(email);

	}

	public void crearInmobiliaria(Inmobiliaria i) throws Exception {
		pruebaEJB.crearInmobiliaria(i);
	}

	public Inmobiliaria consultarInmobiliaria(int codigo) {
		return pruebaEJB.consultarInmobiliaria(codigo);
	}

	public void modificarInmobiliariaNombre(int codigo, String nombre) {
		pruebaEJB.modificarInmobiliariaNombre(codigo, nombre);
	}

	public void modificarInmobiliariaDireccion(int codigo, String nombre) {
		pruebaEJB.modificarInmobiliariaDireccion(codigo, nombre);
	}

	public void modificarInmobiliariaEmail(int codigo, String nombre) {
		pruebaEJB.modificarInmobiliariaEmail(codigo, nombre);
	}

	public void modificarInmobiliariaPassword(int codigo, String nombre) {
		pruebaEJB.modificarInmobiliariaPassword(codigo, nombre);
	}

	public void eliminarInmobiliaria(int codigo) {
		pruebaEJB.eliminarInmobiliaria(codigo);

	}

	public List<Inmobiliaria> listarInmobiliarias() {
		return pruebaEJB.listarInmobiliarias();
	}

	@Override
	public List<Proyecto> consultarProyecto(String nombre) {
		return pruebaEJB.consultarProyecto(nombre);
	}

	@Override
	public List<Proyecto> listarProyectos() {
		return pruebaEJB.listarProyectos();
	}

	@Override
	public void registrarProyecto(Proyecto miProyecto) throws Exception {
		pruebaEJB.registrarProyecto(miProyecto);
	}

	@Override
	public boolean verificarEmail(String email) {
		return pruebaEJB.verificarEmail(email);
	}

	@Override
	public List<Ciudad> listarCiudades() {
		return pruebaEJB.listarCiudades();
	}

	@Override
	public List<Servicio> listarServicios() {
		return pruebaEJB.listarServicios();
	}

	@Override
	public Servicio obtenerServicio(int codigo) throws Exception {
		return pruebaEJB.obtenerServicio(codigo);
	}

	@Override
	public Proyecto obtenerProyecto(int codigo) throws Exception {
		return pruebaEJB.obtenerProyecto(codigo);
	}

	@Override
	public List<Comentario> obtenerComentariosProyecto(int codigo) throws Exception {
		return pruebaEJB.obtenerComentariosProyecto(codigo);
	}

	@Override
	public List<Servicio> obtenerServicios(int codigo) {
		return pruebaEJB.obtenerServicios(codigo);
	}

	@Override
	public Cliente consultarCliente(int codigo) {
		// TODO Auto-generated method stub
		return pruebaEJB.consultarCliente(codigo);
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		pruebaEJB.modificarCliente(cliente);
	}

	@Override
	public List<Cliente> listarClientes() {
		return pruebaEJB.listarClientes();
	}

	@Override
	public void registrarVivienda(Vivienda vivienda) throws Exception {
		pruebaEJB.registrarVivienda(vivienda);
		
	}

	@Override
	public Proyecto consultarProyectoId(Integer nombre) {
		// TODO Auto-generated method stub
		return pruebaEJB.consultarProyectoId(nombre);
	}

}

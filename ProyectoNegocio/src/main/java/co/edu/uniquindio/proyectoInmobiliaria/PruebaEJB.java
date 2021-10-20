package co.edu.uniquindio.proyectoInmobiliaria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.management.j2ee.statistics.JavaMailStats;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.entidadesProyecto.Administrador;
import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Comentario;
import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.entidadesProyecto.Vivienda;

/**
 * Session Bean implementation class PruebaEJB
 */
@Stateless
@LocalBean
public class PruebaEJB implements PruebaEJBRemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PruebaEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void registrarUsuario(Usuario u) throws Exception {

		Usuario buscado = entityManager.find(Usuario.class, u.getCodigo());
		if (buscado != null) {
			throw new Exception("El codigo del usuario ya existe");
		}

		entityManager.persist(u);

	}

	@Override
	public boolean verificarEmail(String email) {

		TypedQuery<Usuario> q = entityManager.createNamedQuery(Usuario.VERIFICAR_EMAIL_UNICO, Usuario.class);
		q.setParameter("email", email);

		if (q.getResultList().size() == 0) {
			return false;
		}
		return true;

	}

	@Override
	public Usuario autenticarUsuario(String email, String password) throws Exception {
		TypedQuery<Usuario> q = entityManager.createNamedQuery(Usuario.AUTENTICAR_USUARIO, Usuario.class);
		q.setParameter("email", email);
		q.setParameter("password", password);

		List<Usuario> l = q.getResultList();
		if (l.isEmpty()) {
			throw new Exception("Los datos ingresados son incorrectos");
		}
		return l.get(0);
	}

	@Override
	public List<Usuario> enviarCorreoRecuperacion(String email) throws Exception {
		EnviarConGmail correo = new EnviarConGmail();
		TypedQuery<Usuario> q = entityManager.createNamedQuery(Usuario.AUTENTICAR_USUARIO_CODIGO, Usuario.class);
		q.setParameter("email", email);
		List<Usuario> l = q.getResultList();
		if (l.isEmpty()) {
			throw new Exception("Los datos ingresados son incorrectos");
		} else {
			correo.enviarConGMail(email, "Recuperacion de contraseña",
					"Su contraseña es: " + q.getResultList().get(0).getPassword());
			;
		}

		return l;
	}

	@Override
	public void crearInmobiliaria(Inmobiliaria i) throws Exception {
		Inmobiliaria miInmobiliaria = entityManager.find(Inmobiliaria.class, i.getCodigo());
		if (miInmobiliaria != null) {
			throw new Exception("ya esta");
		}
		if (verificarEmail(i.getEmail())) {
			throw new Exception("ya esta registrado");
		}

		entityManager.persist(i);
	}

	@Override
	public Inmobiliaria consultarInmobiliaria(int codigo) {
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, codigo);
		return inmo;
	}

	@Override
	public Cliente consultarCliente(int codigo) {
		Cliente inmo = entityManager.find(Cliente.class, codigo);
		return inmo;
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		entityManager.merge(cliente);
	}

	@Override
	public void modificarInmobiliariaNombre(int codigo, String nombre) {
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, codigo);
		inmo.setNombre(nombre);
		entityManager.merge(inmo);
	}

	@Override
	public void modificarInmobiliariaDireccion(int codigo, String nombre) {
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, codigo);
		inmo.setDireccion(nombre);
		entityManager.merge(inmo);
	}

	@Override
	public void modificarInmobiliariaEmail(int codigo, String nombre) {
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, codigo);
		inmo.setEmail(nombre);
		entityManager.merge(inmo);
	}

	@Override
	public void modificarInmobiliariaPassword(int codigo, String nombre) {
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, codigo);
		inmo.setPassword(nombre);
		entityManager.merge(inmo);
	}

	@Override
	public void eliminarInmobiliaria(int codigo) {
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, codigo);
		entityManager.remove(inmo);
	}

	@Override
	public List<Inmobiliaria> listarInmobiliarias() {
		TypedQuery<Inmobiliaria> msInmo = entityManager.createNamedQuery(Inmobiliaria.LISTAR_INMOBILIARIAS,
				Inmobiliaria.class);
		List<Inmobiliaria> listas = msInmo.getResultList();
		String cadena = "";
		for (int i = 0; i < listas.size(); i++) {
			cadena += listas.get(i).toString() + "/n";
		}
		return listas;
	}

	@Override
	public List<Cliente> listarClientes() {
		TypedQuery<Cliente> msInmo = entityManager.createNamedQuery(Cliente.LISTAR_CLIENTES, Cliente.class);
		List<Cliente> listas = msInmo.getResultList();
		if (!listas.isEmpty()) {
			return listas;
		}
		return null;
	}

	@Override
	public List<Proyecto> consultarProyecto(String nombre) {
		TypedQuery<Proyecto> misPro = entityManager.createNamedQuery(Proyecto.BUSCAR_NOMBRE, Proyecto.class);
		misPro.setParameter("nombre", "%" + nombre + "%");
		return misPro.getResultList();
	}

	@Override
	public Proyecto consultarProyectoId(Integer nombre) {
		Proyecto p = entityManager.find(Proyecto.class, nombre);
		System.out.println(p.toString());
		return p;
	}

	@Override
	public void registrarProyecto(Proyecto miProyecto) throws Exception {
		entityManager.persist(miProyecto);
	}

	@Override
	public void registrarVivienda(Vivienda vivienda) throws Exception {
		entityManager.persist(vivienda);
	}

	@Override
	public List<Proyecto> listarProyectos() {
		TypedQuery<Proyecto> misPro = entityManager.createNamedQuery(Proyecto.LISTAR_PROYECTOS, Proyecto.class);
		return (misPro.getResultList());
	}


	@Override
	public List<Servicio> listarServicios() {
		TypedQuery<Servicio> misSer = entityManager.createNamedQuery(Servicio.LISTAR_SERVICIOS, Servicio.class);
		return (misSer.getResultList());
	}

	public Ciudad obtenerCiudad(int codigo) throws Exception {
		Ciudad c = entityManager.find(Ciudad.class, codigo);
		if (c != null) {
			return c;
		}
		throw new Exception("El codigo de la ciudad no existe");
	}

	@Override
	public Proyecto obtenerProyecto(int codigo) throws Exception {
		Proyecto p = entityManager.find(Proyecto.class, codigo);
		if (p != null) {
			return p;
		}
		throw new Exception("El codigo del proyecto no existe");
	}

	@Override
	public Servicio obtenerServicio(int codigo) throws Exception {
		Servicio s = entityManager.find(Servicio.class, codigo);
		if (s != null) {
			return s;
		}
		throw new Exception("El codigo del servicio no existe");
	}

	public Inmobiliaria obtenerInmobiliaria(int codigo) throws Exception {
		Inmobiliaria c = entityManager.find(Inmobiliaria.class, codigo);
		if (c != null) {
			return c;
		}
		throw new Exception("El codigo de la ciudad no existe");
	}

	@Override
	public List<Comentario> obtenerComentariosProyecto(int codigo) throws Exception {
		TypedQuery<Comentario> q = entityManager.createNamedQuery(Comentario.LISTA_COMENTARIOS_PROYECTO,
				Comentario.class);
		q.setParameter("codigo", codigo);
		return q.getResultList();
	}

	@Override
	public List<Ciudad> listarCiudades() {
		TypedQuery<Ciudad> q = entityManager.createNamedQuery(Ciudad.LISTA_CIUDADES, Ciudad.class);
		return q.getResultList();
	}

	@Override
	public List<Servicio> obtenerServicios(int codigo) {
		TypedQuery<Servicio> q = entityManager.createNamedQuery(Servicio.LISTAR_SERVICIOS, Servicio.class);
		List<Servicio> serv = q.getResultList();
		List<Proyecto> proy = null;
		List<Servicio> servicio = new ArrayList<Servicio>();
		for (int i = 0; i < serv.size(); i++) {
			proy = serv.get(i).getProyectos();
			for (int j = 0; j < proy.size(); j++) {
				if (proy.get(j).getCodigo() == codigo) {
					servicio.add(serv.get(i));
				}
			}
			return servicio;
		}
		return null;
	}

}

package co.edu.uniquindio.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;

import org.apache.poi.hssf.record.formula.eval.ConcatEval;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sun.xml.registry.uddi.bindings_v2_2.Contact;

import co.edu.uniquindio.entidadesProyecto.Administrador;
import co.edu.uniquindio.entidadesProyecto.Calificacion;
import co.edu.uniquindio.entidadesProyecto.CalificacionPK;
import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Comentario;
import co.edu.uniquindio.entidadesProyecto.Contacto;
import co.edu.uniquindio.entidadesProyecto.ContactoPK;
import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.entidadesProyecto.Vivienda;

@RunWith(Arquillian.class)

public class ModeloTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Ciudad.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test() {

	}

	/**
	 * modifica una ciudad ya existente y se verfica el proceso
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void modificarPersistenciaCiudadTest() {

		// se busca la ciudad
		Ciudad ciudadBuscada = entityManager.find(Ciudad.class, 1);

		ciudadBuscada.setNombre("Bogota");
		entityManager.merge(ciudadBuscada);
		Assert.assertEquals("Bogota", ciudadBuscada.getNombre());
	}

	/**
	 * elimina una ciudad y verfica que haya eliminado correctamente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarPersistenciaCiudadTest2() {

		// se busca la ciudad
		Ciudad ciudadBuscada = entityManager.find(Ciudad.class, 1);
		entityManager.remove(ciudadBuscada);
		Ciudad Ciudad2 = entityManager.find(Ciudad.class, 1);
		Assert.assertNull(Ciudad2);
	}

	/**
	 * Se guarda una nueva ciudad y se verifica que haya quedado guardada
	 * correctamente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void guardarPersistenciaCiudadTest3() {
		Ciudad ciudadNueva = new Ciudad(2);
		entityManager.persist(ciudadNueva);
		Ciudad ciudad2 = entityManager.find(Ciudad.class, 2);
		Assert.assertNotNull(ciudad2);

	}

	/**
	 * test para insertar una inmobiliaria en el archivo json
	 */
	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarInmobiliariaTest() {
		// se crea una nueva inmobiliaria con todos los datos necesarios

		Inmobiliaria inmo = new Inmobiliaria();
		inmo.setCodigo(1);
		inmo.setNombre("AAAAAA");
		inmo.setDireccion("aaaaaa");
		inmo.setEmail("aaa@gmail.com");
		inmo.setPassword("abc");

		// se persiste la inmobiliaria
		entityManager.persist(inmo);

		// se verifica que quede bien guardada la inmobiliaria
		Inmobiliaria inmo2 = entityManager.find(Inmobiliaria.class, 1);
		Assert.assertNotNull(inmo2);
		Assert.assertEquals("AAAAAA", inmo.getNombre());
		Assert.assertEquals("aaaaaa", inmo.getDireccion());
		Assert.assertEquals("aaa@gmail.com", inmo.getEmail());
		Assert.assertEquals("abc", inmo.getPassword());
	}

	/**
	 * Prueba que permite verificar si se puede modificar una inmobiliaria Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarInmobiliariaTest() {

		// se busca la inmobiliaria
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, 3);

		// se le cambia la informacion y se guarda
		inmo.setNombre("aaaa");
		inmo.setDireccion("aaaa");
		inmo.setEmail("aaa@hotail.com");
		inmo.setPassword("aaaa");
		entityManager.merge(inmo);

		// se verifica que haya quedado bien guardado
		Inmobiliaria inmo2 = entityManager.find(Inmobiliaria.class, 3);
		Assert.assertNotNull(inmo2);
		Assert.assertEquals("aaaa", inmo.getNombre());
		Assert.assertEquals("aaaa", inmo.getDireccion());
		Assert.assertEquals("aaa@hotail.com", inmo.getEmail());
		Assert.assertEquals("aaaa", inmo.getPassword());

		System.out.println(inmo);

	}

	/**
	 * test para buscar una inmobiliaria
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarInmobiliariaTest() {

		// se busca la inmobiliaria
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, 3);
		Assert.assertEquals("LA PAZ", inmo.getNombre());

		// se imprime la inmobiliaria y sus datos
		// System.out.println(inmo.toString());

	}

	/**
	 * test para eliminar una inmobiliaria
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarInmobiliariaTest() {
		// se busca la ciudad que se desea eliminar y se elimina
		Inmobiliaria inmo = entityManager.find(Inmobiliaria.class, 3);
		entityManager.remove(inmo);

		// se verifica que la ciudad quede eliminada correctamente con el assertNull
		Inmobiliaria inmo2 = entityManager.find(Inmobiliaria.class, 3);
		Assert.assertNull(inmo2);

	}

	/**
	 * test para insertar un Proyecto en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarProyectoTest() {
		// se crea un nuevo Proyecto con todos los datos necesarios

		Proyecto pro = new Proyecto();
		pro.setCodigo(10);
		pro.setDescripcion("aaaaaa");
		pro.setNombre("aaa");
		pro.setUbicacion_latitud(5.9876);
		pro.setUbicacion_longitud(7.14444);

		// se persiste la inmobiliaria
		entityManager.persist(pro);

		// se verifica que quede bien guardada la inmobiliaria
		Proyecto pro2 = entityManager.find(Proyecto.class, 1);
		Assert.assertNotNull(pro2);
		Assert.assertEquals("aaaaaa", pro.getDescripcion());
		Assert.assertEquals("aaa", pro.getNombre());
		Assert.assertEquals("5.9876", pro.getUbicacion_latitud());
		Assert.assertEquals("7.14444", pro.getUbicacion_longitud());
	}

	/**
	 * Prueba que permite verificar si se puede modificar un proyecto Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarProyectoTest() {

		// se busca el proyecto
		Proyecto pro = entityManager.find(Proyecto.class, 1);

		// se le cambia la informacion y se guarda
		pro.setNombre("aaaa");
		pro.setDescripcion("ashas");
		pro.setUbicacion_latitud(1.2345);
		pro.setUbicacion_longitud(1.9887);
		entityManager.merge(pro);

		// se verifica que haya quedado bien guardado
		Proyecto pro2 = entityManager.find(Proyecto.class, 1);
		Assert.assertNotNull(pro2);

	}

	/**
	 * test para buscar un proyecto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarProyectoTest() {

		// se busca el proyecto
		Proyecto pro = entityManager.find(Proyecto.class, 1);
		Assert.assertEquals("TORRES DEL RIO", pro.getNombre());

	}

	/**
	 * test para eliminar un proyecto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarProyectoTest() {
		// se busca el proyecto que se desea eliminar y se elimina
		Proyecto pro = entityManager.find(Proyecto.class, 2);
		entityManager.remove(pro);

		// se verifica que el proyecto quede eliminada correctamente con el assertNull
		Proyecto pro2 = entityManager.find(Proyecto.class, 2);
		Assert.assertNull(pro2);

	}

	/**
	 * test para insertar un administrador en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarAdministradorTest() {
		// se crea una nueva iamgen de un Proyecto con todos los datos necesarios

		Administrador admin = new Administrador();
		admin.setCodigo(1);
		admin.setEmail("aaa@gmail.com");
		admin.setNombre("aaa");
		admin.setPassword("abc123");

		// se persiste la inmobiliaria
		entityManager.persist(admin);

		// se verifica que quede bien guardada la inmobiliaria
		Administrador admin2 = entityManager.find(Administrador.class, 1);
		Assert.assertNotNull(admin2);
		Assert.assertEquals("aaa@gmail.com", admin.getEmail());
		Assert.assertEquals("aaa", admin.getNombre());
		Assert.assertEquals("abc123", admin.getPassword());
	}

	/**
	 * Prueba que permite verificar si se puede modificar un Administrador Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarAdministradorTest() {

		// se busca el administrador
		Administrador admin = entityManager.find(Administrador.class, 2);

		// se le cambia la informacion y se guarda
		admin.setNombre("abc");
		admin.setEmail("abc@gmail.com");
		admin.setPassword("aaa12");
		entityManager.merge(admin);

		// se verifica que haya quedado bien guardado
		Administrador admin2 = entityManager.find(Administrador.class, admin.getCodigo());
		Assert.assertNotNull(admin2);
		Assert.assertEquals("abc", admin.getNombre());
		Assert.assertEquals("abc@gmail.com", admin.getEmail());
		Assert.assertEquals("aaa12", admin.getPassword());

		System.out.println(admin);

	}

	/**
	 * test para buscar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarAdministradorTest() {

		// se busca el administrador
		Administrador admin = entityManager.find(Administrador.class, 3);
		Assert.assertEquals("julian", admin.getNombre());

		// se imprime el administrador y sus datos
		System.out.println(admin);

	}

	/**
	 * test para eliminar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarAdministradorTest() {
		// se busca el administrador que se desea eliminar y se elimina
		Administrador admin = entityManager.find(Administrador.class, 1);
		entityManager.remove(admin);

		// se verifica que el administrador quede eliminada correctamente con el
		// assertNull
		Administrador admin2 = entityManager.find(Administrador.class, 1);
		Assert.assertNull(admin2);

	}

	/**
	 * test para insertar una vivienda en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarViviendaTest() {
		// se crea una nueva vivienda con todos los datos necesarios

		Vivienda vivi = new Vivienda();
		vivi.setCodigo(1);
		vivi.setArea(0);
		vivi.setDescripcion(null);
		vivi.setNum_banos(3);
		vivi.setNum_habitaciones(4);
		vivi.setPrecio(50000);
		vivi.setUrl_imagen(null);

		// se persiste la vivienda
		entityManager.persist(vivi);

		// se verifica que quede bien guardada la vivienda
		Vivienda vivi2 = entityManager.find(Vivienda.class, vivi.getCodigo());
		Assert.assertNotNull(vivi2);
		Assert.assertEquals(null, vivi.getArea());
		Assert.assertEquals(null, vivi.getDescripcion());
		Assert.assertEquals(3, vivi.getNum_banos());
		Assert.assertEquals(4, vivi.getNum_habitaciones());
		Assert.assertEquals("50000", vivi.getPrecio());
		Assert.assertEquals(null, vivi.getUrl_imagen());
	}

	/**
	 * Prueba que permite verificar si se puede modificar un vivienda Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarViviendaTest() {

		// se busca la vivienda
		Vivienda vivi = entityManager.find(Vivienda.class, 3);

		// se le cambia la informacion y se guarda
		vivi.setArea(0);
		vivi.setDescripcion(null);
		vivi.setNum_banos(2);
		vivi.setNum_habitaciones(5);
		vivi.setPrecio(700000);
		vivi.setUrl_imagen("aaaa");
		entityManager.merge(vivi);

		// se verifica que haya quedado bien guardado
		Vivienda vivi2 = entityManager.find(Vivienda.class, vivi.getCodigo());
		Assert.assertNotNull(vivi2);
		Assert.assertEquals(null, vivi.getArea());
		Assert.assertEquals(null, vivi.getDescripcion());
		Assert.assertEquals(2, vivi.getNum_banos());
		Assert.assertEquals(5, vivi.getNum_habitaciones());
		Assert.assertEquals(700000, vivi.getPrecio());
		Assert.assertEquals("aaaa", vivi.getUrl_imagen());

	}

	/**
	 * test para buscar una Vivienda
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarViviendaTest() {

		// se busca la vivienda
		Vivienda vivi = entityManager.find(Vivienda.class, 2);
		Assert.assertEquals(4000000, vivi.getPrecio());

	}

	/**
	 * test para eliminar una Vivienda
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarViviendaTest() {
		// se busca la vivienda que se desea eliminar y se elimina
		Vivienda vivi = entityManager.find(Vivienda.class, 1);
		entityManager.remove(vivi);

		// se verifica que la vivienda quede eliminada correctamente con el assertNull
		Vivienda vivi2 = entityManager.find(Vivienda.class, 1);
		Assert.assertNull(vivi2);

	}

	/**
	 * test para insertar un Servicio en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarServicioTest() {
		// se crea un nuevo servicio con todos los datos necesarios

		Servicio serv = new Servicio();
		serv.setCodigo_servicio(2);
		serv.setNombre("aaaa");

		// se persiste el servicio
		entityManager.persist(serv);

		// se verifica que quede bien guardada el servicio
		Servicio serv2 = entityManager.find(Servicio.class, serv.getCodigo_servicio());
		Assert.assertNotNull(serv2);
		Assert.assertEquals("aaaaa", serv.getNombre());
	}

	/**
	 * Prueba que permite verificar si se puede modificar un Servicio Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarServicioTest() {

		// se busca el servicio
		Servicio serv = entityManager.find(Servicio.class, 1);

		// se le cambia la informacion y se guarda
		serv.setNombre("aa");
		entityManager.merge(serv);

		// se verifica que haya quedado bien guardado
		Servicio serv2 = entityManager.find(Servicio.class, serv.getCodigo_servicio());
		Assert.assertNotNull(serv2);
		Assert.assertEquals("aa", serv.getNombre());

	}

	/**
	 * test para buscar un Servicio
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarServicioTest() {

		// se busca el servicio
		Servicio serv = entityManager.find(Servicio.class, 3);
		Assert.assertEquals("servicio3", serv.getNombre());

	}

	/**
	 * test para eliminar un Servicio
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarServicioTest() {
		// se busca el servicio que se desea eliminar y se elimina
		Servicio serv = entityManager.find(Servicio.class, 2);
		entityManager.remove(serv);

		// se verifica que el Servicio quede eliminada correctamente con el assertNull
		Servicio serv2 = entityManager.find(Servicio.class, 2);
		Assert.assertNull(serv2);

	}

	/**
	 * test para insertar un usuario en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarUsuarioTest() {
		// se crea un nuevo usuario con todos los datos necesarios

		Usuario usuario = new Usuario();
		usuario.setCodigo(2);
		usuario.setEmail("aaa@gmail.com");
		usuario.setNombre("aaaa");
		usuario.setPassword("abc");

		// se persiste el usuario
		entityManager.persist(usuario);

		// se verifica que quede bien guardada el usuario
		Usuario usuario2 = entityManager.find(Usuario.class, usuario.getCodigo());
		Assert.assertNotNull(usuario2);
		Assert.assertEquals("aaa@gmail.com", usuario.getEmail());
		Assert.assertEquals("aaaa", usuario.getNombre());
		Assert.assertEquals("abc", usuario.getPassword());
	}

	/**
	 * Prueba que permite verificar si se puede modificar un Usuario Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarUsuarioTest() {

		// se busca la usuario
		Usuario usuario = entityManager.find(Usuario.class, 1);

		// se le cambia la informacion y se guarda
		usuario.setEmail(null);
		usuario.setNombre("aa");
		usuario.setPassword(null);
		entityManager.merge(usuario);

		// se verifica que haya quedado bien guardado
		Usuario usuario2 = entityManager.find(Usuario.class, usuario.getCodigo());
		Assert.assertNotNull(usuario2);
		Assert.assertEquals(null, usuario.getEmail());
		Assert.assertEquals("aa", usuario.getNombre());
		Assert.assertEquals(null, usuario.getPassword());

	}

	/**
	 * test para buscar un Usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarUsuarioTest() {

		// se busca el usuario
		Usuario usuario = entityManager.find(Usuario.class, 2);
		Assert.assertEquals("usuario2", usuario.getNombre());

	}

	/**
	 * test para eliminar un usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarUsuarioTest() {
		// se busca el usuario que se desea eliminar y se elimina
		Usuario usuario = entityManager.find(Usuario.class, 1);
		entityManager.remove(usuario);

		// se verifica que el usuario quede eliminada correctamente con el assertNull
		Usuario usuario2 = entityManager.find(Usuario.class, 1);
		Assert.assertNull(usuario2);

	}

	/**
	 * test para insertar un cliente en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarClienteTest() {
		// se crea un nuevo cliente con todos los datos necesarios

		Cliente cliente = new Cliente();
		cliente.setCodigo(2);
		cliente.setNombre("aaaa");
		cliente.setCalificaciones(null);
		cliente.setComentarios(null);
		cliente.setContactos(null);
		cliente.setEmail("aaa@gmial.com");
		cliente.setFavoritosCliente(null);
		cliente.setFecha_nacimiento(null);
		cliente.setPassword("aab");
		cliente.setTelefono(null);

		// se persiste el cliente
		entityManager.persist(cliente);

		// se verifica que quede bien guardada la cliente
		Cliente cliente2 = entityManager.find(Cliente.class, cliente.getCodigo());
		Assert.assertNotNull(cliente2);
		Assert.assertEquals("aaa@gmail.com", cliente.getEmail());
		Assert.assertEquals("aaaa", cliente.getNombre());
		Assert.assertEquals("aab", cliente.getPassword());
	}

	/**
	 * Prueba que permite verificar si se puede modificar un Cliente Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarClienteTest() {

		// se busca el cliente
		Cliente cliente = entityManager.find(Cliente.class, 1);

		// se le cambia la informacion y se guarda
		cliente.setEmail(null);
		cliente.setEmail(null);
		cliente.setNombre("aa");
		cliente.setPassword("abc");
		entityManager.merge(cliente);

		// se verifica que haya quedado bien guardado
		Cliente cliente2 = entityManager.find(Cliente.class, cliente.getCodigo());
		Assert.assertNotNull(cliente2);
		Assert.assertEquals(null, cliente.getEmail());
		Assert.assertEquals("aa", cliente.getNombre());
		Assert.assertEquals("abc", cliente.getPassword());

	}

	/**
	 * test para buscar un cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarClienteTest() {

		// se busca el cliente
		Cliente cliente = entityManager.find(Cliente.class, 2);
		Assert.assertEquals("cliente2", cliente.getNombre());

	}

	/**
	 * test para eliminar un cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarClienteTest() {
		// se busca el cliente que se desea eliminar y se elimina
		Cliente cliente = entityManager.find(Cliente.class, 3);
		entityManager.remove(cliente);

		// se verifica que el cliente quede eliminada correctamente con el assertNull
		Cliente cliente2 = entityManager.find(Cliente.class, 3);
		Assert.assertNull(cliente2);

	}

	/**
	 * test para insertar una calificacion en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarCalificacionTest() {
		// se crea una nueva calificacion con todos los datos necesarios

		CalificacionPK calificaciopk = new CalificacionPK();
		calificaciopk.setCodigoCliente(1);
		calificaciopk.setCodigoProyecto(1);
		Calificacion miCalificacion = new Calificacion();
		miCalificacion.setCalificacion(1);
		miCalificacion.setClave(calificaciopk);

		// se persiste la calificacion
		entityManager.persist(miCalificacion);

		// se verifica que quede bien guardada la cliente
		Calificacion calificacion2 = entityManager.find(Calificacion.class, miCalificacion.getClave());
		Assert.assertNotNull(calificacion2);

	}

	/**
	 * Prueba que permite verificar si se puede modificar una calificacion Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarCalificacionTest() {

		CalificacionPK calificaciopk = new CalificacionPK();
		calificaciopk.setCodigoCliente(1);
		calificaciopk.setCodigoProyecto(1);
		Calificacion calificacion = new Calificacion();

		// se le cambia la informacion y se guarda
		calificacion.setCalificacion(2);
		calificacion.setClave(calificaciopk);
		entityManager.merge(calificacion);

		// se verifica que haya quedado bien guardado
		Calificacion calificacion2 = entityManager.find(Calificacion.class, calificacion.getClave());
		Assert.assertNotNull(calificacion2);

	}

	/**
	 * test para buscar una calificacion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarCalificacionTest() {

		// se busca el Calificacion
		Calificacion calificacion = new Calificacion();
		calificacion.setCalificacion(2);
		CalificacionPK calificacionPk = new CalificacionPK(2, 2);
		calificacion.setClave(calificacionPk);
		calificacion = entityManager.find(Calificacion.class, calificacion.getClave());
		Assert.assertEquals(2, calificacion.getCalificacion());
	}

	/**
	 * test para eliminar un cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarCalificacionTest() {
		// se busca el cliente que se desea eliminar y se elimina
		Calificacion calificacion = new Calificacion();
		CalificacionPK calificacionPK = new CalificacionPK(3, 3);
		calificacion.setClave(calificacionPK);
		calificacion = entityManager.find(Calificacion.class, calificacion.getClave());
		entityManager.remove(calificacion);

		// se verifica que el cliente quede eliminada correctamente con el assertNull
		Calificacion calificacion2 = entityManager.find(Calificacion.class, calificacion.getClave());
		Assert.assertNull(calificacion2);

	}

	/**
	 * test para insertar un comentario en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarComentarioTest() {
		// se crea un nuevo comentario con todos los datos necesarios

		Comentario comentario = new Comentario();
		comentario.setCodigo(2);

		// se persiste el comentario
		entityManager.persist(comentario);

		// se verifica que quede bien guardada la cliente
		Comentario comentario2 = entityManager.find(Comentario.class, comentario.getCodigo());
		Assert.assertNotNull(comentario2);

	}

	/**
	 * Prueba que permite verificar si se puede modificar un comentario Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarComentarioTest() {

		// se busca un comentario
		Comentario comentario = entityManager.find(Comentario.class, 1);

		// se le cambia la informacion y se guarda
		comentario.setCodigo(2);
		entityManager.merge(comentario);

		// se verifica que haya quedado bien guardado
		Comentario comentario2 = entityManager.find(Comentario.class, comentario.getCodigo());
		Assert.assertNotNull(comentario2);

	}

	/**
	 * test para buscar un comentario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarComentarioTest() {

		// se busca el comentario
		Comentario comentario = entityManager.find(Comentario.class, 2);
		Assert.assertEquals(2, comentario.getCodigo());
	}

	/**
	 * test para eliminar un comentario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarComentarioTest() {
		// se busca el comentario que se desea eliminar y se elimina
		Comentario comentario = entityManager.find(Comentario.class, 2);
		entityManager.remove(comentario);

		// se verifica que el comentario quede eliminada correctamente con el assertNull
		Comentario comentario2 = entityManager.find(Comentario.class, 2);
		Assert.assertNull(comentario2);

	}

	/**
	 * test para insertar un contacto en el archivo json
	 */

	@Test
	@UsingDataSet({ "unihogar.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarContactoTest() {
		// se crea un nuevo contacto con todos los datos necesarios
		ContactoPK contactoPK = new ContactoPK(2, 2);
		Contacto contacto = new Contacto();
		contacto.setAsunto("cambio");
		contacto.setClave(contactoPK);

		// se persiste el contacto
		entityManager.persist(contacto);

		// se verifica que quede bien guardado
		Contacto contacto2 = entityManager.find(Contacto.class, contacto.getClave());
		Assert.assertNotNull(contacto2);

	}

	/**
	 * Prueba que permite verificar si se puede modificar un contacto Usa las
	 * anotaciones @Test, @Transactional, UsingDataSet
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ModificarContactoTest() {

		// se busca un contacto
		ContactoPK contactoPK = new ContactoPK(2, 2);
		Contacto contacto = new Contacto();
		contacto.setClave(contactoPK);
		contacto = entityManager.find(Contacto.class, contacto.getClave());

		// se le cambia la informacion y se guarda
		contacto.setAsunto("cambio");
		entityManager.merge(contacto);

		// se verifica que haya quedado bien guardado
		Contacto contacto2 = entityManager.find(Contacto.class, contacto.getClave());
		Assert.assertNotNull(contacto2);

	}

	/**
	 * test para buscar un comentario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void buscarContactoTest() {

		// se busca el contacto
		ContactoPK contactoPK = new ContactoPK(2, 2);
		Contacto contacto = new Contacto();
		contacto.setClave(contactoPK);
		contacto.setAsunto("asunto");
		Contacto contacto2 = entityManager.find(Contacto.class, contacto.getClave());
		Assert.assertNotNull(contacto2);
	}

	/**
	 * test para eliminar un contacto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void eliminarContactoTest() {
		// se busca el contacto que se desea eliminar y se elimina
		ContactoPK contactoPK = new ContactoPK(2, 2);
		Contacto contacto = new Contacto();
		contacto.setClave(contactoPK);
		Contacto contacto2 = entityManager.find(Contacto.class, contacto.getClave());
		entityManager.remove(contacto2);

		// se verifica que el contacto quede eliminada correctamente con el assertNull
		Contacto contacto3 = entityManager.find(Contacto.class, contacto2.getClave());
		Assert.assertNull(contacto3);

	}
}

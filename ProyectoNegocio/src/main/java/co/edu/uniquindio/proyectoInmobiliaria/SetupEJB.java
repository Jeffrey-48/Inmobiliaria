package co.edu.uniquindio.proyectoInmobiliaria;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.entidadesProyecto.Administrador;
import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.entidadesProyecto.Usuario;

/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SetupEJB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void config() {

		boolean hayAdmins = hayAdministradores();
		boolean hayInmobiliarias = hayInmobiliarias();

		if (!hayAdmins) {

			Administrador a = new Administrador();

			a.setCodigo(1);
			a.setEmail("jsalvareza@uqvirtual.edu.co");
			a.setNombre("pepo");
			a.setPassword("123");
			entityManager.persist(a);

			Usuario u = new Usuario();
			u.setCodigo(1);
			u.setEmail("jsalvareza@uqvirtual.edu.co");
			u.setNombre("pepo");
			u.setPassword("123");
			entityManager.persist(u);
		}

		if (!hayInmobiliarias) {

			Ciudad c = new Ciudad("Armenia", 1);
			entityManager.persist(c);

			Inmobiliaria i = new Inmobiliaria(1, "rosales amarillos ", "asd@gmail", "girasoles", "123");
			entityManager.persist(i);

			Proyecto p1 = new Proyecto("proyecto ejemplo", "Viña del mar", 4.54831, -75.66191, i, c);
			Proyecto p2 = new Proyecto("proyecto ejemplo2", "Viña del rio", 4.54583, -75.66505, i, c);

			entityManager.persist(p1);
			entityManager.persist(p2);
			
			Ciudad c1 = new Ciudad("Calarca", 2);
			entityManager.persist(c1);
			
			Ciudad c2 = new Ciudad("Circacia", 3);
			entityManager.persist(c2);
			
			Ciudad c3 = new Ciudad("Salento", 4);
			entityManager.persist(c3);
			
			Ciudad c4 = new Ciudad("Pereira", 5);
			entityManager.persist(c4);
			
			Ciudad c5 = new Ciudad("Montenegro", 6);
			entityManager.persist(c5);
			
			Servicio s=new Servicio("Piscina");
			entityManager.persist(s);
			
			Servicio s1=new Servicio("Cancha tenis");
			entityManager.persist(s1);
			
			Servicio s2=new Servicio("Sendero ecologico");
			entityManager.persist(s2);
			
			Servicio s3=new Servicio("Parqueadero visitante");
			entityManager.persist(s3);
			
			Servicio s4=new Servicio("Zona BBQ");
			entityManager.persist(s4);
			
			Servicio s5=new Servicio("Cancha futbol");
			entityManager.persist(s5);
			
			Servicio s6=new Servicio("Zona infaltin");
			entityManager.persist(s6);
			
			Servicio s7=new Servicio("Sauna");
			entityManager.persist(s7);
			

		}

	}

	public boolean hayAdministradores() {
		TypedQuery<Administrador> q = entityManager.createNamedQuery(Administrador.LISTA_ADMINISTRADORES,
				Administrador.class);
		if (q.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hayInmobiliarias() {
		TypedQuery<Inmobiliaria> q = entityManager.createNamedQuery(Inmobiliaria.LISTAR_INMOBILIARIAS,
				Inmobiliaria.class);
		if (q.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}

}

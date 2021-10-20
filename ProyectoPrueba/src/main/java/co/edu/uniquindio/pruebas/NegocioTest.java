package co.edu.uniquindio.pruebas;

import org.apache.poi.hssf.record.formula.eval.ConcatEval;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@RunWith(Arquillian.class)
public class NegocioTest {

	@EJB
	private PruebaEJB pruebaEJB;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(PruebaEJB.class)

				.addPackage(Usuario.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void autenticacionUsusarioTest() {
		
		try {
			pruebaEJB.autenticarUsuario("j-stivrn@sdsd", "123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

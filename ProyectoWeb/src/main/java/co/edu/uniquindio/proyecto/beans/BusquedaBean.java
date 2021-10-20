package co.edu.uniquindio.proyecto.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named("busquedaBean")
@RequestScoped
public class BusquedaBean {

	private List<Proyecto> lista;
	private String busqueda;
	@Inject
	@ManagedProperty(value = "#{param.busqueda}")
	private String busquedaParam;

	@EJB
	private PruebaEJB clienteEJB;

	@PostConstruct
	public void inicializar() {
		this.busqueda = busquedaParam;

		if (busqueda != null) {
			lista = clienteEJB.consultarProyecto(busqueda);
		}

	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	/*
	 * public String buscarProyecto() {
	 * 
	 * List<Proyecto> lista = clienteEJB.consultarProyecto(busqueda); if
	 * (!lista.isEmpty()) { return "resultadoBusqueda"; } FacesMessage msj = new
	 * FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta",
	 * "No existen proyectos con ese nombre");
	 * 
	 * FacesContext.getCurrentInstance().addMessage(null, msj); return null; }
	 */
	public String buscar() {

		return "resultadoBusqueda?faces-redirect=true&amp;busqueda=" + busqueda;
	}

	public List<Proyecto> getLista() {
		return lista;
	}

	public void setLista(List<Proyecto> lista) {
		this.lista = lista;
	}

	public String getBusquedaParam() {
		return busquedaParam;
	}

	public void setBusquedaParam(String busquedaParam) {
		this.busquedaParam = busquedaParam;
	}

}

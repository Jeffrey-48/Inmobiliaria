package co.edu.uniquindio.proyecto.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Comentario;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@Named("detalleProyecto")
@RequestScoped
public class DetalleProyecto {

	@Inject
	@ManagedProperty(value = "#{param.proyecto}")
	private String proyectoParam;

	@EJB
	private PruebaEJB pruebaEJB;

	private Proyecto proyecto;

	private List<Comentario> comentarios;
	private String comentarioA;
	
	private List<Servicio> servicios;
	private List<Proyecto> imageens;
	private SeguridadBean seguridad;
	private String name;
	private Usuario user;
	private boolean accionComentar;

	@PostConstruct
	public void inicializar() {
		comentarioA="";
		accionComentar=false;
		name = "";
		if (proyectoParam != null) {
			try {
				proyecto = pruebaEJB.obtenerProyecto(Integer.parseInt(proyectoParam));
				comentarios = pruebaEJB.obtenerComentariosProyecto(Integer.parseInt(proyectoParam));
				servicios = pruebaEJB.obtenerServicios(Integer.parseInt(proyectoParam));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void agregarFavorito()
	{
		user = seguridad.getUsuario();
		List<Proyecto> lista = new ArrayList<Proyecto>();
		lista.add(proyecto);
		Cliente cliente = pruebaEJB.consultarCliente(user.getCodigo()+1);
		cliente.setFavoritosCliente(lista);
		//name =cliente.getFavoritosCliente().get(0).getNombre();
		pruebaEJB.modificarCliente(cliente);
	}
	public void realizarComentario()
	{
		accionComentar=true;
	}
	
	public String irPrincipal() {
		Comentario c = new Comentario();
		c.setComentario(comentarioA);
		return "detalleProyecto?faces-redirect=true&amp;proyecto=";
	}
	
	public String irAProyecto(Integer codigo) {

		return "detalleProyecto?faces-redirect=true&amp;proyecto=" + codigo;
	}

	public String getProyectoParam() {
		return proyectoParam;
	}

	public void setProyectoParam(String proyectoParam) {
		this.proyectoParam = proyectoParam;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;

	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAccionComentar() {
		return accionComentar;
	}

	public void setAccionComentar(boolean accionComentar) {
		this.accionComentar = accionComentar;
	}

	public String getComentarioA() {
		return comentarioA;
	}

	public void setComentarioA(String comentarioA) {
		this.comentarioA = comentarioA;
	}
	
	
}

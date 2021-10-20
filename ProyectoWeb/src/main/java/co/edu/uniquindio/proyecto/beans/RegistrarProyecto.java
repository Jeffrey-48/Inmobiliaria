package co.edu.uniquindio.proyecto.beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;

import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@Named("registrarProyecto")
@ViewScoped
public class RegistrarProyecto implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> imagenes;
	private Map mapa;
	@EJB
	private PruebaEJB pruebaEJB;
	private Proyecto miProyecto;
	private double lat, lng;
	@NotNull
	@Size(max = 255)
	private String nombreProyecto;
	private String descripcionProyecto;
	@NotNull
	private Ciudad ciudadProyecto;
	private List<Ciudad> ciudades;
	private List<Cliente> clientes;
	private List<Proyecto> proyectos;
	private List<Servicio> servicios;
	private Servicio servicioSeleccionados[];
	private String ruta;
	@Inject
	@ManagedProperty(value = "#{seguridadBean.usuario}")
	private Usuario usuario;

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getDescripcionProyecto() {
		return descripcionProyecto;
	}

	public void setDescripcionProyecto(String descripcionProyecto) {
		this.descripcionProyecto = descripcionProyecto;
	}

	public Ciudad getCiudadProyecto() {
		return ciudadProyecto;
	}

	public void setCiudadProyecto(Ciudad ciudadProyecto) {
		this.ciudadProyecto = ciudadProyecto;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Proyecto getMiProyecto() {
		return miProyecto;
	}

	public void setMiProyecto(Proyecto miProyecto) {
		this.miProyecto = miProyecto;
	}

	@PostConstruct
	public void inicializar() {

		imagenes = new ArrayList<String>();
		mapa = new Map();
		mapa.setCenter(new LatLong("4.55357", "-75.66101")).setWidth("100%").setHeight("400px").setZoom(18);

		Layer l = new Layer();
		mapa.addLayer(l);

		ciudades = pruebaEJB.listarCiudades();
		proyectos = pruebaEJB.listarProyectos();
		servicios = pruebaEJB.listarServicios();
		clientes = pruebaEJB.listarClientes();
		ruta = "/../docroot/unihogar/";

		for (Proyecto proyecto : proyectos) {
			l.addMarker(
					new Marker(new LatLong(proyecto.getUbicacion_latitud() + "", proyecto.getUbicacion_longitud() + ""),
							proyecto.getNombre()));
		}

	}

	public void registrarProyecto() {
		try {
			if (lat != 0 && lng != 0) {
				if (!imagenes.isEmpty() && usuario instanceof Inmobiliaria) {
					Proyecto p = new Proyecto(nombreProyecto, descripcionProyecto, lat, lng, (Inmobiliaria) usuario,
							ciudadProyecto);
					p.setImagenes(imagenes);
					p.setServicios(Arrays.asList(servicioSeleccionados));
					pruebaEJB.registrarProyecto(p);
					FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
							"El registro del proyecto fue exitoso");
					FacesContext.getCurrentInstance().addMessage("mensajes_regproyecto", msj);
				} else {
					FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta",
							"Es necesario añadir al menos una imagen al proyecto");
					FacesContext.getCurrentInstance().addMessage("mensajes_regproyecto", msj);
				}

			} else {
				FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta",
						"Ubique el proyecto en el mapa");
				FacesContext.getCurrentInstance().addMessage("mensajes_regproyecto", msj);
			}

		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("mensajes_regproyecto", msj);

		}

	}

	public void crearMarker() {
		java.util.Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String lat = parametros.get("lat");
		String lng = parametros.get("lng");
		this.lat = Double.parseDouble(lat);
		this.lng = Double.parseDouble(lng);

		// FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Posición",
		// lat + ", " + lng);
		// FacesContext.getCurrentInstance().addMessage("mensaje_pos", msj);
	}

	public void subirArchivos(FileUploadEvent event) {

		UploadedFile file = event.getFile();

		try {
			InputStream br = file.getInputStream();
			byte[] buffer = new byte[br.available()];
			FileOutputStream salida = new FileOutputStream(ruta + "" + file.getFileName());
			br.read(buffer);
			salida.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagenes.add(file.getFileName());
	}

	public Map getMapa() {
		return mapa;
	}

	public void setMapa(Map mapa) {
		this.mapa = mapa;
	}

	public ArrayList<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(ArrayList<String> imagenes) {
		this.imagenes = imagenes;
	}

	public Servicio[] getServicioSeleccionados() {
		return servicioSeleccionados;
	}

	public void setServicioSeleccionados(Servicio[] servicioSeleccionados) {
		this.servicioSeleccionados = servicioSeleccionados;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}

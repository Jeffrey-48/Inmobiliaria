package co.edu.uniquindio.proyecto.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Tipo;

import co.edu.uniquindio.entidadesProyecto.Vivienda;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@Named("registrarViviendaBean")
@ViewScoped
public class RegistrarViviendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Debe subir algun plano")
	private String plano;
	@NotNull(message = "Debe ingresar alguna cantidad")
	@DecimalMin("10.0")
	private String area;

	private String precio;
	@NotNull(message = "Debe tener alguna descripcion")
	private String descripcion;
	@NotNull(message = "Debe ingresar alguna cantidad")
	@Min(1)
	private String numHabitaciones;
	@NotNull(message = "Debe ingresar alguna cantidad")
	@Min(1)
	private String numBanos;
	@NotNull(message = "Debe seleccionar un tipo")
	private String tipo;
	@NotNull(message = "Debe seleccionar un proyecto")
	private Proyecto proyecto;

	private List<Proyecto> proyectos;
	
	private Inmobiliaria inmobiliaria = new Inmobiliaria(1, "1", "1", "1", "1");

	@EJB
	private PruebaEJB pruebaEJB;

	public void registrarVivienda() {
		if (plano != null && !"".equals(plano)) {
			if (proyecto != null) {
				Vivienda vivienda;
				if (tipo == "0") {

					vivienda = new Vivienda(Double.parseDouble(area), Double.parseDouble(precio), descripcion,
							Tipo.APARTAMENTO, Integer.parseInt(numHabitaciones), Integer.parseInt(numBanos), proyecto);

				} else {
					vivienda = new Vivienda(Double.parseDouble(area), Double.parseDouble(precio), descripcion,
							Tipo.CASA, Integer.parseInt(numHabitaciones), Integer.parseInt(numBanos), proyecto);
				}
				try {
					pruebaEJB.registrarVivienda(vivienda);
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
							"Registro exitoso");
					FacesContext.getCurrentInstance().addMessage("mensajes_bean", msg);

				} catch (Exception e) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro erroneo",
							"Registro erroneo " + e.getMessage());
					FacesContext.getCurrentInstance().addMessage("mensajes_bean", msg);
				}

			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro erroneo",
						"Registro erroneo debe seleccionar algun proyecto");
				FacesContext.getCurrentInstance().addMessage("mensajes_bean", msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro erroneo",
					"Registro erroneo debe subir alguna imagen ");
			FacesContext.getCurrentInstance().addMessage("mensajes_bean", msg);
		}
	}

	@PostConstruct
	public void init() {
		try {
			proyectos = pruebaEJB.listarProyectos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subirImagen(FileUploadEvent e) {
		UploadedFile f = e.getFile();
		String url = "C:\\glassfish5\\glassfish\\domains\\domain1\\docroot\\" + e.getFile().getFileName();
		try {
			CopyOption options = StandardCopyOption.REPLACE_EXISTING;
			Files.copy(e.getFile().getInputStream(), new File(url).toPath(), options);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		plano = (e.getFile().toString());

	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(String numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public String getNumBanos() {
		return numBanos;
	}

	public void setNumBanos(String numBanos) {
		this.numBanos = numBanos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

}

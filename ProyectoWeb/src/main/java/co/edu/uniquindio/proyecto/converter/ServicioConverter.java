package co.edu.uniquindio.proyecto.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@Named("servicioConverter")
@ApplicationScoped
public class ServicioConverter implements Converter<Servicio>, Serializable {
	@EJB
	private PruebaEJB pruebaEJB;
	private static final long serialVersionUID = 1L;

	@Override
	public Servicio getAsObject(FacesContext context, UIComponent component, String value) {
		Servicio servicio = null;
		if (value != null) {
			try {
				servicio = pruebaEJB.obtenerServicio(Integer.parseInt(value));
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() + ":codigo no valido"));
			}
		}
		return servicio;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Servicio value) {
		if (value != null) {
			return value.getCodigo_servicio() + "";
		}
		return "";
	}

}

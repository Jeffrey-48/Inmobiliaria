package co.edu.uniquindio.proyecto.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import co.edu.uniquindio.entidadesProyecto.Ciudad;
import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.entidadesProyecto.Servicio;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named("proyectoConverter")
@ApplicationScoped
public class ProyectoConverter implements Converter<Proyecto>, Serializable {

	@EJB
	private PruebaEJB pruebaEJB;
	private static final long serialVersionUID = 1L;

	@Override
	public Proyecto getAsObject(FacesContext context, UIComponent component, String value) {
		Proyecto proyecto = null;
		if (value != null) {
			try {
				proyecto = pruebaEJB.obtenerProyecto(Integer.parseInt(value));
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() + ":codigo no valido"));
			}
		}
		return proyecto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Proyecto value) {
		if (value != null)
			return value.getCodigo() + "";
		return "";
	}

}

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
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named("ciudadConverter")
@ApplicationScoped
public class CiudadConverter implements Converter<Ciudad>,Serializable{

	@EJB
	private PruebaEJB pruebaEJB;
	private static final long serialVersionUID = 1L;

	@Override
	public Ciudad getAsObject(FacesContext context, UIComponent component, String value) {
		Ciudad ciudad = null;
		if(value!=null)
		{
			try {
				ciudad=pruebaEJB.obtenerCiudad(Integer.parseInt(value));
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId()+
						":codigo no valido"));
			}
		}
		return ciudad;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Ciudad value) {
		if(value != null)
		return value.getCodigo_ciudad()+"";
		return "";
	}

}

package co.edu.uniquindio.proyecto.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;

import co.edu.uniquindio.entidadesProyecto.Proyecto;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@Named("proyectoBean")
@RequestScoped
public class ProyectoBean {

	private Map mapa;
	@EJB
	private PruebaEJB pruebaEJB;

	@PostConstruct
	public void inicializar() {

		mapa = new Map();
		mapa.setCenter(new LatLong("4.55357", "-75.66101")).setWidth("100%").setHeight("400px").setZoom(18);
		
		List<Proyecto> lista = pruebaEJB.listarProyectos();
		Layer l = new Layer();
		for (Proyecto proyecto : lista) {
			l.addMarker(new Marker(new LatLong(proyecto.getUbicacion_latitud()+"", proyecto.getUbicacion_longitud()+""), proyecto.getNombre()));
		}

		mapa.addLayer(l);
	}

	public Map getMapa() {
		return mapa;
	}

	public void setMapa(Map mapa) {
		this.mapa = mapa;
	}

}

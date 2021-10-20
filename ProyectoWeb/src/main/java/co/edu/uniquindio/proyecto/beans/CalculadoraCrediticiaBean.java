package co.edu.uniquindio.proyecto.beans;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("calculadoraCrediticiaBean")
@RequestScoped
public class CalculadoraCrediticiaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer monto;
	private Integer duracion;
	private Double tasaInteres;
	private List<CalculadoraValores> valores;

	public CalculadoraCrediticiaBean() {
		valores = new ArrayList<CalculadoraValores>();
	}

	public void calcular() {

		try {
			valores.clear();
			tasaInteres=tasaInteres/100;
			Integer cuota = calcularCuota(monto, tasaInteres, duracion);
			Integer interes = calcularInteres(monto, tasaInteres);
			Integer capital = calcularCapital(cuota, interes);
			
			valores.add(new CalculadoraValores(1, cuota, capital, interes, monto));

			for (int i = 0; i < duracion; i++) {
				monto = monto - capital;
				interes = (int) (monto * tasaInteres);
				capital = cuota - interes;
				valores.add(new CalculadoraValores(i+2, cuota, capital, interes, monto));				
			}

			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Tabla amortización realizada con éxito");
			FacesContext.getCurrentInstance().addMessage("messagesBean", msj);
			
		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Error" + e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messagesBean", msj);
		}
	}

	public Integer calcularCapital(Integer cuota, Integer interes) {
		return cuota - interes;
	}

	/**
	 * @return
	 */
	public Integer calcularInteres(Integer monto, Double tasaInteres) {
		return (int)(monto * tasaInteres);
	}

	/**
	 * @return
	 */
	public Integer calcularCuota(Integer monto, Double tasaInteres, Integer duracion) {
		return (int) ((int)(monto * tasaInteres) / (1 - (Math.pow(1 + tasaInteres, duracion * (-1)))));
	}

	/**
	 * @return the monto
	 */
	public Integer getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	/**
	 * @return the duracion
	 */
	public Integer getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the tasaInteres
	 */
	public Double getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres the tasaInteres to set
	 */
	public void setTasaInteres(Double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	/**
	 * @return the valores
	 */
	public List<CalculadoraValores> getValores() {
		return valores;
	}

	/**
	 * @param valores the valores to set
	 */
	public void setValores(List<CalculadoraValores> valores) {
		this.valores = valores;
	}
	
	
}

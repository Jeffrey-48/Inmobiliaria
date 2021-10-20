package co.edu.uniquindio.proyecto.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("calculadoraValores")
@ApplicationScoped
public class CalculadoraValores {

	private Integer periodo;
	private Integer cuota;
	private Integer capital;
	private Integer interes;
	private Integer saldo;
	
	/**
	 * @param periodo
	 * @param cuota
	 * @param capital
	 * @param interes
	 * @param saldo
	 */
	public CalculadoraValores(Integer periodo, Integer cuota, Integer capital, Integer interes, Integer saldo) {
		super();
		this.periodo = periodo;
		this.cuota = cuota;
		this.capital = capital;
		this.interes = interes;
		this.saldo = saldo;
	}
	/**
	 * @return the periodo
	 */
	public Integer getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the cuota
	 */
	public Integer getCuota() {
		return cuota;
	}
	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(Integer cuota) {
		this.cuota = cuota;
	}
	/**
	 * @return the capital
	 */
	public Integer getCapital() {
		return capital;
	}
	/**
	 * @param capital the capital to set
	 */
	public void setCapital(Integer capital) {
		this.capital = capital;
	}
	/**
	 * @return the interes
	 */
	public Integer getInteres() {
		return interes;
	}
	/**
	 * @param interes the interes to set
	 */
	public void setInteres(Integer interes) {
		this.interes = interes;
	}
	/**
	 * @return the saldo
	 */
	public Integer getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

}

package co.edu.uniquindio.entidadesProyecto;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.ws.rs.CookieParam;

/**
 * Entity implementation class for Entity: Vivienda Entidad dedicada a la
 * descripcion general de las viviendas que ofertan las inmobiliarias
 */
@Entity

public class Vivienda implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	@Column(nullable = false)
	private String url_imagen;

	@Column(nullable = false)
	private double area;

	@Column(nullable = false)
	private double precio;

	@Lob
	private String descripcion;

	@Enumerated(EnumType.ORDINAL)
	private Tipo tipo;

	@Column(nullable = false)
	private int num_habitaciones;

	@Column(nullable = false)
	private int num_banos;

	@ManyToOne
	private Proyecto miProyecto;

	private static final long serialVersionUID = 1L;

	public Vivienda() {
		super();
	}

	public Vivienda( double area, double precio, String descripcion, Tipo tipo, int num_habitaciones,
			int num_banos, Proyecto miProyecto) {
		super();

		this.area = area;
		this.precio = precio;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.num_habitaciones = num_habitaciones;
		this.num_banos = num_banos;
		this.miProyecto = miProyecto;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getUrl_imagen() {
		return this.url_imagen;
	}

	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNum_habitaciones() {
		return num_habitaciones;
	}

	public void setNum_habitaciones(int num_habitaciones) {
		this.num_habitaciones = num_habitaciones;
	}

	public int getNum_banos() {
		return num_banos;
	}

	public void setNum_banos(int num_banos) {
		this.num_banos = num_banos;
	}

	public Vivienda(Tipo tipo) {
		super();
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vivienda other = (Vivienda) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Proyecto getMiProyecto() {
		return miProyecto;
	}

	public void setMiProyecto(Proyecto miProyecto) {
		this.miProyecto = miProyecto;
	}

	@Override
	public String toString() {
		return "Vivienda [codigo=" + codigo + ", url_imagen=" + url_imagen + ", area=" + area + ", precio=" + precio
				+ ", descripcion=" + descripcion + ", tipo=" + tipo + ", num_habitaciones=" + num_habitaciones
				+ ", num_banos=" + num_banos + ", miProyecto=" + miProyecto + "]";
	}

}
